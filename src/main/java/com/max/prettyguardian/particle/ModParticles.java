package com.max.prettyguardian.particle;

import com.max.prettyguardian.PrettyGuardian;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModParticles {
    private ModParticles() {}

    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, PrettyGuardian.MOD_ID);

    public static final RegistryObject<SimpleParticleType> PINK_CRIT_PARTICLES = PARTICLE_TYPES.register("pink_crit_particles", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> STAR_LIGHT_PARTICLES = PARTICLE_TYPES.register("star_light_particles", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> LOVE_PARTICLES = PARTICLE_TYPES.register("love_particles", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> BUBBLE = PARTICLE_TYPES.register("bubble_particles", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> CELESTIAL_RABBIT_PARTICLES = PARTICLE_TYPES.register("celestial_rabbit_particles", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> PINK_FAIRY_PARTICLES = PARTICLE_TYPES.register("pink_fairy_particles", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> BLUE_FAIRY_PARTICLES = PARTICLE_TYPES.register("blue_fairy_particles", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> PINK_SONIC_BOOM_PARTICLES = PARTICLE_TYPES.register("pink_sonic_boom_particles", () -> new SimpleParticleType(true));

    public static void register(IEventBus eventBus) {
        PARTICLE_TYPES.register(eventBus);
    }
}