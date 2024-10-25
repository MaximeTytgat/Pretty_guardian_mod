package com.max.prettyguardian.client.gui.components;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Lists;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.Whence;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Style;
import net.minecraft.util.Mth;
import net.minecraft.util.StringUtil;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

@OnlyIn(Dist.CLIENT)
public class CustomMultilineTextField {
    private final Font font;
    private final List<CustomMultilineTextField.StringView> displayLines = Lists.newArrayList();
    private String value;
    private int cursor;
    private int selectCursor;
    private boolean selecting;
    private static final int CHARACTER_LIMIT = Integer.MAX_VALUE;
    private final int width;
    private final Consumer<String> valueListener = s -> {};
    private Runnable cursorListener = () -> {
    };

    public CustomMultilineTextField(Font font, int i) {
        this.font = font;
        this.width = i;
        this.setValue("");
    }

    public int characterLimit() {
        return CHARACTER_LIMIT;
    }

    public boolean hasCharacterLimit() {
        return false;
    }

    public void setCursorListener(Runnable runnable) {
        this.cursorListener = runnable;
    }

    public void setValue(String s) {
        this.value = this.truncateFullText(s);
        this.cursor = this.value.length();
        this.selectCursor = this.cursor;
        this.onValueChange();
    }

    public String value() {
        return this.value;
    }

    public void insertText(String s) {
        if (!s.isEmpty() || this.hasSelection()) {
            String s1 = this.truncateInsertionText(StringUtil.filterText(s, true));
            CustomMultilineTextField.StringView selected = this.getSelected();
            this.value = (new StringBuilder(this.value)).replace(selected.beginIndex, selected.endIndex, s1).toString();
            this.cursor = selected.beginIndex + s1.length();
            this.selectCursor = this.cursor;
            this.onValueChange();
        }
    }

    public void deleteText(int i) {
        if (!this.hasSelection()) {
            this.selectCursor = Mth.clamp(this.cursor + i, 0, this.value.length());
        }

        this.insertText("");
    }

    public int cursor() {
        return this.cursor;
    }

    public void setSelecting(boolean b) {
        this.selecting = b;
    }

    protected CustomMultilineTextField.StringView getSelected() {
        return new CustomMultilineTextField.StringView(Math.min(this.selectCursor, this.cursor), Math.max(this.selectCursor, this.cursor));
    }

    public int getLineCount() {
        return this.displayLines.size();
    }

    public int getLineAtCursor() {
        for(int i = 0; i < this.displayLines.size(); ++i) {
            CustomMultilineTextField.StringView stringView = this.displayLines.get(i);
            if (this.cursor >= stringView.beginIndex && this.cursor <= stringView.endIndex) {
                return i;
            }
        }

        return -1;
    }

    protected CustomMultilineTextField.StringView getLineView(int i) {
        return this.displayLines.get(Mth.clamp(i, 0, this.displayLines.size() - 1));
    }

    public void seekCursor(Whence whence, int i) {
        switch (whence) {
            case ABSOLUTE:
                this.cursor = i;
                break;
            case RELATIVE:
                this.cursor += i;
                break;
            case END:
                this.cursor = this.value.length() + i;
        }

        this.cursor = Mth.clamp(this.cursor, 0, this.value.length());
        this.cursorListener.run();
        if (!this.selecting) {
            this.selectCursor = this.cursor;
        }

    }

    public void seekCursorLine(int i1) {
        if (i1 != 0) {
            int i = this.font.width(this.value.substring(this.getCursorLineView().beginIndex, this.cursor)) + 2;
            CustomMultilineTextField.StringView cursorLineView = this.getCursorLineView(i1);
            int length = this.font.plainSubstrByWidth(this.value.substring(cursorLineView.beginIndex, cursorLineView.endIndex), i).length();
            this.seekCursor(Whence.ABSOLUTE, cursorLineView.beginIndex + length);
        }
    }

    public void seekCursorToPoint(double v, double v1) {
        int floor1 = Mth.floor(v);
        Objects.requireNonNull(this.font);
        int floor = Mth.floor(v1 / 9.0);
        CustomMultilineTextField.StringView stringView = this.displayLines.get(Mth.clamp(floor, 0, this.displayLines.size() - 1));
        int length = this.font.plainSubstrByWidth(this.value.substring(stringView.beginIndex, stringView.endIndex), floor1).length();
        this.seekCursor(Whence.ABSOLUTE, stringView.beginIndex + length);
    }

    public boolean keyPressed(int i) {
        this.selecting = Screen.hasShiftDown();
        if (Screen.isSelectAll(i)) {
            this.cursor = this.value.length();
            this.selectCursor = 0;
            return true;
        } else if (Screen.isCopy(i)) {
            Minecraft.getInstance().keyboardHandler.setClipboard(this.getSelectedText());
            return true;
        } else if (Screen.isPaste(i)) {
            this.insertText(Minecraft.getInstance().keyboardHandler.getClipboard());
            return true;
        } else if (Screen.isCut(i)) {
            Minecraft.getInstance().keyboardHandler.setClipboard(this.getSelectedText());
            this.insertText("");
            return true;
        } else {
            return otherKeys(i);
        }
    }

