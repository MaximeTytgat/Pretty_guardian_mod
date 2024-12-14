package com.max.prettyguardian.entity.client.celestialrabbit;

import com.max.prettyguardian.PrettyGuardian;
import com.max.prettyguardian.client.ClientPlayerEntityOnShoulderData;
import com.max.prettyguardian.entity.client.ModModelLayers;
import com.max.prettyguardian.entity.custom.CelestialRabbitEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;


public class CelestialRabbitOnShoulderLayer<T extends Player> extends RenderLayer<T, PlayerModel<T>> {
    private static final ResourceLocation CELESTIAL_RABBIT_LOCATION = ResourceLocation.fromNamespaceAndPath(PrettyGuardian.MOD_ID, "textures/entity/rabbit/celestial/celestial_rabbit.png");
    private final CelestialRabbitModel<CelestialRabbitEntity> model;

    public CelestialRabbitOnShoulderLayer(RenderLayerParent<T, PlayerModel<T>> parent/*, EntityModelSet modelSet*/) {
        super(parent);
        this.model = new CelestialRabbitModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModModelLayers.CELESTIAL_RABBIT_LAYER));
    }

    public void render(@NotNull PoseStack poseStack, @NotNull MultiBufferSource bufferSource, int light, @NotNull T player, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        this.render(poseStack, bufferSource, light, player, netHeadYaw, headPitch);
    }

    private void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, T player, float netHeadYaw, float headPitch) {
        if(ClientPlayerEntityOnShoulderData.hasEntityOnShoulder(player.getStringUUID())) {

            poseStack.pushPose();
            poseStack.translate(0.4F, player.isCrouching() ? -1.3F : -1.5F, 0.0F);
            poseStack.scale(0.75f, 0.75f, 0.75f);
            poseStack.translate(0, -1.5, 0);

            VertexConsumer vertexConsumer = bufferSource.getBuffer(this.model.renderType(CELESTIAL_RABBIT_LOCATION)/*.renderType(ParrotRenderer.getVariantTexture(variant))*/);
            this.model.renderOnShoulder(poseStack, vertexConsumer, light, OverlayTexture.NO_OVERLAY, netHeadYaw, headPitch);

            poseStack.popPose();
        }
    }
}