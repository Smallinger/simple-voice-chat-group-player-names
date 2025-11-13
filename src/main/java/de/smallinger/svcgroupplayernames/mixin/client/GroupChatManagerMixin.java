package de.smallinger.svcgroupplayernames.mixin.client;

import com.llamalad7.mixinextras.sugar.Local;
import de.smallinger.svcgroupplayernames.SimpleVoiceChatGroupPlayerNamesClient;
import de.maxhenkel.voicechat.voice.client.ClientVoicechat;
import de.maxhenkel.voicechat.voice.client.GroupChatManager;
import de.maxhenkel.voicechat.voice.common.PlayerState;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Mixin to render player names next to group player heads
 * Original mod by Greenman999
 * NeoForge port by Smallinger
 */
@Mixin(value = GroupChatManager.class, remap = false)
public class GroupChatManagerMixin {

    @Inject(
            method = "renderIcons",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/mojang/blaze3d/systems/RenderSystem;enableBlend()V",
                    shift = At.Shift.AFTER
            )
    )
    private static void renderPlayerNames(GuiGraphics guiGraphics, CallbackInfo ci,
                                          @Local PlayerState state,
                                          @Local(ordinal = 1) int posX,
                                          @Local(ordinal = 2) int posY,
                                          @Local float scale,
                                          @Local ClientVoicechat client) {
        // Calculate the actual position of the icon (1 pixel offset + 8 pixel width)
        int x = posX < 0 ? -1 - 8 : 1;
        int y = posY < 0 ? -1 - 8 : 1;
        SimpleVoiceChatGroupPlayerNamesClient.renderPlayerNames(guiGraphics, x, y, 8, 8, state, scale, client);
    }
}
