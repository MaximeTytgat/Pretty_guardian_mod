package com.max.prettyguardian.client.renderer.entity.projectiles;


import com.max.prettyguardian.PrettyGuardian;
import com.max.prettyguardian.worldgen.entity.projectile.HeartEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;


public class HeartRenderer extends EntityRenderer<HeartEntity> {
    private static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(PrettyGuardian.MOD_ID, "textures/entity/projectiles/heart.png");
    private static final RenderType RENDER_TYPE = RenderType.entityCutoutNoCull(TEXTURE_LOCATION);

    public HeartRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull HeartEntity heartEntity) {
        return TEXTURE_LOCATION;
    }

    protected int getBlockLightLevel(@NotNull HeartEntity heartEntity, @NotNull BlockPos blockPos) {
        return 15;
    }

    public void render(@NotNull HeartEntity heartEntity, float v, float v1, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        poseStack.pushPose();
        poseStack.scale(2.0F, 2.0F, 2.0F);
        poseStack.mulPose(this.entityRenderDispatcher.cameraOrientation());
        poseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
        PoseStack.Pose last = poseStack.last();
        Matrix4f matrix4f = last.pose();
        VertexConsumer vertexconsumer = multiBufferSource.getBuffer(RENDER_TYPE);
        vertex(vertexconsumer, matrix4f, last, i, 0.0F, 0, 0, 1);
        vertex(vertexconsumer, matrix4f, last, i, 1.0F, 0, 1, 1);
        vertex(vertexconsumer, matrix4f, last, i, 1.0F, 1, 1, 0);
        vertex(vertexconsumer, matrix4f, last, i, 0.0F, 1, 0, 0);
        poseStack.popPose();
        super.render(heartEntity, v, v1, poseStack, multiBufferSource, i);
    }

    private static void vertex(VertexConsumer consumer, Matrix4f f, PoseStack.Pose pose, int p253829, float v, int i, int i1, int i2) {
        consumer.vertex(f, v - 0.5F, i - 0.25F, 0.0F).color(255, 255, 255, 255).uv(i1, i2).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(p253829).normal(pose, 0.0F, 1.0F, 0.0F).endVertex();
    }
}