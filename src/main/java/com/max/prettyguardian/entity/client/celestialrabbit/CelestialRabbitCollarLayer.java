package com.max.prettyguardian.entity.client.celestialrabbit;

import com.max.prettyguardian.PrettyGuardian;
import com.max.prettyguardian.entity.custom.CelestialRabbitEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class CelestialRabbitCollarLayer<T extends CelestialRabbitEntity, M extends CelestialRabbitModel<T>> extends RenderLayer<T, M> {
    private static final RenderType CELESTIAL_RABBIT_COLLAR_LOCATION = RenderType.entityCutoutNoCull(ResourceLocation.fromNamespaceAndPath(PrettyGuardian.MOD_ID, "textures/entity/rabbit/celestial/celestial_rabbit_collar.png"));

    public CelestialRabbitCollarLayer(RenderLayerParent<T, M> layerParent) {
        super(layerParent);
    }

    public void render(@NotNull PoseStack poseStack, @NotNull MultiBufferSource multiBufferSource, int i, CelestialRabbitEntity celestialRabbitEntity, float v, float v1, float v2, float v3, float v4, float v5) {
        if (celestialRabbitEntity.isTame() && !celestialRabbitEntity.isInvisible()) {
            VertexConsumer vertexconsumer = multiBufferSource.getBuffer(this.renderType());
            this.getParentModel().renderToBuffer(poseStack, vertexconsumer, i, LivingEntityRenderer.getOverlayCoords(celestialRabbitEntity, 0.0F), 1);
        }
    }

    public RenderType renderType() {
        return CELESTIAL_RABBIT_COLLAR_LOCATION;
    }
}