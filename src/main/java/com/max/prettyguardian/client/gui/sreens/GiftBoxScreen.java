package com.max.prettyguardian.client.gui.sreens;

import com.max.prettyguardian.PrettyGuardian;
import com.max.prettyguardian.client.gui.sreens.inventory.GiftBoxMenu;
import com.max.prettyguardian.client.gui.sreens.inventory.PicnicBasketMenu;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import java.awt.*;

public class GiftBoxScreen extends AbstractContainerScreen<GiftBoxMenu> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(PrettyGuardian.MOD_ID, "textures/gui/container/gift_box.png");

    public GiftBoxScreen(GiftBoxMenu giftBoxMenu, Inventory inventory, Component component) {
        super(giftBoxMenu, inventory, component);
    }

    @Override
    protected void init() {
        super.init();
        this.inventoryLabelY = 10000;
        this.titleLabelY = 10000;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1F, 1F, 1F, 1F);
        RenderSystem.setShaderTexture(1, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        guiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        int transparentPinkColor = new Color(255, 105, 180, 128).getRGB();
        guiGraphics.fillGradient(0, 0, this.width, this.height, transparentPinkColor, transparentPinkColor);

//        super.render(guiGraphics, mouseX, mouseY, delta);
//        renderTooltip(guiGraphics, mouseX, mouseY);
    }
}
