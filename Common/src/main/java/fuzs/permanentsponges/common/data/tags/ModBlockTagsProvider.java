package fuzs.permanentsponges.common.data.tags;

import fuzs.permanentsponges.common.init.ModRegistry;
import fuzs.puzzleslib.common.api.data.v2.core.DataProviderContext;
import fuzs.puzzleslib.common.api.data.v2.tags.AbstractTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;

public class ModBlockTagsProvider extends AbstractTagProvider<Block> {

    public ModBlockTagsProvider(DataProviderContext context) {
        super(Registries.BLOCK, context);
    }

    @Override
    public void addTags(HolderLookup.Provider provider) {
        this.tag(BlockTags.MINEABLE_WITH_HOE).add(ModRegistry.AQUEOUS_SPONGE_BLOCK, ModRegistry.MAGMATIC_SPONGE_BLOCK);
        this.tag(ModRegistry.PERMANENT_SPONGES_BLOCK_TAG)
                .add(ModRegistry.AQUEOUS_SPONGE_BLOCK, ModRegistry.MAGMATIC_SPONGE_BLOCK);
    }
}
