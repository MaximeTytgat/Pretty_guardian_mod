package com.max.prettyguardian.item.custom;

import com.max.prettyguardian.client.gui.sreens.inventory.FakeLoveLetterMenu;
import com.max.prettyguardian.data.ModDataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class LoveLetterItem extends Item {
    public LoveLetterItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, @NotNull Player player, @NotNull InteractionHand interactionHand) {
        if (!level.isClientSide && player instanceof ServerPlayer serverPlayer) {
            SimpleMenuProvider simpleMenuProvider = new SimpleMenuProvider((id, inv, player1) -> new FakeLoveLetterMenu(id, new SimpleContainer(1)), Component.empty());
            serverPlayer.openMenu(simpleMenuProvider);
        }

        return super.use(level, player, interactionHand);
    }

    @Override
    public boolean isFoil(ItemStack itemStack) {
        return itemStack.has(ModDataComponents.LOVE_LETTER_AUTHOR);
    }
}