    private boolean otherKeys(int i) {
        StringView stringView;
        return switch (i) {
            case 257, 335 -> {
                this.insertText("\n");
                yield true;
            }
            case 259 -> {
                if (Screen.hasControlDown()) {
                    stringView = this.getPreviousWord();
                    this.deleteText(stringView.beginIndex - this.cursor);
                } else {
                    this.deleteText(-1);
                }

                yield true;
            }
            case 261 -> {
                if (Screen.hasControlDown()) {
                    stringView = this.getNextWord();
                    this.deleteText(stringView.beginIndex - this.cursor);
                } else {
                    this.deleteText(1);
                }

                yield true;
            }
            case 262 -> {
                if (Screen.hasControlDown()) {
                    stringView = this.getNextWord();
                    this.seekCursor(Whence.ABSOLUTE, stringView.beginIndex);
                } else {
                    this.seekCursor(Whence.RELATIVE, 1);
                }

                yield true;
            }
            case 263 -> {
                if (Screen.hasControlDown()) {
                    stringView = this.getPreviousWord();
                    this.seekCursor(Whence.ABSOLUTE, stringView.beginIndex);
                } else {
                    this.seekCursor(Whence.RELATIVE, -1);
                }

                yield true;
            }
            case 264 -> {
                if (!Screen.hasControlDown()) {
                    this.seekCursorLine(1);
                }

                yield true;
            }
            case 265 -> {
                if (!Screen.hasControlDown()) {
                    this.seekCursorLine(-1);
                }

                yield true;
            }
            case 266 -> {
                this.seekCursor(Whence.ABSOLUTE, 0);
                yield true;
            }
            case 267 -> {
                this.seekCursor(Whence.END, 0);
                yield true;
            }
            case 268 -> {
                if (Screen.hasControlDown()) {
                    this.seekCursor(Whence.ABSOLUTE, 0);
                } else {
                    this.seekCursor(Whence.ABSOLUTE, this.getCursorLineView().beginIndex);
                }
                yield true;
            }
            case 269 -> {
                if (Screen.hasControlDown()) {
                    this.seekCursor(Whence.END, 0);
                } else {
                    this.seekCursor(Whence.ABSOLUTE, this.getCursorLineView().endIndex);
                }
                yield  true;
            }
            default -> false;
        };
    }

    protected Iterable<CustomMultilineTextField.StringView> iterateLines() {
        return this.displayLines;
    }

    public boolean hasSelection() {
        return this.selectCursor != this.cursor;
    }

    @VisibleForTesting
    public String getSelectedText() {
        CustomMultilineTextField.StringView selected = this.getSelected();
        return this.value.substring(selected.beginIndex, selected.endIndex);
    }

    private CustomMultilineTextField.StringView getCursorLineView() {
        return this.getCursorLineView(0);
    }

    private CustomMultilineTextField.StringView getCursorLineView(int i) {
        int lineAtCursor = this.getLineAtCursor();
        if (lineAtCursor < 0) {
            int var10002 = this.cursor;
            throw new IllegalStateException("Cursor is not within text (cursor = " + var10002 + ", length = " + this.value.length() + ")");
        } else {
            return this.displayLines.get(Mth.clamp(lineAtCursor + i, 0, this.displayLines.size() - 1));
        }
    }

    @VisibleForTesting
    protected CustomMultilineTextField.StringView getPreviousWord() {
        if (this.value.isEmpty()) {
            return CustomMultilineTextField.StringView.EMPTY;
        } else {
            int clamp = Mth.clamp(this.cursor, 0, this.value.length() - 1);

            while(clamp > 0 && !Character.isWhitespace(this.value.charAt(clamp - 1))) {
                --clamp;
            }

            return new CustomMultilineTextField.StringView(clamp, this.getWordEndPosition(clamp));
        }
    }

    @VisibleForTesting
    protected CustomMultilineTextField.StringView getNextWord() {
        if (this.value.isEmpty()) {
            return CustomMultilineTextField.StringView.EMPTY;
        } else {
            int clamp = Mth.clamp(this.cursor, 0, this.value.length() - 1);
            while(clamp < this.value.length() && Character.isWhitespace(this.value.charAt(clamp))) {
                ++clamp;
            }
            return new CustomMultilineTextField.StringView(clamp, this.getWordEndPosition(clamp));
        }
    }

    private int getWordEndPosition(int i) {
        return i;
    }

    private void onValueChange() {
        this.updateDisplayLines();
        this.valueListener.accept(this.value);
        this.cursorListener.run();
    }

    private void updateDisplayLines() {
        this.displayLines.clear();
        if (this.value.isEmpty()) {
            this.displayLines.add(CustomMultilineTextField.StringView.EMPTY);
        } else {
            this.font.getSplitter().splitLines(this.value, this.width, Style.EMPTY, false, (style, i, i1) -> this.displayLines.add(new StringView(i, i1)));
            if (this.value.charAt(this.value.length() - 1) == '\n') {
                this.displayLines.add(new CustomMultilineTextField.StringView(this.value.length(), this.value.length()));
            }

        }
    }

    private String truncateFullText(String s) {
        return this.hasCharacterLimit() ? StringUtil.truncateStringIfNecessary(s, CHARACTER_LIMIT, false) : s;
    }

    private String truncateInsertionText(String s) {
        if (this.hasCharacterLimit()) {
            int i = CHARACTER_LIMIT - this.value.length();
            return StringUtil.truncateStringIfNecessary(s, i, false);
        } else {
            return s;
        }
    }

    @OnlyIn(Dist.CLIENT)
    protected record StringView(int beginIndex, int endIndex) {
        static final CustomMultilineTextField.StringView EMPTY = new CustomMultilineTextField.StringView(0, 0);
    }
}