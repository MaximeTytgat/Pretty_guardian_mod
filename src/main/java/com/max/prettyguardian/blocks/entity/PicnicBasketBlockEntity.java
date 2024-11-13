package com.max.prettyguardian.blocks.entity;

import com.max.prettyguardian.blocks.custom.PicnicBasketBlock;
import com.max.prettyguardian.client.gui.sreens.inventory.PicnicBasketMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.BarrelBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.items.IItemHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.stream.IntStream;

public class  PicnicBasketBlockEntity extends RandomizableContainerBlockEntity implements WorldlyContainer {
    private static final int[] SLOTS = IntStream.range(0, 4).toArray();
    private NonNullList<ItemStack> itemStacks = NonNullList.withSize(4, ItemStack.EMPTY);
    private int openCount;

    public PicnicBasketBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.PICNIC_BASKET_BE.get(), blockPos, blockState);
    }

    @Override
    public void startOpen(@NotNull Player player) {
        if (this.level == null) return;

        if (!this.remove && !player.isSpectator()) {
            if (this.openCount < 0) {
                this.openCount = 0;
            }

            ++this.openCount;
            this.level.blockEvent(this.worldPosition, this.getBlockState().getBlock(), 1, this.openCount);
            updateBlockState(this.getBlockState(), true);
            if (this.openCount == 1) {
                this.level.gameEvent(player, GameEvent.CONTAINER_OPEN, this.worldPosition);
                this.level.playSound(null, this.worldPosition, SoundEvents.BARREL_OPEN, SoundSource.BLOCKS, 0.5F, this.level.random.nextFloat() * 0.1F + 0.9F);
            }
        }

    }

    @Override
    public void stopOpen(@NotNull Player player) {
        if (this.level == null) return;

        if (!this.remove && !player.isSpectator()) {
            --this.openCount;
            updateBlockState(this.getBlockState(), false);
            this.level.blockEvent(this.worldPosition, this.getBlockState().getBlock(), 1, this.openCount);
            if (this.openCount <= 0) {
                this.level.gameEvent(player, GameEvent.CONTAINER_CLOSE, this.worldPosition);
                this.level.playSound(null, this.worldPosition, SoundEvents.BARREL_CLOSE, SoundSource.BLOCKS, 0.5F, this.level.random.nextFloat() * 0.1F + 0.9F);
            }
        }
    }

    void updateBlockState(BlockState blockState, boolean open) {
        if (this.level == null) return;
        this.level.setBlock(this.getBlockPos(), blockState.setValue(BarrelBlock.OPEN, open), 3);
    }

    @Override
    public int getContainerSize() {
        return this.itemStacks.size();
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.translatable("block.prettyguardian.picnic_basket");
    }

    @Override
    protected @NotNull Component getDefaultName() {
        return Component.translatable("block.prettyguardian.picnic_basket");
    }

    @Override
    protected void loadAdditional(@NotNull CompoundTag compoundTag, HolderLookup.@NotNull Provider provider) {
        super.loadAdditional(compoundTag, provider);
        this.loadFromTag(compoundTag, provider);
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag compoundTag, HolderLookup.@NotNull Provider provider) {
        if (!this.trySaveLootTable(compoundTag)) {
            ContainerHelper.saveAllItems(compoundTag, this.itemStacks, false, provider);
        }

        super.saveAdditional(compoundTag, provider);
    }

    public void loadFromTag(CompoundTag compoundTag, HolderLookup.Provider provider) {
        this.itemStacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        if (!this.tryLoadLootTable(compoundTag) && compoundTag.contains("Items", 9)) {
            ContainerHelper.loadAllItems(compoundTag, this.itemStacks, provider);
        }
    }

    @Override
    protected @NotNull NonNullList<ItemStack> getItems() {
        return this.itemStacks;
    }

    @Override
    protected void setItems(@NotNull NonNullList<ItemStack> itemStacks) {
        this.itemStacks = itemStacks;
    }

    @Override
    public int @NotNull [] getSlotsForFace(@NotNull Direction direction) {
        return SLOTS;
    }
    @Override
    public boolean canPlaceItemThroughFace(int i, ItemStack itemStack, @Nullable Direction direction) {
        return !(Block.byItem(itemStack.getItem()) instanceof PicnicBasketBlock) && itemStack.getItem().canFitInsideContainerItems();
    }

    public boolean canTakeItemThroughFace(int i, @NotNull ItemStack itemStack, @NotNull Direction direction) {
        return true;
    }

    @Override
    protected @NotNull AbstractContainerMenu createMenu(int id, @NotNull Inventory inventory) {
        return new PicnicBasketMenu(id, inventory, this);
    }

    @Override
    protected @NotNull IItemHandler createUnSidedHandler() {
        return new net.minecraftforge.items.wrapper.SidedInvWrapper(this, Direction.UP);
    }
}
