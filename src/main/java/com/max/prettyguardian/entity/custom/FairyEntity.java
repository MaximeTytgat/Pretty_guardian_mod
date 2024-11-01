package com.max.prettyguardian.entity.custom;

import com.max.prettyguardian.entity.ModEntities;
import com.max.prettyguardian.item.PrettyGuardianItem;
import com.max.prettyguardian.item.custom.tool.ButterflyNetItem;
import com.max.prettyguardian.particle.ModParticles;
import com.max.prettyguardian.sound.ModSounds;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.*;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.function.IntFunction;

public class FairyEntity extends Animal implements FlyingAnimal, VariantHolder<FairyEntity.Variant> {
    private static final EntityDataAccessor<Integer> DATA_VARIANT = SynchedEntityData.defineId(FairyEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_REMAINING_TIME_BEFORE_DUST = SynchedEntityData.defineId(FairyEntity.class, EntityDataSerializers.INT);
    private static final UniformInt DUST_TIME = TimeUtil.rangeOfSeconds(40, 60);


    public FairyEntity(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new FairyEntity.FairyMoveControl(this);
    }

    public static final AnimationState idleAnimationState = new AnimationState();

    @Override
    public boolean isPushable() { return false; }

    @Override
    protected void doPush(@NotNull Entity entity) {}

    @Override
    protected void pushEntities() {}

    @Override
    public float getWalkTargetValue(@NotNull BlockPos blockPos) {
        return 0.0F;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(5, new FairyEntity.RandomFloatAroundGoal(this));
        this.goalSelector.addGoal(5, new FairyEntity.GhastLookGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.FLYING_SPEED, 0.7D)
                .add(Attributes.FOLLOW_RANGE, 10.0D);
    }


    @Override
    public SpawnGroupData finalizeSpawn(@NotNull ServerLevelAccessor serverLevelAccessor, @NotNull DifficultyInstance difficultyInstance, @NotNull MobSpawnType mobSpawnType, @Nullable SpawnGroupData spawnGroupData) {
        boolean flag = false;
        if (mobSpawnType == MobSpawnType.BUCKET) {
            return spawnGroupData;
        } else {
            RandomSource randomsource = serverLevelAccessor.getRandom();
            if (spawnGroupData instanceof FairyGroupData fairyGroupData) {
                if (fairyGroupData.getGroupSize() >= 2) {
                    flag = true;
                }
            } else {
                spawnGroupData = new FairyGroupData(Variant.getSpawnVariant(randomsource), Variant.getSpawnVariant(randomsource));
            }

            this.setVariant(((FairyGroupData)spawnGroupData).getVariant(randomsource));
            if (flag) {
                this.setAge(-24000);
            }
            ((FairyGroupData) spawnGroupData).getVariant(randomsource);

            return super.finalizeSpawn(serverLevelAccessor, difficultyInstance, mobSpawnType, spawnGroupData);
        }
    }

    public boolean hasDust() {
        return this.getRemainingTimeBeforeDust() == 0;
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide && this.getRemainingTimeBeforeDust() > 0) {
            this.setRemainingTimeBeforeDust(this.getRemainingTimeBeforeDust() - 1);
        }

        if (this.level().isClientSide) {
            setupAnimationStates();
        }

        if (this.hasDust()) {
            FairyEntity.Variant variant = this.getVariant();
            ParticleOptions particleOptions;
            if (variant == FairyEntity.Variant.PINK) {
                particleOptions = ModParticles.PINK_FAIRY_PARTICLES.get();
            } else {
                particleOptions = ModParticles.BLUE_FAIRY_PARTICLES.get();
            }

            if (this.random.nextFloat() < 0.05F) {
                for(int i = 0; i < this.random.nextInt(2) + 1; ++i) {
                    this.spawnFluidParticle(this.level(), this.getX() - 0.30000001192092896, this.getX() + 0.30000001192092896, this.getZ() - 0.30000001192092896, this.getZ() + 0.30000001192092896, this.getY(0.5), particleOptions);
                }
            }

            if (this.level().getDayTime() % 100 == 0 && this.random.nextFloat() < 0.7F) {
                this.level().playSound(this, this.blockPosition(), ModSounds.FAIRY.get(), this.getSoundSource(), 0.35F, 1F);
            }
        }
    }

    private void spawnFluidParticle(Level level, double v, double v1, double v2, double v3, double v4, ParticleOptions particleOptions) {
        level.addParticle(particleOptions, Mth.lerp(level.random.nextDouble(), v, v1), v4, Mth.lerp(level.random.nextDouble(), v2, v3), 0.0, 0.0, 0.0);
    }


    private void setupAnimationStates() {
        if (this.walkAnimation.isMoving()) {
            idleAnimationState.stop();
        } else if (!idleAnimationState.isStarted()) {
            idleAnimationState.start(40);
        }
    }

