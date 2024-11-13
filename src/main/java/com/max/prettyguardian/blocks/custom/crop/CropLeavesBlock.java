package com.max.prettyguardian.blocks.custom.crop;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.OptionalInt;

public class CropLeavesBlock extends LeavesBlock implements BonemealableBlock {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
    public static final int MAX_AGE = 3;

    public final RegistryObject<Item> fruit;

    public CropLeavesBlock(RegistryObject<Item>  fruit, Properties properties) {
        super(properties);
        this.fruit = fruit;
        registerDefaultState(stateDefinition.any().setValue(DISTANCE, 7).setValue(PERSISTENT, false).setValue(AGE, 0).setValue(WATERLOGGED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(DISTANCE, PERSISTENT, AGE, WATERLOGGED);
    }

    @Override
    public boolean isValidBonemealTarget(@NotNull LevelReader levelReader, @NotNull BlockPos blockPos, @NotNull BlockState blockState) {
        return false;
    }

    @Override
    public boolean isBonemealSuccess(@NotNull Level worldIn, @NotNull RandomSource rand, @NotNull BlockPos pos, BlockState state) {
        return state.getValue(AGE) != 3;
    }

    protected int getBonemealAgeIncrease(Level level) {
        return Mth.nextInt(level.random, 2, 5);
    }


    @Override
    public void performBonemeal(ServerLevel world, @NotNull RandomSource rand, @NotNull BlockPos pos, BlockState state) {
        int i = state.getValue(AGE) + this.getBonemealAgeIncrease(world.getLevel());
        int j = MAX_AGE;
        if (i > j) {
            i = j;
        }

        world.setBlock(pos, state.setValue(AGE, i), 2);
    }

    @Override
    public void randomTick(@NotNull BlockState state, @NotNull ServerLevel world, @NotNull BlockPos pos, @NotNull RandomSource rand) {
        if (shouldDecay(state)) {
            dropResources(state, world, pos);
            world.removeBlock(pos, false);
        } else if (canGrow(state) && world.getMaxLocalRawBrightness(pos.above()) >= 9) {
            boolean def =  rand.nextInt() > (99 - 5);

             if (ForgeHooks.onCropsGrowPre(world, pos, state, def)) {
                performBonemeal(world, rand, pos, state);
                ForgeHooks.onCropsGrowPost(world, pos, state);
            }
        }
    }

    private static BlockState updateDistance(BlockState blockState, LevelAccessor levelAccessor, BlockPos blockPos) {
        int i = 7;
        BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();

        for(Direction direction : Direction.values()) {
            mutableBlockPos.setWithOffset(blockPos, direction);
            i = Math.min(i, getDistanceAt(levelAccessor.getBlockState(mutableBlockPos)) + 1);
            if (i == 1) {
                break;
            }
        }

        return blockState.setValue(DISTANCE, i);
    }

    private static int getDistanceAt(BlockState blockState) {
        return getOptionalDistanceAt(blockState).orElse(7);
    }

    public static @NotNull OptionalInt getOptionalDistanceAt(BlockState blockState) {
        if (blockState.is(BlockTags.LOGS)) {
            return OptionalInt.of(0);
        } else {
            return blockState.hasProperty(DISTANCE) ? OptionalInt.of(blockState.getValue(DISTANCE)) : OptionalInt.empty();
        }
    }

    @Override
    public void tick(@NotNull BlockState blockState, ServerLevel serverLevel, @NotNull BlockPos blockPos, @NotNull RandomSource randomSource) {
        serverLevel.setBlock(blockPos, updateDistance(blockState, serverLevel, blockPos), 3);
    }

    @Override
    public boolean isRandomlyTicking(@NotNull BlockState state) {
        return shouldDecay(state) || canGrow(state) || state.getValue(AGE) == 0;
    }

    public boolean shouldDecay(BlockState state) {
        return state.getValue(DISTANCE) == 7 && !state.getValue(PERSISTENT);
    }

    public boolean canGrow(BlockState state) {
        return state.getValue(AGE) >= 0 && (!state.getValue(PERSISTENT) || state.getValue(DISTANCE) == 1);
    }

    @Override
    public @NotNull BlockState updateShape(
            @NotNull BlockState state,
            @NotNull Direction facing,
            @NotNull BlockState facingState,
            @NotNull LevelAccessor worldIn,
            @NotNull BlockPos currentPos,
            @NotNull BlockPos facingPos
    ) {
        if (canGrow(state) || state.getValue(AGE) == 0) {
            return super.updateShape(state, facing, facingState, worldIn, currentPos, facingPos);
        }
        return state;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        return defaultBlockState().setValue(PERSISTENT, true).setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
    }

    @Override
    protected @NotNull InteractionResult useWithoutItem(
            @NotNull BlockState blockState,
           @NotNull Level level,
           @NotNull BlockPos blockPos,
           @NotNull Player player,
           @NotNull BlockHitResult blockHitResult
    ) {
        if (blockState.getValue(AGE) == 3 && level.setBlockAndUpdate(blockPos, blockState.setValue(AGE, 1))) {
            if (!level.isClientSide) {
                ItemStack fruitItem = new ItemStack(this.fruit.get());
                if (player instanceof ServerPlayer) {
                    popResourceFromFace(level, blockPos, blockHitResult.getDirection(), fruitItem);
                } else {
                    ItemHandlerHelper.giveItemToPlayer(player, fruitItem);
                }
            }
            return InteractionResult.SUCCESS;
        }
        return super.useWithoutItem(blockState, level, blockPos, player, blockHitResult);
    }
}