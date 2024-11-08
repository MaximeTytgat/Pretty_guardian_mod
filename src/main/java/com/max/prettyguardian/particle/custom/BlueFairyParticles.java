package com.max.prettyguardian.particle.custom;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

public class BlueFairyParticles extends TextureSheetParticle {
    private final double xStart;
    private final double yStart;
    private final double zStart;

    protected BlueFairyParticles(ClientLevel clientLevel, double v, double v1, double v2, double v3, double v4, double v5) {
        super(clientLevel, v, v1, v2);
        this.xd = v3;
        this.yd = v4;
        this.zd = v5;
        this.x = v;
        this.y = v1;
        this.z = v2;
        this.xStart = this.x;
        this.yStart = this.y;
        this.zStart = this.z;
        this.quadSize = 0.1F * (this.random.nextFloat() * 0.2F + 0.5F);
        this.rCol = 1F;
        this.gCol = 1F;
        this.bCol = 1F;
        this.lifetime = (int)(this.random.nextInt() * 10.0) + 40;
    }

    public @NotNull ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    @Override
    public void move(double v, double v1, double v2) {
        this.setBoundingBox(this.getBoundingBox().move(v, v1, v2));
        this.setLocationFromBoundingbox();
    }

    @Override
    public float getQuadSize(float v) {
        float f = (this.age + v) / this.lifetime;
        f = 1.0F - f;
        f *= f;
        f = 1.0F - f;
        return this.quadSize * f;
    }

    @Override
    public int getLightColor(float v) {
        int i = super.getLightColor(v);
        float f = (float)this.age / (float)this.lifetime;
        f *= f;
        f *= f;
        int j = i & 255;
        int k = i >> 16 & 255;
        k += (int)(f * 15.0F * 16.0F);
        if (k > 240) {
            k = 240;
        }

        return j | k << 16;
    }

    @Override
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.age++ >= this.lifetime) {
            this.remove();
        } else {
            float f = (float)this.age / (float)this.lifetime;
            float f1 = -f + f * f * 2.0F;
            float f2 = 1.0F - f1;
            this.x = this.xStart + this.xd * f2;
            this.y = this.yStart + this.yd * f2 + (1.0F - f);
            this.z = this.zStart + this.zd * f2;
            this.setPos(this.x, this.y, this.z);
        }

    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprite;

        public Provider(SpriteSet spriteSet) {
            this.sprite = spriteSet;
        }

        public Particle createParticle(@NotNull SimpleParticleType t, @NotNull ClientLevel clientLevel, double v, double v1, double v2, double v3, double v4, double v5) {
            BlueFairyParticles portalparticle = new BlueFairyParticles(clientLevel, v, v1, v2, v3, v4, v5);
            portalparticle.pickSprite(this.sprite);
            return portalparticle;
        }
    }
}
