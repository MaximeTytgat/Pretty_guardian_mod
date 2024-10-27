package com.max.prettyguardian.entity.client.celestialrabbit;

import com.max.prettyguardian.entity.animations.ModAnimationDefinitions;
import com.max.prettyguardian.entity.custom.CelestialRabbitEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.NotNull;


public class CelestialRabbitModel<T extends Entity> extends HierarchicalModel<T> {
	public static final String LEG_FRONT = "leg_front";
	public static final String LEG_BACK = "leg_back";
	public static final String TAIL_2 = "tail2";
	public static final String TAIL_3 = "tail3";
	private final ModelPart celestialRabbit;
	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart leg0;
	private final ModelPart leg1;
	private final ModelPart leg2;
	private final ModelPart leg3;
	private final ModelPart tail;
	private final ModelPart tail2;
	private final ModelPart tail3;
	private final ModelPart tail4;

//	private final String

	public final ModelPart flame;




	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(28, 0).addBox(-3.0F, -3.0F, -6.0F, 6.0F, 6.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 17).addBox(-4.0F, -3.0F, -6.0F, 8.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, -6.0F));

		head.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(16, 30).addBox(-2.0F, -0.955F, -2.0F, 4.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, -6.0F));

		head.addOrReplaceChild("small_ear_left", CubeListBuilder.create().texOffs(24, 49).addBox(-1.0F, -2.0F, -0.5F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -3.0F, -3.5F));

		head.addOrReplaceChild("small_ear_right", CubeListBuilder.create().texOffs(24, 49).mirror().addBox(-1.0F, -2.0F, -0.5F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-3.0F, -3.0F, -3.5F));

		PartDefinition earLeft = head.addOrReplaceChild("ear_left", CubeListBuilder.create().texOffs(12, 39).addBox(-0.005F, -1.5F, -2.0F, 2.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -1.5F, -3.0F));

		earLeft.addOrReplaceChild("ear_left2", CubeListBuilder.create().texOffs(44, 29).addBox(-1.0F, 0.0F, -2.25F, 2.0F, 7.0F, 4.5F, new CubeDeformation(0.0F))
				.texOffs(0, 30).addBox(-1.5F, 2.0F, -2.75F, 3.0F, 1.0F, 5.5F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 3.5F, 0.0F));

		PartDefinition earRight = head.addOrReplaceChild("ear_right", CubeListBuilder.create().texOffs(12, 39).mirror().addBox(-1.995F, -1.5F, -2.0F, 2.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-4.0F, -1.5F, -3.0F));

		earRight.addOrReplaceChild("ear_right2", CubeListBuilder.create().texOffs(44, 29).mirror().addBox(-1.0F, 0.0F, -2.25F, 2.0F, 7.0F, 4.5F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(0, 30).addBox(-1.5F, 2.0F, -2.75F, 3.0F, 1.0F, 5.5F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 3.5F, 0.0F));

		head.addOrReplaceChild("flame", CubeListBuilder.create().texOffs(54, 48).addBox(-2.5F, -5.0F, 0.0F, 5.0F, 5.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(54, 43).addBox(0.0F, -5.0F, -2.5F, 0.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, -2.5F));

		PartDefinition legs = body.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, -0.5F));

		PartDefinition legFront = legs.addOrReplaceChild(LEG_FRONT, CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, -3.5F));

		legFront.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(0, 49).addBox(-0.005F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 0.0F, 0.0F));

		legFront.addOrReplaceChild("leg3", CubeListBuilder.create().texOffs(36, 39).addBox(-1.995F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 0.0F, 0.0F));

		PartDefinition legBack = legs.addOrReplaceChild(LEG_BACK, CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, 3.5F));

		legBack.addOrReplaceChild("leg0", CubeListBuilder.create().texOffs(16, 49).addBox(-0.005F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 0.0F, 0.0F));

		legBack.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(8, 49).addBox(-1.995F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 0.0F, 0.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(48, 17).addBox(-2.0F, 0.0F, -1.75F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 4.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition tail2 = tail.addOrReplaceChild(TAIL_2, CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, 0.0F, -3.5F, 7.0F, 9.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.0F, 0.25F));

		PartDefinition tail3 = tail2.addOrReplaceChild(TAIL_3, CubeListBuilder.create().texOffs(28, 17).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 8.0F, 0.0F));

		 tail3.addOrReplaceChild("tail4", CubeListBuilder.create().texOffs(24, 39).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	public CelestialRabbitModel(ModelPart root) {
		this.celestialRabbit = root.getChild("root");
		this.body = this.celestialRabbit.getChild("body");
		this.head = this.celestialRabbit.getChild("body").getChild("head");
		this.leg0 = this.celestialRabbit.getChild("body").getChild("legs").getChild(LEG_BACK).getChild("leg0");
		this.leg1 = this.celestialRabbit.getChild("body").getChild("legs").getChild(LEG_BACK).getChild("leg1");
		this.leg2 = this.celestialRabbit.getChild("body").getChild("legs").getChild(LEG_FRONT).getChild("leg2");
		this.leg3 = this.celestialRabbit.getChild("body").getChild("legs").getChild(LEG_FRONT).getChild("leg3");
		this.tail = this.celestialRabbit.getChild("body").getChild("tail");
		this.tail2 = this.celestialRabbit.getChild("body").getChild("tail").getChild(TAIL_2);
		this.tail3 = this.celestialRabbit.getChild("body").getChild("tail").getChild(TAIL_2).getChild(TAIL_3);
		this.tail4 = this.celestialRabbit.getChild("body").getChild("tail").getChild(TAIL_2).getChild(TAIL_3).getChild("tail4");
		this.flame = this.head.getChild("flame");
	}

	@Override
	public void setupAnim(@NotNull T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.setupAnim(getState((CelestialRabbitEntity) entity), (CelestialRabbitEntity) entity, limbSwing, limbSwingAmount, ageInTicks);
	}

	public void renderOnShoulder(PoseStack poseStack, VertexConsumer vertexConsumer, int i, int i1, float v, float v1, float v2, float v3, int i2) {
		this.setupAnim(i2, v, v1, 0.0F, v2, v3);
		celestialRabbit.render(poseStack, vertexConsumer, i, i1);
	}

	private void setupAnim(CelestialRabbitModel.State state, CelestialRabbitEntity celestialRabbit, float limbSwing, float limbSwingAmount, float ageInTicks) {
		switch (state) {
			case SITTING:
				this.animate(celestialRabbit.sitAnimationState, ModAnimationDefinitions.CELESTIAL_RABBIT_SIT, ageInTicks, 1F);
				break;
			case FLYING:
				this.animateWalk(ModAnimationDefinitions.CELESTIAL_RABBIT_WALK, limbSwing, limbSwingAmount, 1f, 2.5F);
				break;
			default:
				this.animate(celestialRabbit.idleAnimationState, ModAnimationDefinitions.CELESTIAL_RABBIT_IDLE, ageInTicks, 1F);
				break;
		}

	}

	private void setupAnim(int tickCount, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.xRot = headPitch * 0.017453292F;
        this.head.yRot = netHeadYaw * 0.017453292F;

        this.body.x = 1F;
        this.body.y = 30F;
        this.body.z = 0F;

        this.body.xRot = -0.2f;

        this.leg0.xRot = 0.5f;
        this.leg1.xRot = 0.5f;
        this.leg2.xRot = 0.5f;
        this.leg3.xRot = 0.5f;

        this.tail.xRot = 1.1F;
        this.tail2.xRot = -0.2F;
        this.tail3.xRot = -0.2F;
        this.tail4.xRot = -0.1F;
    }

	private static State getState(CelestialRabbitEntity celestialRabbit) {
		if (celestialRabbit.isInSittingPose()) {
			return State.SITTING;
		} else {
			return celestialRabbit.walkAnimation.isMoving() ? State.FLYING : State.IDLE;
		}
	}

	@Override
	public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		celestialRabbit.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public @NotNull ModelPart root() {
		return celestialRabbit;
	}

	public enum State {
		FLYING,
		IDLE,
		SITTING,
		ON_SHOULDER;

		State() {
		}
	}
}