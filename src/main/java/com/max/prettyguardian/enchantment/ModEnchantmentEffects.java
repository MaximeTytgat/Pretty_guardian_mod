package com.max.prettyguardian.enchantment;

import com.max.prettyguardian.PrettyGuardian;
import com.max.prettyguardian.enchantment.custom.SlowEnchantmentEffect;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModEnchantmentEffects {
    private ModEnchantmentEffects() {}
    public static final DeferredRegister<MapCodec<? extends EnchantmentEntityEffect>> ENTITY_ENCHANTMENT_EFFECTS =
            DeferredRegister.create(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, PrettyGuardian.MOD_ID);

    public static final RegistryObject<MapCodec<? extends EnchantmentEntityEffect>> SLOW =
            ENTITY_ENCHANTMENT_EFFECTS.register("slow", () -> SlowEnchantmentEffect.slow_enchantment_effect_codec);

    public static void register(IEventBus eventBus) {
        ENTITY_ENCHANTMENT_EFFECTS.register(eventBus);
    }
}
