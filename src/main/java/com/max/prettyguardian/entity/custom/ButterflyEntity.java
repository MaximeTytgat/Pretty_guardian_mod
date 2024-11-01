package com.max.prettyguardian.entity.custom;

import com.max.prettyguardian.entity.ModEntities;
import com.max.prettyguardian.item.PrettyGuardianItem;
import com.max.prettyguardian.item.custom.tool.ButterflyNetItem;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.function.IntFunction;

public class ButterflyEntity extends Animal implements FlyingAnimal, VariantHolder<ButterflyEntity.Variant> {
    private static final EntityDataAccessor<Integer> DATA_VARIANT = SynchedEntityData.defineId(ButterflyEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> DATA_PLAYING_DEAD = SynchedEntityData.defineId(ButterflyEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> FROM_BUTTERFLY_NET = SynchedEntityData.defineId(ButterflyEntity.class, EntityDataSerializers.BOOLEAN);

    public ButterflyEntity(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new FlyingMoveControl(this, 20, true);
    }

    public static final AnimationState idleAnimationState = new AnimationState();

    @Override
    public boolean isPushable() { return false; }

    @Override
    public float getWalkTargetValue(@NotNull BlockPos blockPos) {
        return 0.0F;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new BreathAirGoal(this));

        this.goalSelector.addGoal(1, new TemptGoal(this, 1.15D, Ingredient.of(ItemTags.FLOWERS), false));
        this.goalSelector.addGoal(2, new FollowParentGoal(this, 1.3D));

        this.goalSelector.addGoal(3, new WaterAvoidingRandomFlyingGoal(this, 1.0D));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 2.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.FLYING_SPEED, 0.6D)
                .add(Attributes.FOLLOW_RANGE, 10.0D);
    }

