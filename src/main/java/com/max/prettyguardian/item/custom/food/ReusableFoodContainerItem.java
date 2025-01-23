package com.max.prettyguardian.item.custom.food;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

public class ReusableFoodContainerItem extends Item {
    private final int drinkDuration;
    private final boolean isDrink;
    private final RegistryObject<Item> reuseItem;

    public ReusableFoodContainerItem(Properties properties, int drinkDuration, RegistryObject<Item> reuseItem) {
        super(properties);
        this.reuseItem = reuseItem;
        this.drinkDuration = drinkDuration;
        this.isDrink = true;
    }

    public ReusableFoodContainerItem(Properties properties, int drinkDuration, RegistryObject<Item> reuseItem, boolean isDrink) {
        super(properties);
        this.reuseItem = reuseItem;
        this.drinkDuration = drinkDuration;
        this.isDrink = isDrink;
    }

    @Override
    public @NotNull ItemStack finishUsingItem(@NotNull ItemStack itemStack, @NotNull Level level, @NotNull LivingEntity livingEntity) {
        super.finishUsingItem(itemStack, level, livingEntity);
        if (livingEntity instanceof ServerPlayer serverplayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger(serverplayer, itemStack);
            serverplayer.awardStat(Stats.ITEM_USED.get(this));
        }

        if (livingEntity instanceof Player player && !player.getAbilities().instabuild) {
            itemStack.shrink(1);
        }

        if (itemStack.isEmpty()) {
            return new ItemStack(reuseItem.get());
        } else {
            if (livingEntity instanceof Player player) {
                player.addItem(new ItemStack(reuseItem.get()));
            }
            return itemStack;
        }
    }

    @Override
    public int getUseDuration(@NotNull ItemStack itemStack, @NotNull LivingEntity livingEntity) {
        return this.drinkDuration;
    }

    @Override
    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack itemStack) {
        return isDrink ? UseAnim.DRINK : UseAnim.EAT;
    }
}
