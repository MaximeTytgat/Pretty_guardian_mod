package com.max.prettyguardian.worldgen;

import com.max.prettyguardian.PrettyGuardian;
import com.max.prettyguardian.blocks.PrettyGuardianBlock;
import com.max.prettyguardian.blocks.custom.SeaShell;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PinkPetalsBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModConfiguredFeatures {
    private ModConfiguredFeatures() {}
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_PINK_SAPPHIRE_ORE_KEY = registerKey("pink_sapphire_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_RUBY_ORE_KEY = registerKey("ruby_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PISTACHIO_KEY = registerKey("pistachio");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LEMON_KEY = registerKey("lemon");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BOBA_KEY = registerKey("boba");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SEA_SHELL_KEY = registerKey("sea_shell");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceabeles = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceabeles = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        List<OreConfiguration.TargetBlockState> overworldPinkSapphireOres = List.of(OreConfiguration.target(stoneReplaceabeles,
                        PrettyGuardianBlock.PINK_SAPPHIRE_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceabeles, PrettyGuardianBlock.DEEPSLATE_PINK_SAPPHIRE_ORE.get().defaultBlockState()));

        register(context, OVERWORLD_PINK_SAPPHIRE_ORE_KEY, Feature.ORE, new OreConfiguration(overworldPinkSapphireOres, 2)); // vein per chunk

        List<OreConfiguration.TargetBlockState> overworldRubyOres = List.of(OreConfiguration.target(deepslateReplaceabeles, PrettyGuardianBlock.DEEPSLATE_RUBY_ORE.get().defaultBlockState()));

        register(context, OVERWORLD_RUBY_ORE_KEY, Feature.ORE, new OreConfiguration(overworldRubyOres, 3)); // vein per chunk

        register(context, PISTACHIO_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG.defaultBlockState()),
                new StraightTrunkPlacer(4, 2, 0),
                new WeightedStateProvider(weightedBlockStateBuilder()
                        .add(Blocks.OAK_LEAVES.defaultBlockState(), 5)
                        .add(PrettyGuardianBlock.PISTACHIO_LEAVES_CROP.get().defaultBlockState(), 1)),
                new BlobFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), 3),
                new TwoLayersFeatureSize(1, 0, 2)).build());

        register(context, LEMON_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG.defaultBlockState()),
                new StraightTrunkPlacer(4, 2, 0),
                new WeightedStateProvider(weightedBlockStateBuilder()
                        .add(Blocks.OAK_LEAVES.defaultBlockState(), 5)
                        .add(PrettyGuardianBlock.LEMON_LEAVES_CROP.get().defaultBlockState(), 1)),
                new BlobFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), 3),
                new TwoLayersFeatureSize(1, 0, 2)).build());

        register(context, BOBA_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG.defaultBlockState()),
                new StraightTrunkPlacer(4, 2, 0),
                new WeightedStateProvider(weightedBlockStateBuilder()
                        .add(Blocks.OAK_LEAVES.defaultBlockState(), 5)
                        .add(PrettyGuardianBlock.BOBA_LEAVES_CROP.get().defaultBlockState(), 1)),
                new BlobFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), 3),
                new TwoLayersFeatureSize(1, 0, 2)).build());


        register(context, SEA_SHELL_KEY, Feature.RANDOM_PATCH, seaShell(PrettyGuardianBlock.SEA_SHELL.get(), List.of(Blocks.SAND, Blocks.RED_SAND)));

    }

    private static RandomPatchConfiguration seaShell(Block block, List<Block> whitelist) {
        SimpleWeightedRandomList.Builder<BlockState> builder = SimpleWeightedRandomList.builder();
        for(int i = 0; i <= 3; ++i) {
            for(Direction direction : Direction.Plane.HORIZONTAL) {
                builder.add(block.defaultBlockState().setValue(SeaShell.VARIANT, i).setValue(PinkPetalsBlock.FACING, direction), 1);
            }
        }

        return FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(builder)), whitelist, 16);
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(PrettyGuardian.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }


    private static SimpleWeightedRandomList.Builder<BlockState> weightedBlockStateBuilder() {
        return SimpleWeightedRandomList.builder();
    }
}