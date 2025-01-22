package com.max.prettyguardian.item.custom.tool;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.portal.DimensionTransition;
import org.jetbrains.annotations.NotNull;

public class PlutonsKey extends Item {

    public PlutonsKey(Properties properties) {
        super(properties.rarity(Rarity.EPIC));
    }

    @Override
    public boolean isFoil(@NotNull ItemStack itemStack) {
        return true;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand interactionHand) {
        if (interactionHand == InteractionHand.MAIN_HAND && player instanceof ServerPlayer serverPlayer) {
                ResourceKey<Level> dimension = serverPlayer.getRespawnDimension();
                ServerLevel serverLevel = null;
                if (level instanceof ServerLevel serverLevel1) {
                    if (dimension == level.dimension()) {
                        serverLevel = serverLevel1;
                    } else {
                        serverLevel = serverPlayer.getServer().getLevel(Level.OVERWORLD);
                    }
                }

                BlockPos respawnPoint = serverPlayer.getRespawnPosition();

                if (serverLevel != null ) {
                    if (respawnPoint != null) {
                        BlockState blockAtRespawn = serverLevel.getBlockState(respawnPoint);
                        if (blockAtRespawn.getBlock() instanceof BedBlock) {
                            if (dimension != level.dimension()) {
                                DimensionTransition dimensionTransition = new DimensionTransition(serverLevel, serverPlayer, entity -> entity.teleportTo(respawnPoint.getX(), respawnPoint.getY() + 0.5, respawnPoint.getZ()));
                                serverPlayer.changeDimension(dimensionTransition);
                            } else {
                                serverPlayer.teleportTo(respawnPoint.getX(), respawnPoint.getY() + 0.5, respawnPoint.getZ());
                            }
                            return InteractionResultHolder.success(player.getItemInHand(interactionHand));
                        }
                    }

                    BlockPos worldSpawn = serverLevel.getSharedSpawnPos();

                    if (dimension != level.dimension()) {
                        DimensionTransition dimensionTransition = new DimensionTransition(serverLevel, serverPlayer, entity -> entity.teleportTo(worldSpawn.getX(), worldSpawn.getY(), worldSpawn.getZ()));
                        serverPlayer.changeDimension(dimensionTransition);
                    } else {
                        serverPlayer.teleportTo(worldSpawn.getX(), worldSpawn.getY(), worldSpawn.getZ());
                    }

                    return InteractionResultHolder.success(player.getItemInHand(interactionHand));
                } else {
                    return InteractionResultHolder.fail(player.getItemInHand(interactionHand));
                }
            }


        player.getCooldowns().addCooldown(this, 600);
        return super.use(level, player, interactionHand);
    }

    @Override
    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack itemStack) {
        return UseAnim.SPEAR;
    }
}
