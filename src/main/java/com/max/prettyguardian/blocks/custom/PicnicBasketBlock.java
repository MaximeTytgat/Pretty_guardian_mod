package com.max.prettyguardian.blocks.custom;

import com.max.prettyguardian.blocks.entity.PicnicBasketBlockEntity;
import com.mojang.serialization.MapCodec;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.stream.Stream;

public class PicnicBasketBlock extends BaseEntityBlock {

    public static final MapCodec<PicnicBasketBlock> CODEC = simpleCodec(PicnicBasketBlock::new);
    public static final BooleanProperty OPEN = BlockStateProperties.OPEN;
    public static final EnumProperty<Direction> FACING = DirectionalBlock.FACING;
    public static final ResourceLocation CONTENTS = ResourceLocation.withDefaultNamespace("contents");
    private static final Component UNKNOWN_CONTENTS = Component.translatable("prettyGuardian.container.picnicBasket.unknownContents");

    private static final VoxelShape SHAPE_NORTH = Stream.of(Block.box(2, 1, 3, 14, 7, 13), Block.box(3, 0, 4, 13, 1, 12)).reduce(Shapes::or).get();
    private static final VoxelShape SHAPE_EAST = Stream.of(Block.box(3, 1, 2, 13, 7, 14), Block.box(4, 0, 3, 12, 1, 13)).reduce(Shapes::or).get();


    public PicnicBasketBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(OPEN, false));
    }

    @Override
    protected @NotNull MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public @NotNull VoxelShape getShape(
            BlockState blockState,
            @NotNull BlockGetter blockGetter,
            @NotNull BlockPos blockPos,
            @NotNull CollisionContext collisionContext
    ) {
        Direction direction = blockState.getValue(FACING);
        return direction.getAxis() == Direction.Axis.X ? SHAPE_EAST : SHAPE_NORTH;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState()
                .setValue(FACING, context.getHorizontalDirection().getOpposite())
                .setValue(OPEN, false);
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
        if (!blockState.is(blockState1.getBlock())) {
            BlockEntity blockentity = level.getBlockEntity(blockPos);
            if (blockentity instanceof PicnicBasketBlockEntity) {
                level.updateNeighbourForOutputSignal(blockPos, blockState.getBlock());
            }

            super.onRemove(blockState, level, blockPos, blockState1, b);
        }
    }

    @Override
    public void appendHoverText(
            @NotNull ItemStack itemStack, Item.
            @NotNull TooltipContext tooltipContext,
            @NotNull List<Component> components,
            @NotNull TooltipFlag tooltipFlag
    ) {
        super.appendHoverText(itemStack, tooltipContext, components, tooltipFlag);

        if (itemStack.has(DataComponents.CONTAINER_LOOT)) {
            components.add(UNKNOWN_CONTENTS);
        }

        int i = 0;
        int i1 = 0;

        for (ItemStack stack : (itemStack.getOrDefault(DataComponents.CONTAINER, ItemContainerContents.EMPTY)).nonEmptyItems()) {
            ++i1;
            if (i <= 4) {
                ++i;
                components.add(Component.translatable("prettyGuardian.container.picnicBasket.itemCount", stack.getHoverName(), stack.getCount()));
            }
        }

        if (i1 - i > 0) {
            components.add(Component.translatable("prettyGuardian.container.picnicBasket.more", i1 - i).withStyle(ChatFormatting.ITALIC));
        }
    }

    @Override
    protected @NotNull InteractionResult useWithoutItem(
            @NotNull BlockState blockState,
            @NotNull Level level,
            @NotNull BlockPos blockPos,
            @NotNull Player player,
            @NotNull BlockHitResult blockHitResult
    ) {
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            BlockEntity blockEntity = level.getBlockEntity(blockPos);
            if (blockEntity instanceof PicnicBasketBlockEntity picnicBasketBlockEntity) {
                player.openMenu(picnicBasketBlockEntity);
            }

            return InteractionResult.CONSUME;
        }
    }

    @Override
    public @NotNull BlockState playerWillDestroy(
            Level level,
            @NotNull BlockPos blockPos,
            @NotNull BlockState blockState,
            @NotNull Player player
    ) {
        BlockEntity blockentity = level.getBlockEntity(blockPos);
        if (blockentity instanceof PicnicBasketBlockEntity picnicBasketBlockEntity) {
            if (!level.isClientSide && player.isCreative() && !picnicBasketBlockEntity.isEmpty()) {
                ItemStack itemstack = new ItemStack(this);
                blockentity.saveToItem(itemstack, level.registryAccess());
                if (picnicBasketBlockEntity.hasCustomName()) {
                    itemstack.set(DataComponents.CUSTOM_NAME, picnicBasketBlockEntity.getCustomName());
                }

                ItemEntity itementity = new ItemEntity(level, blockPos.getX() + 0.5D, blockPos.getY() + 0.5D, blockPos.getZ() + 0.5D, itemstack);
                itementity.setDefaultPickUpDelay();
                level.addFreshEntity(itementity);
            } else {
                picnicBasketBlockEntity.unpackLootTable(player);
            }
        }

        return super.playerWillDestroy(level, blockPos, blockState, player);
    }

    @Override
    public @NotNull List<ItemStack> getDrops(@NotNull BlockState blockState, LootParams.Builder builder) {
        BlockEntity blockentity = builder.getOptionalParameter(LootContextParams.BLOCK_ENTITY);
        if (blockentity instanceof PicnicBasketBlockEntity picnicBasketBlockEntity) {
            builder = builder.withDynamicDrop(CONTENTS, itemStackConsumer -> {
                for(int i = 0; i < picnicBasketBlockEntity.getContainerSize(); ++i) {
                    itemStackConsumer.accept(picnicBasketBlockEntity.getItem(i));
                }
            });
        }

        return super.getDrops(blockState, builder);
    }

    @Override
    public boolean hasAnalogOutputSignal(@NotNull BlockState blockState) {
        return true;
    }

    @Override
    public int getAnalogOutputSignal(@NotNull BlockState blockState, Level level, @NotNull BlockPos blockPos) {
        return AbstractContainerMenu.getRedstoneSignalFromContainer((Container) level.getBlockEntity(blockPos));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState) {
        return new PicnicBasketBlockEntity(blockPos, blockState);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder builder) {
        builder.add(OPEN, FACING);
    }
}
