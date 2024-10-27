package com.max.prettyguardian.entity.client.fairy;

import com.google.common.collect.Maps;
import com.max.prettyguardian.PrettyGuardian;
import com.max.prettyguardian.entity.custom.FairyEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;
import java.util.Map;

public class FairyGlowLayer<T extends FairyEntity, M extends FairyModel<T>> extends RenderLayer<T, M> {
    private static final Map<FairyEntity.Variant, RenderType> RENDER_BY_TYPE = Util.make(Maps.newHashMap(), variantRenderTypeHashMap -> {
        for(FairyEntity.Variant fairy$variant : FairyEntity.Variant.values()) {
            variantRenderTypeHashMap.put(fairy$variant, RenderType.entityTranslucentEmissive(new ResourceLocation(PrettyGuardian.MOD_ID, String.format(Locale.ROOT, "textures/entity/fairy/fairy_%s.png", fairy$variant.getName()))));
        }
    });
    public FairyGlowLayer(RenderLayerParent<T, M> tmRenderLayerParent) {
        super(tmRenderLayerParent);
    }

    @Override
    public void render(@NotNull PoseStack poseStack, MultiBufferSource multiBufferSource, int i, @NotNull T fairyEntity, float v, float v1, float v2, float v3, float v4, float v5) {
        VertexConsumer vertexconsumer = multiBufferSource.getBuffer(this.renderType(fairyEntity));
        if (fairyEntity.hasDust()) {
            this.getParentModel().renderToBuffer(poseStack, vertexconsumer, 15728640, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        }
    }

    public RenderType renderType(FairyEntity fairyEntity) {
        return RENDER_BY_TYPE.get(fairyEntity.getVariant());
    }
}