    @Override
    public SpawnGroupData finalizeSpawn(@NotNull ServerLevelAccessor serverLevelAccessor, @NotNull DifficultyInstance difficultyInstance, @NotNull MobSpawnType mobSpawnType, @Nullable SpawnGroupData spawnGroupData) {
        boolean flag = false;
        if (mobSpawnType == MobSpawnType.BUCKET) {
            return spawnGroupData;
        } else {
            RandomSource randomsource = serverLevelAccessor.getRandom();
            if (spawnGroupData instanceof ButterflyGroupData butterflyGroupData) {
                if (butterflyGroupData.getGroupSize() >= 2) {
                    flag = true;
                }
            } else {
                spawnGroupData = new ButterflyGroupData(Variant.getSpawnVariant(randomsource), Variant.getSpawnVariant(randomsource));
            }

            this.setVariant(((ButterflyGroupData)spawnGroupData).getVariant(randomsource));
            if (flag) {
                this.setAge(-24000);
            }
            ((ButterflyGroupData) spawnGroupData).getVariant(randomsource);

            return super.finalizeSpawn(serverLevelAccessor, difficultyInstance, mobSpawnType, spawnGroupData);
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide) {
            setupAnimationStates();
        }
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
            public boolean isStableDestination(BlockPos below) {
                return !this.level.getBlockState(below.below()).isAir();
            }
        };
        flyingpathnavigation.setCanOpenDoors(false);
        flyingpathnavigation.setCanFloat(false);
        flyingpathnavigation.setCanPassDoors(true);
        return flyingpathnavigation;
    }


    @Override
    public boolean requiresCustomPersistence() {
        return super.requiresCustomPersistence() || this.fromButterflyNet();
    }

    @Override
    public boolean removeWhenFarAway(double v) {
        return !this.fromButterflyNet() && !this.hasCustomName();
    }

    @Override
    public @NotNull InteractionResult mobInteract(Player player, @NotNull InteractionHand interactionHand) {
        ItemStack itemstack = player.getItemInHand(interactionHand);

        if (itemstack.getItem() instanceof ButterflyNetItem && !this.isBaby() && !this.level().isClientSide) {
            this.setFromButterflyNet(true);
            this.setHealth(0);
            this.discard();

            Variant v = this.getVariant();
            ItemStack butterflyEgg = switch (v) {
                case ADMIRAL -> new ItemStack(PrettyGuardianItem.ADMIRAL_BUTTERFLY_EGG.get());
                case APOLLO -> new ItemStack(PrettyGuardianItem.APOLLO_BUTTERFLY_EGG.get());
                case DUSK -> new ItemStack(PrettyGuardianItem.DUSK_BUTTERFLY_EGG.get());
                case LEMON -> new ItemStack(PrettyGuardianItem.LEMON_BUTTERFLY_EGG.get());
                case MORPHO -> new ItemStack(PrettyGuardianItem.MORPHO_BUTTERFLY_EGG.get());
                case ORCHID -> new ItemStack(PrettyGuardianItem.ORCHID_BUTTERFLY_EGG.get());
                case PEACOCK -> new ItemStack(PrettyGuardianItem.PEACOCK_BUTTERFLY_EGG.get());
                case PINK -> new ItemStack(PrettyGuardianItem.PINK_BUTTERFLY_EGG.get());
                case SKIPPER -> new ItemStack(PrettyGuardianItem.SKIPPER_BUTTERFLY_EGG.get());
                case VIOLETTE -> new ItemStack(PrettyGuardianItem.VIOLETTE_BUTTERFLY_EGG.get());
            };

            if (!player.isCreative()) {
                player.addItem(butterflyEgg);
            } else {
                if (!player.getInventory().contains(butterflyEgg)) {
                    player.addItem(butterflyEgg);
                }
            }

            return InteractionResult.sidedSuccess(this.level().isClientSide);
        }


        return super.mobInteract(player, interactionHand);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(@NotNull ServerLevel serverLevel, @NotNull AgeableMob ageableMob) {
        return ModEntities.BUTTERFLY.get().create(serverLevel);
    }

    @Override
    public boolean isFood(ItemStack itemStack) {
        return itemStack.is(ItemTags.FLOWERS);
    }

    @Override
    public boolean isFlying() {
        return !this.onGround();
    }

    @Override
    protected void defineSynchedData(@NotNull SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_VARIANT, 0);
        builder.define(DATA_PLAYING_DEAD, false);
        builder.define(FROM_BUTTERFLY_NET, false);
    }

    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("Variant", this.getVariant().getId());
        tag.putBoolean("FromButterflyNet", this.fromButterflyNet());
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.setVariant(Variant.byId(tag.getInt("Variant")));
        this.setFromButterflyNet(tag.getBoolean("FromButterflyNet"));
    }

    public boolean fromButterflyNet() {
        return this.entityData.get(FROM_BUTTERFLY_NET);
    }

    public void setFromButterflyNet(boolean b) {
        this.entityData.set(FROM_BUTTERFLY_NET, b);
    }

    @Override
    public void setVariant(Variant variant) {
        this.entityData.set(DATA_VARIANT, variant.getId());
    }

    @Override
    public @NotNull Variant getVariant() {
        return Variant.byId(this.entityData.get(DATA_VARIANT));
    }

    public static class ButterflyGroupData extends AgeableMobGroupData {
        public final Variant[] types;

        public ButterflyGroupData(Variant... variants) {
            super(false);
            this.types = variants;
        }

        public Variant getVariant(RandomSource randomSource) {
            return this.types[randomSource.nextInt(this.types.length)];
        }
    }

    public enum Variant implements StringRepresentable {
        ADMIRAL(0, "admiral"),
        APOLLO(1, "apollo"),
        DUSK(2, "dusk"),
        LEMON(3, "lemon"),
        MORPHO(4, "morpho"),
        ORCHID(5, "orchid"),
        PEACOCK(6, "peacock"),
        PINK(7, "pink"),
        SKIPPER(8, "skipper"),
        VIOLETTE(9, "violette");

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
