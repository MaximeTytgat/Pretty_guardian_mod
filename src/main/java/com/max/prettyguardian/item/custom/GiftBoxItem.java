package com.max.prettyguardian.item.custom;

import com.max.prettyguardian.client.gui.sreens.inventory.GiftBoxMenu;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GiftBoxItem extends Item {

    public GiftBoxItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand interactionHand) {
        if (!level.isClientSide && player instanceof ServerPlayer serverPlayer) {
            ItemStack stack = player.getItemInHand(interactionHand);

            if (interactionHand != InteractionHand.MAIN_HAND) {
                return InteractionResultHolder.pass(stack);
            }
            SimpleContainer simpleContainer = new SimpleContainer(1) {
                @Override
                public void startOpen(Player player) {
                    ItemStack giftBox = player.getItemInHand(InteractionHand.MAIN_HAND);
                    CompoundTag tag = giftBox.getTag();
                    this.setItem(0, tryToGetContent(tag));
                }

                @Override
                public void setChanged() {
                    trytoSaveContent(player.getItemInHand(InteractionHand.MAIN_HAND), this.getItem(0));
                }
            };

            SimpleMenuProvider simpleMenuProvider = new SimpleMenuProvider((id, inv, player1) -> new GiftBoxMenu(id, inv, simpleContainer), stack.getHoverName());
            NetworkHooks.openScreen(serverPlayer, simpleMenuProvider);

            return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
        } else {
            return InteractionResultHolder.pass(player.getItemInHand(interactionHand));
        }
    }
    private ItemStack tryToGetContent(CompoundTag tag) {
        ItemStack item = ItemStack.EMPTY;
        if (tag != null && tag.contains("Items")) {
            Tag itemsTag = tag.get("Items");
            if (itemsTag instanceof ListTag itemsList) {
                if (!itemsList.isEmpty()) {
                    Tag itemTag = itemsList.get(0);
                    if (itemTag instanceof CompoundTag compoundItemTag) {
                        item = ItemStack.of(compoundItemTag);
                    }
                }
            }
        }
        return item;
    }
    private void trytoSaveContent(ItemStack giftBox, ItemStack item) {
        CompoundTag tag = giftBox.getOrCreateTag();
        ListTag itemsTag = new ListTag();
        CompoundTag itemTag = new CompoundTag();

        item.save(itemTag);
        itemsTag.add(itemTag);
        tag.put("Items", itemsTag);
        giftBox.setTag(tag);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag tooltipFlag) {
        super.appendHoverText(itemStack, level, components, tooltipFlag);
        components.add(Component.translatable("prettyGuardian.container.giftBox.hoverText", 1).withStyle(ChatFormatting.LIGHT_PURPLE));
    }
}
