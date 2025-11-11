package de.smallinger.svcgroupplayernames.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import de.smallinger.svcgroupplayernames.SimpleVoiceChatGroupPlayerNamesClient;
import de.maxhenkel.voicechat.voice.client.ClientVoicechat;
import de.maxhenkel.voicechat.voice.client.GroupChatManager;
import de.maxhenkel.voicechat.voice.common.PlayerState;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

/**
 * Mixin to render player names next to group player heads
 * Original mod by Greenman999
 * NeoForge port by Smallinger
 */
@Mixin(value = GroupChatManager.class)
public class GroupChatManagerMixin {

    @WrapOperation(
            method = "renderIcons",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/ResourceLocation;IIFFIIII)V",
                    ordinal = 1
            )
    )
    private static void renderPlayerNames(GuiGraphics guiGraphics,
                                          RenderPipeline pipeline,
                                          ResourceLocation texture,
                                          int x,
                                          int y,
                                          float u,
                                          float v,
                                          int width,
                                          int height,
                                          int textureWidth,
                                          int textureHeight,
                                          Operation<Void> original,
                                          @Local PlayerState state,
                                          @Local float scale,
                                          @Local ClientVoicechat client) {
        original.call(guiGraphics, pipeline, texture, x, y, u, v, width, height, textureWidth, textureHeight);
        SimpleVoiceChatGroupPlayerNamesClient.renderPlayerNames(guiGraphics, x, y, width, height, state, scale, client);
    }
}
