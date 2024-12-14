package com.max.prettyguardian.entity.client.butterfly;

import com.max.prettyguardian.PrettyGuardian;
import com.max.prettyguardian.entity.client.ModModelLayers;
import com.max.prettyguardian.entity.custom.ButterflyEntity;
import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;
import java.util.Map;

public class ButterflyRenderer extends MobRenderer<ButterflyEntity, ButterflyModel<ButterflyEntity>> {
    private static final Map<ButterflyEntity.Variant, ResourceLocation> TEXTURE_BY_TYPE = Util.make(Maps.newHashMap(), variantResourceLocationHashMap -> {
        for(ButterflyEntity.Variant butterfly$variant : ButterflyEntity.Variant.values()) {
            variantResourceLocationHashMap.put(butterfly$variant, ResourceLocation.fromNamespaceAndPath(PrettyGuardian.MOD_ID, String.format(Locale.ROOT, "textures/entity/butterfly/%s_butterfly.png", butterfly$variant.getName())));
        }

    });

    public ButterflyRenderer(EntityRendererProvider.Context context) {
        super(context, new ButterflyModel<>(context.bakeLayer(ModModelLayers.BUTTERFLY_LAYER)), 0.25f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ButterflyEntity butterflyEntity) {
        return TEXTURE_BY_TYPE.get(butterflyEntity.getVariant());
    }

    @Override
    public void render(ButterflyEntity butterflyEntity, float v, float v1, @NotNull PoseStack poseStack, @NotNull MultiBufferSource multiBufferSource, int i) {
        if (butterflyEntity.isBaby()) {
            poseStack.scale(0.6f, 0.6f, 0.6f);
        }

        super.render(butterflyEntity, v, v1, poseStack, multiBufferSource, i);
    }
}
