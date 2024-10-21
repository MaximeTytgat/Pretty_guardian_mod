package com.max.prettyguardian.blocks.entity.renderer;

import com.max.prettyguardian.blocks.entity.MoonAltarBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class MoonAltarBlockEntityRenderer implements BlockEntityRenderer<MoonAltarBlockEntity> {
    public MoonAltarBlockEntityRenderer(BlockEntityRendererProvider.Context ignoredContext) {}

    @Override
    public void render(
            MoonAltarBlockEntity moonAltarBlockEntity,
            float v,
            @NotNull PoseStack poseStack,
            @NotNull MultiBufferSource multiBufferSource,
            int i,
            int i1
    ) {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        ItemStack itemStack1 = moonAltarBlockEntity.getRenderStack(0);
        ItemStack itemStack2 = moonAltarBlockEntity.getRenderStack(1);
        ItemStack itemStack3 = moonAltarBlockEntity.getRenderStack(2);

        ItemStack outputStack = moonAltarBlockEntity.getRenderStack(3);

       if (outputStack != null && !outputStack.isEmpty()) {
           poseStack.pushPose();
           applyPoseStackForOutputStack(poseStack, moonAltarBlockEntity.getFacingProperty());
           itemRenderer.renderStatic(outputStack, ItemDisplayContext.FIXED, getLightLevel(Objects.requireNonNull(moonAltarBlockEntity.getLevel()), moonAltarBlockEntity.getBlockPos()),
                   OverlayTexture.NO_OVERLAY, poseStack, multiBufferSource, moonAltarBlockEntity.getLevel(), 1);
           poseStack.popPose();
           return;
       }

       if (itemStack1 != null && !itemStack1.isEmpty()) {
           poseStack.pushPose();
           applyPoseStackForItemStack1(poseStack, moonAltarBlockEntity.getFacingProperty());
           itemRenderer.renderStatic(itemStack1, ItemDisplayContext.FIXED, getLightLevel(Objects.requireNonNull(moonAltarBlockEntity.getLevel()), moonAltarBlockEntity.getBlockPos()),
                   OverlayTexture.NO_OVERLAY, poseStack, multiBufferSource, moonAltarBlockEntity.getLevel(), 1);
           poseStack.popPose();
       }

       if (itemStack2 != null && !itemStack2.isEmpty()) {
           poseStack.pushPose();
           applyPoseStackForItemStack2(poseStack, moonAltarBlockEntity.getFacingProperty());
           itemRenderer.renderStatic(itemStack2, ItemDisplayContext.FIXED, getLightLevel(Objects.requireNonNull(moonAltarBlockEntity.getLevel()), moonAltarBlockEntity.getBlockPos()),
                   OverlayTexture.NO_OVERLAY, poseStack, multiBufferSource, moonAltarBlockEntity.getLevel(), 1);
           poseStack.popPose();
       }

       if (itemStack3 != null && !itemStack3.isEmpty()) {
           poseStack.pushPose();
           applyPoseStackForItemStack3(poseStack, moonAltarBlockEntity.getFacingProperty());
           itemRenderer.renderStatic(itemStack3, ItemDisplayContext.FIXED, getLightLevel(Objects.requireNonNull(moonAltarBlockEntity.getLevel()), moonAltarBlockEntity.getBlockPos()),
                   OverlayTexture.NO_OVERLAY, poseStack, multiBufferSource, moonAltarBlockEntity.getLevel(), 1);
           poseStack.popPose();
       }
    }

    private void applyPoseStackForOutputStack(PoseStack poseStack, Direction direction) {
        switch (direction) {
            case Direction.NORTH -> {
                poseStack.mulPose(Axis.YP.rotationDegrees(180));
                poseStack.translate(-0.52f, 1.1f, -0.3f);
                poseStack.scale(0.3f, 0.3f, 0.3f);
            }
            case Direction.EAST -> {
                poseStack.mulPose(Axis.YP.rotationDegrees(90));
                poseStack.translate(-0.52f, 1.1f, 0.7f);
                poseStack.scale(0.3f, 0.3f, 0.3f);
            }
            case Direction.WEST -> {
                poseStack.mulPose(Axis.YP.rotationDegrees(270));
                poseStack.translate(0.48f, 1.1f, -0.3f);
                poseStack.scale(0.3f, 0.3f, 0.3f);
            }
            default -> {
                poseStack.translate(0.48f, 1.1f, 0.7f);
                poseStack.scale(0.3f, 0.3f, 0.3f);
            }
        }
    }

    private void applyPoseStackForItemStack1(PoseStack poseStack, Direction direction) {
        switch (direction) {
            case Direction.NORTH -> {
                poseStack.mulPose(Axis.YP.rotationDegrees(180));
                poseStack.translate(-0.7f, 1.1f, -0.62f);
                poseStack.scale(0.3f, 0.3f, 0.3f);
            }
            case Direction.EAST -> {
                poseStack.mulPose(Axis.YP.rotationDegrees(90));
                poseStack.translate(-0.7f, 1.1f, 0.38f);
                poseStack.scale(0.3f, 0.3f, 0.3f);
            }
            case Direction.WEST -> {
                poseStack.mulPose(Axis.YP.rotationDegrees(270));
                poseStack.translate(0.3f, 1.1f, -0.62f);
                poseStack.scale(0.3f, 0.3f, 0.3f);
            }
            default -> {
                poseStack.translate(0.3f, 1.1f, 0.38f);
                poseStack.scale(0.3f, 0.3f, 0.3f);
            }
        }
    }

    private void applyPoseStackForItemStack2(PoseStack poseStack, Direction direction) {
        switch (direction) {
            case Direction.NORTH -> {
                poseStack.mulPose(Axis.YP.rotationDegrees(180));
                poseStack.translate(-0.35f, 1.1f, -0.62f);
                poseStack.scale(0.3f, 0.3f, 0.3f);
            }
            case Direction.EAST -> {
                poseStack.mulPose(Axis.YP.rotationDegrees(90));
                poseStack.translate(-0.35f, 1.1f, 0.38f);
                poseStack.scale(0.3f, 0.3f, 0.3f);
            }
            case Direction.WEST -> {
                poseStack.mulPose(Axis.YP.rotationDegrees(270));
                poseStack.translate(0.65f, 1.1f, -0.62f);
                poseStack.scale(0.3f, 0.3f, 0.3f);
            }
            default -> {
                poseStack.translate(0.65f, 1.1f, 0.38f);
                poseStack.scale(0.3f, 0.3f, 0.3f);
            }
        }
    }

    private void applyPoseStackForItemStack3(PoseStack poseStack, Direction direction) {
        switch (direction) {
            case Direction.NORTH -> {
                poseStack.mulPose(Axis.YP.rotationDegrees(180));
                poseStack.translate(-0.52f, 1.1f, -0.3f);
                poseStack.scale(0.3f, 0.3f, 0.3f);
            }
            case Direction.EAST -> {
                poseStack.mulPose(Axis.YP.rotationDegrees(90));
                poseStack.translate(-0.52f, 1.1f, 0.7f);
                poseStack.scale(0.3f, 0.3f, 0.3f);
            }
            case Direction.WEST -> {
                poseStack.mulPose(Axis.YP.rotationDegrees(270));
                poseStack.translate(0.48f, 1.1f, -0.3f);
                poseStack.scale(0.3f, 0.3f, 0.3f);
            }
            default -> {
                poseStack.translate(0.48f, 1.1f, 0.7f);
                poseStack.scale(0.3f, 0.3f, 0.3f);
            }
        }
    }

    private int getLightLevel(Level level, BlockPos blockPos) {
        int bLight = level.getBrightness(LightLayer.BLOCK, blockPos);
        int sLight = level.getBrightness(LightLayer.SKY, blockPos);
        return LightTexture.pack(bLight, sLight);
    }
}
