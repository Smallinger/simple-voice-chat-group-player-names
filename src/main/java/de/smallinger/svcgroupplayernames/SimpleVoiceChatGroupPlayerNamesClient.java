package de.smallinger.svcgroupplayernames;

import de.maxhenkel.voicechat.VoicechatClient;
import de.maxhenkel.voicechat.voice.client.ClientVoicechat;
import de.maxhenkel.voicechat.voice.client.GroupPlayerIconOrientation;
import de.maxhenkel.voicechat.voice.common.PlayerState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

/**
 * Client-side initialization for Simple Voice Chat Group Player Names
 * Original mod by Greenman999
 * NeoForge port by Smallinger
 */
@Mod(value = SimpleVoiceChatGroupPlayerNames.MODID, dist = Dist.CLIENT)
public class SimpleVoiceChatGroupPlayerNamesClient {
    
    public SimpleVoiceChatGroupPlayerNamesClient(IEventBus modEventBus, ModContainer container) {
        SimpleVoiceChatGroupPlayerNames.LOGGER.info("Simple Voice Chat Group Player Names Client Initialized");
        
        // Register config screen
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    public static void renderPlayerNames(GuiGraphics guiGraphics,
                                          int x,
                                          int y,
                                          int width,
                                          int height,
                                          PlayerState state,
                                          float scale,
                                          ClientVoicechat client) {
        guiGraphics.pose().pushMatrix();
        float invScale = 1.0f / scale;
        guiGraphics.pose().scale(invScale, invScale);

        int nameOffsetX = (int) (x + (width * scale) + (scale - 1) + 4 + scale - 1);
        int nameOffsetY = (int) ((y + scale - 1) + ((height * scale) / 2) - (float) (7 / 2) - 1);

        int hudX = VoicechatClient.CLIENT_CONFIG.groupPlayerIconPosX.get();
        int hudY = VoicechatClient.CLIENT_CONFIG.groupPlayerIconPosY.get();
        boolean horizontal = VoicechatClient.CLIENT_CONFIG.groupPlayerIconOrientation.get().equals(GroupPlayerIconOrientation.HORIZONTAL);
        
        if (horizontal) {
            guiGraphics.pose().rotate((float) (Math.PI / 2));
            if (hudX < 0 && hudY < 0) {
                nameOffsetX = (int) (-Minecraft.getInstance().font.width(state.getName()) - (height * scale) - (scale - 1) - 4 - (scale - 1));
                nameOffsetY = (int) (scale + (width * scale) / 2 - (float) (7 / 2) - 1);
            } else if (hudX < 0) {
                nameOffsetX = (int) ((int) (height * scale) + (scale - 1) + 4 + (scale - 1));
            } else if (hudY < 0) {
                nameOffsetY = (int) (y - (width * scale) + 7 + (2 - scale) + (width * scale) / 2 - (float) (7 / 2) - 1);
                nameOffsetX = (int) (-Minecraft.getInstance().font.width(state.getName()) - (height * scale) - (scale - 1) - 4 - (scale - 1));
            } else {
                nameOffsetY = (int) (y - (width * scale) - scale + (width * scale) / 2 - (float) (7 / 2) - 1);
            }
        } else {
            if (hudX < 0) {
                nameOffsetX = (int) (-Minecraft.getInstance().font.width(state.getName()) - (width * scale) - (scale - 1) - 4 - (scale - 1));
            }
            if (hudY < 0) {
                nameOffsetY = (int) (y - (width * scale) + 7 + (2 - scale) + ((height * scale) / 2) - (float) (7 / 2) - 1);
            }
        }

        int transparencyWhenTalking = whiteWithAlpha(ModConfig.TRANSPARENCY_WHEN_TALKING.get());
        int transparencyWhenNotTalking = whiteWithAlpha(ModConfig.TRANSPARENCY_WHEN_NOT_TALKING.get());
        
        if (ModConfig.ONLY_SHOW_NAMES_WHEN_TALKING.get() && !client.getTalkCache().isTalking(state.getUuid())) {
            guiGraphics.pose().popMatrix();
            return;
        }
        
        boolean isTalking = client.getTalkCache().isTalking(state.getUuid());
        int color = isTalking ? transparencyWhenTalking : transparencyWhenNotTalking;
        boolean useOutline = ModConfig.USE_TEXT_OUTLINE.get();
        
        guiGraphics.drawString(
            Minecraft.getInstance().font, 
            state.getName(), 
            nameOffsetX, 
            nameOffsetY, 
            color, 
            useOutline
        );
        
        guiGraphics.pose().popMatrix();
    }

    public static int whiteWithAlpha(int percent) {
        percent = Math.max(0, Math.min(100, percent));
        int alpha = Math.round(percent / 100.0f * 255.0f);
        alpha = Math.max(0, Math.min(255, alpha));
        return ((alpha & 0xFF) << 24) | 0x00FFFFFF;
    }
}
