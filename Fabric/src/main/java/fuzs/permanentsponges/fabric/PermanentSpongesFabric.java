package fuzs.permanentsponges.fabric;

import fuzs.permanentsponges.common.PermanentSponges;
import fuzs.puzzleslib.common.api.core.v1.ModConstructor;
import net.fabricmc.api.ModInitializer;

public class PermanentSpongesFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        ModConstructor.construct(PermanentSponges.MOD_ID, PermanentSponges::new);
    }
}
