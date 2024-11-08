package com.max.prettyguardian.item.custom;

import com.max.prettyguardian.client.gui.sreens.inventory.GiftBoxMenu;
import com.max.prettyguardian.component.ModDataComponentTypes;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

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
            SimpleMenuProvider simpleMenuProvider = getSimpleMenuProvider(player, stack);
            serverPlayer.openMenu(simpleMenuProvider);

            return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
        } else {
            return InteractionResultHolder.pass(player.getItemInHand(interactionHand));
        }
    }

    private @NotNull SimpleMenuProvider getSimpleMenuProvider(@NotNull Player player, ItemStack stack) {
        SimpleContainer simpleContainer = new SimpleContainer(1) {
            @Override
            public void startOpen(@NotNull Player player) {
                this.setItem(0, tryToGetContent(player));
            }

            @Override
            public void setChanged() {
                tryToSaveContent(player.getItemInHand(InteractionHand.MAIN_HAND), this.getItem(0));
            }
        };

        return new SimpleMenuProvider((id, inv, player1) -> new GiftBoxMenu(id, inv, simpleContainer), stack.getHoverName());
    }

    private ItemStack tryToGetContent(Player player) {
        ItemStack giftBox = player.getItemInHand(InteractionHand.MAIN_HAND);
        if (giftBox.has(ModDataComponentTypes.GIFT_BOX_INVENTORY.get())) {
            ItemStack gift = giftBox.get(ModDataComponentTypes.GIFT_BOX_INVENTORY.get());
            if (gift != null) {
                return gift;
            }
        }
        return ItemStack.EMPTY;
    }

    private void tryToSaveContent(ItemStack giftBox, ItemStack item) {
        if (item != ItemStack.EMPTY) {
            giftBox.set(ModDataComponentTypes.GIFT_BOX_INVENTORY.get(), item);
        }
    }

    @Override
    public void appendHoverText(@NotNull ItemStack itemStack, @NotNull TooltipContext tooltipContext, @NotNull List<Component> components, @NotNull TooltipFlag tooltipFlag) {
        super.appendHoverText(itemStack, tooltipContext, components, tooltipFlag);
        components.add(Component.translatable("prettyGuardian.container.giftBox.hoverText", 1).withStyle(ChatFormatting.LIGHT_PURPLE));
    }
}
