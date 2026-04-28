package fuzs.permanentsponges.neoforge;

import fuzs.permanentsponges.common.PermanentSponges;
import fuzs.permanentsponges.common.data.loot.ModBlockLootProvider;
import fuzs.permanentsponges.common.data.tags.ModBlockTagProvider;
import fuzs.permanentsponges.common.data.ModRecipeProvider;
import fuzs.puzzleslib.common.api.core.v1.ModConstructor;
import fuzs.puzzleslib.neoforge.api.data.v2.core.DataProviderHelper;
import net.neoforged.fml.common.Mod;

@Mod(PermanentSponges.MOD_ID)
public class PermanentSpongesNeoForge {

    public PermanentSpongesNeoForge() {
        ModConstructor.construct(PermanentSponges.MOD_ID, PermanentSponges::new);
        DataProviderHelper.registerDataProviders(PermanentSponges.MOD_ID,
                ModBlockLootProvider::new,
                ModBlockTagProvider::new,
                ModRecipeProvider::new
        );
    }
}
