package com.max.prettyguardian.event.custom;

import com.max.prettyguardian.effect.ModEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import com.max.prettyguardian.PrettyGuardian;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@Mod.EventBusSubscriber(modid = PrettyGuardian.MOD_ID, value = Dist.CLIENT)
public class LoveGuiRendererEvent {
    private LoveGuiRendererEvent() {}

    @SubscribeEvent
    public static void onRenderGameOverlay(Register  event) {
        Player player = Minecraft.getInstance().player;
        if (player == null) return;
        if (player.getHealth() <= 0) return;
        if (player.hasEffect(ModEffects.LOVE.getHolder().get())) {
            renderPinkBlurOnScreen(event.getGuiGraphics());
        }
    }

    protected static void renderPinkBlurOnScreen(GuiGraphics guiGraphics) {
        int width = Minecraft.getInstance().getWindow().getGuiScaledWidth();
        int height = Minecraft.getInstance().getWindow().getGuiScaledHeight();
        guiGraphics.fill(RenderType.lightning(), 0, 0, width, height, 50293172);
    }
}