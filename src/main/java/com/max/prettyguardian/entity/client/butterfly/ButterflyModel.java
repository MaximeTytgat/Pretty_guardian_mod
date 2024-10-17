package com.max.prettyguardian.entity.client.butterfly;

import com.max.prettyguardian.entity.animations.ModAnimationDefinitions;
import com.max.prettyguardian.entity.custom.ButterflyEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

public class ButterflyModel<T extends Entity> extends HierarchicalModel<T> {
	private final ModelPart butterfly;
	public ButterflyModel(ModelPart root) {
		this.butterfly = root.getChild("body");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 8).addBox(-0.5F, -0.5F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 23.0F, 0.0F));

		PartDefinition antennas = body.addOrReplaceChild("antennas", CubeListBuilder.create(), PartPose.offset(0.0F, -0.5F, -1.5F));

		PartDefinition antenna_left = antennas.addOrReplaceChild("antenna_left", CubeListBuilder.create().texOffs(2, 30).addBox(-0.5F, 0.005F, -2.0F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 0.0F, 0.0F));

		PartDefinition antenna_right = antennas.addOrReplaceChild("antenna_right", CubeListBuilder.create().texOffs(-2, 30).addBox(-1.5F, 0.005F, -2.0F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 0.0F, 0.0F));

		PartDefinition wings = body.addOrReplaceChild("wings", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition wing_left = wings.addOrReplaceChild("wing_left", CubeListBuilder.create().texOffs(-1, 23).addBox(0.0F, 0.0F, -4.5F, 6.0F, 0.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 0.0F, 0.0F));

		PartDefinition wing_right = wings.addOrReplaceChild("wing_right", CubeListBuilder.create().texOffs(-1, 0).addBox(-6.0F, 0.0F, -4.5F, 6.0F, 0.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 0.0F, 0.0F));

		PartDefinition foots = body.addOrReplaceChild("foots", CubeListBuilder.create(), PartPose.offset(0.0F, 0.5F, 0.0F));

		PartDefinition foots_left = foots.addOrReplaceChild("foots_left", CubeListBuilder.create().texOffs(4, 9).addBox(0.0F, -0.5F, -2.0F, 0.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 0.0F, 0.0F));

		PartDefinition foots_right = foots.addOrReplaceChild("foots_right", CubeListBuilder.create().texOffs(4, 9).addBox(0.0F, -0.5F, -2.0F, 0.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		this.animateWalk(ModAnimationDefinitions.BUTTERFLY_FLY, limbSwing, limbSwingAmount, 2f, 2.5f);
		this.animate(ButterflyEntity.idleAnimationState, ModAnimationDefinitions.BUTTERFLY_IDLE, ageInTicks, 1F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		butterfly.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return this.butterfly;
	}
}