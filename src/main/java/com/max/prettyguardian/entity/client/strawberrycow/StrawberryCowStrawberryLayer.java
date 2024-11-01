package com.max.prettyguardian.entity.client.strawberrycow;

import com.max.prettyguardian.blocks.PrettyGuardianBlock;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.CowModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.MushroomCowMushroomLayer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.entity.animal.MushroomCow;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class StrawberryCowStrawberryLayer<T extends MushroomCow> extends MushroomCowMushroomLayer<T> {
    private final BlockRenderDispatcher blockRenderer;
    public StrawberryCowStrawberryLayer(RenderLayerParent<T, CowModel<T>> renderLayerParent, BlockRenderDispatcher blockRenderDispatcher) {
        super(renderLayerParent, blockRenderDispatcher);
        this.blockRenderer = blockRenderDispatcher;
    }

    @Override
    public void render(@NotNull PoseStack poseStack, @NotNull MultiBufferSource multiBufferSource, int i1, T t, float v, float v1, float v2, float v3, float v4, float v5) {
        if (!t.isBaby()) {
            Minecraft minecraft = Minecraft.getInstance();
            boolean flag = minecraft.shouldEntityAppearGlowing(t) && t.isInvisible();
            if (!t.isInvisible() || flag) {
                BlockState blockstate = PrettyGuardianBlock.STRAWBERRY_CROP_FLOWER.get().defaultBlockState();
                int i = LivingEntityRenderer.getOverlayCoords(t, 0.0F);
                BakedModel bakedmodel = this.blockRenderer.getBlockModel(blockstate);
                poseStack.pushPose();
                poseStack.translate(0.2F, -0.35F, 0.5F);
                poseStack.mulPose(Axis.YP.rotationDegrees(-48.0F));
                poseStack.scale(-0.7F, -0.7F, 0.7F);
                poseStack.translate(-0.5F, -0.7F, -0.5F);
                this.mushroomBlockRender(poseStack, multiBufferSource, i1, flag, blockstate, i, bakedmodel);
                poseStack.popPose();
                poseStack.pushPose();
                poseStack.translate(0.2F, -0.35F, 0.5F);
                poseStack.mulPose(Axis.YP.rotationDegrees(42.0F));
                poseStack.translate(0.1F, 0.0F, -0.6F);
                poseStack.mulPose(Axis.YP.rotationDegrees(-48.0F));
                poseStack.scale(-0.7F, -0.7F, 0.7F);
                poseStack.translate(-0.5F, -0.7F, -0.5F);
                this.mushroomBlockRender(poseStack, multiBufferSource, i1, flag, blockstate, i, bakedmodel);
                poseStack.popPose();
                poseStack.pushPose();
                this.getParentModel().getHead().translateAndRotate(poseStack);
                poseStack.translate(0.0F, -0.7F, -0.2F);
                poseStack.mulPose(Axis.YP.rotationDegrees(-78.0F));
                poseStack.scale(-0.7F, -0.7F, 0.7F);
                poseStack.translate(-0.5F, -0.7F, -0.5F);
                this.mushroomBlockRender(poseStack, multiBufferSource, i1, flag, blockstate, i, bakedmodel);
                poseStack.popPose();
            }
        }
    }

    private void mushroomBlockRender(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, boolean b, BlockState blockState, int i1, BakedModel bakedModel) {
        if (b) {
            this.blockRenderer.getModelRenderer().renderModel(poseStack.last(), multiBufferSource.getBuffer(RenderType.outline(TextureAtlas.LOCATION_BLOCKS)), blockState, bakedModel, 0.0F, 0.0F, 0.0F, i, i1);
        } else {
            this.blockRenderer.renderSingleBlock(blockState, poseStack, multiBufferSource, i, i1);
        }

    }
}
