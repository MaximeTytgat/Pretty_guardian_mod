package com.max.prettyguardian.datagen.loot;

import com.max.prettyguardian.blocks.PrettyGuardianBlock;
import com.max.prettyguardian.blocks.custom.crop.StrawberryCropBlock;
import com.max.prettyguardian.blocks.custom.furniture.JapDoubleLanternBlock;
import com.max.prettyguardian.blocks.custom.furniture.JapHugeLanternBlock;
import com.max.prettyguardian.blocks.custom.furniture.JapScreenBlock;
import com.max.prettyguardian.item.PrettyGuardianItem;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {


    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(PrettyGuardianBlock.STRAWBERRY_CROP.get());
        this.dropSelf(PrettyGuardianBlock.PINK_SAPPHIRE_BLOCK.get());
        this.dropSelf(PrettyGuardianBlock.RUBY_BLOCK.get());
        this.dropSelf(PrettyGuardianBlock.CHOCOLATE_BLOCK.get());
        this.dropSelf(PrettyGuardianBlock.MARSHMELLO_BLOCK.get());
        this.dropSelf(PrettyGuardianBlock.ROASTED_MARSHMELLO_BLOCK.get());
        this.dropSelf(PrettyGuardianBlock.PISTACHIO_SAPLING.get());
        this.dropSelf(PrettyGuardianBlock.LEMON_SAPLING.get());
        this.dropSelf(PrettyGuardianBlock.BOBA_SAPLING.get());
        this.dropSelf(PrettyGuardianBlock.MYMELODY_PLUSH.get());
        this.dropSelf(PrettyGuardianBlock.KUROMI_PLUSH.get());
        this.dropSelf(PrettyGuardianBlock.CAVALIER_PLUSH.get());
        this.dropSelf(PrettyGuardianBlock.TEDDYBEAR_PLUSH.get());
        this.dropSelf(PrettyGuardianBlock.RABBIT_PLUSH.get());
        this.dropSelf(PrettyGuardianBlock.COW_PLUSH.get());
        this.dropSelf(PrettyGuardianBlock.CINNAMOROLL_PLUSH.get());
        this.dropSelf(PrettyGuardianBlock.BABYYODA_PLUSH.get());
        this.dropSelf(PrettyGuardianBlock.CAPPUCCINO_PLUSH.get());
        this.dropSelf(PrettyGuardianBlock.CHIFFON_PLUSH.get());
        this.dropSelf(PrettyGuardianBlock.EXPRESSO_PLUSH.get());
        this.dropSelf(PrettyGuardianBlock.MOCHA_PLUSH.get());
        this.dropSelf(PrettyGuardianBlock.PRINCESS_PORON_PLUSH.get());
        this.dropSelf(PrettyGuardianBlock.PLUSH_BEAR_HUGE.get());

        this.add(PrettyGuardianBlock.SCREEN_JAPANESE_CHERRY_PLANK.get(), this::createSreenLootTable);
        this.add(PrettyGuardianBlock.SCREEN_JAPANESE_CHERRY_LOG.get(), this::createSreenLootTable);
        this.add(PrettyGuardianBlock.SCREEN_JAPANESE_BIRCH.get(), this::createSreenLootTable);

        this.dropSelf(PrettyGuardianBlock.SHOJI_BLOSSOM.get());
        this.dropSelf(PrettyGuardianBlock.SHOJI_BLOSSOM_BOTTOM.get());
        this.dropSelf(PrettyGuardianBlock.SHOJI_BLOSSOM_SMALL.get());
        this.dropSelf(PrettyGuardianBlock.SHOJI_BLOSSOM_SMALL_BOTTOM.get());

        this.dropSelf(PrettyGuardianBlock.SHOJI_CHERRY.get());
        this.dropSelf(PrettyGuardianBlock.SHOJI_CHERRY_BOTTOM.get());
        this.dropSelf(PrettyGuardianBlock.SHOJI_CHERRY_SMALL.get());
        this.dropSelf(PrettyGuardianBlock.SHOJI_CHERRY_SMALL_BOTTOM.get());

        this.dropSelf(PrettyGuardianBlock.SHOJI_BIRCH.get());
        this.dropSelf(PrettyGuardianBlock.SHOJI_BIRCH_BOTTOM.get());
        this.dropSelf(PrettyGuardianBlock.SHOJI_BIRCH_SMALL.get());
        this.dropSelf(PrettyGuardianBlock.SHOJI_BIRCH_SMALL_BOTTOM.get());

        this.dropSelf(PrettyGuardianBlock.LANTERN_JAPANESE.get());
        this.dropSelf(PrettyGuardianBlock.LANTERN_JAPANESE_SAKURA.get());
        this.dropSelf(PrettyGuardianBlock.LANTERN_JAPANESE_FESTIVAL.get());

        this.add(PrettyGuardianBlock.LANTERN_JAPANESE_BIG.get(), this::createBigLanternLootTable);
        this.add(PrettyGuardianBlock.LANTERN_JAPANESE_SAKURA_BIG.get(), this::createBigLanternLootTable);
        this.add(PrettyGuardianBlock.LANTERN_JAPANESE_FESTIVAL_BIG.get(), this::createBigLanternLootTable);

        this.add(PrettyGuardianBlock.LANTERN_HUGE_JAPANESE.get(), this::createHugeLanternLootTable);
        this.add(PrettyGuardianBlock.LANTERN_SAKURA_HUGE_JAPANESE.get(), this::createHugeLanternLootTable);
        this.add(PrettyGuardianBlock.LANTERN_FESTIVAL_HUGE_JAPANESE.get(), this::createHugeLanternLootTable);

        this.add(PrettyGuardianBlock.BONZAI_CHERRY.get(), this::createBonzaiLootTable);
        this.add(PrettyGuardianBlock.LUCKY_NEKO.get(), this::createBonzaiLootTable);
        this.add(PrettyGuardianBlock.SCROLL_JAPANESE_AXOLOTL.get(), this::createBonzaiLootTable);
        this.add(PrettyGuardianBlock.SCROLL_JAPANESE_BEE.get(), this::createBonzaiLootTable);
        this.add(PrettyGuardianBlock.SCROLL_JAPANESE_CINNAMON_ROLL.get(), this::createBonzaiLootTable);
        this.add(PrettyGuardianBlock.SCROLL_JAPANESE_MUSHROOM.get(), this::createBonzaiLootTable);
        this.add(PrettyGuardianBlock.SCROLL_JAPANESE_DAY.get(), this::createBonzaiLootTable);
        this.add(PrettyGuardianBlock.SCROLL_JAPANESE_NIGHT.get(), this::createBonzaiLootTable);
        this.add(PrettyGuardianBlock.SCROLL_JAPANESE_DAY_2.get(), this::createBonzaiLootTable);
        this.add(PrettyGuardianBlock.SCROLL_JAPANESE_SUNFLOWER.get(), this::createBonzaiLootTable);

        this.dropSelf(PrettyGuardianBlock.LAMP_JAPANESE_OAK.get());
        this.dropSelf(PrettyGuardianBlock.LAMP_JAPANESE_BIRCH.get());
        this.dropSelf(PrettyGuardianBlock.LAMP_JAPANESE_SPRUCE.get());
        this.dropSelf(PrettyGuardianBlock.LAMP_JAPANESE_JUNGLE.get());
        this.dropSelf(PrettyGuardianBlock.LAMP_JAPANESE_ACACIA.get());
        this.dropSelf(PrettyGuardianBlock.LAMP_JAPANESE_DARK_OAK.get());
        this.dropSelf(PrettyGuardianBlock.LAMP_JAPANESE_MANGROVE.get());
        this.dropSelf(PrettyGuardianBlock.LAMP_JAPANESE_CHERRY.get());

        this.add(PrettyGuardianBlock.DOOR_SHOJI_BLOSSOM.get(), this::createBonzaiLootTable);
        this.add(PrettyGuardianBlock.DOOR_SHOJI_BLOSSOM_SMALL.get(), this::createBonzaiLootTable);
        this.add(PrettyGuardianBlock.DOOR_SHOJI_BIRCH.get(), this::createBonzaiLootTable);
        this.add(PrettyGuardianBlock.DOOR_SHOJI_BIRCH_SMALL.get(), this::createBonzaiLootTable);
        this.add(PrettyGuardianBlock.DOOR_SHOJI_CHERRY.get(), this::createBonzaiLootTable);
        this.add(PrettyGuardianBlock.DOOR_SHOJI_CHERRY_SMALL.get(), this::createBonzaiLootTable);

        this.dropSelf(PrettyGuardianBlock.TABLE_JAPANESE_OAK.get());
        this.dropSelf(PrettyGuardianBlock.TABLE_JAPANESE_BIRCH.get());
        this.dropSelf(PrettyGuardianBlock.TABLE_JAPANESE_SPRUCE.get());
        this.dropSelf(PrettyGuardianBlock.TABLE_JAPANESE_CHERRY_LOG.get());
        this.dropSelf(PrettyGuardianBlock.TABLE_JAPANESE_CHERRY_PLANK.get());

        this.dropSelf(PrettyGuardianBlock.CHAIR_JAPANESE_OAK.get());
        this.dropSelf(PrettyGuardianBlock.CHAIR_JAPANESE_BIRCH.get());
        this.dropSelf(PrettyGuardianBlock.CHAIR_JAPANESE_SPRUCE.get());
        this.dropSelf(PrettyGuardianBlock.CHAIR_JAPANESE_CHERRY_LOG.get());
        this.dropSelf(PrettyGuardianBlock.CHAIR_JAPANESE_CHERRY_PLANK.get());

        this.dropSelf(PrettyGuardianBlock.SILVER_CRYSTAL.get());

        this.dropSelf(PrettyGuardianBlock.SEA_SHELL.get());

        this.dropSelf(PrettyGuardianBlock.GEM_POLISHING_STATION.get());
        this.dropSelf(PrettyGuardianBlock.MOON_ALTAR.get());


        this.add(PrettyGuardianBlock.PISTACHIO_LEAVES_CROP.get(), block -> createLeavesDrops(block, PrettyGuardianBlock.PISTACHIO_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        this.add(PrettyGuardianBlock.LEMON_LEAVES_CROP.get(), block -> createLeavesDrops(block, PrettyGuardianBlock.LEMON_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        this.add(PrettyGuardianBlock.BOBA_LEAVES_CROP.get(), block -> createLeavesDrops(block, PrettyGuardianBlock.BOBA_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));


        this.createLapisOreDrops(PrettyGuardianBlock.THREE_STRAWBERRY_CAKE.get());

        
        this.add(PrettyGuardianBlock.PINK_SAPPHIRE_ORE.get(), createOreDrop(PrettyGuardianBlock.PINK_SAPPHIRE_ORE.get(), PrettyGuardianItem.PINK_SAPPHIRE.get()));
        this.add(PrettyGuardianBlock.DEEPSLATE_PINK_SAPPHIRE_ORE.get(), createOreDrop(PrettyGuardianBlock.DEEPSLATE_PINK_SAPPHIRE_ORE.get(), PrettyGuardianItem.PINK_SAPPHIRE.get()));

        this.add(PrettyGuardianBlock.RUBY_ORE.get(), createOreDrop(PrettyGuardianBlock.RUBY_ORE.get(), PrettyGuardianItem.RUBY.get()));
        this.add(PrettyGuardianBlock.DEEPSLATE_RUBY_ORE.get(), createOreDrop(PrettyGuardianBlock.DEEPSLATE_RUBY_ORE.get(), PrettyGuardianItem.RUBY.get()));

        LootItemCondition.Builder builder = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(PrettyGuardianBlock.STRAWBERRY_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(StrawberryCropBlock.AGE, 4));

        this.add(PrettyGuardianBlock.STRAWBERRY_CROP.get(),
                this.applyExplosionDecay(PrettyGuardianBlock.STRAWBERRY_CROP.get(),
                        LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(PrettyGuardianItem.STRAWBERRY_SEEDS.get())))
                                .withPool(LootPool.lootPool().when(builder).add(LootItem.lootTableItem(PrettyGuardianItem.STRAWBERRY.get())
                                        .apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.FORTUNE, 0.5714286F, 2))))
                                .withPool(LootPool.lootPool().when(builder).add(LootItem.lootTableItem(PrettyGuardianItem.STRAWBERRY_SEEDS.get())
                                        .when(LootItemRandomChanceCondition.randomChance(0.1F))))));

        LootItemCondition.Builder builder1 = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(PrettyGuardianBlock.MINT_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(StrawberryCropBlock.AGE, 4));

        this.add(PrettyGuardianBlock.MINT_CROP.get(), createCropDrops(PrettyGuardianBlock.MINT_CROP.get(), PrettyGuardianItem.MINT.get(),
                PrettyGuardianItem.MINT_SEEDS.get(), builder1));

        LootItemCondition.Builder builder2 = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(PrettyGuardianBlock.VANILLA_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(StrawberryCropBlock.AGE, 7));

        this.add(PrettyGuardianBlock.VANILLA_CROP.get(), createCropDrops(PrettyGuardianBlock.VANILLA_CROP.get(), PrettyGuardianItem.VANILLA.get(),
                PrettyGuardianItem.VANILLA_SEEDS.get(), builder2));

        this.add(PrettyGuardianBlock.PICNIC_BASKET.get(), this::createShulkerBoxDrop);
    }

    protected LootTable.Builder createSreenLootTable(Block block) {
        return LootTable.lootTable().withPool(
                this.applyExplosionCondition(block, LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                                .add(LootItem.lootTableItem(block)
                                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                                .setProperties(StatePropertiesPredicate.Builder.properties()
                                                        .hasProperty(DoorBlock.HALF, DoubleBlockHalf.LOWER)
                                                        .hasProperty(JapScreenBlock.PART, JapScreenBlock.ParaventPart.LEFT))))));

    }

    protected LootTable.Builder createHugeLanternLootTable(Block block) {
        return LootTable.lootTable().withPool(
                this.applyExplosionCondition(block, LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(block)
                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                        .setProperties(StatePropertiesPredicate.Builder.properties()
                                                .hasProperty(JapHugeLanternBlock.HALF, JapHugeLanternBlock.TripleBlockHalf.MIDDLE))))));

    }

    protected LootTable.Builder createBigLanternLootTable(Block block) {
        return LootTable.lootTable().withPool(
                this.applyExplosionCondition(block, LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(block)
                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                        .setProperties(StatePropertiesPredicate.Builder.properties()
                                                .hasProperty(JapDoubleLanternBlock.HALF, DoubleBlockHalf.UPPER))))));

    }

    protected LootTable.Builder createBonzaiLootTable(Block block) {
        return LootTable.lootTable().withPool(
                this.applyExplosionCondition(block, LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(block)
                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                        .setProperties(StatePropertiesPredicate.Builder.properties()
                                                .hasProperty(JapDoubleLanternBlock.HALF, DoubleBlockHalf.LOWER))))));
    }

    @Override
    protected LootTable.@NotNull Builder createLapisOreDrops(@NotNull Block block) {
        return createSilkTouchDispatchTable(block,
                this.applyExplosionDecay(block,
                        LootItem.lootTableItem(PrettyGuardianItem.STRAWBERRY_SEEDS.get())
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 9.0F)))
                                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.FORTUNE))));
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return PrettyGuardianBlock.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
