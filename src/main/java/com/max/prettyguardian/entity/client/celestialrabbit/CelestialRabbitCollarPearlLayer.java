package com.max.prettyguardian.entity.client.celestialrabbit;

import com.max.prettyguardian.PrettyGuardian;
import com.max.prettyguardian.entity.custom.CelestialRabbitEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class CelestialRabbitCollarPearlLayer<T extends CelestialRabbitEntity, M extends CelestialRabbitModel<T>> extends RenderLayer<T, M> {
    private static final ResourceLocation CELESTIAL_RABBIT_COLLAR_LOCATION = ResourceLocation.fromNamespaceAndPath(PrettyGuardian.MOD_ID,"textures/entity/rabbit/celestial/celestial_rabbit_pearl.png");

    public CelestialRabbitCollarPearlLayer(RenderLayerParent<T, M> tmRenderLayerParent) {
        super(tmRenderLayerParent);
    }

    public void render(@NotNull PoseStack poseStack, @NotNull MultiBufferSource multiBufferSource, int i, T celestialRabbitEntity, float v, float v1, float v2, float v3, float v4, float v5) {
        if (celestialRabbitEntity.isTame() && !celestialRabbitEntity.isInvisible()) {
            renderColoredCutoutModel(this.getParentModel(), CELESTIAL_RABBIT_COLLAR_LOCATION, poseStack, multiBufferSource, i, celestialRabbitEntity, celestialRabbitEntity.getCollarColor().getTextureDiffuseColor());
        }
    }
}