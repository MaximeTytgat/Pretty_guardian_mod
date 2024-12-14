package com.max.prettyguardian.enchantment;

import com.max.prettyguardian.PrettyGuardian;
import com.max.prettyguardian.enchantment.custom.SlowEnchantmentEffect;
import com.max.prettyguardian.util.ModTags;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentTarget;

public class ModEnchantments {
    private ModEnchantments() {}

    public static final ResourceKey<Enchantment> SLOW = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(PrettyGuardian.MOD_ID, "slow"));

    public static Holder.Reference<Enchantment> SLOW_ENCHANTMENT;

    public static void bootstrap(BootstrapContext<Enchantment> context) {
        var enchantments = context.lookup(Registries.ENCHANTMENT);
        var items = context.lookup(Registries.ITEM);

        SLOW_ENCHANTMENT =
        register(context, Enchantment.enchantment(Enchantment.definition(
                items.getOrThrow(ModTags.Items.SPACE_SWORD_ENCHANTABLE),
                10,
                2,
                Enchantment.dynamicCost(1, 11),
                Enchantment.dynamicCost(21, 11),
                1,
                EquipmentSlotGroup.MAINHAND
        ))
                .exclusiveWith(enchantments.getOrThrow(EnchantmentTags.MINING_EXCLUSIVE))
                .withEffect(
                        EnchantmentEffectComponents.POST_ATTACK,
                        EnchantmentTarget.ATTACKER,
                        EnchantmentTarget.VICTIM,
                        new SlowEnchantmentEffect()
                )
        );
    }

    private static Holder.Reference<Enchantment> register(BootstrapContext<Enchantment> registry, Enchantment.Builder builder) {
        return registry.register(ModEnchantments.SLOW, builder.build(ModEnchantments.SLOW.location()));
    }
}
