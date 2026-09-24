package fuzs.permanentsponges.common.data.client;

import fuzs.permanentsponges.common.PermanentSponges;
import fuzs.permanentsponges.common.init.ModRegistry;
import fuzs.puzzleslib.common.api.client.data.v3.language.AbstractLanguageProvider;
import fuzs.puzzleslib.common.api.data.v3.core.DataProviderContext;

public class ModLanguageProvider extends AbstractLanguageProvider {

    public ModLanguageProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addTranslations() {
        this.add(ModRegistry.CREATIVE_MODE_TAB.value(), PermanentSponges.MOD_NAME);
        this.add(ModRegistry.AQUEOUS_SPONGE_BLOCK.value(), "Aqueous Sponge Block");
        this.add(ModRegistry.MAGMATIC_SPONGE_BLOCK.value(), "Magmatic Sponge Block");
        this.add(ModRegistry.AQUEOUS_SPONGE_ITEM.value(), "Aqueous Sponge");
        this.add(ModRegistry.MAGMATIC_SPONGE_ITEM.value(), "Magmatic Sponge");
    }
}
