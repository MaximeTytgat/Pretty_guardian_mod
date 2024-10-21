package com.max.prettyguardian.blocks.custom.table;

import com.max.prettyguardian.blocks.entity.ModBlockEntities;
import com.max.prettyguardian.blocks.entity.MoonAltarBlockEntity;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MoonAltarBlock extends BaseEntityBlock {
    public static final MapCodec<MoonAltarBlock> CODEC = simpleCodec(MoonAltarBlock::new);

    private static final VoxelShape SHAPE = Shapes.or(
            Block.box(1, 11, 1, 15, 15, 15),
            Block.box(4, 3, 4, 12, 11, 12),
            Block.box(3, 3, 3, 13, 5, 13),
            Block.box(1, 0, 1, 15, 3, 15),
            Block.box(2, 15, 0.5, 14, 15.5, 15.5),
            Block.box(7, 4, 15.000000000000002, 9, 5, 15.500000000000002),
            Block.box(6, 5, 15.000000000000002, 10, 6, 15.500000000000002),
            Block.box(5, 6, 15.000000000000002, 11, 7, 15.500000000000002),
            Block.box(4, 7, 15.000000000000002, 12, 9, 15.500000000000002),
            Block.box(3, 9, 15.000000000000002, 13, 12, 15.500000000000002),
            Block.box(2, 12, 15.000000000000002, 14, 15, 15.500000000000002),
            Block.box(7, 4, 0.5, 9, 5, 0.9999999999999997),
            Block.box(6, 5, 0.5, 10, 6, 0.9999999999999997),
            Block.box(5, 6, 0.5, 11, 7, 0.9999999999999997),
            Block.box(4, 7, 0.5, 12, 9, 0.9999999999999997),
            Block.box(3, 9, 0.5, 13, 12, 0.9999999999999997),
            Block.box(2, 12, 0.5, 14, 15, 0.9999999999999997)
    );
    private static final VoxelShape SHAPE_EAST = Shapes.or(
            Block.box(1, 11, 1, 15, 15, 15),
            Block.box(4, 3, 4, 12, 11, 12),
            Block.box(3, 3, 3, 13, 5, 13),
            Block.box(1, 0, 1, 15, 3, 15),
            Block.box(0.5, 15, 2, 15.5, 15.5, 14),
            Block.box(0.4999999999999982, 4, 7, 0.9999999999999982, 5, 9),
            Block.box(0.4999999999999982, 5, 6, 0.9999999999999982, 6, 10),
            Block.box(0.4999999999999982, 6, 5, 0.9999999999999982, 7, 11),
            Block.box(0.4999999999999982, 7, 4, 0.9999999999999982, 9, 12),
            Block.box(0.4999999999999982, 9, 3, 0.9999999999999982, 12, 13),
            Block.box(0.4999999999999982, 12, 2, 0.9999999999999982, 15, 14),
            Block.box(15, 4, 7, 15.5, 5, 9),
            Block.box(15, 5, 6, 15.5, 6, 10),
            Block.box(15, 6, 5, 15.5, 7, 11),
            Block.box(15, 7, 4, 15.5, 9, 12),
            Block.box(15, 9, 3, 15.5, 12, 13),
            Block.box(15, 12, 2, 15.5, 15, 14)
    );
    public static final EnumProperty<Direction> FACING = DirectionalBlock.FACING;

    public MoonAltarBlock(Properties properties) {
        super(properties);
        this.defaultBlockState().setValue(FACING, Direction.NORTH);
    }

    @Override
    protected @NotNull MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState) {
        return new MoonAltarBlockEntity(blockPos, blockState);
    }

    @Override
    public @NotNull VoxelShape getShape(
            BlockState blockState,
            @NotNull BlockGetter blockGetter,
            @NotNull BlockPos blockPos,
            @NotNull CollisionContext collisionContext
    ) {
        Direction direction = blockState.getValue(FACING);
        return direction.getAxis() == Direction.Axis.X ? SHAPE_EAST : SHAPE;
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
            if (blockEntity instanceof MoonAltarBlockEntity) {
                ((MoonAltarBlockEntity) blockEntity).drops();
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
            if (blockEntity instanceof MoonAltarBlockEntity moonAltarBlockEntity) {
                player.openMenu(moonAltarBlockEntity);
            }

            return InteractionResult.CONSUME;
        }
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(
            Level level,
            @NotNull BlockState blockState,
            @NotNull BlockEntityType<T> tBlockEntityType
    ) {
        if (level.isClientSide()) {
            return null;
        }
        return createTickerHelper(
                tBlockEntityType,
                ModBlockEntities.MOON_ALTAR_BE.get(),
                (pLevel1, pPos, pState, pBlockEntity) -> pBlockEntity.tick(level, pPos, pState)
        );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

}

