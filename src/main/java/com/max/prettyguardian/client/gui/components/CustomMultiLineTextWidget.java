package com.max.prettyguardian.client.gui.components;

import net.minecraft.Util;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractStringWidget;
import net.minecraft.client.gui.components.MultiLineLabel;
import net.minecraft.network.chat.Component;
import net.minecraft.util.SingleKeyCache;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.OptionalInt;

@OnlyIn(Dist.CLIENT)
public class CustomMultiLineTextWidget extends AbstractStringWidget {
    private OptionalInt maxWidth;
    private final OptionalInt maxRows;
    private final SingleKeyCache<CustomMultiLineTextWidget.CacheKey, MultiLineLabel> cache;
    private final boolean centered;

    public CustomMultiLineTextWidget(int i, int i1, Component component, Font font) {
        super(i, i1, 0, 0, component, font);
        this.maxWidth = OptionalInt.empty();
        this.maxRows = OptionalInt.empty();
        this.centered = false;
        this.cache = Util.singleKeyCache(cacheKey ->
                cacheKey.maxRows.isPresent() ?
                        MultiLineLabel.create(font, cacheKey.message, cacheKey.maxWidth, cacheKey.maxRows.getAsInt()) :
                        MultiLineLabel.create(font, cacheKey.message, cacheKey.maxWidth));
        this.active = false;
    }

    @Override
    public @NotNull CustomMultiLineTextWidget setColor(int i) {
        super.setColor(i);
        return this;
    }

    public CustomMultiLineTextWidget setMaxWidth(int i) {
        this.maxWidth = OptionalInt.of(i);
        return this;
    }

    @Override
    public int getWidth() {
        return this.cache.getValue(this.getFreshCacheKey()).getWidth();
    }

    @Override
    public int getHeight() {
        int var10000 = this.cache.getValue(this.getFreshCacheKey()).getLineCount();
        Objects.requireNonNull(this.getFont());
        return var10000 * 9;
    }

    public void renderWidget(@NotNull GuiGraphics guiGraphics, int i, int i1, float v) {
        MultiLineLabel value = this.cache.getValue(this.getFreshCacheKey());
        int x = this.getX();
        int y = this.getY();
        Objects.requireNonNull(this.getFont());
        int i2 = 9;
        int color = this.getColor();
        if (this.centered) {
            value.renderCentered(guiGraphics, x + this.getWidth() / 2, y, i2, color);
        } else {
            value.renderLeftAlignedNoShadow(guiGraphics, x, y, i2, color);
        }
    }

    private CustomMultiLineTextWidget.CacheKey getFreshCacheKey() {
        return new CustomMultiLineTextWidget.CacheKey(this.getMessage(), this.maxWidth.orElse(Integer.MAX_VALUE), this.maxRows);
    }

    @OnlyIn(Dist.CLIENT)
    record CacheKey(Component message, int maxWidth, OptionalInt maxRows) { }
}