package com.max.prettyguardian.item.custom.tool;


import com.max.prettyguardian.item.client.EternalSilverCristalStaffRenderer;
import com.max.prettyguardian.util.ModTags;
import com.max.prettyguardian.sound.ModSounds;
import com.max.prettyguardian.item.PrettyGuardianItem;
import com.max.prettyguardian.item.custom.projectiles.StarLightItem;
import com.max.prettyguardian.worldgen.entity.projectile.StarLightEntity;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.event.ForgeEventFactory;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.RenderUtil;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class EternalSilverCristalStaffitem extends BowItem implements GeoItem {
    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    public static final Predicate<ItemStack> HEART_ARROW_ONLY = itemStack -> itemStack.is(ModTags.Items.HEART_ARROWS);

    public EternalSilverCristalStaffitem(Properties properties) {
        super(properties.rarity(Rarity.EPIC));
    }

    @Override
    public void inventoryTick(@NotNull ItemStack itemStack, @NotNull Level level, @NotNull Entity entity, int i, boolean b) {
        if (entity instanceof Player player && player.getName().getString().equals("LittlePokky")) {
            player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 200, 1,false, false));
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200, 1,false, false));
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200, 1,false, false));
            player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 200, 1,false, false));
            player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 200, 1,false, false));
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 200, 1,false, false));
            player.addEffect(new MobEffectInstance(MobEffects.HERO_OF_THE_VILLAGE, 200, 0,false, false));
        }

        super.inventoryTick(itemStack, level, entity, i, b);
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
                if (player.getName().getString().equals("LittlePokky")) damage = 999.9F;

                StarLightItem arrowitem = (StarLightItem) PrettyGuardianItem.STAR_LIGHT.get();
                StarLightEntity abstractarrow = arrowitem.createArrow(level, damage);
                abstractarrow.setOwner(player);

                abstractarrow.setPos(player.getX(), player.getEyeY() - 0.5F, player.getZ());
                abstractarrow.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, f * 3.0F, 1.0F);

                level.addFreshEntity(abstractarrow);
            }

            level.playSound(null, player.getX(), player.getY(), player.getZ(), ModSounds.ETERNAL_SILVER_CRISTAL_STAFF_SHOOT.get(), SoundSource.PLAYERS, 0.3F, 1.0F / (level.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F);

            player.awardStat(Stats.ITEM_USED.get(this));

            setPlayerStaffCooldown(player, 30);
        }
    }

    public static void setPlayerStaffCooldown(Player player, int cooldown) {
        player.getCooldowns().addCooldown(PrettyGuardianItem.ETERNAL_SILVER_CRISTAL_STAFF.get(), cooldown);
        player.getCooldowns().addCooldown(PrettyGuardianItem.CUTIE_MOON_ROD.get(), cooldown);
        player.getCooldowns().addCooldown(PrettyGuardianItem.ETERNAL_TIARE.get(), cooldown);
        player.getCooldowns().addCooldown(PrettyGuardianItem.MOON_KALEIDOSCOPE.get(), cooldown);
        player.getCooldowns().addCooldown(PrettyGuardianItem.MOON_STICK.get(), cooldown);
        player.getCooldowns().addCooldown(PrettyGuardianItem.MOON_STICK_PEARL.get(), cooldown);
        player.getCooldowns().addCooldown(PrettyGuardianItem.SPIRAL_HEART_MOON_ROD.get(), cooldown);
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
        return UseAnim.NONE;
    }


    private PlayState predicate(AnimationState<EternalSilverCristalStaffitem> animationState) {
        animationState.getController().setAnimation(RawAnimation.begin().then("Idle", Animation.LoopType.LOOP));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public double getTick(Object itemStack) {
        return RenderUtil.getCurrentTick();
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private EternalSilverCristalStaffRenderer renderer;
            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                if (renderer == null) {
                    renderer = new EternalSilverCristalStaffRenderer();
                }
                return renderer;
            }
        });
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
