package com.max.prettyguardian.entity.client.celestialrabbit;

import com.max.prettyguardian.PrettyGuardian;
import com.max.prettyguardian.entity.client.ModModelLayers;
import com.max.prettyguardian.entity.custom.CelestialRabbitEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class CelestialRabbitRenderer extends MobRenderer<CelestialRabbitEntity, CelestialRabbitModel<CelestialRabbitEntity>> {
    private static final ResourceLocation CELESTIAL_RABBIT_LOCATION = new ResourceLocation(PrettyGuardian.MOD_ID, "textures/entity/rabbit/celestial/celestial_rabbit.png");
    private static final ResourceLocation CELESTIAL_RABBIT_ANGRY_LOCATION = new ResourceLocation(PrettyGuardian.MOD_ID, "textures/entity/rabbit/celestial/celestial_rabbit_angry.png");

    public CelestialRabbitRenderer(EntityRendererProvider.Context context) {
        super(context, new CelestialRabbitModel<>(context.bakeLayer(ModModelLayers.CELESTIAL_RABBIT_LAYER)), 0.5f);
        this.addLayer(new CelestialRabbitFlameLayer<>(this));
        this.addLayer(new CelestialRabbitGlowLayer<>(this));
        this.addLayer(new CelestialRabbitCollarLayer<>(this));
        this.addLayer(new CelestialRabbitCollarPearlLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(CelestialRabbitEntity celestialRabbitEntity) {
        return celestialRabbitEntity.isAngry() ? CELESTIAL_RABBIT_ANGRY_LOCATION : CELESTIAL_RABBIT_LOCATION;
    }

    @Override
    public void render(CelestialRabbitEntity celestialRabbit, float p_115456_, float p_115457_, PoseStack poseStack, MultiBufferSource multiBufferSource, int p_115460_) {
        if ( celestialRabbit.isBaby() ) {
            poseStack.scale(0.6f, 0.6f, 0.6f);
        }


        super.render(celestialRabbit, p_115456_, p_115457_, poseStack, multiBufferSource, p_115460_);
    }
}
