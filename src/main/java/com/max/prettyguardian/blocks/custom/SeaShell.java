package com.max.prettyguardian.blocks.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class SeaShell extends Block {
    public static final IntegerProperty VARIANT = IntegerProperty.create("variant", 0, 3);
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    private static final VoxelShape[] SHAPE_BY_VARIANT = {
            Block.box(2, 0, 2, 14, 2, 14),
            Block.box(2, 0, 2, 15, 2, 14),
            Block.box(3, 0, 2, 13, 2, 13),
            Block.box(3, 0, 2, 13, 9, 13)
    };
    private static final Random random = new Random();

    public SeaShell(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull VoxelShape getShape(
            BlockState blockState,
            @NotNull BlockGetter blockGetter,
            @NotNull BlockPos blockPos,
            @NotNull CollisionContext collisionContext
    ) {
        return SHAPE_BY_VARIANT[blockState.getValue(VARIANT)];
    }

    @Override
    public boolean canSurvive(@NotNull BlockState blockState, LevelReader level, BlockPos blockPos) {
        BlockPos blockpos = blockPos.below();
        BlockState blockstate = level.getBlockState(blockpos);

        return  blockstate.is(Blocks.SAND) || blockstate.is(Blocks.RED_SAND);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        int variant = random.nextInt(4);
        return this.defaultBlockState()
                .setValue(VARIANT, variant)
                .setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(VARIANT, FACING);
    }
}
