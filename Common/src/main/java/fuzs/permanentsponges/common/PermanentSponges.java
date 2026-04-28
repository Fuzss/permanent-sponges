package fuzs.permanentsponges.common;

import fuzs.permanentsponges.common.config.ServerConfig;
import fuzs.permanentsponges.common.handler.TryEmptyBucketHandler;
import fuzs.permanentsponges.common.init.ModRegistry;
import fuzs.puzzleslib.common.api.config.v3.ConfigHolder;
import fuzs.puzzleslib.common.api.core.v1.ModConstructor;
import fuzs.puzzleslib.common.api.event.v1.entity.player.PlayerInteractEvents;
import net.minecraft.resources.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PermanentSponges implements ModConstructor {
    public static final String MOD_ID = "permanentsponges";
    public static final String MOD_NAME = "Permanent Sponges";
    public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);

    public static final ConfigHolder CONFIG = ConfigHolder.builder(MOD_ID).server(ServerConfig.class);

    @Override
    public void onConstructMod() {
        ModRegistry.bootstrap();
        registerEventHandlers();
    }

    private static void registerEventHandlers() {
        PlayerInteractEvents.USE_ITEM.register(TryEmptyBucketHandler::onUseItem);
    }

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }
}