    @Override
    protected void updateWalkAnimation(float v) {
        float f;
        if (this.getPose() == Pose.STANDING) {
            f = Math.min(v * 6, 1f);
        } else {
            f = 0;
        }

        this.walkAnimation.update(f, 0.2f);
    }

    @Override
    protected @NotNull PathNavigation createNavigation(@NotNull Level level) {
        FlyingPathNavigation flyingpathnavigation = new FlyingPathNavigation(this, level) {
            @Override
            public boolean isStableDestination(BlockPos blockPos) {
                return !this.level.getBlockState(blockPos.below()).isAir();
            }
        };
        flyingpathnavigation.setCanOpenDoors(false);
        flyingpathnavigation.setCanFloat(false);
        flyingpathnavigation.setCanPassDoors(true);
        return flyingpathnavigation;
    }

    @Override
    public @NotNull InteractionResult mobInteract(Player player, @NotNull InteractionHand interactionHand) {
        ItemStack itemstack = player.getItemInHand(interactionHand);

        if (itemstack.getItem() instanceof ButterflyNetItem && !this.isBaby() && !this.level().isClientSide && this.hasDust()) {
            this.startDustTime();
            ItemStack fairyDust = new ItemStack(PrettyGuardianItem.FAIRY_DUST.get());
            player.addItem(fairyDust);
            return InteractionResult.sidedSuccess(this.level().isClientSide);
        }


        return super.mobInteract(player, interactionHand);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(@NotNull ServerLevel serverLevel, @NotNull AgeableMob ageableMob) {
        return ModEntities.FAIRY.get().create(serverLevel);
    }

    @Override
    public boolean isFlying() {
        return !this.onGround();
    }

    @Override
    protected void defineSynchedData(@NotNull SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_VARIANT, 0);
        builder.define(DATA_REMAINING_TIME_BEFORE_DUST, DUST_TIME.sample(this.random));
    }

    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("Variant", this.getVariant().getId());

    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.setVariant(Variant.byId(tag.getInt("Variant")));
    }

    @Override
    public boolean isFood(@NotNull ItemStack itemStack) {
        return false;
    }


    public int getRemainingTimeBeforeDust() {
        return this.entityData.get(DATA_REMAINING_TIME_BEFORE_DUST);
    }

    public void setRemainingTimeBeforeDust(int i) {
        this.entityData.set(DATA_REMAINING_TIME_BEFORE_DUST, i);
    }

    public void startDustTime() {
        this.setRemainingTimeBeforeDust(DUST_TIME.sample(this.random));
    }

    @Override
    public void setVariant(Variant variant) {
        this.entityData.set(DATA_VARIANT, variant.getId());
    }

    @Override
    public @NotNull Variant getVariant() {
        return Variant.byId(this.entityData.get(DATA_VARIANT));
    }

    public static class FairyGroupData extends AgeableMobGroupData {
        public final Variant[] types;

        public FairyGroupData(Variant... variants) {
            super(false);
            this.types = variants;
        }

        public Variant getVariant(RandomSource randomSource) {
            return this.types[randomSource.nextInt(this.types.length)];
        }
    }

    static class FairyMoveControl extends MoveControl {
        private final FairyEntity fairy;
        private int floatDuration;

        public FairyMoveControl(FairyEntity fairy) {
            super(fairy);
            this.fairy = fairy;
        }

        @Override
        public void tick() {
            if (this.operation == MoveControl.Operation.MOVE_TO && this.floatDuration-- <= 0) {
                this.floatDuration += this.fairy.getRandom().nextInt(5) + 2;
                Vec3 vec3 = new Vec3(this.wantedX - this.fairy.getX(), this.wantedY - this.fairy.getY(), this.wantedZ - this.fairy.getZ());
                double d0 = vec3.length();
                vec3 = vec3.normalize();
                if (this.canReach(vec3, Mth.ceil(d0))) {
                    this.fairy.setDeltaMovement(this.fairy.getDeltaMovement().add(vec3.scale(0.1D)));
                } else {
                    this.operation = MoveControl.Operation.WAIT;
                }
            }
        }

        private boolean canReach(Vec3 vec3, int i1) {
            AABB aabb = this.fairy.getBoundingBox();

            for(int i = 1; i < i1; ++i) {
                aabb = aabb.move(vec3);
                if (!this.fairy.level().noCollision(this.fairy, aabb)) {
                    return false;
                }
            }

            return true;
        }
    }

    @Override
    public void travel(@NotNull Vec3 vec3) {
        if (this.isControlledByLocalInstance()) {
            if (this.isInWater()) {
                this.moveRelative(0.02F, vec3);
                this.move(MoverType.SELF, this.getDeltaMovement());
                this.setDeltaMovement(this.getDeltaMovement().scale(0.8F));
            } else if (this.isInLava()) {
                this.moveRelative(0.02F, vec3);
                this.move(MoverType.SELF, this.getDeltaMovement());
                this.setDeltaMovement(this.getDeltaMovement().scale(0.5D));
            } else {
                BlockPos ground = getBlockPosBelowThatAffectsMyMovement();
                float f = 0.91F;
                if (this.onGround()) {
                    f = this.level().getBlockState(ground).getFriction(this.level(), ground, this) * 0.91F;
                }

                float f1 = 0.16277137F / (f * f * f);
                f = 0.91F;
                if (this.onGround()) {
                    f = this.level().getBlockState(ground).getFriction(this.level(), ground, this) * 0.91F;
                }

                this.moveRelative(this.onGround() ? 0.1F * f1 : 0.02F, vec3);
                this.move(MoverType.SELF, this.getDeltaMovement());
                this.setDeltaMovement(this.getDeltaMovement().scale(f));
            }
        }

        this.calculateEntityAnimation(false);
    }

    @Override
    public boolean onClimbable() {
        return false;
    }

    static class RandomFloatAroundGoal extends Goal {
        private final FairyEntity fairy;

        public RandomFloatAroundGoal(FairyEntity fairy) {
            this.fairy = fairy;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        public boolean canUse() {
            MoveControl movecontrol = this.fairy.getMoveControl();
            if (!movecontrol.hasWanted()) {
                return true;
            } else {
                double d0 = movecontrol.getWantedX() - this.fairy.getX();
                double d1 = movecontrol.getWantedY() - this.fairy.getY();
                double d2 = movecontrol.getWantedZ() - this.fairy.getZ();
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                return d3 < 1.0D || d3 > 3600.0D;
            }
        }

        @Override
        public boolean canContinueToUse() {
            return false;
        }

        @Override
        public void start() {
            RandomSource randomsource = this.fairy.getRandom();

            double d0 = this.fairy.getX() + ((randomsource.nextFloat() * 2.0F - 1.0F) * 16.0F);
            double d1 = this.fairy.getY() + ((randomsource.nextFloat() * 2.0F - 1.0F) * 16.0F);
            double d2 = this.fairy.getZ() + ((randomsource.nextFloat() * 2.0F - 1.0F) * 16.0F);

            BlockPos targetPos = new BlockPos((int) d0, (int) d1, (int) d2);
            BlockState blockState = this.fairy.level().getBlockState(targetPos.below(10));

            if (blockState.is(Blocks.AIR)) {
                d1 = d1 - 10;
            }

            this.fairy.getMoveControl().setWantedPosition(d0, d1, d2, 1.0D);
        }

    }

    static class GhastLookGoal extends Goal {
        private final FairyEntity fairy;

        public GhastLookGoal(FairyEntity fairy) {
            this.fairy = fairy;
            this.setFlags(EnumSet.of(Goal.Flag.LOOK));
        }

        public boolean canUse() {
            return true;
        }

        @Override
        public boolean requiresUpdateEveryTick() {
            return true;
        }

        @Override
        public void tick() {
            if (this.fairy.getTarget() == null) {
                Vec3 vec3 = this.fairy.getDeltaMovement();
                this.fairy.setYRot(-((float)Mth.atan2(vec3.x, vec3.z)) * (180F / (float)Math.PI));
                this.fairy.yBodyRot = this.fairy.getYRot();
            } else {
                LivingEntity livingentity = this.fairy.getTarget();
                if (livingentity.distanceToSqr(this.fairy) < 4096.0D) {
                    double d1 = livingentity.getX() - this.fairy.getX();
                    double d2 = livingentity.getZ() - this.fairy.getZ();
                    this.fairy.setYRot(-((float)Mth.atan2(d1, d2)) * (180F / (float)Math.PI));
                    this.fairy.yBodyRot = this.fairy.getYRot();
                }
            }

        }
    }

    public enum Variant implements StringRepresentable {
        BLUE(0, "blue"),
        PINK(1, "pink");

        private static final IntFunction<Variant> BY_ID = ByIdMap.continuous(Variant::getId, values(), ByIdMap.OutOfBoundsStrategy.ZERO);
        private final int id;
        private final String name;
        Variant(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return this.id;
        }

        public String getName() {
            return this.name;
        }

        @Override
        public @NotNull String getSerializedName() {
            return this.name;
        }

        public static Variant byId(int i) {
            return BY_ID.apply(i);
        }

        private static Variant getSpawnVariant(RandomSource randomSource) {
            Variant[] variants = Arrays.stream(values()).toArray(Variant[]::new);
            return Util.getRandom(variants, randomSource);
        }
    }
}
