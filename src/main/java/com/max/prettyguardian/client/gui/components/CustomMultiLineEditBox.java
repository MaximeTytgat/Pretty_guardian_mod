package com.max.prettyguardian.client.gui.components;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.narration.NarratedElementType;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.util.StringUtil;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Iterator;
import java.util.Objects;

@OnlyIn(Dist.CLIENT)
public class CustomMultiLineEditBox extends CustomAbstractScrollWidget {
    private static final int CURSOR_INSERT_COLOR = 11828699;
    private static final String CURSOR_APPEND_CHARACTER = "_";
    private static final int TEXT_COLOR = 11828699;
    private static final int PLACEHOLDER_TEXT_COLOR = 11828699;
    private final Font font;
    private final Component placeholder;
    private final CustomMultilineTextField textField;
    private int frame;

    public CustomMultiLineEditBox(Font font, int i, int i1, int i2, int i3, Component component, Component component1) {
        super(i, i1, i2, i3, component1);
        this.font = font;
        this.placeholder = component;
        this.textField = new CustomMultilineTextField(font, i2 - this.totalInnerPadding());
        this.textField.setCursorListener(this::scrollToCursor);
    }

    public void setValue(String s) {
        this.textField.setValue(s);
    }

    public String getValue() {
        return this.textField.value();
    }

    public void tick() {
        ++this.frame;
    }

