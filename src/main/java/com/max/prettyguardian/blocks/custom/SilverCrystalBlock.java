package com.max.prettyguardian.blocks.custom;

import com.max.prettyguardian.blocks.entity.ModBlockEntities;
import com.max.prettyguardian.blocks.entity.SilverCrystalBlockEntity;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SilverCrystalBlock extends BaseEntityBlock {
    public static final MapCodec<SilverCrystalBlock> CODEC = simpleCodec(SilverCrystalBlock::new);
    public static final VoxelShape SHAPE = Shapes.or(
            Block.box(4, 0, 4, 12, 3.75, 12)
    );

    public SilverCrystalBlock(Properties properties) {
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

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState) {
        return new SilverCrystalBlockEntity(blockPos, blockState);
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
                ModBlockEntities.SILVER_CRYSTAL_BE.get(),
                (pLevel1, pPos, pState, pBlockEntity) -> pBlockEntity.tick(level, pPos)
        );
    }

    @Override
    public @NotNull RenderShape getRenderShape(@NotNull BlockState blockState) {
        return RenderShape.MODEL;
    }
}
