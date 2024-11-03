package com.max.prettyguardian.item.custom;

import com.max.prettyguardian.entity.ModEntities;
import com.max.prettyguardian.entity.custom.ButterflyEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ButterflyEggItem extends Item {
    private final Supplier<? extends EntityType<? extends Mob>> type;
    private final ButterflyEntity.Variant variant;
    public ButterflyEggItem(Supplier<? extends EntityType<? extends Mob>> type, ButterflyEntity.Variant variant, Item.Properties props) {
        super(props);
        this.type = type;
        this.variant = variant;
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext useOnContext) {
        Level level = useOnContext.getLevel();
        if (!(level instanceof ServerLevel)) return InteractionResult.SUCCESS;

        ItemStack itemstack = useOnContext.getItemInHand();
        BlockPos blockpos = useOnContext.getClickedPos();
        Direction direction = useOnContext.getClickedFace();
        BlockState blockstate = level.getBlockState(blockpos);

        BlockPos blockpos1;
        if (blockstate.getCollisionShape(level, blockpos).isEmpty()) {
            blockpos1 = blockpos;
        } else {
            blockpos1 = blockpos.relative(direction);
        }

        this.type.get();

        if (this.spawn((ServerLevel)level, itemstack, useOnContext.getPlayer(), blockpos1, MobSpawnType.SPAWN_EGG, true, !Objects.equals(blockpos, blockpos1) && direction == Direction.UP) != null) {
            itemstack.shrink(1);
            level.gameEvent(useOnContext.getPlayer(), GameEvent.ENTITY_PLACE, blockpos);
        }

        return InteractionResult.CONSUME;
    }

    @Nullable
    public ButterflyEntity spawn(ServerLevel serverLevel, @Nullable ItemStack itemStack, @Nullable Player player, BlockPos blockPos, MobSpawnType mobSpawnType, boolean b, boolean b1) {
        Consumer<ButterflyEntity> consumer;
        CompoundTag compoundtag;
        if (itemStack != null) {
            compoundtag = itemStack.getTag();
            consumer = createDefaultStackConfig(serverLevel, itemStack, player);
        } else {
            consumer = butterflyEntity -> {};
            compoundtag = null;
        }

        return this.spawn(serverLevel, compoundtag, consumer, blockPos, mobSpawnType, b, b1);
    }

    public static <T extends Entity> Consumer<T> createDefaultStackConfig(ServerLevel serverLevel, ItemStack itemStack, @Nullable Player player) {
        return appendDefaultStackConfig(t -> {
        }, serverLevel, itemStack, player);
    }
    public static <T extends Entity> Consumer<T> appendDefaultStackConfig(Consumer<T> tConsumer, ServerLevel serverLevel, ItemStack itemStack, @Nullable Player player) {
        return appendCustomEntityStackConfig(appendCustomNameConfig(tConsumer, itemStack), serverLevel, itemStack, player);
    }

    public static <T extends Entity> Consumer<T> appendCustomNameConfig(Consumer<T> tConsumer, ItemStack itemStack) {
        return itemStack.hasCustomHoverName() ? tConsumer.andThen(t ->  t.setCustomName(itemStack.getHoverName())) : tConsumer;
    }

    public static <T extends Entity> Consumer<T> appendCustomEntityStackConfig(Consumer<T> tConsumer, ServerLevel serverLevel, ItemStack itemStack, @Nullable Player player) {
        CompoundTag compoundtag = itemStack.getTag();
        return compoundtag != null ? tConsumer.andThen(t -> updateCustomEntityTag(serverLevel, player, t, compoundtag)) : tConsumer;
    }

    public static void updateCustomEntityTag(Level level, @Nullable Player player, @Nullable Entity entity, @Nullable CompoundTag compoundTag) {
        MinecraftServer minecraftserver = level.getServer();
        if (
                compoundTag != null
                && compoundTag.contains("EntityTag", 10)
                && minecraftserver != null
                && entity != null
                && (level.isClientSide || !entity.onlyOpCanSetNbt() || player != null
                && minecraftserver.getPlayerList().isOp(player.getGameProfile()))
        ) {
                CompoundTag compoundtag = entity.saveWithoutId(new CompoundTag());
                UUID uuid = entity.getUUID();
                compoundtag.merge(compoundTag.getCompound("EntityTag"));
                entity.setUUID(uuid);
                entity.load(compoundtag);
        }
    }


    @Nullable
    public ButterflyEntity spawn(ServerLevel serverLevel, @Nullable CompoundTag compoundTag, @Nullable Consumer<ButterflyEntity> butterflyEntityConsumer, BlockPos blockPos, MobSpawnType mobSpawnType, boolean b, boolean b1) {
        ButterflyEntity t = this.create(serverLevel, compoundTag, butterflyEntityConsumer, blockPos, mobSpawnType, b, b1);
        if (t != null) {
            serverLevel.addFreshEntityWithPassengers(t);
        }

        return t;
    }

    protected static double getYOffset(LevelReader levelReader, BlockPos blockPos, boolean b, AABB aabb1) {
        AABB aabb = new AABB(blockPos);
        if (b) {
            aabb = aabb.expandTowards(0.0D, -1.0D, 0.0D);
        }

        Iterable<VoxelShape> iterable = levelReader.getCollisions(null, aabb);
        return 1.0D + Shapes.collide(Direction.Axis.Y, aabb1, iterable, b ? -2.0D : -1.0D);
    }

    @Nullable
    public ButterflyEntity create(ServerLevel serverLevel, @Nullable CompoundTag compoundTag, @Nullable Consumer<ButterflyEntity> butterflyEntityConsumer, BlockPos p_262595_, MobSpawnType p_262666_, boolean b, boolean p_262588_) {
        ButterflyEntity t = this.create(serverLevel);
        if (t == null) {
            return null;
        } else {
            double d0;
            if (b) {
                t.setPos(p_262595_.getX() + 0.5D, p_262595_.getY() + 1, p_262595_.getZ() + 0.5D);
                d0 = getYOffset(serverLevel, p_262595_, p_262588_, t.getBoundingBox());
            } else {
                d0 = 0.0D;
            }

            t.moveTo(p_262595_.getX() + 0.5D, p_262595_.getY() + d0, p_262595_.getZ() + 0.5D, Mth.wrapDegrees(serverLevel.random.nextFloat() * 360.0F), 0.0F);
            if (t instanceof ButterflyEntity) {
                t.yHeadRot = t.getYRot();
                t.yBodyRot = t.getYRot();
                ButterflyEntity.ButterflyGroupData mobgroupdata = new ButterflyEntity.ButterflyGroupData(this.variant);
                t.finalizeSpawn(serverLevel, serverLevel.getCurrentDifficultyAt(t.blockPosition()), p_262666_, mobgroupdata);
                t.playAmbientSound();
            }

            if (butterflyEntityConsumer != null) {
                butterflyEntityConsumer.accept(t);
            }

            return t;
        }
    }

    @Nullable
    public ButterflyEntity create(Level level) {
        return !this.isEnabled(level.enabledFeatures()) ? null : ModEntities.BUTTERFLY.get().create(level);
    }


    public interface EntityFactory<T extends Entity> {
        T create(EntityType<T> p_20722_, Level p_20723_);
    }
}
