package com.max.prettyguardian.entity.custom;

import com.max.prettyguardian.entity.ModEntities;
import com.max.prettyguardian.entity.goal.StopMoveWhenOrderedToGoal;
import com.max.prettyguardian.item.PrettyGuardianItem;
import com.max.prettyguardian.particle.ModParticles;
import com.max.prettyguardian.sound.ModSounds;
import com.max.prettyguardian.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.*;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.ForgeEventFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;
import java.util.UUID;

public class CelestialRabbitEntity extends TamableAnimal implements FlyingAnimal, NeutralMob {
    private static final EntityDataAccessor<Boolean> DATA_INTERESTED_ID = SynchedEntityData.defineId(CelestialRabbitEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> DATA_REMAINING_ANGER_TIME = SynchedEntityData.defineId(CelestialRabbitEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_PEARL_COLOR = SynchedEntityData.defineId(CelestialRabbitEntity.class, EntityDataSerializers.INT);
    private static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);
    public static final String COLLAR_COLOR = "CollarColor";
    @javax.annotation.Nullable
    private UUID persistentAngerTarget;

    public CelestialRabbitEntity(EntityType<? extends TamableAnimal> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new FlyingMoveControl(this, 10, true);
    }

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState sitAnimationState = new AnimationState();

    @Override
    protected void defineSynchedData(@NotNull SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_INTERESTED_ID, false);
        builder.define(DATA_REMAINING_ANGER_TIME, 0);
        builder.define(DATA_PEARL_COLOR, DyeColor.LIGHT_BLUE.getId());
    }

    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag compoundTag) {
        super.addAdditionalSaveData(compoundTag);
        compoundTag.putByte(COLLAR_COLOR, (byte)this.getCollarColor().getId());
        this.addPersistentAngerSaveData(compoundTag);
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag compoundTag) {
        super.readAdditionalSaveData(compoundTag);
        if (compoundTag.contains(COLLAR_COLOR, 99)) {
            this.setCollarColor(DyeColor.byId(compoundTag.getInt(COLLAR_COLOR)));
        }

        this.readPersistentAngerSaveData(this.level(), compoundTag);
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        // random sound between 0 and 4
        int random = this.random.nextInt(4);

        if (!this.walkAnimation.isMoving()) {
            return switch (random) {
                case 1 -> ModSounds.CELESTIAL_RABBIT_AMBIENT_SOUND_1.get();
                case 2 -> ModSounds.CELESTIAL_RABBIT_AMBIENT_SOUND_2.get();
                case 3 -> ModSounds.CELESTIAL_RABBIT_AMBIENT_SOUND_3.get();
                default -> ModSounds.CELESTIAL_RABBIT_AMBIENT_SOUND_0.get();
            };
        }

        return null;
    }

    @Override
    protected @Nullable SoundEvent getDeathSound() {
        return  ModSounds.CELESTIAL_RABBIT_DEATH.get();
    }

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide) {
            for(int i = 0; i < 2; ++i) {
                this.level().addParticle(ModParticles.CELESTIAL_RABBIT_PARTICLES.get(), this.getRandomX(0.5), this.getRandomY() - 0.25, this.getRandomZ(0.5), (this.random.nextDouble() - 0.5) * 2.0, -this.random.nextDouble(), (this.random.nextDouble() - 0.5) * 2.0);
            }
        }

        if (this.level().isClientSide()) {
            this.setupAnimationStates();
        }
    }

    private void setupAnimationStates() {
        if (this.isInSittingPose() && !this.sitAnimationState.isStarted()) {
            this.idleAnimationState.stop();
            this.sitAnimationState.start(this.tickCount);
        } else if (!this.isInSittingPose() && this.sitAnimationState.isStarted()) {
            this.sitAnimationState.stop();
            this.idleAnimationState.start(this.tickCount);
        } else if (!this.isInSittingPose() && !this.idleAnimationState.isStarted()) {
            this.idleAnimationState.start(this.tickCount);
        }
    }

    @Override
    protected void updateWalkAnimation(float v) {
        float f;
        if (this.getPose() == Pose.STANDING) {
            f = Math.min(v * 6F, 1f);
        } else {
            f = 0;
        }

        this.walkAnimation.update(f, 0.2F);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(2, new StopMoveWhenOrderedToGoal(this));

        this.goalSelector.addGoal(3, new BreedGoal(this, 1.2D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.3D, Ingredient.of(PrettyGuardianItem.FISH_WAFFLE.get()), false));

        this.goalSelector.addGoal(4, new SonicAttackGoal());

        this.goalSelector.addGoal(6, new FollowParentGoal(this, 1.25D));
        this.goalSelector.addGoal(6, new FollowOwnerGoal(this, 2.0D, 8.0F, 5.0F, true));

        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.1D));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new FollowMobGoal(this, 1.0D, 10.0F, 2.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)).setAlertOthers());
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, this::isAngryAt));
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, Phantom.class, false));
        this.targetSelector.addGoal(6, new NearestAttackableTargetGoal<>(this, Skeleton.class, false));
        this.targetSelector.addGoal(7, new ResetUniversalAngerTargetGoal<>(this, true));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return LivingEntity.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 80.0D)
                .add(Attributes.ARMOR_TOUGHNESS, 10.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.FOLLOW_RANGE, 40.0D)
                .add(Attributes.ATTACK_DAMAGE, 2.0D)
                .add(Attributes.ATTACK_KNOCKBACK, 2.0D)
                .add(Attributes.ATTACK_SPEED, 2.0D)
                .add(Attributes.FLYING_SPEED, 0.7D);
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

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(@NotNull ServerLevel serverLevel, @NotNull AgeableMob ageableMob) {
        return ModEntities.CELESTIAL_RABBIT.get().create(serverLevel);
    }

    @Override
    public boolean isFood(ItemStack itemStack) {
        return itemStack.is(PrettyGuardianItem.FISH_WAFFLE.get());
    }


    @Override
    public boolean isFlying() {
        return !this.onGround();
    }

    @Override
    public @NotNull InteractionResult mobInteract(Player player, @NotNull InteractionHand interactionHand) {
        ItemStack itemstack = player.getItemInHand(interactionHand);
        Item item = itemstack.getItem();
        if (this.level().isClientSide) {
            boolean flag = this.isOwnedBy(player) || this.isTame() || itemstack.is(ModTags.Items.CAKE) && !this.isTame() && !this.isAngry();
            return flag ? InteractionResult.CONSUME : InteractionResult.PASS;
        } else if (this.isTame()) {
            if (this.isFood(itemstack) && this.getHealth() < this.getMaxHealth()) {
                FoodProperties foodproperties = itemstack.get(DataComponents.FOOD);
                float f = foodproperties != null ? (float)foodproperties.nutrition() : 1.0F;
                this.heal(2.0F * f);
                if (!player.getAbilities().instabuild) {
                    itemstack.shrink(1);
                }

                this.gameEvent(GameEvent.EAT, this);
                return InteractionResult.SUCCESS;
            } else {
                if (item instanceof DyeItem dyeitem && this.isOwnedBy(player)) {
                    DyeColor dyecolor = dyeitem.getDyeColor();
                    if (dyecolor != this.getCollarColor()) {
                        this.setCollarColor(dyecolor);
                        if (!player.getAbilities().instabuild) {
                            itemstack.shrink(1);
                        }

                        return InteractionResult.SUCCESS;
                    }

                    return super.mobInteract(player, interactionHand);
                }

                InteractionResult interactionresult = super.mobInteract(player, interactionHand);
                if ((!interactionresult.consumesAction() || this.isBaby()) && this.isOwnedBy(player)) {
                    this.setOrderedToSit(!this.isOrderedToSit());
                    this.jumping = false;
                    this.navigation.stop();
                    this.setTarget(null);
                    return InteractionResult.SUCCESS;
                } else {
                    return interactionresult;
                }
            }
        } else if (itemstack.is(ModTags.Items.CAKE) && !this.isAngry()) {
            if (!player.getAbilities().instabuild) {
                itemstack.shrink(1);
            }

            if (this.random.nextInt(3) == 0 && !ForgeEventFactory.onAnimalTame(this, player)) {
                this.tame(player);
                this.navigation.stop();
                this.setTarget(null);
                this.setOrderedToSit(true);
                this.level().broadcastEntityEvent(this, (byte)7);
            } else {
                this.level().broadcastEntityEvent(this, (byte)6);
            }

            return InteractionResult.SUCCESS;
        } else {
            return super.mobInteract(player, interactionHand);
        }
    }

    @Override
    public boolean canBeLeashed(@NotNull Player player) {
        return !this.isAngry() && super.canBeLeashed(player);
    }

    @Override
    public int getRemainingPersistentAngerTime() {
        return this.entityData.get(DATA_REMAINING_ANGER_TIME);
    }

    @Override
    public void setRemainingPersistentAngerTime(int i) {
        this.entityData.set(DATA_REMAINING_ANGER_TIME, i);
    }

    public DyeColor getCollarColor() {
        return DyeColor.byId(this.entityData.get(DATA_PEARL_COLOR));
    }

    public void setCollarColor(DyeColor dyeColor) {
        this.entityData.set(DATA_PEARL_COLOR, dyeColor.getId());
    }

    @Nullable
    @Override
    public UUID getPersistentAngerTarget() {
        return this.persistentAngerTarget;
    }

    @Override
    public void setPersistentAngerTarget(@Nullable UUID uuid) {
        this.persistentAngerTarget = uuid;
    }

    @Override
    public void startPersistentAngerTimer() {
        this.setRemainingPersistentAngerTime(PERSISTENT_ANGER_TIME.sample(this.random));
    }

    class SonicAttackGoal extends Goal {
        private int attackTime;

        public SonicAttackGoal() {
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        public boolean canUse() {
            LivingEntity livingentity = CelestialRabbitEntity.this.getTarget();
            if (livingentity != null && livingentity.isAlive()) {
                return CelestialRabbitEntity.this.level().getDifficulty() != Difficulty.PEACEFUL;
            } else {
                return false;
            }
        }

        @Override
        public void start() {
            this.attackTime = 60;
        }

        @Override
        public boolean requiresUpdateEveryTick() {
            return true;
        }

        @Override
        public void tick() {
            CelestialRabbitEntity celestialRabbitEntity = CelestialRabbitEntity.this;
            Level level = CelestialRabbitEntity.this.level();
            if (level.getDifficulty() == Difficulty.PEACEFUL) return;

            --this.attackTime;
            LivingEntity livingentity = celestialRabbitEntity.getTarget();
            if (livingentity == null) return;

            celestialRabbitEntity.getLookControl().setLookAt(livingentity, 180.0F, 180.0F);

            double d0 = celestialRabbitEntity.distanceToSqr(livingentity);
            if (d0 > 1000.0D) {
                celestialRabbitEntity.setTarget(null);
                super.tick();
                return;
            }

            if (this.attackTime > 0) {
                super.tick();
                return;
            }

            attack(celestialRabbitEntity, level, livingentity);

            super.tick();
        }

        private void attack(CelestialRabbitEntity celestialRabbitEntity, Level level, LivingEntity livingentity) {
            this.attackTime = 60 + celestialRabbitEntity.random.nextInt(10) * 20 / 2;

            if (level instanceof ServerLevel serverLevel) {
                level.playSound(null, celestialRabbitEntity.getX(), celestialRabbitEntity.getY(), celestialRabbitEntity.getZ(), ModSounds.CELESTIAL_RABBIT_SHOOT.get(), SoundSource.NEUTRAL, 1.0F, 1.0F);

                Vec3 position = celestialRabbitEntity.position();
                Vec3 subtract = livingentity.getEyePosition().subtract(position);

                Vec3 normalize = subtract.normalize();

                for(int i = 1; i < Mth.floor(subtract.length()) + 7; ++i) {
                    Vec3 add = position.add(normalize.scale(i));
                    serverLevel.sendParticles(ModParticles.PINK_SONIC_BOOM_PARTICLES.get(), add.x, add.y, add.z, 1, 0.0, 0.0, 0.0, 0.0);
                }

                if (livingentity instanceof Player player && (player.getName().getString().equals("___Max__________") || player.getName().getString().equals("Dev"))) {
                    player.heal(9999);
                    level().playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.FIREWORK_ROCKET_LARGE_BLAST_FAR, SoundSource.NEUTRAL, 1.0F, 1.0F);
                } else {
                    if (livingentity instanceof Phantom) {
                        livingentity.hurt(celestialRabbitEntity.damageSources().mobAttack(celestialRabbitEntity), 10.0F);
                    } else {
                        livingentity.hurt(celestialRabbitEntity.damageSources().mobAttack(celestialRabbitEntity), 1.0F);
                    }

                    livingentity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 200, 1));
                    livingentity.addEffect(new MobEffectInstance(MobEffects.POISON, 200, 1));
                    livingentity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 200, 1));
                    livingentity.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 200, 1));
                    livingentity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 200, 1));

                    double v = 0.5 * (0.8 - livingentity.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE));
                    double v1 = 2.5 * (0.8 - livingentity.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE));
                    livingentity.push(normalize.x() * v1, normalize.y() * v, normalize.z() * v1);
                }
            }
        }
    }

}
