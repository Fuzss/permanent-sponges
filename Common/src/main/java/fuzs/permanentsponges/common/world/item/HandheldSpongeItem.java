package fuzs.permanentsponges.common.world.item;

import fuzs.permanentsponges.common.PermanentSponges;
import fuzs.permanentsponges.common.config.ServerConfig;
import fuzs.permanentsponges.common.world.level.block.PermanentSpongeBlock;
import fuzs.permanentsponges.common.world.level.block.SpongeMaterial;
import fuzs.puzzleslib.common.api.item.v2.ItemHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

public class HandheldSpongeItem extends Item {
    private final SpongeMaterial spongeMaterial;

    public HandheldSpongeItem(SpongeMaterial spongeMaterial, Properties properties) {
        super(properties);
        this.spongeMaterial = spongeMaterial;
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack itemInHand = player.getItemInHand(interactionHand);
        BlockHitResult blockHitResult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.ANY);
        if (blockHitResult.getType() == HitResult.Type.MISS) {
            return InteractionResult.PASS;
        } else if (blockHitResult.getType() != HitResult.Type.BLOCK) {
            return InteractionResult.PASS;
        } else {
            BlockPos blockPos = blockHitResult.getBlockPos();
            Direction direction = blockHitResult.getDirection();
            BlockPos blockPos2 = blockPos.relative(direction);
            if (!level.mayInteract(player, blockPos) || !player.mayUseItemAt(blockPos2, direction, itemInHand)) {
                return InteractionResult.FAIL;
            } else {
                BlockState blockState = level.getBlockState(blockPos);
                if (blockState.getBlock() instanceof BucketPickup bucketPickup) {
                    int cooldownTicks = PermanentSponges.CONFIG.get(ServerConfig.class).stickCooldownTicks;
                    if (cooldownTicks > 0) {
                        player.getCooldowns().addCooldown(itemInHand, cooldownTicks);
                    }

                    if (level instanceof ServerLevel serverLevel) {
                        if (PermanentSpongeBlock.removeAllLiquid(this.spongeMaterial, serverLevel, blockPos, true)) {
                            itemInHand.setDamageValue(itemInHand.getMaxDamage() - 1);
                        }

                        ItemHelper.hurtAndBreak(itemInHand, 1, player, interactionHand);
                    }

                    player.awardStat(Stats.ITEM_USED.get(this));
                    bucketPickup.getPickupSound().ifPresent((SoundEvent soundEvent) -> {
                        player.playSound(soundEvent, 1.0F, 1.0F);
                    });
                    level.gameEvent(player, GameEvent.FLUID_PICKUP, blockPos);
                    return InteractionResult.SUCCESS.heldItemTransformedTo(itemInHand);
                } else {
                    return InteractionResult.FAIL;
                }
            }
        }
    }
}
