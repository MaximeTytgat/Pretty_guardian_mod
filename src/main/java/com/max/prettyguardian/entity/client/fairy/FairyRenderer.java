package com.max.prettyguardian.entity.client.fairy;

import com.max.prettyguardian.PrettyGuardian;
import com.max.prettyguardian.entity.client.ModModelLayers;
import com.max.prettyguardian.entity.custom.FairyEntity;
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

public class FairyRenderer extends MobRenderer<FairyEntity, FairyModel<FairyEntity>> {

    private static final Map<FairyEntity.Variant, ResourceLocation> TEXTURE_BY_TYPE = Util.make(Maps.newHashMap(), variantResourceLocationHashMap -> {
        for(FairyEntity.Variant fairy$variant : FairyEntity.Variant.values()) {
            variantResourceLocationHashMap.put(fairy$variant, ResourceLocation.fromNamespaceAndPath(PrettyGuardian.MOD_ID, String.format(Locale.ROOT, "textures/entity/fairy/fairy_%s.png", fairy$variant.getName())));
        }
    });

    public FairyRenderer(EntityRendererProvider.Context context) {
        super(context, new FairyModel<>(context.bakeLayer(ModModelLayers.FAIRY_LAYER)), 0.25f);
        this.addLayer(new FairyGlowLayer<>(this));
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(FairyEntity fairyEntity) {
        return TEXTURE_BY_TYPE.get(fairyEntity.getVariant());
    }

    @Override
    public void render(FairyEntity fairyEntity, float v, float v1, @NotNull PoseStack poseStack, @NotNull MultiBufferSource multiBufferSource, int i) {
        if (fairyEntity.isBaby()) {
            poseStack.scale(0.6f, 0.6f, 0.6f);
        }

        super.render(fairyEntity, v, v1, poseStack, multiBufferSource, i);
    }
}
