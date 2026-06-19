package fuzs.permanentsponges.common.handler;

import fuzs.permanentsponges.common.PermanentSponges;
import fuzs.permanentsponges.common.config.ServerConfig;
import fuzs.permanentsponges.common.util.LiquidAbsorptionHelper;
import fuzs.puzzleslib.common.api.event.v1.core.EventResultHolder;
import net.minecraft.advancements.triggers.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlockContainer;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import org.jspecify.annotations.Nullable;

public class TryEmptyBucketHandler {

    /**
     * @see BucketItem#use(Level, Player, InteractionHand)
     */
    public static EventResultHolder<InteractionResult> onUseItem(Player player, Level level, InteractionHand interactionHand) {
        if (!PermanentSponges.CONFIG.get(ServerConfig.class).preventEmptyingBuckets) {
            return EventResultHolder.pass();
        }

        ItemStack itemInHand = player.getItemInHand(interactionHand);
        if (itemInHand.getItem() instanceof BucketItem item) {
            if (item.content != Fluids.EMPTY) {
                BlockHitResult hitResult = Item.getPlayerPOVHitResult(level, player, ClipContext.Fluid.NONE);
                if (hitResult.getType() == HitResult.Type.BLOCK) {
                    BlockPos pos = hitResult.getBlockPos();
                    Direction direction = hitResult.getDirection();
                    BlockPos directionOffsetPos = pos.relative(direction);
                    if (level.mayInteract(player, pos) && player.mayUseItemAt(directionOffsetPos,
                            direction,
                            itemInHand)) {
                        BlockState clicked = level.getBlockState(pos);
                        BlockPos placePos =
                                clicked.getBlock() instanceof LiquidBlockContainer && item.content == Fluids.WATER ?
                                        pos : directionOffsetPos;
                        if (emptyContents(player, level, placePos, hitResult, item.content)) {
                            item.checkExtraContent(player, level, itemInHand, placePos);
                            if (player instanceof ServerPlayer) {
                                CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayer) player, placePos, itemInHand);
                            }

                            player.awardStat(Stats.ITEM_USED.get(item));
                            ItemStack successItem = BucketItem.getEmptySuccessItem(itemInHand, player);
                            if (itemInHand != successItem) {
                                player.setItemInHand(interactionHand, successItem);
                            }

                            return EventResultHolder.interrupt(InteractionResult.SUCCESS.heldItemTransformedTo(
                                    successItem));
                        }
                    }
                }
            }
        }

        return EventResultHolder.pass();
    }

    /**
     * @see BucketItem#emptyContents(LivingEntity, Level, BlockPos, BlockHitResult)
     */
    private static boolean emptyContents(@Nullable Player player, Level level, BlockPos blockPos, @Nullable BlockHitResult hitResult, Fluid fluid) {
        if (!(fluid instanceof FlowingFluid)) {
            return false;
        } else {
            BlockState blockState = level.getBlockState(blockPos);
            Block block = blockState.getBlock();
            boolean mayReplace = blockState.canBeReplaced(fluid);
            boolean canPlaceFluidInsideBlock = blockState.isAir() || mayReplace
                    || block instanceof LiquidBlockContainer && ((LiquidBlockContainer) block).canPlaceLiquid(player,
                    level,
                    blockPos,
                    blockState,
                    fluid);
            if (!canPlaceFluidInsideBlock) {
                return hitResult != null && emptyContents(player,
                        level,
                        hitResult.getBlockPos().relative(hitResult.getDirection()),
                        null,
                        fluid);
            } else if (level instanceof ServerLevel serverLevel && LiquidAbsorptionHelper.tryPreventLiquidFromEntering(
                    serverLevel,
                    blockPos,
                    fluid)) {
                LiquidAbsorptionHelper.removeLiquidEffects(level, blockPos, fluid);
                return true;
            }
        }

        return false;
    }
}
