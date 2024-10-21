package com.max.prettyguardian.blocks.entity;

import com.max.prettyguardian.blocks.PrettyGuardianBlock;
import com.max.prettyguardian.blocks.custom.table.MoonAltarBlock;
import com.max.prettyguardian.client.gui.sreens.inventory.MoonAltarMenu;
import com.max.prettyguardian.item.PrettyGuardianItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MoonAltarBlockEntity extends RandomizableContainerBlockEntity implements MenuProvider, Container {
    private NonNullList<ItemStack> items = NonNullList.withSize(4, ItemStack.EMPTY);
    private static final int INPUT_SLOT_1 = 0;
    private static final int INPUT_SLOT_2 = 1;
    private static final int INPUT_SLOT_3 = 2;
    private static final int OUTPUT_SLOT = 3;
    private final LazyOptional<ItemStackHandler> lazyItemHandler = LazyOptional.empty();

    public MoonAltarBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.MOON_ALTAR_BE.get(), blockPos, blockState);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void setChanged() {
        if (level != null && !level.isClientSide()) {
            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
        }
        super.setChanged();
    }

    public void drops() {
        if (this.level == null) return;
        SimpleContainer inventory = new SimpleContainer(items.size());
        for (int i = 0; i < items.size(); i++) {
            inventory.setItem(i, items.get(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.translatable("block.prettyGuardian.moon_altar");
    }

    @Override
    protected @NotNull Component getDefaultName() {
        return Component.translatable("block.prettyGuardian.moon_altar");
    }

    @Override
    protected @NotNull NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    protected void setItems(@NotNull NonNullList<ItemStack> itemStacks) {
        this.items = itemStacks;
    }

    @Override
    public @NotNull AbstractContainerMenu createMenu(int id, @NotNull Inventory inventory) {
        return new MoonAltarMenu(id, inventory, this);
    }

    @Override
    public void loadAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider provider) {
        super.loadAdditional(tag, provider);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        if (!this.tryLoadLootTable(tag)) {
            ContainerHelper.loadAllItems(tag, this.items, provider);
        }

    }

    @Override
    public void setItem(int i, ItemStack itemStack) {
        this.unpackLootTable(null);
        if (itemStack.getItem() == PrettyGuardianItem.PLUTONS_KEY.get()) {
            this.getItems().set(0, itemStack);
        } else if (itemStack.getItem() == PrettyGuardianItem.SPACE_SWORD.get()) {
            this.getItems().set(1, itemStack);
        } else if (itemStack.getItem() == PrettyGuardianItem.NEPTUNES_MIRROR.get()) {
            this.getItems().set(2, itemStack);
        } else {
            this.getItems().set(i, itemStack);
        }

        if (itemStack.getCount() > this.getMaxStackSize()) {
            itemStack.setCount(this.getMaxStackSize());
        }

        this.setChanged();
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider provider) {
        if (!this.trySaveLootTable(tag)) {
            ContainerHelper.saveAllItems(tag, this.items, provider);
        }

        super.saveAdditional(tag, provider);
    }


    public void tick(Level level, BlockPos pPos, BlockState pState) {
        if (hasRecipe()) {
            setChanged(level, pPos, pState);
            craftItem();
        }
    }

    private void craftItem() {
        ItemStack result = new ItemStack(PrettyGuardianBlock.SILVER_CRYSTAL.get());
        if (this.items == null) {
            return;
        }
        this.items.clear();

        this.items.set(OUTPUT_SLOT, new ItemStack(result.getItem(), this.items.get(OUTPUT_SLOT).getCount() + result.getCount()));
        setChanged();
    }

    private boolean hasRecipe() {
        ItemStack item1 = this.items.get(INPUT_SLOT_1);
        ItemStack item2 = this.items.get(INPUT_SLOT_2);
        ItemStack item3 = this.items.get(INPUT_SLOT_3);

        if (item1.isEmpty() || item2.isEmpty() || item3.isEmpty()) {
            return false;
        }

        ItemStack result = new ItemStack(PrettyGuardianBlock.SILVER_CRYSTAL.get());
        return (!item1.isEmpty() && !item2.isEmpty() && !item3.isEmpty()) && canInsertAmountIntoOutputSlot(result.getCount()) && canInsertItemIntoOutputSlot(result.getItem());
    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.items.get(OUTPUT_SLOT).isEmpty() || this.items.get(OUTPUT_SLOT).is(item);
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        return this.items.get(OUTPUT_SLOT).getCount() + count <= this.items.get(OUTPUT_SLOT).getMaxStackSize();
    }

    public @Nullable ItemStack getRenderStack(int slot) {
        return items.get(slot);
    }

    public Direction getFacingProperty() {
        return this.getBlockState().getValue(MoonAltarBlock.FACING);
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public int getContainerSize() {
        return this.items.size();
    }
}
