package com.max.prettyguardian.entity.client.celestialrabbit;

import com.max.prettyguardian.PrettyGuardian;
import com.max.prettyguardian.entity.custom.CelestialRabbitEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class CelestialRabbitGlowLayer<T extends CelestialRabbitEntity, M extends CelestialRabbitModel<T>> extends RenderLayer<T, M> {
    private static final RenderType RABBIT_LIGHT_TEXTURE = RenderType.entityTranslucentEmissive(ResourceLocation.fromNamespaceAndPath(PrettyGuardian.MOD_ID, "textures/entity/rabbit/celestial/celestial_rabbit_glow.png"));
    public CelestialRabbitGlowLayer(RenderLayerParent<T, M> tmRenderLayerParent) {
        super(tmRenderLayerParent);
    }

    @Override
    public void render(@NotNull PoseStack poseStack, MultiBufferSource multiBufferSource, int i, @NotNull T t, float v, float v1, float v2, float v3, float v4, float v5) {
        VertexConsumer vertexconsumer = multiBufferSource.getBuffer(this.renderType());
        this.getParentModel().renderToBuffer(poseStack, vertexconsumer, 15728640, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    }

    public RenderType renderType() {
        return RABBIT_LIGHT_TEXTURE;
    }
}
