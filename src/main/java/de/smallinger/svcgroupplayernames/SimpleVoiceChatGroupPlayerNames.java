package de.smallinger.svcgroupplayernames;

import org.slf4j.Logger;
import com.mojang.logging.LogUtils;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.loading.FMLEnvironment;

/**
 * Simple Voice Chat Group Player Names
 * Original mod by Greenman999 - https://github.com/Greeenman999/simple-voice-chat-group-player-names
 * NeoForge port by Smallinger - https://github.com/smallinger/simple-voice-chat-group-player-names
 * 
 * This mod displays the player names next to the group player heads in the hud.
 * Licensed under GPL-3.0
 */
@Mod(SimpleVoiceChatGroupPlayerNames.MODID)
public class SimpleVoiceChatGroupPlayerNames {
    public static final String MODID = "svcgroupplayernames";
    public static final Logger LOGGER = LogUtils.getLogger();

    public SimpleVoiceChatGroupPlayerNames(IEventBus modEventBus, ModContainer modContainer) {
        LOGGER.info("Initializing Simple Voice Chat Group Player Names");
        
        // Register the config
        modContainer.registerConfig(net.neoforged.fml.config.ModConfig.Type.CLIENT, ModConfig.SPEC);
        
        // Initialize client-side code only on client dist
        if (FMLEnvironment.dist == Dist.CLIENT) {
            new SimpleVoiceChatGroupPlayerNamesClient(modEventBus, modContainer);
        }
    }
}
