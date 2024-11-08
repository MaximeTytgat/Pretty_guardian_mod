package com.max.prettyguardian.particle.custom;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

public class BubbleParticles extends TextureSheetParticle {
    private final SpriteSet sprites;


    protected BubbleParticles(
            ClientLevel level,
            double xCoord,
            double yCoord,
            double zCoord,
            SpriteSet spriteSet,
            double xd,
            double yd,
            double zd
    ) {
        super(level, xCoord, yCoord, zCoord, xd, yd, zd);

        this.friction = 0.8F;
        this.xd = xd;
        this.yd = yd;
        this.zd = zd;

        this.quadSize *= 1.85F;
        this.lifetime = 40;

        this.rCol = 1F;
        this.gCol = 1F;
        this.bCol = 1F;

        this.sprites = spriteSet;
        this.setSpriteFromAge(spriteSet);

    }

    @Override
    public void tick() {
        super.tick();
        if (this.age++ >= this.lifetime) {
            this.remove();
        } else {
            this.setSpriteFromAge(this.sprites);
        }
    }

    @Override
    public @NotNull ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_LIT;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet spriteSet) {
            this.sprites = spriteSet;
        }

        public Particle createParticle(@NotNull SimpleParticleType particleType, @NotNull ClientLevel level,
                                       double x, double y, double z,
                                       double dx, double dy, double dz) {
            return new BubbleParticles(level, x, y, z, this.sprites, dx, dy, dz);
        }
    }
}
