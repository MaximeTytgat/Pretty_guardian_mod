package com.max.prettyguardian.event.custom;

import com.max.prettyguardian.PrettyGuardian;
import com.max.prettyguardian.blocks.custom.furniture.JapChairBlock;
import com.max.prettyguardian.worldgen.entity.ModEntityType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Mod.EventBusSubscriber(modid = PrettyGuardian.MOD_ID)
public class JapChairEvent {
    private JapChairEvent() {}
    @SubscribeEvent
    public static void onIteractWithBlock(PlayerInteractEvent.RightClickBlock event) {
        if (!event.getSide().isClient()) {
            Player player = event.getEntity();

            if (player == null || player.isSpectator() || player.getVehicle() != null) {
                return;
            }

            Level level = event.getLevel();
            BlockPos pos = event.getPos();

            BlockState blockState = level.getBlockState(pos);


            if (blockState.getBlock() instanceof JapChairBlock) {
                SeatJapChairEntity seatJapChairEntity = new SeatJapChairEntity(level, pos);
                level.addFreshEntity(seatJapChairEntity);

                player.startRiding(seatJapChairEntity);
            }
        }
    }

    public static class SeatJapChairEntity extends Entity {

        public SeatJapChairEntity(EntityType<?> entityType, Level level) {
            super(entityType, level);
        }

        public SeatJapChairEntity(Level level, BlockPos pos) {
            this(ModEntityType.SEAT_JAP_CHAIR.get(), level);
            setPos(pos.getX() + 0.5D, pos.getY() + 0.35D, pos.getZ() + 0.5D);
        }

        @Override
        public void tick() {
            super.tick();

            BlockPos pos = this.blockPosition();
            if (!(this.level().getBlockState(pos).getBlock() instanceof JapChairBlock)) {
                this.ejectPassengers();
                this.discard();
            }

            List<Entity> passengers = getPassengers();
            if (passengers.isEmpty()) {
                this.ejectPassengers();
                this.discard();
            }

            for (Entity entity: passengers) {
                if (entity instanceof Player player && player.isShiftKeyDown()) {
                    this.discard();
                    player.setPos(player.getX(), player.getY() + 1D, player.getZ());
                }
            }
        }

        @Override
        protected void defineSynchedData(@NotNull SynchedEntityData.Builder builder) {}

        @Override
        protected void readAdditionalSaveData(@NotNull CompoundTag compoundTag) {}

        @Override
        protected void addAdditionalSaveData(@NotNull CompoundTag compoundTag) {}
    }

    public static class SeatJapChairRenderer extends EntityRenderer<SeatJapChairEntity> {

        public SeatJapChairRenderer(EntityRendererProvider.Context context) {
            super(context);
        }

        @Override
        public @NotNull ResourceLocation getTextureLocation(@NotNull SeatJapChairEntity entity) {return null;}
    }
}
