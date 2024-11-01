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
    public ButterflyEntity spawn(ServerLevel p_20593_, @Nullable ItemStack p_20594_, @Nullable Player p_20595_, BlockPos p_20596_, MobSpawnType p_20597_, boolean p_20598_, boolean p_20599_) {
        Consumer<ButterflyEntity> consumer;
        CompoundTag compoundtag;
        if (p_20594_ != null) {
            compoundtag = p_20594_.getTag();
            consumer = createDefaultStackConfig(p_20593_, p_20594_, p_20595_);
        } else {
            consumer = (p_263563_) -> {
            };
            compoundtag = null;
        }

        return this.spawn(p_20593_, compoundtag, consumer, p_20596_, p_20597_, p_20598_, p_20599_);
    }

    public static <T extends Entity> Consumer<T> createDefaultStackConfig(ServerLevel p_263583_, ItemStack p_263568_, @Nullable Player p_263575_) {
        return appendDefaultStackConfig((p_262561_) -> {
        }, p_263583_, p_263568_, p_263575_);
    }
    public static <T extends Entity> Consumer<T> appendDefaultStackConfig(Consumer<T> p_265154_, ServerLevel p_265733_, ItemStack p_265598_, @Nullable Player p_265666_) {
        return appendCustomEntityStackConfig(appendCustomNameConfig(p_265154_, p_265598_), p_265733_, p_265598_, p_265666_);
    }

    public static <T extends Entity> Consumer<T> appendCustomNameConfig(Consumer<T> p_263567_, ItemStack p_263564_) {
        return p_263564_.hasCustomHoverName() ? p_263567_.andThen((p_262560_) -> {
            p_262560_.setCustomName(p_263564_.getHoverName());
        }) : p_263567_;
    }

    public static <T extends Entity> Consumer<T> appendCustomEntityStackConfig(Consumer<T> p_263579_, ServerLevel p_263571_, ItemStack p_263582_, @Nullable Player p_263574_) {
        CompoundTag compoundtag = p_263582_.getTag();
        return compoundtag != null ? p_263579_.andThen((p_262558_) -> {
            updateCustomEntityTag(p_263571_, p_263574_, p_262558_, compoundtag);
        }) : p_263579_;
    }

    public static void updateCustomEntityTag(Level level, @Nullable Player player, @Nullable Entity entity, @Nullable CompoundTag p_20624_) {
        if (p_20624_ != null && p_20624_.contains("EntityTag", 10)) {
            MinecraftServer minecraftserver = level.getServer();
            if (minecraftserver != null && entity != null) {
                if (level.isClientSide || !entity.onlyOpCanSetNbt() || player != null && minecraftserver.getPlayerList().isOp(player.getGameProfile())) {
                    CompoundTag compoundtag = entity.saveWithoutId(new CompoundTag());
                    UUID uuid = entity.getUUID();
                    compoundtag.merge(p_20624_.getCompound("EntityTag"));
                    entity.setUUID(uuid);
                    entity.load(compoundtag);
                }
            }
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
    public ButterflyEntity create(ServerLevel serverLevel, @Nullable CompoundTag compoundTag, @Nullable Consumer<ButterflyEntity> butterflyEntityConsumer, BlockPos p_262595_, MobSpawnType p_262666_, boolean p_262685_, boolean p_262588_) {
        ButterflyEntity t = this.create(serverLevel);
        if (t == null) {
            return null;
        } else {
            double d0;
            if (p_262685_) {
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
