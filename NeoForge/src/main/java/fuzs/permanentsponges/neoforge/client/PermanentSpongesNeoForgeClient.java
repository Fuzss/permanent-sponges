package fuzs.permanentsponges.neoforge.client;

import fuzs.permanentsponges.common.PermanentSponges;
import fuzs.permanentsponges.common.client.PermanentSpongesClient;
import fuzs.permanentsponges.common.data.client.ModLanguageProvider;
import fuzs.permanentsponges.common.data.client.ModModelProvider;
import fuzs.puzzleslib.common.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.neoforge.api.data.v2.core.DataProviderHelper;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;

@Mod(value = PermanentSponges.MOD_ID, dist = Dist.CLIENT)
public class PermanentSpongesNeoForgeClient {

    public PermanentSpongesNeoForgeClient() {
        ClientModConstructor.construct(PermanentSponges.MOD_ID, PermanentSpongesClient::new);
        DataProviderHelper.registerDataProviders(PermanentSponges.MOD_ID,
                ModModelProvider::new,
                ModLanguageProvider::new
        );
    }
}
