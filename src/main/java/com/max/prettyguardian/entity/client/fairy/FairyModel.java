package com.max.prettyguardian.entity.client.fairy;

import com.max.prettyguardian.entity.animations.ModAnimationDefinitions;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.NotNull;

public class FairyModel<T extends Entity> extends HierarchicalModel<T> {
	private final ModelPart fairy;

	public FairyModel(ModelPart root) {
		this.fairy = root.getChild("body");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 22.0F, 0.0F));

		body.addOrReplaceChild("left_wing", CubeListBuilder.create().texOffs(4, 9).addBox(-3.0F, -1.5F, 0.0F, 4.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, -0.5F, 0.0F));

		body.addOrReplaceChild("right_wing", CubeListBuilder.create().texOffs(4, 13).addBox(-1.0F, -1.5F, 0.0F, 4.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -0.5F, 0.0F));

		body.addOrReplaceChild("left_antenna", CubeListBuilder.create().texOffs(12, -3).mirror().addBox(0.0F, -2.5F, -2.5F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-1.0F, -1.5F, -1.5F));

		body.addOrReplaceChild("right_antenna", CubeListBuilder.create().texOffs(18, -3).mirror().addBox(0.0F, -2.5F, -2.5F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(1.0F, -1.5F, -1.5F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}


	@Override
	public void setupAnim(@NotNull T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		this.animateWalk(ModAnimationDefinitions.FAIRY_FLY, limbSwing, limbSwingAmount, 2f, 2.5f);
	}

	@Override
	public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		fairy.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public @NotNull ModelPart root() {
		return this.fairy;
	}
}