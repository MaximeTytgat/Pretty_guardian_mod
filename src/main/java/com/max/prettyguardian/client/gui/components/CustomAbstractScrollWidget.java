package com.max.prettyguardian.client.gui.components;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public abstract class CustomAbstractScrollWidget extends AbstractWidget implements Renderable, GuiEventListener {
    private double scrollAmount;
    private boolean scrolling;

    protected CustomAbstractScrollWidget(int i, int i1, int i2, int i3, Component component) {
        super(i, i1, i2, i3, component);
    }

    @Override
    public boolean mouseClicked(double v, double v1, int i) {
        if (!this.visible) {
            return false;
        } else {
            boolean b = this.withinContentAreaPoint(v, v1);
            boolean b1 = this.scrollbarVisible() && v >= (this.getX() + this.width) && v <= (this.getX() + this.width + 8) && v1 >= this.getY() && v1 < (this.getY() + this.height);
            if (b1 && i == 0) {
                this.scrolling = true;
                return true;
            } else {
                return b || b1;
            }
        }
    }

    @Override
    public boolean mouseReleased(double v, double v1, int i) {
        if (i == 0) {
            this.scrolling = false;
        }

        return super.mouseReleased(v, v1, i);
    }

    @Override
    public boolean mouseDragged(double v, double v1, int i, double v2, double v3) {
        if (this.visible && this.isFocused() && this.scrolling) {
            if (v1 < this.getY()) {
                this.setScrollAmount(0.0);
            } else if (v1 > (this.getY() + this.height)) {
                this.setScrollAmount(this.getMaxScrollAmount());
            } else {
                int scrollBarHeight = this.getScrollBarHeight();
                double max = Math.max(1, this.getMaxScrollAmount() / (this.height - scrollBarHeight));
                this.setScrollAmount(this.scrollAmount + v3 * max);
            }

            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean keyPressed(int i, int i1, int i2) {
        boolean b = i == 265;
        boolean b1 = i == 264;
        if (b || b1) {
            double scrollAmount1 = this.scrollAmount;
            this.setScrollAmount(this.scrollAmount + (b ? -1 : 1) * this.scrollRate());
            if (scrollAmount1 != this.scrollAmount) {
                return true;
            }
        }

        return super.keyPressed(i, i1, i2);
    }

    public void renderWidget(@NotNull GuiGraphics guiGraphics, int i, int i1, float v) {
        if (this.visible) {
            guiGraphics.enableScissor(this.getX() + 1, this.getY() + 1, this.getX() + this.width - 1, this.getY() + this.height - 1);
            guiGraphics.pose().pushPose();
            guiGraphics.pose().translate(0.0, -this.scrollAmount, 0.0);
            this.renderContents(guiGraphics, i, i1, v);
            guiGraphics.pose().popPose();
            guiGraphics.disableScissor();
            this.renderDecorations(guiGraphics);
        }
    }

    private int getScrollBarHeight() {
        return Mth.clamp((int)((float)(this.height * this.height) / (float)this.getContentHeight()), 32, this.height);
    }

    protected void renderDecorations(GuiGraphics guiGraphics) {
        if (this.scrollbarVisible()) {
            this.renderScrollBar(guiGraphics);
        }

    }

    protected int innerPadding() {
        return 4;
    }

    protected int totalInnerPadding() {
        return this.innerPadding() * 2;
    }

    protected double scrollAmount() {
        return this.scrollAmount;
    }

    protected void setScrollAmount(double v) {
        this.scrollAmount = Mth.clamp(v, 0.0, this.getMaxScrollAmount());
    }

    protected int getMaxScrollAmount() {
        return Math.max(0, this.getContentHeight() - (this.height - 4));
    }

    private int getContentHeight() {
        return this.getInnerHeight() + 4;
    }

    private void renderScrollBar(GuiGraphics guiGraphics) {
        int scrollBarHeight = this.getScrollBarHeight();
        int i = this.getX() + this.width;
        int i1 = this.getX() + this.width + 8;
        int max = Math.max(this.getY(), (int)this.scrollAmount * (this.height - scrollBarHeight) / this.getMaxScrollAmount() + this.getY());
        int i2 = max + scrollBarHeight;
        guiGraphics.fill(i, max, i1, i2, -8355712);
        guiGraphics.fill(i, max, i1 - 1, i2 - 1, -4144960);
    }

    protected boolean withinContentAreaTopBottom(int i, int i1) {
        return i1 - this.scrollAmount >= this.getY() && i - this.scrollAmount <= (this.getY() + this.height);
    }

    protected boolean withinContentAreaPoint(double v, double v1) {
        return v >= this.getX() && v < (this.getX() + this.width) && v1 >= this.getY() && v1 < (this.getY() + this.height);
    }

    protected boolean scrollbarVisible() {
        return this.getInnerHeight() > this.getHeight();
    }

    protected abstract int getInnerHeight();

    protected abstract double scrollRate();

    protected abstract void renderContents(GuiGraphics var1, int var2, int var3, float var4);
}
