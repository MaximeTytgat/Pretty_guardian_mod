package com.max.prettyguardian.client.gui.sreens;

import com.max.prettyguardian.effect.ModEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.LayeredDraw;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.RenderType;
import org.jetbrains.annotations.NotNull;

public class LoveEffectHubOverlay implements LayeredDraw.Layer {
    public LoveEffectHubOverlay() {}

    @Override
    public void render(@NotNull GuiGraphics guiGraphics, float v) {
        Minecraft minecraft = Minecraft.getInstance();
        LocalPlayer player = minecraft.player;
        if (player == null) return;
        if (player.getHealth() <= 0) return;
        if (player.hasEffect(ModEffects.LOVE.getHolder().get())) {
            renderPinkBlurOnScreen(guiGraphics);
        }
    }

    protected static void renderPinkBlurOnScreen(GuiGraphics guiGraphics) {
        int width = Minecraft.getInstance().getWindow().getGuiScaledWidth();
        int height = Minecraft.getInstance().getWindow().getGuiScaledHeight();
        guiGraphics.fill(RenderType.lightning(), 0, 0, width, height, 50293172);
    }
}
