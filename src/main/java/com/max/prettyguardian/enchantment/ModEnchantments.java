package com.max.prettyguardian.enchantment;

import com.max.prettyguardian.PrettyGuardian;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEnchantments {
    private ModEnchantments() {}
    public static final DeferredRegister<Enchantment> ENCHANTMENTS =
            DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, PrettyGuardian.MOD_ID);

    public static final RegistryObject<Enchantment> SLOW = ENCHANTMENTS.register("slow",  SlowEnchantment::new);

    public static void register(IEventBus eventBus) {
        ENCHANTMENTS.register(eventBus);
    }
}
