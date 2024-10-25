//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.max.prettyguardian.client.gui.components;

import java.util.Objects;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractStringWidget;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class CustomStringWidget extends AbstractStringWidget {
    private final float alignX;

    public CustomStringWidget(int i, int i1, int i2, int i3, Component component, Font font) {
        super(i, i1, i2, i3, component, font);
        this.alignX = 0.5F;
        this.active = false;
    }

    @Override
    public @NotNull CustomStringWidget setColor(int i) {
        super.setColor(i);
        return this;
    }

    public void renderWidget(GuiGraphics guiGraphics, int i, int i1, float v) {
        Component message = this.getMessage();
        Font font = this.getFont();
        int i2 = this.getX() + Math.round(this.alignX * (this.getWidth() - font.width(message)));
        int y = this.getY();
        int height1 = this.getHeight();
        Objects.requireNonNull(font);
        int i3 = y + (height1 - 9) / 2;
        guiGraphics.drawString(font, message, i2, i3, this.getColor(), false);
    }
}
