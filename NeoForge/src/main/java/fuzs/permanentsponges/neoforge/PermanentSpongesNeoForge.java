package fuzs.permanentsponges.neoforge;

import fuzs.permanentsponges.common.PermanentSponges;
import fuzs.permanentsponges.common.data.loot.ModBlockLootProvider;
import fuzs.permanentsponges.common.data.tags.ModBlockTagsProvider;
import fuzs.permanentsponges.common.data.ModRecipeProvider;
import fuzs.puzzleslib.common.api.core.v1.ModConstructor;
import fuzs.puzzleslib.neoforge.api.data.v3.core.DataProviderBuilder;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.fml.common.Mod;

@Mod(PermanentSponges.MOD_ID)
public class PermanentSpongesNeoForge {

    public PermanentSpongesNeoForge() {
        ModConstructor.construct(PermanentSponges.MOD_ID, PermanentSponges::new);
        DataProviderBuilder.of(PermanentSponges.MOD_ID)
                .addLootProvider(ModBlockLootProvider::new, LootContextParamSets.BLOCK)
                .addProvider(ModBlockTagsProvider::new)
                .addRecipeProvider(ModRecipeProvider::new);
    }
}
