package com.max.prettyguardian.item.custom.tool;


import com.max.prettyguardian.item.PrettyGuardianItem;
import com.max.prettyguardian.item.custom.projectiles.HeartItem;
import com.max.prettyguardian.sound.ModSounds;
import com.max.prettyguardian.util.ModTags;
import com.max.prettyguardian.worldgen.entity.projectile.HeartEntity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.ForgeEventFactory;
import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

public class CuteWandItem extends BowItem  {

    public static final Predicate<ItemStack> HEART_ARROW_ONLY = itemStack -> itemStack.is(ModTags.Items.HEART_ARROWS);

    public CuteWandItem(Properties properties) {
        super(properties.rarity(Rarity.EPIC));
    }

    @Override
    public void releaseUsing(@NotNull ItemStack itemStack, @NotNull Level level, @NotNull LivingEntity livingEntity, int i1) {
        if (livingEntity instanceof Player player) {
            boolean flag = player.getAbilities().instabuild || EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY, itemStack) > 0;
            ItemStack itemstack = player.getProjectile(itemStack);

            int i = this.getUseDuration(itemStack) - i1;
            i = ForgeEventFactory.onArrowLoose(itemStack, level, player, i, !itemstack.isEmpty() || flag);
            if (i < 0) return;


            float f = 1.0F;
            if (!level.isClientSide) {
                float damage = 8.0F;
                HeartItem arrowitem = (HeartItem) PrettyGuardianItem.CUTE_HEART.get();
                HeartEntity abstractarrow = arrowitem.createArrow(level, player, damage);

                abstractarrow.setOwner(player);

                Vec3 look = player.getLookAngle();
                abstractarrow.setPos(player.getX(), player.getEyeY() - 0.5F, player.getZ());
                abstractarrow.shoot(look.x, look.y + 0.05F, look.z, f * 3.0F, 1.0F);

                level.addFreshEntity(abstractarrow);
            }

            level.playSound(null, player.getX(), player.getY(), player.getZ(), ModSounds.CUTE_WAND_SHOOT.get(), SoundSource.PLAYERS, 0.3F, 1.0F / (level.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F);

            player.awardStat(Stats.ITEM_USED.get(this));

            EternalSilverCristalStaffitem.setPlayerStaffCooldown(player, 30);
        }
    }

    @Override
    public boolean isFoil(@NotNull ItemStack itemStack) {
        return true;
    }

    @Override
    public int getUseDuration(@NotNull ItemStack itemStack) {
        return 72000;
    }

    @Override
    public @NotNull Predicate<ItemStack> getAllSupportedProjectiles() {
        return HEART_ARROW_ONLY;
    }

    @Override
    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack itemStack) {
        return UseAnim.SPEAR;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand interactionHand) {
        ItemStack itemstack = player.getItemInHand(interactionHand);
        boolean flag = true;

        InteractionResultHolder<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(itemstack, level, player, interactionHand, flag);
        if (ret != null) return ret;

        player.startUsingItem(interactionHand);
        return InteractionResultHolder.consume(itemstack);
    }
}
