package com.max.prettyguardian.blocks.custom.table;

import com.max.prettyguardian.blocks.entity.GemPolishingStationBlockEntity;
import com.max.prettyguardian.blocks.entity.ModBlockEntities;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GemPolishingStationBlock extends BaseEntityBlock {
    public static final MapCodec<GemPolishingStationBlock> CODEC = simpleCodec(GemPolishingStationBlock::new);


    private static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 12, 16);

    public GemPolishingStationBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected @NotNull MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public @NotNull VoxelShape getShape(
            @NotNull BlockState blockState,
            @NotNull BlockGetter blockGetter,
            @NotNull BlockPos blockPos,
            @NotNull CollisionContext collisionContext
    ) {
        return SHAPE;
    }

    @Override
    public @NotNull RenderShape getRenderShape(@NotNull BlockState blockState) {
        return RenderShape.MODEL;
    }

    @Override
    public void onRemove(
            BlockState blockState,
            @NotNull Level level,
            @NotNull BlockPos blockPos,
            BlockState blockState1,
            boolean b
    ) {
        if (blockState.getBlock() != blockState1.getBlock()) {
            BlockEntity blockEntity = level.getBlockEntity(blockPos);
            if (blockEntity instanceof GemPolishingStationBlockEntity) {
                ((GemPolishingStationBlockEntity) blockEntity).drops();
            }
        }

        super.onRemove(blockState, level, blockPos, blockState1, b);
    }

    @Override
    protected @NotNull InteractionResult useWithoutItem(
            @NotNull BlockState blockState,
            Level level,
            @NotNull BlockPos blockPos,
            @NotNull Player player,
            @NotNull BlockHitResult blockHitResult
    ) {
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            BlockEntity blockEntity = level.getBlockEntity(blockPos);
            if (blockEntity instanceof GemPolishingStationBlockEntity gemPolishingStationBlockEntity) {
                player.openMenu(gemPolishingStationBlockEntity);
            }

            return InteractionResult.CONSUME;
        }
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState) {
        return new GemPolishingStationBlockEntity(blockPos, blockState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(
            Level level,
            @NotNull BlockState blockState,
            @NotNull BlockEntityType<T> tBlockEntityType
    ) {
        if (level.isClientSide()) return null;
        return createTickerHelper(
                tBlockEntityType,
                ModBlockEntities.GEM_POLISHING_BE.get(),
                (pLevel1, pPos, pState, pBlockEntity) -> pBlockEntity.tick(level, pPos, pState)
        );
    }
}
