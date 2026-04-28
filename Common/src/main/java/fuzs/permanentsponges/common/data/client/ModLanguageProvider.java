package fuzs.permanentsponges.common.data.client;

import fuzs.permanentsponges.common.PermanentSponges;
import fuzs.permanentsponges.common.init.ModRegistry;
import fuzs.puzzleslib.common.api.client.data.v2.AbstractLanguageProvider;
import fuzs.puzzleslib.common.api.data.v2.core.DataProviderContext;

public class ModLanguageProvider extends AbstractLanguageProvider {

    public ModLanguageProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addTranslations(TranslationBuilder builder) {
        builder.add(ModRegistry.CREATIVE_MODE_TAB.value(), PermanentSponges.MOD_NAME);
        builder.add(ModRegistry.AQUEOUS_SPONGE_BLOCK.value(), "Aqueous Sponge");
        builder.add(ModRegistry.MAGMATIC_SPONGE_BLOCK.value(), "Magmatic Sponge");
        builder.add(ModRegistry.HANDHELD_AQUEOUS_SPONGE_ITEM.value(), "Handheld Aqueous Sponge");
        builder.add(ModRegistry.HANDHELD_MAGMATIC_SPONGE_ITEM.value(), "Handheld Magmatic Sponge");
    }
}