    public void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
        narrationElementOutput.add(NarratedElementType.TITLE, Component.translatable("gui.narrate.editBox", this.getMessage(), this.getValue()));
    }

    public boolean mouseClicked(double v, double v1, int i) {
        if (super.mouseClicked(v, v1, i)) {
            return true;
        } else if (this.withinContentAreaPoint(v, v1) && i == 0) {
            this.textField.setSelecting(Screen.hasShiftDown());
            this.seekCursorScreen(v, v1);
            return true;
        } else {
            return false;
        }
    }

    public boolean mouseDragged(double v, double v1, int i, double v2, double v3) {
        if (super.mouseDragged(v, v1, i, v2, v3)) {
            return true;
        } else if (this.withinContentAreaPoint(v, v1) && i == 0) {
            this.textField.setSelecting(true);
            this.seekCursorScreen(v, v1);
            this.textField.setSelecting(Screen.hasShiftDown());
            return true;
        } else {
            return false;
        }
    }

    public boolean keyPressed(int i, int i1, int i2) {
        return this.textField.keyPressed(i);
    }

    public boolean charTyped(char c, int i) {
        if (this.visible && this.isFocused() && StringUtil.isAllowedChatCharacter(c)) {
            this.textField.insertText(Character.toString(c));
            return true;
        } else {
            return false;
        }
    }

    protected void renderContents(GuiGraphics guiGraphics, int i, int i1, float v) {
        String textFieldValue = this.textField.value();
        if (textFieldValue.isEmpty() && !this.isFocused()) {
            guiGraphics.drawWordWrap(this.font, this.placeholder, this.getX() + this.innerPadding(), this.getY() + this.innerPadding(), this.width - this.totalInnerPadding(), PLACEHOLDER_TEXT_COLOR);
            return;
        }
        int cursor = this.textField.cursor();
        boolean isFocused = this.isFocused() && this.frame / 6 % 2 == 0;
        boolean inText = cursor < textFieldValue.length();
        int i5 = 0;
        int i4 = 0;
        int y = this.getY() + this.innerPadding();

        int var10002;
        int var10004;
        for(Iterator<CustomMultilineTextField.StringView> stringViewIterator = this.textField.iterateLines().iterator(); stringViewIterator.hasNext(); y += 9) {
            CustomMultilineTextField.StringView stringView = stringViewIterator.next();
            Objects.requireNonNull(this.font);
            boolean withinContentAreaTopBottom = this.withinContentAreaTopBottom(y, y + 9);
            if (isFocusedAndInTextAndCursorInView(isFocused, inText, cursor, stringView)) {
                if (withinContentAreaTopBottom) {
                    i5 = guiGraphics.drawString(this.font, textFieldValue.substring(stringView.beginIndex(), cursor), this.getX() + this.innerPadding(), y, TEXT_COLOR, false) - 1;
                    var10002 = y - 1;
                    int var10003 = i5 + 1;
                    var10004 = y + 1;
                    Objects.requireNonNull(this.font);
                    guiGraphics.fill(i5, var10002, var10003, var10004 + 9, CURSOR_INSERT_COLOR);
                    guiGraphics.drawString(this.font, textFieldValue.substring(cursor, stringView.endIndex()), i5, y, TEXT_COLOR, false);
                }
            } else {
                if (withinContentAreaTopBottom) {
                    i5 = guiGraphics.drawString(this.font, textFieldValue.substring(stringView.beginIndex(), stringView.endIndex()), this.getX() + this.innerPadding(), y, TEXT_COLOR, false) - 1;
                }

                i4 = y;
            }

            Objects.requireNonNull(this.font);
        }

        changeScrollingAndDrawSelection(guiGraphics, isFocused, inText, i4, i5, textFieldValue);
    }

    private void changeScrollingAndDrawSelection(GuiGraphics guiGraphics, boolean isFocused, boolean inText, int i4, int i5, String textFieldValue) {
        if (isFocused && !inText) {
            Objects.requireNonNull(this.font);
            if (this.withinContentAreaTopBottom(i4, i4 + 9)) {
                guiGraphics.drawString(this.font, CURSOR_APPEND_CHARACTER, i5, i4, CURSOR_INSERT_COLOR, false);
            }
        }

        if (this.textField.hasSelection()) {
            textFieldHasSelection(guiGraphics, textFieldValue);
        }
    }

    private static boolean isFocusedAndInTextAndCursorInView(boolean isFocused, boolean inText, int cursor, CustomMultilineTextField.StringView stringView) {
        return isFocused && inText && cursor >= stringView.beginIndex() && cursor <= stringView.endIndex();
    }

    private void textFieldHasSelection(GuiGraphics guiGraphics, String textFieldValue) {
        int var10004;
        int y;
        int var10002;
        CustomMultilineTextField.StringView selected = this.textField.getSelected();
        int i3 = this.getX() + this.innerPadding();
        y = this.getY() + this.innerPadding();

        for (CustomMultilineTextField.StringView $$15 : this.textField.iterateLines()) {
            if (selected.beginIndex() <= $$15.endIndex()) {
                if ($$15.beginIndex() > selected.endIndex()) {
                    break;
                }

                Objects.requireNonNull(this.font);
                if (this.withinContentAreaTopBottom(y, y + 9)) {
                    int width1 = this.font.width(textFieldValue.substring($$15.beginIndex(), Math.max(selected.beginIndex(), $$15.beginIndex())));
                    int i2;
                    if (selected.endIndex() > $$15.endIndex()) {
                        i2 = this.width - this.innerPadding();
                    } else {
                        i2 = this.font.width(textFieldValue.substring($$15.beginIndex(), selected.endIndex()));
                    }

                    var10002 = i3 + width1;
                    var10004 = i3 + i2;
                    Objects.requireNonNull(this.font);
                    this.renderHighlight(guiGraphics, var10002, y, var10004, y + 9);
                }

            }
            Objects.requireNonNull(this.font);
            y += 9;
        }
    }


    protected void renderDecorations(GuiGraphics guiGraphics) {
        super.renderDecorations(guiGraphics);
        if (this.textField.hasCharacterLimit()) {
            int i = this.textField.characterLimit();
            Component translatable = Component.translatable("gui.multiLineEditBox.character_limit", this.textField.value().length(), i);
            guiGraphics.drawString(this.font, translatable, this.getX() + this.width - this.font.width(translatable), this.getY() + this.height + 4, 10526880);
        }

    }

    public int getInnerHeight() {
        Objects.requireNonNull(this.font);
        return 9 * this.textField.getLineCount();
    }

    protected boolean scrollbarVisible() {
        return (double)this.textField.getLineCount() > this.getDisplayableLineCount();
    }

    protected double scrollRate() {
        Objects.requireNonNull(this.font);
        return 9.0 / 2.0;
    }

    private void renderHighlight(GuiGraphics guiGraphics, int i, int i1, int i2, int i3) {
        guiGraphics.fill(RenderType.guiTextHighlight(), i, i1, i2, i3, -16776961);
    }

    private void scrollToCursor() {
        double v = this.scrollAmount();
        CustomMultilineTextField textField1 = this.textField;
        Objects.requireNonNull(this.font);
        CustomMultilineTextField.StringView lineView = textField1.getLineView((int)(v / 9.0));
        int var5;
        if (this.textField.cursor() <= lineView.beginIndex()) {
            var5 = this.textField.getLineAtCursor();
            Objects.requireNonNull(this.font);
            v = var5 * 9.0;
        } else {
            double var10001 = v + (double)this.height;
            Objects.requireNonNull(this.font);
            CustomMultilineTextField.StringView lineView1 = textField1.getLineView((int)(var10001 / 9.0) - 1);
            if (this.textField.cursor() > lineView1.endIndex()) {
                var5 = this.textField.getLineAtCursor();
                Objects.requireNonNull(this.font);
                var5 = var5 * 9 - this.height;
                Objects.requireNonNull(this.font);
                v = var5 + 9 + this.totalInnerPadding();
            }
        }

        this.setScrollAmount(v);
    }

    private double getDisplayableLineCount() {
        double var10000 = this.height - this.totalInnerPadding();
        Objects.requireNonNull(this.font);
        return var10000 / 9.0;
    }

    private void seekCursorScreen(double v, double v1) {
        double v2 = v - (double)this.getX() - (double)this.innerPadding();
        double v3 = v1 - (double)this.getY() - (double)this.innerPadding() + this.scrollAmount();
        this.textField.seekCursorToPoint(v2, v3);
    }
}
