package com.max.prettyguardian.particle.custom;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.HugeExplosionParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class PinkSonicBoomParticle extends HugeExplosionParticle {
    protected PinkSonicBoomParticle(ClientLevel clientLevel, double v, double v1, double v2, double v3, SpriteSet spriteSet) {
        super(clientLevel, v, v1, v2, v3, spriteSet);
        this.lifetime = 16;
        this.quadSize = 1F;
        this.setSpriteFromAge(spriteSet);
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet spriteSet) {
            this.sprites = spriteSet;
        }

        public Particle createParticle(@NotNull SimpleParticleType t, @NotNull ClientLevel clientLevel, double v, double v1, double v2, double v3, double v4, double v5) {
            return new PinkSonicBoomParticle(clientLevel, v, v1, v2, v3, this.sprites);
        }
    }
}
