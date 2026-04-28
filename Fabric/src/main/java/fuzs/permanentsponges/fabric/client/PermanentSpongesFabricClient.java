package fuzs.permanentsponges.fabric.client;

import fuzs.permanentsponges.common.PermanentSponges;
import fuzs.permanentsponges.common.client.PermanentSpongesClient;
import fuzs.puzzleslib.common.api.client.core.v1.ClientModConstructor;
import net.fabricmc.api.ClientModInitializer;

public class PermanentSpongesFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientModConstructor.construct(PermanentSponges.MOD_ID, PermanentSpongesClient::new);
    }
}
