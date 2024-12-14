package com.max.prettyguardian.datagen;

import com.max.prettyguardian.PrettyGuardian;
import com.max.prettyguardian.blocks.PrettyGuardianBlock;
import com.max.prettyguardian.item.PrettyGuardianItem;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {

    public ModRecipeProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput recipeOutput) {
        SimpleCookingRecipeBuilder.generic(
                        Ingredient.of(Items.SUGAR), RecipeCategory.MISC, PrettyGuardianItem.CARAMEL.get(), 0.35F, 200, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new)
                .group("caramel_from_sugar")
                .unlockedBy(getHasName(Items.SUGAR), has(Items.SUGAR))
                .save(recipeOutput);

        SimpleCookingRecipeBuilder.generic(
                        Ingredient.of(PrettyGuardianBlock.PINK_SAPPHIRE_ORE.get()), RecipeCategory.MISC, PrettyGuardianItem.PINK_SAPPHIRE.get(), 0.35F, 200, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new)
                .unlockedBy(getHasName(PrettyGuardianBlock.PINK_SAPPHIRE_ORE.get()), has(PrettyGuardianBlock.PINK_SAPPHIRE_ORE.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(PrettyGuardian.MOD_ID, "pink_sapphire_from_ore"));

        SimpleCookingRecipeBuilder.generic(
                        Ingredient.of(PrettyGuardianBlock.DEEPSLATE_PINK_SAPPHIRE_ORE.get()), RecipeCategory.MISC, PrettyGuardianItem.PINK_SAPPHIRE.get(), 0.35F, 200, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new)
                .unlockedBy(getHasName(PrettyGuardianBlock.DEEPSLATE_PINK_SAPPHIRE_ORE.get()), has(PrettyGuardianBlock.DEEPSLATE_PINK_SAPPHIRE_ORE.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(PrettyGuardian.MOD_ID, "pink_sapphire_from_deepsalte_ore"));

        blockIngotRecipes(recipeOutput,
                PrettyGuardianBlock.PINK_SAPPHIRE_BLOCK.get(),
                PrettyGuardianItem.PINK_SAPPHIRE.get()
        );

        toolRecipes(recipeOutput,
                PrettyGuardianItem.PINK_SAPPHIRE_SWORD.get(),
                PrettyGuardianItem.PINK_SAPPHIRE_PICKAXE.get(),
                PrettyGuardianItem.PINK_SAPPHIRE_AXE.get(),
                PrettyGuardianItem.PINK_SAPPHIRE_SHOVEL.get(),
                PrettyGuardianItem.PINK_SAPPHIRE_HOE.get(),
                PrettyGuardianItem.PINK_SAPPHIRE.get()
        );

        armorRecipes(recipeOutput,
                PrettyGuardianItem.PINK_SAPPHIRE_HELMET.get(),
                PrettyGuardianItem.PINK_SAPPHIRE_CHESTPLATE.get(),
                PrettyGuardianItem.PINK_SAPPHIRE_LEGGINGS.get(),
                PrettyGuardianItem.PINK_SAPPHIRE_BOOTS.get(),
                PrettyGuardianItem.PINK_SAPPHIRE.get()
        );

        blockIngotRecipes(recipeOutput,
                PrettyGuardianBlock.RUBY_BLOCK.get(),
                PrettyGuardianItem.RUBY.get()
        );

        toolRecipes(recipeOutput,
                PrettyGuardianItem.RUBY_SWORD.get(),
                PrettyGuardianItem.RUBY_PICKAXE.get(),
                PrettyGuardianItem.RUBY_AXE.get(),
                PrettyGuardianItem.RUBY_SHOVEL.get(),
                PrettyGuardianItem.RUBY_HOE.get(),
                PrettyGuardianItem.RUBY.get()
        );

        armorRecipes(recipeOutput,
                PrettyGuardianItem.RUBY_HELMET.get(),
                PrettyGuardianItem.RUBY_CHESTPLATE.get(),
                PrettyGuardianItem.RUBY_LEGGINGS.get(),
                PrettyGuardianItem.RUBY_BOOTS.get(),
                PrettyGuardianItem.RUBY.get()
        );

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, PrettyGuardianItem.CHOCOLATE.get())
                .pattern("AB")
                .pattern("BA")
                .pattern("AB")
                .define('A', Items.COCOA_BEANS)
                .define('B', Items.SUGAR)
                .unlockedBy(getHasName(Items.COCOA_BEANS), has(Items.COCOA_BEANS))
                .save(recipeOutput);

        blockIngotRecipes(recipeOutput,
                PrettyGuardianBlock.CHOCOLATE_BLOCK.get(),
                PrettyGuardianItem.CHOCOLATE.get()
        );

        simpleShapeless(recipeOutput, RecipeCategory.FOOD, PrettyGuardianItem.AGARAGAR.get(), 2, Items.KELP);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, PrettyGuardianItem.CREAM.get(), 3)
                .pattern("A")
                .pattern("B")
                .pattern("C")
                .define('A', PrettyGuardianItem.VANILLA.get())
                .define('B', Items.SUGAR)
                .define('C', Items.MILK_BUCKET)
                .unlockedBy(getHasName(Items.SUGAR), has(Items.SUGAR))
                .unlockedBy(getHasName(Items.MILK_BUCKET), has(Items.MILK_BUCKET))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, PrettyGuardianItem.MARSHMALLOW.get(), 4)
                .pattern("ABA")
                .pattern("BCB")
                .pattern("ABA")
                .define('A', PrettyGuardianItem.AGARAGAR.get())
                .define('B', Items.SUGAR)
                .define('C', Items.WATER_BUCKET)
                .unlockedBy(getHasName(PrettyGuardianItem.AGARAGAR.get()), has(PrettyGuardianItem.AGARAGAR.get()))
                .save(recipeOutput);

        doubleSimpleShapeless(recipeOutput, PrettyGuardianItem.MARSHMALLOW_STICK.get(), 1,
                PrettyGuardianItem.MARSHMALLOW.get(), Items.STICK);

        String roastedMarshmallowStick = "roasted_marshmallow_stick";

        SimpleCookingRecipeBuilder.generic(
                Ingredient.of(PrettyGuardianItem.MARSHMALLOW_STICK.get()), RecipeCategory.FOOD, PrettyGuardianItem.ROASTED_MARSHMALLOW_STICK.get(), 0.35F, 200, RecipeSerializer.CAMPFIRE_COOKING_RECIPE, CampfireCookingRecipe::new)
                .group(roastedMarshmallowStick)
                .unlockedBy(getHasName(PrettyGuardianItem.MARSHMALLOW_STICK.get()), has(PrettyGuardianItem.MARSHMALLOW_STICK.get()))
                .save(recipeOutput);

        blockIngotRecipes(recipeOutput,
                PrettyGuardianBlock.MARSHMELLO_BLOCK.get(),
                PrettyGuardianItem.MARSHMALLOW.get()
        );

        SimpleCookingRecipeBuilder.generic(
                Ingredient.of(PrettyGuardianBlock.MARSHMELLO_BLOCK.get()), RecipeCategory.FOOD, PrettyGuardianBlock.ROASTED_MARSHMELLO_BLOCK.get(), 0.35F, 200, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new)
                .group("roasted_marshmello_block")
                .unlockedBy(getHasName(PrettyGuardianBlock.MARSHMELLO_BLOCK.get()), has(PrettyGuardianBlock.MARSHMELLO_BLOCK.get()))
                .save(recipeOutput);


        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, PrettyGuardianItem.DONUT.get(), 4)
                .pattern("ABA")
                .pattern("BCB")
                .pattern("ABA")
                .define('A', Items.SUGAR)
                .define('B', Items.WHEAT)
                .define('C', Items.EGG)
                .unlockedBy(getHasName(Items.SUGAR), has(Items.SUGAR))
                .unlockedBy(getHasName(Items.WHEAT), has(Items.WHEAT))
                .unlockedBy(getHasName(Items.EGG), has(Items.EGG))
                .save(recipeOutput);

        doubleSimpleShapeless(recipeOutput, PrettyGuardianItem.CHOCOLATE_DONUT.get(), 1,
                PrettyGuardianItem.DONUT.get(), PrettyGuardianItem.CHOCOLATE.get());
        doubleSimpleShapeless(recipeOutput, PrettyGuardianItem.STRAWBERRY_DONUT.get(), 1,
                PrettyGuardianItem.DONUT.get(), PrettyGuardianItem.STRAWBERRY.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, PrettyGuardianItem.MOCHI_DONUT.get())
                .pattern("ABA")
                .pattern("BCB")
                .pattern("ABA")
                .define('A', PrettyGuardianItem.AGARAGAR.get())
                .define('B', Items.WHEAT)
                .define('C', Items.EGG)
                .unlockedBy(getHasName(PrettyGuardianItem.AGARAGAR.get()), has(PrettyGuardianItem.AGARAGAR.get()))
                .save(recipeOutput);

        doubleSimpleShapeless(recipeOutput, PrettyGuardianItem.CHOCOLATE_MOCHI_DONUT.get(), 1,
                PrettyGuardianItem.MOCHI_DONUT.get(), PrettyGuardianItem.CHOCOLATE.get());
        doubleSimpleShapeless(recipeOutput, PrettyGuardianItem.STRAWBERRY_MOCHI_DONUT.get(), 1,
                PrettyGuardianItem.MOCHI_DONUT.get(), PrettyGuardianItem.STRAWBERRY.get());
        doubleSimpleShapeless(recipeOutput, PrettyGuardianItem.CHOCOLATE_STRAWBERRY.get(), 1,
                PrettyGuardianItem.CHOCOLATE.get(), PrettyGuardianItem.STRAWBERRY.get());

        simpleMilkBucket(recipeOutput, PrettyGuardianItem.VANILLA.get(), PrettyGuardianItem.VANILLA_MILK_BUCKET.get());
        simpleMilkBucket(recipeOutput, PrettyGuardianItem.STRAWBERRY.get(), PrettyGuardianItem.STRAWBERRY_MILK_BUCKET.get());
        simpleMilkBucket(recipeOutput, PrettyGuardianItem.CHOCOLATE.get(), PrettyGuardianItem.CHOCOLATE_MILK_BUCKET.get());

        simpleShapeless(recipeOutput, RecipeCategory.FOOD, PrettyGuardianItem.VANILLA_MILK_CARTON.get(), 3, PrettyGuardianItem.VANILLA_MILK_BUCKET.get());
        simpleShapeless(recipeOutput, RecipeCategory.FOOD, PrettyGuardianItem.STRAWBERRY_MILK_CARTON.get(), 3, PrettyGuardianItem.STRAWBERRY_MILK_BUCKET.get());
        simpleShapeless(recipeOutput, RecipeCategory.FOOD, PrettyGuardianItem.CHOCOLATE_MILK_CARTON.get(), 3, PrettyGuardianItem.CHOCOLATE_MILK_BUCKET.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PrettyGuardianItem.ICE_CREAM_CUP.get(), 3)
                .pattern("AAA")
                .pattern(" A ")
                .define('A', Items.GLASS_PANE)
                .unlockedBy(getHasName(Items.GLASS_PANE), has(Items.GLASS_PANE))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PrettyGuardianItem.JUICE_GLASS.get(), 6)
                .pattern("AA")
                .pattern("AA")
                .pattern("AA")
                .define('A', Items.GLASS_PANE)
                .unlockedBy(getHasName(Items.GLASS_PANE), has(Items.GLASS_PANE))
                .save(recipeOutput);

        mojinRecipes(recipeOutput, PrettyGuardianItem.APPLE_MOJITO.get(), Items.APPLE);
        mojinRecipes(recipeOutput, PrettyGuardianItem.STRAWBERRY_MOJITO.get(), PrettyGuardianItem.STRAWBERRY.get());
        mojinRecipes(recipeOutput, PrettyGuardianItem.MINT_MOJITO.get(), PrettyGuardianItem.MINT.get());

        simpleBubbleTea(recipeOutput, PrettyGuardianItem.BUBBLETEA_MELON.get(), Items.MELON_SLICE);
        simpleBubbleTea(recipeOutput, PrettyGuardianItem.BUBBLETEA_CARAMEL.get(), PrettyGuardianItem.CARAMEL.get());
        simpleBubbleTea(recipeOutput, PrettyGuardianItem.BUBBLETEA_STRAWBERRY.get(), PrettyGuardianItem.STRAWBERRY.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, PrettyGuardianItem.RHUM_BOTTLE.get())
                .pattern(" A ")
                .pattern("ABA")
                .pattern(" C ")
                .define('A', Items.SUGAR_CANE)
                .define('B', Items.WATER_BUCKET)
                .define('C', Items.GLASS_BOTTLE)
                .unlockedBy(getHasName(Items.SUGAR_CANE), has(Items.SUGAR_CANE))
                .save(recipeOutput);

        iceCreamRecipes(recipeOutput, PrettyGuardianItem.STRAWBERRY_ICE_CREAM.get(), PrettyGuardianItem.STRAWBERRY.get());
        iceCreamRecipes(recipeOutput, PrettyGuardianItem.VANILLA_ICE_CREAM.get(), PrettyGuardianItem.VANILLA.get());
        iceCreamRecipes(recipeOutput, PrettyGuardianItem.PISTACHIO_ICE_CREAM.get(), PrettyGuardianItem.PISTACHIO.get());
        iceCreamRecipes(recipeOutput, PrettyGuardianItem.CHOCOLATE_ICE_CREAM.get(), PrettyGuardianItem.CHOCOLATE.get());

        waffleIceCreamRecipes(recipeOutput, PrettyGuardianItem.ICE_CREAM_WAFFLE_STRAWBERRY.get(), PrettyGuardianItem.STRAWBERRY.get());
        waffleIceCreamRecipes(recipeOutput, PrettyGuardianItem.ICE_CREAM_WAFFLE_VANILLA.get(), PrettyGuardianItem.VANILLA.get());
        waffleIceCreamRecipes(recipeOutput, PrettyGuardianItem.ICE_CREAM_WAFFLE_PISTACHIO.get(), PrettyGuardianItem.PISTACHIO.get());
        waffleIceCreamRecipes(recipeOutput, PrettyGuardianItem.ICE_CREAM_WAFFLE_CHOCOLATE.get(), PrettyGuardianItem.CHOCOLATE.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, PrettyGuardianItem.POKKY_ICE_CREAM.get())
                .pattern("ABC")
                .pattern("DEF")
                .pattern("GGG")
                .define('A', PrettyGuardianItem.WAFFLE.get())
                .define('B', PrettyGuardianItem.CREAM.get())
                .define('C', PrettyGuardianItem.CHOCOLATE_POCKY.get())
                .define('D', PrettyGuardianItem.CHOCOLATE.get())
                .define('E', PrettyGuardianItem.VANILLA_ICE_CREAM.get())
                .define('F', PrettyGuardianItem.STRAWBERRY.get())
                .define('G', Items.SNOWBALL)
                .unlockedBy(getHasName(PrettyGuardianItem.VANILLA_ICE_CREAM.get()), has(PrettyGuardianItem.VANILLA_ICE_CREAM.get()))
                .unlockedBy(getHasName(PrettyGuardianItem.CHOCOLATE_POCKY.get()), has(PrettyGuardianItem.CHOCOLATE_POCKY.get()))
                .unlockedBy(getHasName(PrettyGuardianItem.WAFFLE.get()), has(PrettyGuardianItem.WAFFLE.get()))
                .save(recipeOutput);


        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, PrettyGuardianItem.WAFFLE.get(), 3)
                .pattern("ABA")
                .pattern("CDC")
                .pattern("EEE")
                .define('A', Items.SUGAR)
                .define('B', PrettyGuardianItem.VANILLA.get())
                .define('C', Items.EGG)
                .define('D', Items.MILK_BUCKET)
                .define('E', Items.WHEAT)
                .unlockedBy(getHasName(Items.WHEAT), has(Items.WHEAT))
                .unlockedBy(getHasName(Items.SUGAR), has(Items.SUGAR))
                .unlockedBy(getHasName(Items.MILK_BUCKET), has(Items.MILK_BUCKET))
                .unlockedBy(getHasName(Items.EGG), has(Items.EGG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, PrettyGuardianItem.FISH_WAFFLE.get(), 3)
                .pattern("ABA")
                .pattern("CDC")
                .pattern("EEE")
                .define('A', Items.SUGAR)
                .define('B', Items.SWEET_BERRIES)
                .define('C', Items.EGG)
                .define('D', Items.MILK_BUCKET)
                .define('E', Items.WHEAT)
                .unlockedBy(getHasName(Items.WHEAT), has(Items.WHEAT))
                .unlockedBy(getHasName(Items.SUGAR), has(Items.SUGAR))
                .unlockedBy(getHasName(Items.MILK_BUCKET), has(Items.MILK_BUCKET))
                .unlockedBy(getHasName(Items.EGG), has(Items.EGG))
                .save(recipeOutput);

        doubleSimpleShapeless(recipeOutput, PrettyGuardianItem.CHOCOLATE_WAFFLE.get(), 1,
                PrettyGuardianItem.WAFFLE.get(), PrettyGuardianItem.CHOCOLATE.get());

        doubleSimpleShapeless(recipeOutput, PrettyGuardianItem.CHOCOLATE_POCKY.get(), 4,
                PrettyGuardianItem.CHOCOLATE.get(), Items.WHEAT);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, PrettyGuardianItem.STRAWBERRY_POCKY.get(), 4)
                .pattern("A")
                .pattern("B")
                .pattern("C")
                .define('A', PrettyGuardianItem.CHOCOLATE.get())
                .define('B', PrettyGuardianItem.STRAWBERRY.get())
                .define('C', Items.WHEAT)
                .unlockedBy(getHasName(PrettyGuardianItem.CHOCOLATE.get()), has(PrettyGuardianItem.CHOCOLATE.get()))
                .unlockedBy(getHasName(PrettyGuardianItem.STRAWBERRY.get()), has(PrettyGuardianItem.STRAWBERRY.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, PrettyGuardianItem.TAKOYAKI.get(), 3)
                .pattern(" A ")
                .pattern("BCB")
                .pattern("DDD")
                .define('A', Items.KELP)
                .define('B', Items.EGG)
                .define('C', PrettyGuardianItem.SQUID_COOKED.get())
                .define('D', Items.WHEAT)
                .unlockedBy(getHasName(Items.KELP), has(Items.KELP))
                .unlockedBy(getHasName(Items.EGG), has(Items.EGG))
                .unlockedBy(getHasName(PrettyGuardianItem.SQUID_COOKED.get()), has(PrettyGuardianItem.SQUID_COOKED.get()))
                .unlockedBy(getHasName(Items.WHEAT), has(Items.WHEAT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, PrettyGuardianItem.DANGO_CARAMEL.get(), 3)
                .pattern("ABA")
                .pattern("CCC")
                .pattern("DDD")
                .define('A', PrettyGuardianItem.VANILLA.get())
                .define('B', PrettyGuardianItem.CARAMEL.get())
                .define('C', PrettyGuardianItem.AGARAGAR.get())
                .define('D', Items.STICK)
                .unlockedBy(getHasName(PrettyGuardianItem.VANILLA.get()), has(PrettyGuardianItem.VANILLA.get()))
                .unlockedBy(getHasName(PrettyGuardianItem.CARAMEL.get()), has(PrettyGuardianItem.CARAMEL.get()))
                .unlockedBy(getHasName(PrettyGuardianItem.AGARAGAR.get()), has(PrettyGuardianItem.AGARAGAR.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, PrettyGuardianItem.TRICOLOR_DANGO.get(), 3)
                .pattern("ABC")
                .pattern("DDD")
                .pattern("EEE")
                .define('A', PrettyGuardianItem.VANILLA.get())
                .define('B', PrettyGuardianItem.STRAWBERRY.get())
                .define('C', Items.MELON_SLICE)
                .define('D', PrettyGuardianItem.AGARAGAR.get())
                .define('E', Items.STICK)
                .unlockedBy(getHasName(PrettyGuardianItem.VANILLA.get()), has(PrettyGuardianItem.VANILLA.get()))
                .unlockedBy(getHasName(PrettyGuardianItem.STRAWBERRY.get()), has(PrettyGuardianItem.STRAWBERRY.get()))
                .unlockedBy(getHasName(PrettyGuardianItem.AGARAGAR.get()), has(PrettyGuardianItem.AGARAGAR.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, PrettyGuardianItem.POPSICLE.get(), 1)
                .pattern("  A")
                .pattern(" B ")
                .pattern("C  ")
                .define('A', Items.SNOWBALL)
                .define('B', Items.SUGAR)
                .define('C', Items.STICK)
                .unlockedBy(getHasName(Items.SNOWBALL), has(Items.SNOWBALL))
                .unlockedBy(getHasName(Items.SUGAR), has(Items.SUGAR))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, PrettyGuardianItem.CREAM_POPSICLE.get(), 1)
                .pattern("  A")
                .pattern(" B ")
                .pattern("C  ")
                .define('A', PrettyGuardianItem.VANILLA.get())
                .define('B', PrettyGuardianItem.CREAM.get())
                .define('C', Items.STICK)
                .unlockedBy(getHasName(Items.SNOWBALL), has(Items.SNOWBALL))
                .unlockedBy(getHasName(PrettyGuardianItem.CREAM.get()), has(PrettyGuardianItem.CREAM.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, PrettyGuardianItem.CHOCOLATE_POPSICLE.get(), 1)
                .pattern("  A")
                .pattern(" B ")
                .pattern("C  ")
                .define('A', PrettyGuardianItem.CHOCOLATE.get())
                .define('B', PrettyGuardianItem.CREAM.get())
                .define('C', Items.STICK)
                .unlockedBy(getHasName(Items.SNOWBALL), has(Items.SNOWBALL))
                .unlockedBy(getHasName(PrettyGuardianItem.CHOCOLATE.get()), has(PrettyGuardianItem.CHOCOLATE.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, PrettyGuardianItem.STRAWBERRY_POPSICLE.get(), 1)
                .pattern("  A")
                .pattern(" B ")
                .pattern("C  ")
                .define('A', PrettyGuardianItem.STRAWBERRY.get())
                .define('B', PrettyGuardianItem.CREAM.get())
                .define('C', Items.STICK)
                .unlockedBy(getHasName(Items.SNOWBALL), has(Items.SNOWBALL))
                .unlockedBy(getHasName(PrettyGuardianItem.STRAWBERRY.get()), has(PrettyGuardianItem.STRAWBERRY.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, PrettyGuardianBlock.RHUM_CAKE.get())
                .pattern("ABA")
                .pattern("CDC")
                .pattern("EEE")
                .define('A', PrettyGuardianItem.CARAMEL.get())
                .define('B', PrettyGuardianItem.RHUM_BOTTLE.get())
                .define('C', Items.EGG)
                .define('D', Items.MILK_BUCKET)
                .define('E', Items.WHEAT)
                .unlockedBy(getHasName(PrettyGuardianItem.CARAMEL.get()), has(PrettyGuardianItem.CARAMEL.get()))
                .unlockedBy(getHasName(PrettyGuardianItem.RHUM_BOTTLE.get()), has(PrettyGuardianItem.RHUM_BOTTLE.get()))
                .save(recipeOutput);

        simpleCake(recipeOutput, PrettyGuardianBlock.STRAWBERRY_CAKE.get(), PrettyGuardianItem.STRAWBERRY.get(), PrettyGuardianItem.CREAM.get());
        simpleCake(recipeOutput, PrettyGuardianBlock.VELVET_CAKE.get(), Items.RED_DYE, PrettyGuardianItem.CHOCOLATE.get());
        simpleCake(recipeOutput, PrettyGuardianBlock.CREAM_CAKE.get(), PrettyGuardianItem.VANILLA.get(), PrettyGuardianItem.CREAM.get());
        simpleCake(recipeOutput, PrettyGuardianBlock.CHOCOLATE_CAKE.get(), PrettyGuardianItem.CHOCOLATE.get(), PrettyGuardianItem.CREAM.get());
        simpleCake(recipeOutput, PrettyGuardianBlock.BERRY_STRAWBERRY_CAKE.get(), PrettyGuardianItem.STRAWBERRY.get(), Items.SWEET_BERRIES);
        simpleCake(recipeOutput, PrettyGuardianBlock.CREAM_STRAWBERRY_CAKE.get(), PrettyGuardianItem.CREAM.get(), PrettyGuardianItem.STRAWBERRY.get());
        simpleCake(recipeOutput, PrettyGuardianBlock.STRAWBERRY_CHOCO_CAKE.get(), PrettyGuardianItem.STRAWBERRY.get(), PrettyGuardianItem.CHOCOLATE.get());

        simpleThreeCake(recipeOutput, PrettyGuardianBlock.THREE_VELVET_CAKE.get(), PrettyGuardianBlock.VELVET_CAKE.get());
        simpleThreeCake(recipeOutput, PrettyGuardianBlock.THREE_STRAWBERRY_CAKE.get(), PrettyGuardianBlock.CREAM_STRAWBERRY_CAKE.get());
        simpleThreeCake(recipeOutput, PrettyGuardianBlock.THREE_CHOCO_CAKE.get(), PrettyGuardianBlock.CHOCOLATE_CAKE.get());
        simpleThreeCake(recipeOutput, PrettyGuardianBlock.THREE_STRAWBERRY_CHOCO_CAKE.get(), PrettyGuardianBlock.STRAWBERRY_CHOCO_CAKE.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, PrettyGuardianItem.MARSHMELLOW_STRAWBERRY_BURGER.get(), 2)
                .pattern("ABA")
                .pattern(" C ")
                .define('A', PrettyGuardianItem.STRAWBERRY.get())
                .define('B', PrettyGuardianItem.CREAM.get())
                .define('C', Items.BREAD)
                .unlockedBy(getHasName(PrettyGuardianItem.STRAWBERRY.get()), has(PrettyGuardianItem.STRAWBERRY.get()))
                .unlockedBy(getHasName(PrettyGuardianItem.CREAM.get()), has(PrettyGuardianItem.CREAM.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, PrettyGuardianItem.SMORE.get(), 4)
                .pattern("AA")
                .pattern("BC")
                .pattern("AA")
                .define('A', Items.WHEAT)
                .define('B', PrettyGuardianItem.CHOCOLATE.get())
                .define('C', PrettyGuardianItem.MARSHMALLOW.get())
                .unlockedBy(getHasName(PrettyGuardianItem.CHOCOLATE.get()), has(PrettyGuardianItem.CHOCOLATE.get()))
                .unlockedBy(getHasName(PrettyGuardianItem.MARSHMALLOW.get()), has(PrettyGuardianItem.MARSHMALLOW.get()))
                .save(recipeOutput);

        simplePudding(recipeOutput, PrettyGuardianItem.CARAMEL_PUDDING.get(), PrettyGuardianItem.CARAMEL.get());
        simplePudding(recipeOutput, PrettyGuardianItem.CHOCOLATE_PUDDING.get(), PrettyGuardianItem.CHOCOLATE.get());
        simplePudding(recipeOutput, PrettyGuardianItem.STRAWBERRY_PUDDING.get(), PrettyGuardianItem.STRAWBERRY.get());
        simplePudding(recipeOutput, PrettyGuardianItem.PISTACHIO_PUDDING.get(), PrettyGuardianItem.PISTACHIO.get());

        simpleCroissant(recipeOutput, PrettyGuardianItem.CHOCOLATE_CROISSANT.get(), PrettyGuardianItem.CHOCOLATE.get());
        simpleCroissant(recipeOutput, PrettyGuardianItem.STRAWBERRY_CROISSANT.get(), PrettyGuardianItem.STRAWBERRY.get());
        simpleCroissant(recipeOutput, PrettyGuardianItem.VANILLA_CROISSANT.get(), PrettyGuardianItem.VANILLA.get());
        simpleCroissant(recipeOutput, PrettyGuardianItem.PISTACHIO_CROISSANT.get(), PrettyGuardianItem.PISTACHIO.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, PrettyGuardianItem.CANDY_APPLE.get(), 1)
                .pattern("  A")
                .pattern(" B ")
                .pattern("C  ")
                .define('A', PrettyGuardianItem.CARAMEL.get())
                .define('B', Items.APPLE)
                .define('C', Items.STICK)
                .unlockedBy(getHasName(PrettyGuardianItem.CARAMEL.get()), has(PrettyGuardianItem.CARAMEL.get()))
                .unlockedBy(getHasName(Items.APPLE), has(Items.APPLE))
                .save(recipeOutput);

        simplePie(recipeOutput, PrettyGuardianBlock.STRAWBERRY_PIE.get(), PrettyGuardianItem.STRAWBERRY.get());
        simplePie(recipeOutput, PrettyGuardianBlock.CHOCOLATE_PIE.get(), PrettyGuardianItem.CHOCOLATE.get());
        simplePie(recipeOutput, PrettyGuardianBlock.APPLE_PIE.get(), Items.APPLE);
        simplePie(recipeOutput, PrettyGuardianBlock.LEMON_PIE.get(), PrettyGuardianItem.LEMON.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, PrettyGuardianBlock.MAGIC_PIE.get(), 1)
                .pattern("ABA")
                .pattern("CDC")
                .pattern("FFF")
                .define('A', Items.SUGAR)
                .define('B', PrettyGuardianItem.FAIRY_DUST.get())
                .define('C', Items.SWEET_BERRIES)
                .define('D', PrettyGuardianItem.STRAWBERRY.get())
                .define('F', Items.WHEAT)
                .unlockedBy(getHasName(PrettyGuardianItem.FAIRY_DUST.get()), has(PrettyGuardianItem.FAIRY_DUST.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PrettyGuardianItem.BUTTERFLY_NET.get(), 1)
                .pattern(" AA")
                .pattern("BAA")
                .define('A', Items.STRING)
                .define('B', Items.STICK)
                .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                .unlockedBy(getHasName(Items.STRING), has(Items.STRING))
                .save(recipeOutput);


        simpleScreen(recipeOutput, PrettyGuardianBlock.SCREEN_JAPANESE_BIRCH.get(), Blocks.BIRCH_PLANKS);
        simpleScreen(recipeOutput, PrettyGuardianBlock.SCREEN_JAPANESE_CHERRY_LOG.get(), Blocks.CHERRY_LOG);
        simpleScreen(recipeOutput, PrettyGuardianBlock.SCREEN_JAPANESE_CHERRY_PLANK.get(), Blocks.CHERRY_PLANKS);

        simpleScrool(recipeOutput, PrettyGuardianBlock.SCROLL_JAPANESE_MUSHROOM.get(), Items.RED_MUSHROOM);
        simpleScrool(recipeOutput, PrettyGuardianBlock.SCROLL_JAPANESE_BEE.get(), Items.HONEYCOMB);
        simpleScrool(recipeOutput, PrettyGuardianBlock.SCROLL_JAPANESE_AXOLOTL.get(), Items.AXOLOTL_BUCKET);
        simpleScrool(recipeOutput, PrettyGuardianBlock.SCROLL_JAPANESE_CINNAMON_ROLL.get(), Items.WHEAT);
        simpleScrool(recipeOutput, PrettyGuardianBlock.SCROLL_JAPANESE_DAY.get(), Items.ORANGE_WOOL);
        simpleScrool(recipeOutput, PrettyGuardianBlock.SCROLL_JAPANESE_NIGHT.get(), Items.BLUE_WOOL);
        simpleScrool(recipeOutput, PrettyGuardianBlock.SCROLL_JAPANESE_SUNFLOWER.get(), Items.SUNFLOWER);
        simpleScrool(recipeOutput, PrettyGuardianBlock.SCROLL_JAPANESE_DAY_2.get(), Items.PINK_WOOL);

        simpleLamp(recipeOutput, PrettyGuardianBlock.LAMP_JAPANESE_BIRCH.get(), Blocks.BIRCH_LOG);
        simpleLamp(recipeOutput, PrettyGuardianBlock.LAMP_JAPANESE_CHERRY.get(), Blocks.CHERRY_LOG);
        simpleLamp(recipeOutput, PrettyGuardianBlock.LAMP_JAPANESE_JUNGLE.get(), Blocks.JUNGLE_LOG);
        simpleLamp(recipeOutput, PrettyGuardianBlock.LAMP_JAPANESE_OAK.get(), Blocks.OAK_LOG);
        simpleLamp(recipeOutput, PrettyGuardianBlock.LAMP_JAPANESE_SPRUCE.get(), Blocks.SPRUCE_LOG);
        simpleLamp(recipeOutput, PrettyGuardianBlock.LAMP_JAPANESE_ACACIA.get(), Blocks.ACACIA_LOG);
        simpleLamp(recipeOutput, PrettyGuardianBlock.LAMP_JAPANESE_DARK_OAK.get(), Blocks.DARK_OAK_LOG);
        simpleLamp(recipeOutput, PrettyGuardianBlock.LAMP_JAPANESE_MANGROVE.get(), Blocks.MANGROVE_LOG);

        simpleLantern(recipeOutput, PrettyGuardianBlock.LANTERN_JAPANESE.get(), Items.PAPER);
        simpleLantern(recipeOutput, PrettyGuardianBlock.LANTERN_JAPANESE_SAKURA.get(), Items.PINK_DYE);
        simpleLantern(recipeOutput, PrettyGuardianBlock.LANTERN_JAPANESE_FESTIVAL.get(), Items.RED_DYE);
        doubleSimpleShapelessOneUnlockedBy(recipeOutput, PrettyGuardianBlock.LANTERN_JAPANESE_BIG.get(), PrettyGuardianBlock.LANTERN_JAPANESE.get(), PrettyGuardianBlock.LANTERN_JAPANESE.get());
        doubleSimpleShapelessOneUnlockedBy(recipeOutput, PrettyGuardianBlock.LANTERN_JAPANESE_SAKURA_BIG.get(), PrettyGuardianBlock.LANTERN_JAPANESE_SAKURA.get(), PrettyGuardianBlock.LANTERN_JAPANESE_SAKURA.get());
        doubleSimpleShapelessOneUnlockedBy(recipeOutput, PrettyGuardianBlock.LANTERN_JAPANESE_FESTIVAL_BIG.get(), PrettyGuardianBlock.LANTERN_JAPANESE_FESTIVAL.get(), PrettyGuardianBlock.LANTERN_JAPANESE_FESTIVAL.get());
        simpleThreeCake(recipeOutput, PrettyGuardianBlock.LANTERN_HUGE_JAPANESE.get(), PrettyGuardianBlock.LANTERN_JAPANESE.get());
        simpleThreeCake(recipeOutput, PrettyGuardianBlock.LANTERN_SAKURA_HUGE_JAPANESE.get(), PrettyGuardianBlock.LANTERN_JAPANESE_SAKURA.get());
        simpleThreeCake(recipeOutput, PrettyGuardianBlock.LANTERN_FESTIVAL_HUGE_JAPANESE.get(), PrettyGuardianBlock.LANTERN_JAPANESE_FESTIVAL.get());

        simpleDoor(recipeOutput, PrettyGuardianBlock.DOOR_SHOJI_BIRCH.get(), Blocks.BIRCH_PLANKS);
        simpleDoor2(recipeOutput, PrettyGuardianBlock.DOOR_SHOJI_BIRCH_SMALL.get(), Blocks.BIRCH_PLANKS);
        simpleDoor(recipeOutput, PrettyGuardianBlock.DOOR_SHOJI_BLOSSOM.get(), Blocks.CHERRY_PLANKS);
        simpleDoor2(recipeOutput, PrettyGuardianBlock.DOOR_SHOJI_BLOSSOM_SMALL.get(), Blocks.CHERRY_PLANKS);
        simpleDoor(recipeOutput, PrettyGuardianBlock.DOOR_SHOJI_CHERRY.get(), Blocks.CHERRY_LOG);
        simpleDoor2(recipeOutput, PrettyGuardianBlock.DOOR_SHOJI_CHERRY_SMALL.get(), Blocks.CHERRY_LOG);

        simpleShoji(recipeOutput, PrettyGuardianBlock.SHOJI_BIRCH.get(), Blocks.BIRCH_PLANKS);
        simpleShoji2(recipeOutput, PrettyGuardianBlock.SHOJI_BIRCH_SMALL.get(), Blocks.BIRCH_PLANKS);
        simpleShojiBase(recipeOutput, PrettyGuardianBlock.SHOJI_BIRCH_BOTTOM.get(), Blocks.BIRCH_PLANKS);
        simpleShojiBase2(recipeOutput, PrettyGuardianBlock.SHOJI_BIRCH_SMALL_BOTTOM.get(), Blocks.BIRCH_PLANKS);
        simpleShoji(recipeOutput, PrettyGuardianBlock.SHOJI_BLOSSOM.get(), Blocks.CHERRY_PLANKS);
        simpleShoji2(recipeOutput, PrettyGuardianBlock.SHOJI_BLOSSOM_SMALL.get(), Blocks.CHERRY_PLANKS);
        simpleShojiBase(recipeOutput, PrettyGuardianBlock.SHOJI_BLOSSOM_BOTTOM.get(), Blocks.CHERRY_PLANKS);
        simpleShojiBase2(recipeOutput, PrettyGuardianBlock.SHOJI_BLOSSOM_SMALL_BOTTOM.get(), Blocks.CHERRY_PLANKS);
        simpleShoji(recipeOutput, PrettyGuardianBlock.SHOJI_CHERRY.get(), Blocks.CHERRY_LOG);
        simpleShoji2(recipeOutput, PrettyGuardianBlock.SHOJI_CHERRY_SMALL.get(), Blocks.CHERRY_LOG);
        simpleShojiBase(recipeOutput, PrettyGuardianBlock.SHOJI_CHERRY_BOTTOM.get(), Blocks.CHERRY_LOG);
        simpleShojiBase2(recipeOutput, PrettyGuardianBlock.SHOJI_CHERRY_SMALL_BOTTOM.get(), Blocks.CHERRY_LOG);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PrettyGuardianBlock.PICNIC_BASKET.get(), 1)
                .pattern(" A ")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', Blocks.BAMBOO)
                .define('B', Items.PINK_WOOL)
                .unlockedBy(getHasName(Blocks.OAK_PLANKS), has(Blocks.OAK_PLANKS))
                .unlockedBy(getHasName(Items.CHEST), has(Items.CHEST))
                .save(recipeOutput);

        simpleTable(recipeOutput, PrettyGuardianBlock.TABLE_JAPANESE_BIRCH.get(), Blocks.STRIPPED_BIRCH_LOG, Blocks.BIRCH_SLAB);
        simpleTable(recipeOutput, PrettyGuardianBlock.TABLE_JAPANESE_CHERRY_PLANK.get(), Blocks.STRIPPED_CHERRY_LOG, Blocks.CHERRY_SLAB);
        simpleTable(recipeOutput, PrettyGuardianBlock.TABLE_JAPANESE_CHERRY_LOG.get(), Blocks.CHERRY_LOG, Blocks.CHERRY_WOOD);
        simpleTable(recipeOutput, PrettyGuardianBlock.TABLE_JAPANESE_OAK.get(), Blocks.STRIPPED_OAK_LOG, Blocks.OAK_SLAB);
        simpleTable(recipeOutput, PrettyGuardianBlock.TABLE_JAPANESE_SPRUCE.get(), Blocks.STRIPPED_SPRUCE_LOG, Blocks.SPRUCE_SLAB);

        simpleChair(recipeOutput, PrettyGuardianBlock.CHAIR_JAPANESE_BIRCH.get(), Blocks.STRIPPED_BIRCH_LOG, Blocks.BIRCH_SLAB);
        simpleChair(recipeOutput, PrettyGuardianBlock.CHAIR_JAPANESE_CHERRY_PLANK.get(), Blocks.STRIPPED_CHERRY_LOG, Blocks.CHERRY_SLAB);
        simpleChair(recipeOutput, PrettyGuardianBlock.CHAIR_JAPANESE_CHERRY_LOG.get(), Blocks.CHERRY_LOG, Blocks.CHERRY_WOOD);
        simpleChair(recipeOutput, PrettyGuardianBlock.CHAIR_JAPANESE_OAK.get(), Blocks.STRIPPED_OAK_LOG, Blocks.OAK_SLAB);
        simpleChair(recipeOutput, PrettyGuardianBlock.CHAIR_JAPANESE_SPRUCE.get(), Blocks.STRIPPED_SPRUCE_LOG, Blocks.SPRUCE_SLAB);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, PrettyGuardianBlock.BONZAI_CHERRY.get(), 1)
                .pattern(" A ")
                .pattern("ABA")
                .pattern(" C ")
                .define('A', Blocks.CHERRY_LEAVES)
                .define('B', Blocks.CHERRY_SAPLING)
                .define('C', Blocks.FLOWER_POT)
                .unlockedBy(getHasName(Blocks.CHERRY_LEAVES), has(Blocks.CHERRY_LEAVES))
                .unlockedBy(getHasName(Blocks.CHERRY_SAPLING), has(Blocks.CHERRY_SAPLING))
                .unlockedBy(getHasName(Blocks.FLOWER_POT), has(Blocks.FLOWER_POT))
                .save(recipeOutput);

        simpleFruitSapling(recipeOutput, PrettyGuardianBlock.BOBA_SAPLING.get(), PrettyGuardianItem.RAW_BOBA.get());
        simpleFruitSapling(recipeOutput, PrettyGuardianBlock.LEMON_SAPLING.get(), PrettyGuardianItem.LEMON.get());
        simpleFruitSapling(recipeOutput, PrettyGuardianBlock.PISTACHIO_SAPLING.get(), PrettyGuardianItem.PISTACHIO.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PrettyGuardianBlock.RANDOM_PLUSH_BOX.get(), 1)
                .pattern("ABA")
                .pattern("CDC")
                .pattern("ABA")
                .define('A', Items.STICK)
                .define('B', Items.PINK_WOOL)
                .define('C', Items.WHITE_WOOL)
                .define('D', Items.STRING)
                .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                .unlockedBy(getHasName(Items.PINK_WOOL), has(Items.PINK_WOOL))
                .unlockedBy(getHasName(Items.WHITE_WOOL), has(Items.WHITE_WOOL))
                .unlockedBy(getHasName(Items.STRING), has(Items.STRING))
                .save(recipeOutput);

        SimpleCookingRecipeBuilder.generic(Ingredient.of(PrettyGuardianItem.RAW_BOBA.get()), RecipeCategory.FOOD, PrettyGuardianItem.BOBA.get(), 0.35F, 200, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new)
                .group(roastedMarshmallowStick)
                .unlockedBy(getHasName(PrettyGuardianItem.RAW_BOBA.get()), has(PrettyGuardianItem.RAW_BOBA.get()))
                .save(recipeOutput);

        doubleSimpleShapeless(recipeOutput, PrettyGuardianItem.SQUID_STICK.get(), 1, PrettyGuardianItem.RAW_SQUID.get(), Items.STICK);

        SimpleCookingRecipeBuilder.generic(Ingredient.of(PrettyGuardianItem.SQUID_STICK.get()), RecipeCategory.FOOD, PrettyGuardianItem.SQUID_COOKED.get(), 0.35F, 200, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new)
                .group(roastedMarshmallowStick)
                .unlockedBy(getHasName(PrettyGuardianItem.SQUID_STICK.get()), has(PrettyGuardianItem.SQUID_STICK.get()))
                .unlockedBy(getHasName(PrettyGuardianItem.RAW_SQUID.get()), has(PrettyGuardianItem.RAW_SQUID.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PrettyGuardianItem.GIFT_BOX.get(), 1)
                .pattern("ABA")
                .pattern("ACA")
                .pattern("AAA")
                .define('A', Items.PAPER)
                .define('B', Items.STRING)
                .define('C', Items.PINK_DYE)
                .unlockedBy(getHasName(Items.PAPER), has(Items.PAPER))
                .unlockedBy(getHasName(Items.STRING), has(Items.STRING))
                .unlockedBy(getHasName(Items.PINK_DYE), has(Items.PINK_DYE))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PrettyGuardianBlock.LUCKY_NEKO.get(), 1)
                .pattern("A A")
                .pattern("BCB")
                .pattern("DDD")
                .define('A', Items.GOLD_INGOT)
                .define('B', Items.STRING)
                .define('C', Items.RED_DYE)
                .define('D', Blocks.GOLD_BLOCK)
                .unlockedBy(getHasName(Items.GOLD_INGOT), has(Items.GOLD_INGOT))
                .unlockedBy(getHasName(Items.STRING), has(Items.STRING))
                .unlockedBy(getHasName(Items.RED_DYE), has(Items.RED_DYE))
                .unlockedBy(getHasName(Items.GOLD_BLOCK), has(Items.GOLD_BLOCK))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PrettyGuardianItem.LOVE_LETTER.get(), 1)
                .requires(Items.PAPER)
                .requires(Items.FEATHER)
                .requires(PrettyGuardianItem.FAIRY_DUST.get())
                .unlockedBy(getHasName(Items.PAPER), has(Items.PAPER))
                .unlockedBy(getHasName(Items.FEATHER), has(Items.FEATHER))
                .unlockedBy(getHasName(PrettyGuardianItem.FAIRY_DUST.get()), has(PrettyGuardianItem.FAIRY_DUST.get()))
                .save(recipeOutput);

        simpleMoonStick(recipeOutput, PrettyGuardianItem.CUTIE_MOON_ROD.get());
        simpleMoonStick(recipeOutput, PrettyGuardianItem.ETERNAL_TIARE.get());
        simpleMoonStick(recipeOutput, PrettyGuardianItem.MOON_KALEIDOSCOPE.get());
        simpleMoonStick(recipeOutput, PrettyGuardianItem.MOON_STICK.get());
        simpleMoonStick(recipeOutput, PrettyGuardianItem.MOON_STICK_PEARL.get());
        simpleMoonStick(recipeOutput, PrettyGuardianItem.SPIRAL_HEART_MOON_ROD.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PrettyGuardianItem.CUPIDON_BOW.get(), 1)
                .pattern("ABB")
                .pattern("BCD")
                .pattern("BDD")
                .define('A', PrettyGuardianItem.PINK_SAPPHIRE.get())
                .define('B', Items.FEATHER)
                .define('C', Items.BOW)
                .define('D', PrettyGuardianItem.FAIRY_DUST.get())
                .unlockedBy(getHasName(PrettyGuardianItem.PINK_SAPPHIRE.get()), has(PrettyGuardianItem.PINK_SAPPHIRE.get()))
                .unlockedBy(getHasName(Items.FEATHER), has(Items.FEATHER))
                .unlockedBy(getHasName(Items.BOW), has(Items.BOW))
                .unlockedBy(getHasName(PrettyGuardianItem.FAIRY_DUST.get()), has(PrettyGuardianItem.FAIRY_DUST.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PrettyGuardianItem.HEART_ARROW.get(), 3)
                .pattern("A")
                .pattern("B")
                .pattern("C")
                .define('A', PrettyGuardianItem.PINK_SAPPHIRE.get())
                .define('B', Items.ARROW)
                .define('C', PrettyGuardianItem.FAIRY_DUST.get())
                .unlockedBy(getHasName(PrettyGuardianItem.PINK_SAPPHIRE.get()), has(PrettyGuardianItem.PINK_SAPPHIRE.get()))
                .unlockedBy(getHasName(Items.ARROW), has(Items.ARROW))
                .unlockedBy(getHasName(PrettyGuardianItem.FAIRY_DUST.get()), has(PrettyGuardianItem.FAIRY_DUST.get()))
                .save(recipeOutput);

        simpleShapeless(recipeOutput, RecipeCategory.MISC, PrettyGuardianItem.STRAWBERRY_SEEDS.get(), 1, PrettyGuardianItem.STRAWBERRY.get());
        simpleShapeless(recipeOutput, RecipeCategory.MISC, PrettyGuardianItem.MINT_SEEDS.get(), 1, PrettyGuardianItem.MINT.get());
        simpleShapeless(recipeOutput, RecipeCategory.MISC, PrettyGuardianItem.VANILLA_SEEDS.get(), 1, PrettyGuardianItem.VANILLA.get());

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(PrettyGuardianItem.RUBY_TEMPLATE_UPGRADE.get()), Ingredient.of(PrettyGuardianItem.PINK_SAPPHIRE_AXE.get()), Ingredient.of(PrettyGuardianItem.RUBY.get()), RecipeCategory.TOOLS, PrettyGuardianItem.RUBY_AXE.get())
                .unlocks(getHasName(PrettyGuardianItem.RUBY_TEMPLATE_UPGRADE.get()), has(PrettyGuardianItem.RUBY_TEMPLATE_UPGRADE.get()))
                .unlocks(getHasName(PrettyGuardianItem.PINK_SAPPHIRE_AXE.get()), has(PrettyGuardianItem.PINK_SAPPHIRE_AXE.get()))
                .unlocks(getHasName(PrettyGuardianItem.RUBY.get()), has(PrettyGuardianItem.RUBY.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(PrettyGuardian.MOD_ID, "ruby_axe_from_pink_sapphire_axe"));

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(PrettyGuardianItem.RUBY_TEMPLATE_UPGRADE.get()), Ingredient.of(PrettyGuardianItem.PINK_SAPPHIRE_PICKAXE.get()), Ingredient.of(PrettyGuardianItem.RUBY.get()), RecipeCategory.TOOLS, PrettyGuardianItem.RUBY_PICKAXE.get())
                .unlocks(getHasName(PrettyGuardianItem.RUBY_TEMPLATE_UPGRADE.get()), has(PrettyGuardianItem.RUBY_TEMPLATE_UPGRADE.get()))
                .unlocks(getHasName(PrettyGuardianItem.PINK_SAPPHIRE_PICKAXE.get()), has(PrettyGuardianItem.PINK_SAPPHIRE_PICKAXE.get()))
                .unlocks(getHasName(PrettyGuardianItem.RUBY.get()), has(PrettyGuardianItem.RUBY.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(PrettyGuardian.MOD_ID, "ruby_pickaxe_from_pink_sapphire_pickaxe"));

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(PrettyGuardianItem.RUBY_TEMPLATE_UPGRADE.get()), Ingredient.of(PrettyGuardianItem.PINK_SAPPHIRE_HOE.get()), Ingredient.of(PrettyGuardianItem.RUBY.get()), RecipeCategory.TOOLS, PrettyGuardianItem.RUBY_HOE.get())
                .unlocks(getHasName(PrettyGuardianItem.RUBY_TEMPLATE_UPGRADE.get()), has(PrettyGuardianItem.RUBY_TEMPLATE_UPGRADE.get()))
                .unlocks(getHasName(PrettyGuardianItem.PINK_SAPPHIRE_HOE.get()), has(PrettyGuardianItem.PINK_SAPPHIRE_HOE.get()))
                .unlocks(getHasName(PrettyGuardianItem.RUBY.get()), has(PrettyGuardianItem.RUBY.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(PrettyGuardian.MOD_ID, "ruby_hoe_from_pink_sapphire_hoe"));

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(PrettyGuardianItem.RUBY_TEMPLATE_UPGRADE.get()), Ingredient.of(PrettyGuardianItem.PINK_SAPPHIRE_SHOVEL.get()), Ingredient.of(PrettyGuardianItem.RUBY.get()), RecipeCategory.TOOLS, PrettyGuardianItem.RUBY_SHOVEL.get())
                .unlocks(getHasName(PrettyGuardianItem.RUBY_TEMPLATE_UPGRADE.get()), has(PrettyGuardianItem.RUBY_TEMPLATE_UPGRADE.get()))
                .unlocks(getHasName(PrettyGuardianItem.PINK_SAPPHIRE_SHOVEL.get()), has(PrettyGuardianItem.PINK_SAPPHIRE_SHOVEL.get()))
                .unlocks(getHasName(PrettyGuardianItem.RUBY.get()), has(PrettyGuardianItem.RUBY.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(PrettyGuardian.MOD_ID, "ruby_shovel_from_pink_sapphire_shovel"));

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(PrettyGuardianItem.RUBY_TEMPLATE_UPGRADE.get()), Ingredient.of(PrettyGuardianItem.PINK_SAPPHIRE_SWORD.get()), Ingredient.of(PrettyGuardianItem.RUBY.get()), RecipeCategory.TOOLS, PrettyGuardianItem.RUBY_SWORD.get())
                .unlocks(getHasName(PrettyGuardianItem.RUBY_TEMPLATE_UPGRADE.get()), has(PrettyGuardianItem.RUBY_TEMPLATE_UPGRADE.get()))
                .unlocks(getHasName(PrettyGuardianItem.PINK_SAPPHIRE_SWORD.get()), has(PrettyGuardianItem.PINK_SAPPHIRE_SWORD.get()))
                .unlocks(getHasName(PrettyGuardianItem.RUBY.get()), has(PrettyGuardianItem.RUBY.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(PrettyGuardian.MOD_ID, "ruby_sword_from_pink_sapphire_sword"));

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(PrettyGuardianItem.RUBY_TEMPLATE_UPGRADE.get()), Ingredient.of(PrettyGuardianItem.PINK_SAPPHIRE_BOOTS.get()), Ingredient.of(PrettyGuardianItem.RUBY.get()), RecipeCategory.TOOLS, PrettyGuardianItem.RUBY_BOOTS.get())
                .unlocks(getHasName(PrettyGuardianItem.RUBY_TEMPLATE_UPGRADE.get()), has(PrettyGuardianItem.RUBY_TEMPLATE_UPGRADE.get()))
                .unlocks(getHasName(PrettyGuardianItem.PINK_SAPPHIRE_BOOTS.get()), has(PrettyGuardianItem.PINK_SAPPHIRE_BOOTS.get()))
                .unlocks(getHasName(PrettyGuardianItem.RUBY.get()), has(PrettyGuardianItem.RUBY.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(PrettyGuardian.MOD_ID, "ruby_boots_from_pink_sapphire_boots"));

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(PrettyGuardianItem.RUBY_TEMPLATE_UPGRADE.get()), Ingredient.of(PrettyGuardianItem.PINK_SAPPHIRE_CHESTPLATE.get()), Ingredient.of(PrettyGuardianItem.RUBY.get()), RecipeCategory.TOOLS, PrettyGuardianItem.RUBY_CHESTPLATE.get())
                .unlocks(getHasName(PrettyGuardianItem.RUBY_TEMPLATE_UPGRADE.get()), has(PrettyGuardianItem.RUBY_TEMPLATE_UPGRADE.get()))
                .unlocks(getHasName(PrettyGuardianItem.PINK_SAPPHIRE_CHESTPLATE.get()), has(PrettyGuardianItem.PINK_SAPPHIRE_CHESTPLATE.get()))
                .unlocks(getHasName(PrettyGuardianItem.RUBY.get()), has(PrettyGuardianItem.RUBY.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(PrettyGuardian.MOD_ID, "ruby_chestplate_from_pink_sapphire_chestplate"));

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(PrettyGuardianItem.RUBY_TEMPLATE_UPGRADE.get()), Ingredient.of(PrettyGuardianItem.PINK_SAPPHIRE_HELMET.get()), Ingredient.of(PrettyGuardianItem.RUBY.get()), RecipeCategory.TOOLS, PrettyGuardianItem.RUBY_HELMET.get())
                .unlocks(getHasName(PrettyGuardianItem.RUBY_TEMPLATE_UPGRADE.get()), has(PrettyGuardianItem.RUBY_TEMPLATE_UPGRADE.get()))
                .unlocks(getHasName(PrettyGuardianItem.PINK_SAPPHIRE_HELMET.get()), has(PrettyGuardianItem.PINK_SAPPHIRE_HELMET.get()))
                .unlocks(getHasName(PrettyGuardianItem.RUBY.get()), has(PrettyGuardianItem.RUBY.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(PrettyGuardian.MOD_ID, "ruby_helmet_from_pink_sapphire_helmet"));

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(PrettyGuardianItem.RUBY_TEMPLATE_UPGRADE.get()), Ingredient.of(PrettyGuardianItem.PINK_SAPPHIRE_LEGGINGS.get()), Ingredient.of(PrettyGuardianItem.RUBY.get()), RecipeCategory.TOOLS, PrettyGuardianItem.RUBY_LEGGINGS.get())
                .unlocks(getHasName(PrettyGuardianItem.RUBY_TEMPLATE_UPGRADE.get()), has(PrettyGuardianItem.RUBY_TEMPLATE_UPGRADE.get()))
                .unlocks(getHasName(PrettyGuardianItem.PINK_SAPPHIRE_LEGGINGS.get()), has(PrettyGuardianItem.PINK_SAPPHIRE_LEGGINGS.get()))
                .unlocks(getHasName(PrettyGuardianItem.RUBY.get()), has(PrettyGuardianItem.RUBY.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(PrettyGuardian.MOD_ID, "ruby_leggings_from_pink_sapphire_leggings"));
    }

    protected static void simpleMoonStick(RecipeOutput finishedRecipeConsumer, ItemLike stick) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, stick, 1)
                .pattern(" A ")
                .pattern(" B ")
                .pattern(" C ")
                .define('A', PrettyGuardianItem.PINK_SAPPHIRE.get())
                .define('B', Items.BLAZE_ROD)
                .define('C', PrettyGuardianItem.FAIRY_DUST.get())
                .unlockedBy(getHasName(PrettyGuardianItem.PINK_SAPPHIRE.get()), has(PrettyGuardianItem.PINK_SAPPHIRE.get()))
                .unlockedBy(getHasName(Items.BLAZE_ROD), has(Items.BLAZE_ROD))
                .unlockedBy(getHasName(PrettyGuardianItem.FAIRY_DUST.get()), has(PrettyGuardianItem.FAIRY_DUST.get()))
                .save(finishedRecipeConsumer);
    }

    protected static void simpleMilkBucket(RecipeOutput finishedRecipeConsumer, ItemLike ingredient, ItemLike result) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result, 1)
                .pattern(" A ")
                .pattern("ABA")
                .pattern(" A ")
                .define('A', ingredient)
                .define('B', Items.MILK_BUCKET)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .unlockedBy(getHasName(Items.MILK_BUCKET), has(Items.MILK_BUCKET))
                .save(finishedRecipeConsumer);
    }

    protected static void simpleTable(RecipeOutput finishedRecipeConsumer, ItemLike table, ItemLike log, ItemLike slab) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, table, 1)
                .pattern("AAA")
                .pattern("B B")
                .pattern("B B")
                .define('A', slab)
                .define('B', log)
                .unlockedBy(getHasName(log), has(log))
                .unlockedBy(getHasName(slab), has(slab))
                .save(finishedRecipeConsumer);
    }

    protected static void simpleChair(RecipeOutput finishedRecipeConsumer, ItemLike chair, ItemLike log, ItemLike slab) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, chair, 1)
                .pattern("A  ")
                .pattern("ABA")
                .pattern("C C")
                .define('A', slab)
                .define('B', ItemTags.WOOL)
                .define('C', log)
                .unlockedBy(getHasName(log), has(log))
                .unlockedBy(getHasName(slab), has(slab))
                .save(finishedRecipeConsumer);
    }

    protected static void simpleFruitSapling(RecipeOutput finishedRecipeConsumer, ItemLike sapling, ItemLike fruit) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, sapling, 1)
                .requires(fruit)
                .requires(fruit)
                .requires(ItemTags.SAPLINGS)
                .unlockedBy(getHasName(fruit), has(fruit))
                .save(finishedRecipeConsumer);
    }

    protected static void simpleShoji(RecipeOutput finishedRecipeConsumer, ItemLike shoji, ItemLike wood) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, shoji, 3)
                .pattern("ABA")
                .pattern("BAB")
                .pattern("ABA")
                .define('A', wood)
                .define('B', Items.PAPER)
                .unlockedBy(getHasName(wood), has(wood))
                .unlockedBy(getHasName(Items.PAPER), has(Items.PAPER))
                .save(finishedRecipeConsumer);
    }
    protected static void simpleShoji2(RecipeOutput finishedRecipeConsumer, ItemLike shoji, ItemLike wood) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, shoji, 3)
                .pattern("BAB")
                .pattern("ABA")
                .pattern("BAB")
                .define('A', wood)
                .define('B', Items.PAPER)
                .unlockedBy(getHasName(wood), has(wood))
                .unlockedBy(getHasName(Items.PAPER), has(Items.PAPER))
                .save(finishedRecipeConsumer);
    }

    protected static void simpleShojiBase(RecipeOutput finishedRecipeConsumer, ItemLike shoji, ItemLike wood) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, shoji, 3)
                .pattern("ABA")
                .pattern("BAB")
                .pattern("AAA")
                .define('A', wood)
                .define('B', Items.PAPER)
                .unlockedBy(getHasName(wood), has(wood))
                .unlockedBy(getHasName(Items.PAPER), has(Items.PAPER))
                .save(finishedRecipeConsumer);
    }
    protected static void simpleShojiBase2(RecipeOutput finishedRecipeConsumer, ItemLike shoji, ItemLike wood) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, shoji, 3)
                .pattern("BAB")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', wood)
                .define('B', Items.PAPER)
                .unlockedBy(getHasName(wood), has(wood))
                .unlockedBy(getHasName(Items.PAPER), has(Items.PAPER))
                .save(finishedRecipeConsumer);
    }

    protected static void simpleDoor(RecipeOutput finishedRecipeConsumer, ItemLike door, ItemLike wood) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, door, 3)
                .pattern("BA")
                .pattern("AB")
                .pattern("BA")
                .define('A', Items.PAPER)
                .define('B', wood)
                .unlockedBy(getHasName(wood), has(wood))
                .save(finishedRecipeConsumer);
    }

    protected static void simpleDoor2(RecipeOutput finishedRecipeConsumer, ItemLike door, ItemLike wood) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, door, 3)
                .pattern("AB")
                .pattern("BA")
                .pattern("AB")
                .define('A', Items.PAPER)
                .define('B', wood)
                .unlockedBy(getHasName(wood), has(wood))
                .save(finishedRecipeConsumer);
    }

    protected static void simpleLantern(RecipeOutput finishedRecipeConsumer, ItemLike lantern, ItemLike ingredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, lantern, 1)
                .pattern("ABA")
                .pattern("ACA")
                .pattern("ABA")
                .define('A', Items.PAPER)
                .define('B', ingredient)
                .define('C', Items.TORCH)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(finishedRecipeConsumer);
    }

    protected static void simpleLamp(RecipeOutput finishedRecipeConsumer, ItemLike lamp, ItemLike wood) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, lamp, 1)
                .pattern("A A")
                .pattern("BCB")
                .pattern("D D")
                .define('A', Items.PAPER)
                .define('B', Items.STICK)
                .define('C', Items.TORCH)
                .define('D', wood)
                .unlockedBy(getHasName(wood), has(wood))
                .save(finishedRecipeConsumer);
    }

    protected static void simpleScrool(RecipeOutput finishedRecipeConsumer, ItemLike result, ItemLike ingredient1) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, result, 1)
                .pattern(" A ")
                .pattern("BCB")
                .pattern("DDD")
                .define('A', Items.STRING)
                .define('B', Items.STICK)
                .define('C', ingredient1)
                .define('D', Items.PAPER)
                .unlockedBy(getHasName(ingredient1), has(ingredient1))
                .unlockedBy(getHasName(Items.PAPER), has(Items.PAPER))
                .save(finishedRecipeConsumer);
    }

    protected static void simpleScreen(RecipeOutput finishedRecipeConsumer, ItemLike screen, ItemLike wood) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, screen, 1)
                .pattern("ABA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', wood)
                .define('B', Items.PAPER)
                .unlockedBy(getHasName(wood), has(wood))
                .unlockedBy(getHasName(Items.PAPER), has(Items.PAPER))
                .save(finishedRecipeConsumer);
    }

    protected static void simplePie(RecipeOutput finishedRecipeConsumer, ItemLike result, ItemLike ingredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, result)
                .pattern("ABC")
                .pattern("DDD")
                .define('A', Items.SUGAR)
                .define('B', ingredient)
                .define('C', Items.EGG)
                .define('D', Items.WHEAT)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(finishedRecipeConsumer);
    }

    protected static void simpleCroissant(RecipeOutput finishedRecipeConsumer, ItemLike result, ItemLike ingredient1) {
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, result, 3)
                .pattern(" AB")
                .pattern("CDE")
                .pattern("CC ")
                .define('A', Items.SUGAR)
                .define('B', ingredient1)
                .define('C', Items.WHEAT)
                .define('D', PrettyGuardianItem.CREAM.get())
                .define('E', Items.EGG)
                .unlockedBy(getHasName(ingredient1), has(ingredient1))
                .save(finishedRecipeConsumer);
    }

    protected static void simplePudding(RecipeOutput finishedRecipeConsumer, ItemLike result, ItemLike ingredient1) {
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, result)
                .pattern("ABA")
                .pattern("CDC")
                .pattern("ABA")
                .define('A', PrettyGuardianItem.AGARAGAR.get())
                .define('B', Items.SUGAR)
                .define('C', ingredient1)
                .define('D', Items.MILK_BUCKET)
                .unlockedBy(getHasName(ingredient1), has(ingredient1))
                .save(finishedRecipeConsumer);
    }

    protected static void simpleCake(RecipeOutput finishedRecipeConsumer, ItemLike result, ItemLike ingredient1, ItemLike ingredient2) {
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, result)
                .pattern("ABA")
                .pattern("CDC")
                .pattern("EEE")
                .define('A', ingredient1)
                .define('B', ingredient2)
                .define('C', Items.SUGAR)
                .define('D', Items.MILK_BUCKET)
                .define('E', Items.WHEAT)
                .unlockedBy(getHasName(ingredient1), has(ingredient1))
                .unlockedBy(getHasName(ingredient2), has(ingredient2))
                .save(finishedRecipeConsumer);
    }

    protected static void simpleThreeCake(RecipeOutput finishedRecipeConsumer, ItemLike result, ItemLike cake) {
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, result)
                .pattern("A")
                .pattern("A")
                .pattern("A")
                .define('A', cake)
                .unlockedBy(getHasName(cake), has(cake))
                .save(finishedRecipeConsumer);
    }

    protected static void simpleShapeless(RecipeOutput finishedRecipeConsumer, RecipeCategory category, ItemLike result, int resultCount, ItemLike ingredient1) {
        ShapelessRecipeBuilder.shapeless(category, result, resultCount)
                .requires(ingredient1, 1)
                .unlockedBy(getHasName(ingredient1), has(ingredient1))
                .save(finishedRecipeConsumer);
    }

    protected static void doubleSimpleShapeless(RecipeOutput finishedRecipeConsumer, ItemLike result, int resultCount, ItemLike ingredient1, ItemLike ingredient2) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, result, resultCount)
                .requires(ingredient1, 1)
                .requires(ingredient2, 1)
                .unlockedBy(getHasName(ingredient1), has(ingredient1))
                .unlockedBy(getHasName(ingredient2), has(ingredient2))
                .save(finishedRecipeConsumer);
    }

    protected static void doubleSimpleShapelessOneUnlockedBy(RecipeOutput finishedRecipeConsumer, ItemLike result, ItemLike ingredient1, ItemLike ingredient2) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, result, 1)
                .requires(ingredient1, 1)
                .requires(ingredient2, 1)
                .unlockedBy(getHasName(ingredient1), has(ingredient1))
                .save(finishedRecipeConsumer);
    }

    protected static void mojinRecipes(RecipeOutput finishedRecipeConsumer, ItemLike mojin, ItemLike flavor) {
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, mojin)
                .pattern("ABC")
                .pattern("DED")
                .pattern(" F ")
                .define('A', PrettyGuardianItem.MINT.get())
                .define('B', PrettyGuardianItem.LEMON.get())
                .define('C', flavor)
                .define('D', Items.SUGAR)
                .define('E', Items.WATER_BUCKET)
                .define('F', PrettyGuardianItem.JUICE_GLASS.get())
                .unlockedBy(getHasName(PrettyGuardianItem.JUICE_GLASS.get()), has(PrettyGuardianItem.JUICE_GLASS.get()))
                .save(finishedRecipeConsumer);
    }

    protected static void simpleBubbleTea(RecipeOutput finishedRecipeConsumer, ItemLike bubbleTea, ItemLike flavor) {
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, bubbleTea)
                .pattern("ABA")
                .pattern("CCC")
                .pattern(" D ")
                .define('A', PrettyGuardianItem.BOBA.get())
                .define('B', Items.WATER_BUCKET)
                .define('C', flavor)
                .define('D', PrettyGuardianItem.JUICE_GLASS.get())
                .unlockedBy(getHasName(PrettyGuardianItem.JUICE_GLASS.get()), has(PrettyGuardianItem.JUICE_GLASS.get()))
                .unlockedBy(getHasName(PrettyGuardianItem.BOBA.get()), has(PrettyGuardianItem.BOBA.get()))
                .save(finishedRecipeConsumer);
    }

    protected static void iceCreamRecipes(RecipeOutput finishedRecipeConsumer, ItemLike iceCream, ItemLike flavor) {
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, iceCream, 1)
                .pattern("ABC")
                .pattern("DEF")
                .define('A', Items.MILK_BUCKET)
                .define('B', Items.SNOWBALL)
                .define('C', Items.EGG)
                .define('D', Items.SUGAR)
                .define('E', PrettyGuardianItem.ICE_CREAM_CUP.get())
                .define('F', flavor)
                .unlockedBy(getHasName(PrettyGuardianItem.ICE_CREAM_CUP.get()), has(PrettyGuardianItem.ICE_CREAM_CUP.get()))
                .save(finishedRecipeConsumer);
    }

    protected static void waffleIceCreamRecipes(RecipeOutput finishedRecipeConsumer, ItemLike waffleIceCream, ItemLike flavor) {
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, waffleIceCream, 1)
                .pattern("ABC")
                .pattern("DEF")
                .define('A', Items.MILK_BUCKET)
                .define('B', Items.SNOWBALL)
                .define('C', Items.EGG)
                .define('D', Items.SUGAR)
                .define('E', PrettyGuardianItem.FISH_WAFFLE.get())
                .define('F', flavor)
                .unlockedBy(getHasName(PrettyGuardianItem.WAFFLE.get()), has(PrettyGuardianItem.WAFFLE.get()))
                .save(finishedRecipeConsumer);
    }


    protected static void blockIngotRecipes(RecipeOutput finishedRecipeConsumer, ItemLike block, ItemLike ingot) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, block)
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ingot)
                .unlockedBy(getHasName(ingot), has(ingot))
                .save(finishedRecipeConsumer, ResourceLocation.fromNamespaceAndPath(PrettyGuardian.MOD_ID, block.asItem() + "_from_" + ingot.asItem() + "_item"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ingot, 9)
                .requires(block)
                .unlockedBy(getHasName(block), has(block))
                .save(finishedRecipeConsumer, ResourceLocation.fromNamespaceAndPath(PrettyGuardian.MOD_ID,ingot.asItem() + "_item_from_" + block.asItem()));
    }

    protected static void armorRecipes(RecipeOutput finishedRecipeConsumer, Item helmet, Item chestplate, Item leggings, Item boots, ItemLike material) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, helmet)
                .pattern("AAA")
                .pattern("A A")
                .define('A', material)
                .unlockedBy(getHasName(material), has(material))
                .save(finishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, chestplate)
                .pattern("A A")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', material)
                .unlockedBy(getHasName(material), has(material))
                .save(finishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, leggings)
                .pattern("AAA")
                .pattern("A A")
                .pattern("A A")
                .define('A', material)
                .unlockedBy(getHasName(material), has(material))
                .save(finishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, boots)
                .pattern("A A")
                .pattern("A A")
                .define('A', material)
                .unlockedBy(getHasName(material), has(material))
                .save(finishedRecipeConsumer);
    }

    protected static void toolRecipes(RecipeOutput finishedRecipeConsumer, Item sword, Item pickaxe, Item axe, Item shovel, Item hoe, ItemLike material) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, sword)
                .pattern(" A ")
                .pattern(" A ")
                .pattern(" B ")
                .define('A', material)
                .define('B', Items.STICK)
                .unlockedBy(getHasName(material), has(material))
                .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                .save(finishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, pickaxe)
                .pattern("AAA")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', material)
                .define('B', Items.STICK)
                .unlockedBy(getHasName(material), has(material))
                .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                .save(finishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, axe)
                .pattern("AA ")
                .pattern("AB ")
                .pattern(" B ")
                .define('A', material)
                .define('B', Items.STICK)
                .unlockedBy(getHasName(material), has(material))
                .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                .save(finishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, shovel)
                .pattern(" A ")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', material)
                .define('B', Items.STICK)
                .unlockedBy(getHasName(material), has(material))
                .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                .save(finishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, hoe)
                .pattern(" AA")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', material)
                .define('B', Items.STICK)
                .unlockedBy(getHasName(material), has(material))
                .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                .save(finishedRecipeConsumer);
    }

}
