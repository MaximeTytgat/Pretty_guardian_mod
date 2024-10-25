package com.max.prettyguardian.client.gui.sreens.inventory;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class FakeLoveLetterMenu extends AbstractContainerMenu {
    private static final int CONTAINER_SIZE = 1;
    private final Container container;

    public FakeLoveLetterMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
        this(id, new SimpleContainer(CONTAINER_SIZE));
    }

    public FakeLoveLetterMenu(int pContainerId, Container data) {
        super(ModMenuTypes.LETTER_EDITOR_MENU.get(), pContainerId);
        this.container = data;
    }

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player playerIn, int pIndex) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        return this.container.stillValid(player);
    }
}
