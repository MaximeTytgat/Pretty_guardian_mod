package com.max.prettyguardian.entity.client.celestialrabbit;

import com.max.prettyguardian.PrettyGuardian;
import com.max.prettyguardian.entity.custom.CelestialRabbitEntity;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.Util;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiFunction;

public class CelestialRabbitFlameLayer<T extends CelestialRabbitEntity, M extends CelestialRabbitModel<T>> extends RenderLayer<T, M> {
    protected static final RenderStateShard.TransparencyStateShard TRANSLUCENT_TRANSPARENCY = new RenderStateShard.TransparencyStateShard("translucent_transparency", () -> {
        RenderSystem.enableBlend();
        RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
    }, () -> {
        RenderSystem.disableBlend();
        RenderSystem.defaultBlendFunc();
    });
    protected static final RenderStateShard.OverlayStateShard OVERLAY = new RenderStateShard.OverlayStateShard(true);
    protected static final RenderStateShard.CullStateShard NO_CULL = new RenderStateShard.CullStateShard(false);
    protected static final RenderStateShard.WriteMaskStateShard COLOR_WRITE = new RenderStateShard.WriteMaskStateShard(true, false);
    protected static final RenderStateShard.ShaderStateShard RENDERTYPE_ENTITY_TRANSLUCENT_EMISSIVE_SHADER = new RenderStateShard.ShaderStateShard(GameRenderer::getRendertypeEntityTranslucentEmissiveShader);
    private static final BiFunction<ResourceLocation, Boolean, RenderType>  ENTITY_TRANSLUCENT_EMISSIVE = Util.memoize((resourceLocation, b) -> {
        RenderType.CompositeState compositeState = RenderType.CompositeState.builder().setShaderState(RENDERTYPE_ENTITY_TRANSLUCENT_EMISSIVE_SHADER).setTextureState(new RenderStateShard.TextureStateShard(resourceLocation, false, false)).setTransparencyState(TRANSLUCENT_TRANSPARENCY).setCullState(NO_CULL).setWriteMaskState(COLOR_WRITE).setOverlayState(OVERLAY).createCompositeState(b);
        return RenderType.create("entity_translucent_emissive", DefaultVertexFormat.NEW_ENTITY, VertexFormat.Mode.QUADS, 256, true, true, compositeState);
    });
    public CelestialRabbitFlameLayer(RenderLayerParent<T, M> renderLayerParent) {
        super(renderLayerParent);
    }

    @Override
    public void render(@NotNull PoseStack poseStack, @NotNull MultiBufferSource multiBufferSource, int i, T celestialRabbitEntity, float v, float v1, float v2, float v3, float v4, float v5) {
        if (!celestialRabbitEntity.isBaby() && !celestialRabbitEntity.isInvisible() && celestialRabbitEntity.level().isClientSide) {
            int animationTicks = celestialRabbitEntity.tickCount;

            DyeColor dyeColor = celestialRabbitEntity.getCollarColor();

            ResourceLocation[] flameFrames;
            if (celestialRabbitEntity.isTame()) {
                flameFrames = getFlameFrames(dyeColor);
            } else {
                flameFrames = getFlameFrames(DyeColor.PINK);
            }

            ResourceLocation flameTexture = switch (animationTicks % 16) {
                case 2, 3 -> flameFrames[1];
                case 4, 5 -> flameFrames[2];
                case 6, 7 -> flameFrames[3];
                case 8, 9 -> flameFrames[4];
                case 10, 11 -> flameFrames[5];
                case 12, 13 -> flameFrames[6];
                case 14, 15 -> flameFrames[7];
                default -> flameFrames[0];
            };

            poseStack.pushPose();
            poseStack.popPose();

            VertexConsumer vertexConsumer = multiBufferSource.getBuffer(ENTITY_TRANSLUCENT_EMISSIVE.apply(flameTexture, true));
            this.getParentModel().renderToBuffer(poseStack, vertexConsumer, i, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1F);
        }
    }

    private static ResourceLocation[] getFlameFrames(DyeColor color) {
        ResourceLocation[] frames = new ResourceLocation[8];
        for (int frame = 0; frame < 8; frame++) {
            ResourceLocation resourceLocation = ResourceLocation.fromNamespaceAndPath(PrettyGuardian.MOD_ID, "textures/entity/rabbit/celestial/" + color.getName() + "_flame_" + frame + ".png");
            frames[frame] = resourceLocation;
        }
        return  frames;
    }
}
