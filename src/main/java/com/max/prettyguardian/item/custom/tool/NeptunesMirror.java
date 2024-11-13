package com.max.prettyguardian.item.custom.tool;

import com.max.prettyguardian.item.PrettyGuardianItem;
import com.max.prettyguardian.item.custom.projectiles.BubbleItem;
import com.max.prettyguardian.worldgen.entity.projectile.BubbleEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;

import java.util.Random;
import java.util.function.Consumer;

public class NeptunesMirror extends Item {

    public static final Random RANDOM = new Random();

    public NeptunesMirror(Properties properties) {
        super(properties.rarity(Rarity.EPIC));
    }

    @Override
    public boolean isFoil(@NotNull ItemStack itemStack) {
        return true;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand interactionHand) {
        ItemStack itemstack = player.getItemInHand(interactionHand);

        player.startUsingItem(interactionHand);
        return InteractionResultHolder.consume(itemstack);
    }

    @Override
    public int getUseDuration(@NotNull ItemStack stack) {
        return 72000;
    }

    @Override
    public void onUseTick(@NotNull Level level, @NotNull LivingEntity livingEntity, @NotNull ItemStack itemStack, int i) {
        if (livingEntity instanceof Player player) {
            float damage = 0.0F;
            BubbleItem bubbleItem = (BubbleItem) PrettyGuardianItem.BUBBLE.get();
            BubbleEntity abstractBubble = bubbleItem.createArrow(level);

            abstractBubble.setOwner(player);

            float random = RANDOM.nextFloat() * 0.4F - 0.2F;
            boolean randBool = RANDOM.nextBoolean();
            boolean randBool1 = RANDOM.nextBoolean();

            Vec3 look = player.getLookAngle();

            // Calculer les coordonnées de décalage à droite
            // Calculer l'angle de rotation en radians
            double angleRadians = Math.atan2(look.z, look.x);

            // Convertir l'angle en degrés
            double angleDegrees = Math.toDegrees(angleRadians);

            // Ajouter un décalage de 90 degrés pour obtenir l'angle de rotation dans le référentiel Minecraft
            angleDegrees += 90.0;

            float distance = 0.3F;

            // Calculer les coordonnées de décalage à droite
            double offsetX = Math.cos(Math.toRadians(angleDegrees)) * distance;
            double offsetZ = Math.sin(Math.toRadians(angleDegrees)) * distance;

            // Positionner la bulle devant le joueur et un peu à sa droite
            abstractBubble.setPos(player.getX() + offsetX, player.getY() + 1.2f, player.getZ() + offsetZ);

            // shoot the bubble in a random direction
            if (random > 0.05F || random < -0.05F) {
                if (randBool) {
                    abstractBubble.shoot(look.x + random, look.y + 0.05F, look.z, 3.0F, 1.0F);
                } else if (randBool1) {
                    abstractBubble.shoot(look.x, look.y + 0.05F, look.z + random, 3.0F, 1.0F);
                } else {
                    abstractBubble.shoot(look.x, look.y + random < 0 ? (0.05F + random) : random, look.z, 3.0F, 1.0F);
                }
            } else {
                abstractBubble.shoot(look.x, look.y + 0.05F, look.z, 3.0F, 1.0F);
            }
            level.addFreshEntity(abstractBubble);
        }


        super.onUseTick(level, livingEntity, itemStack, i);
    }

    @Override
    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack stack) {
        return UseAnim.NONE;
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {

            private static final HumanoidModel.ArmPose EXAMPLE_POSE = HumanoidModel.ArmPose.create("EXAMPLE", false, (model, entity, arm) -> {
                NeptunesMirror.animateHands(model, entity, arm == HumanoidArm.LEFT);
            });

            @Override
            public HumanoidModel.ArmPose getArmPose(LivingEntity entityLiving, InteractionHand hand, ItemStack itemStack) {
                if (!itemStack.isEmpty()) {
                    if (entityLiving.getUsedItemHand() == hand && entityLiving.getUseItemRemainingTicks() > 0) {
                        return EXAMPLE_POSE;
                    }
                }
                return HumanoidModel.ArmPose.EMPTY;
            }

            @Override
            public boolean applyForgeHandTransform(PoseStack poseStack, LocalPlayer player, HumanoidArm arm, ItemStack itemInHand, float partialTick, float equipProcess, float swingProcess) {
                int i = arm == HumanoidArm.RIGHT ? 1 : -1;

                poseStack.translate(i * 0.56F, -0.52F, -0.72F);

                if (player.isUsingItem() && player.getUseItemRemainingTicks() > 0 && player.getUseItem() == itemInHand) {
                    float timeLeft = itemInHand.getUseDuration() - (player.getUseItemRemainingTicks() - partialTick + 1.0F);
                    float f12 = 1;

                    float f15 = Mth.sin((timeLeft - 0.1F) * 1.3F);
                    float f18 = f12 - 0.1F;
                    float f20 = f15 * f18;
                    poseStack.translate(0, f20 * 0.004F, 0);

                    poseStack.translate(0, 0, f12 * 0.04F);
                    poseStack.scale(1.0F, 1.0F, 1.0F + f12 * 0.2F);
                }

                return true;
            }
        });

        super.initializeClient(consumer);
    }

    private static void animateHands(HumanoidModel<?> model, LivingEntity entity, boolean leftHand) {

        ModelPart mainHand = leftHand ? model.leftArm : model.rightArm;
        int dir = (leftHand ? -1 : 1);

        float cr = (entity.isCrouching() ? 0.3F : 0.0F);

        float headXRot = wrapRad(model.head.xRot);
        float headYRot = wrapRad(model.head.yRot);

        float pitch = Mth.clamp(headXRot, -1.6f, 0.8f) + 0.55f - cr;
        mainHand.xRot = (float) (pitch - Math.PI / 2f) - dir * 0.3f * headYRot;

        float yaw = 0.7f * dir;
        mainHand.yRot = Mth.clamp(-yaw * Mth.cos(pitch + cr) + headYRot, -1.1f, 1.1f);
        mainHand.zRot = -yaw * Mth.sin(pitch + cr);

        AnimationUtils.bobModelPart(mainHand, entity.tickCount, -dir);
    }

    private static float wrapRad(float pValue) {
        float p = 6.2831855F;
        float d0 = pValue % p;
        if (d0 >= Math.PI) {
            d0 -= p;
        }

        if (d0 < -3.141592653589793) {
            d0 += p;
        }

        return d0;
    }
}
