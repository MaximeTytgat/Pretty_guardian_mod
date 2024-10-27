package com.max.prettyguardian.effect;

import com.max.prettyguardian.PrettyGuardian;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {
    private ModEffects() {}
    public static final DeferredRegister<MobEffect> EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, PrettyGuardian.MOD_ID);

    public static final RegistryObject<MobEffect> LOVE = EFFECTS.register("love",
            () -> new LoveEffect(
                    MobEffectCategory.NEUTRAL,
                    50293172
            ));

    public static void register(IEventBus eventBus) {
        EFFECTS.register(eventBus);
    }
}
