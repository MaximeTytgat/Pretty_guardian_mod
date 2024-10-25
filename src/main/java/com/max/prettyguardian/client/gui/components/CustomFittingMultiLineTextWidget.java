package com.max.prettyguardian.client.gui.components;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.narration.NarratedElementType;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@OnlyIn(Dist.CLIENT)
public class CustomFittingMultiLineTextWidget extends CustomAbstractScrollWidget {
    private final Font font;
    private final CustomMultiLineTextWidget multilineWidget;

    public CustomFittingMultiLineTextWidget(int i, int i1, int i2, int i3, Component component, Font font) {
        super(i, i1, i2, i3, component);
        this.font = font;
        this.multilineWidget = (new CustomMultiLineTextWidget(0, 0, component, font)).setMaxWidth(this.getWidth() - this.totalInnerPadding());
    }

    public CustomFittingMultiLineTextWidget setColor(int i) {
        this.multilineWidget.setColor(i);
        return this;
    }

    public void setWidth(int i) {
        super.setWidth(i);
        this.multilineWidget.setMaxWidth(this.getWidth() - this.totalInnerPadding());
    }

    protected int getInnerHeight() {
        return this.multilineWidget.getHeight();
    }

    protected double scrollRate() {
        Objects.requireNonNull(this.font);
        return 9.0;
    }

    public void renderWidget(@NotNull GuiGraphics guiGraphics, int i, int i1, float v) {
        if (this.visible) {
            if (!this.scrollbarVisible()) {
                guiGraphics.pose().pushPose();
                guiGraphics.pose().translate((float)this.getX(), (float)this.getY(), 0.0F);
                this.multilineWidget.render(guiGraphics, i, i1, v);
                guiGraphics.pose().popPose();
            } else {
                super.renderWidget(guiGraphics, i, i1, v);
            }

        }
    }

    protected void renderContents(GuiGraphics guiGraphics, int i, int i1, float v) {
        guiGraphics.pose().pushPose();
        guiGraphics.pose().translate((float)(this.getX() + this.innerPadding()), (float)(this.getY() + this.innerPadding()), 0.0F);
        this.multilineWidget.render(guiGraphics, i, i1, v);
        guiGraphics.pose().popPose();
    }

    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
        narrationElementOutput.add(NarratedElementType.TITLE, this.getMessage());
    }
}
