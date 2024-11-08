package com.max.prettyguardian;

import com.max.prettyguardian.blocks.PrettyGuardianBlock;
import com.max.prettyguardian.item.PrettyGuardianItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class CreativeTab {
    private CreativeTab() {}
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, PrettyGuardian.MOD_ID);
    // Creates a creative tab with the id "prettyguardian:example_tab" for the example item, that is placed after the combat tab
    public static final RegistryObject<CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register("pretty_guardian", () -> CreativeModeTab.builder()
            .icon(() -> PrettyGuardianItem.CHOCOLATE.get().getDefaultInstance())
            .title(Component.literal("Pretty Guardian"))
            .displayItems((parameters, output) -> {
                output.accept(PrettyGuardianBlock.CREAM_CAKE.get());
                output.accept(PrettyGuardianBlock.STRAWBERRY_CAKE.get());
                output.accept(PrettyGuardianBlock.BERRY_STRAWBERRY_CAKE.get());
                output.accept(PrettyGuardianBlock.RHUM_CAKE.get());
                output.accept(PrettyGuardianBlock.CREAM_STRAWBERRY_CAKE.get());
                output.accept(PrettyGuardianBlock.THREE_STRAWBERRY_CAKE.get());
                output.accept(PrettyGuardianBlock.CHOCOLATE_CAKE.get());
                output.accept(PrettyGuardianBlock.THREE_CHOCO_CAKE.get());
                output.accept(PrettyGuardianBlock.STRAWBERRY_CHOCO_CAKE.get());
                output.accept(PrettyGuardianBlock.THREE_STRAWBERRY_CHOCO_CAKE.get());
                output.accept(PrettyGuardianBlock.VELVET_CAKE.get());
                output.accept(PrettyGuardianBlock.THREE_VELVET_CAKE.get());

                output.accept(PrettyGuardianItem.SECRET_DONUT.get());
                output.accept(PrettyGuardianItem.DONUT.get());
                output.accept(PrettyGuardianItem.STRAWBERRY_DONUT.get());
                output.accept(PrettyGuardianItem.CHOCOLATE_DONUT.get());
                output.accept(PrettyGuardianItem.SECRET_MOCHI_DONUT.get());
                output.accept(PrettyGuardianItem.MOCHI_DONUT.get());
                output.accept(PrettyGuardianItem.STRAWBERRY_MOCHI_DONUT.get());
                output.accept(PrettyGuardianItem.CHOCOLATE_MOCHI_DONUT.get());


                output.accept(PrettyGuardianBlock.APPLE_PIE.get());
                output.accept(PrettyGuardianBlock.CHOCOLATE_PIE.get());
                output.accept(PrettyGuardianBlock.MAGIC_PIE.get());
                output.accept(PrettyGuardianBlock.LEMON_PIE.get());
                output.accept(PrettyGuardianBlock.STRAWBERRY_PIE.get());
                output.accept(PrettyGuardianItem.CARAMEL.get());
                output.accept(PrettyGuardianItem.RAW_BOBA.get());
                output.accept(PrettyGuardianItem.BOBA.get());
                output.accept(PrettyGuardianItem.LEMON.get());
                output.accept(PrettyGuardianItem.PISTACHIO.get());
                output.accept(PrettyGuardianItem.AGARAGAR.get());
                output.accept(PrettyGuardianItem.CREAM.get());
                output.accept(PrettyGuardianItem.RAW_SQUID.get());
                output.accept(PrettyGuardianItem.SQUID_STICK.get());
                output.accept(PrettyGuardianItem.SQUID_COOKED.get());


                output.accept(PrettyGuardianItem.STRAWBERRY_SEEDS.get());
                output.accept(PrettyGuardianItem.STRAWBERRY.get());
                output.accept(PrettyGuardianItem.CHOCOLATE_STRAWBERRY.get());
                output.accept(PrettyGuardianItem.VANILLA_SEEDS.get());
                output.accept(PrettyGuardianItem.VANILLA.get());
                output.accept(PrettyGuardianItem.MINT_SEEDS.get());
                output.accept(PrettyGuardianItem.MINT.get());

                output.accept(PrettyGuardianItem.WAFFLE.get());
                output.accept(PrettyGuardianItem.FISH_WAFFLE.get());
                output.accept(PrettyGuardianItem.CHOCOLATE_WAFFLE.get());
                output.accept(PrettyGuardianItem.ICE_CREAM_WAFFLE_VANILLA.get());
                output.accept(PrettyGuardianItem.ICE_CREAM_WAFFLE_CHOCOLATE.get());
                output.accept(PrettyGuardianItem.ICE_CREAM_WAFFLE_STRAWBERRY.get());
                output.accept(PrettyGuardianItem.ICE_CREAM_WAFFLE_PISTACHIO.get());

                output.accept(PrettyGuardianItem.SMORE.get());
                output.accept(PrettyGuardianItem.MARSHMELLOW_STRAWBERRY_BURGER.get());

                output.accept(PrettyGuardianItem.STRAWBERRY_MILK_BUCKET.get());
                output.accept(PrettyGuardianItem.CHOCOLATE_MILK_BUCKET.get());
                output.accept(PrettyGuardianItem.VANILLA_MILK_BUCKET.get());
                output.accept(PrettyGuardianItem.STRAWBERRY_MILK_CARTON.get());
                output.accept(PrettyGuardianItem.CHOCOLATE_MILK_CARTON.get());
                output.accept(PrettyGuardianItem.VANILLA_MILK_CARTON.get());

                output.accept(PrettyGuardianItem.PISTACHIO_ICE_CREAM.get());
                output.accept(PrettyGuardianItem.VANILLA_ICE_CREAM.get());
                output.accept(PrettyGuardianItem.CHOCOLATE_ICE_CREAM.get());
                output.accept(PrettyGuardianItem.STRAWBERRY_ICE_CREAM.get());
                output.accept(PrettyGuardianItem.POKKY_ICE_CREAM.get());

                output.accept(PrettyGuardianItem.APPLE_MOJITO.get());
                output.accept(PrettyGuardianItem.MINT_MOJITO.get());
                output.accept(PrettyGuardianItem.STRAWBERRY_MOJITO.get());
                output.accept(PrettyGuardianItem.BUBBLETEA_MELON.get());
                output.accept(PrettyGuardianItem.BUBBLETEA_STRAWBERRY.get());
                output.accept(PrettyGuardianItem.BUBBLETEA_CARAMEL.get());

                output.accept(PrettyGuardianItem.POPSICLE.get());
                output.accept(PrettyGuardianItem.CREAM_POPSICLE.get());
                output.accept(PrettyGuardianItem.CHOCOLATE_POPSICLE.get());
                output.accept(PrettyGuardianItem.STRAWBERRY_POPSICLE.get());
                output.accept(PrettyGuardianItem.CHOCOLATE_POCKY.get());
                output.accept(PrettyGuardianItem.STRAWBERRY_POCKY.get());
                output.accept(PrettyGuardianItem.TAKOYAKI.get());
                output.accept(PrettyGuardianItem.DANGO_CARAMEL.get());
                output.accept(PrettyGuardianItem.TRICOLOR_DANGO.get());

                output.accept(PrettyGuardianItem.CARAMEL_PUDDING.get());
                output.accept(PrettyGuardianItem.CHOCOLATE_PUDDING.get());
                output.accept(PrettyGuardianItem.STRAWBERRY_PUDDING.get());
                output.accept(PrettyGuardianItem.PISTACHIO_PUDDING.get());

                output.accept(PrettyGuardianItem.MARSHMALLOW.get());
                output.accept(PrettyGuardianItem.MARSHMALLOW_STICK.get());
                output.accept(PrettyGuardianItem.ROASTED_MARSHMALLOW_STICK.get());

                output.accept(PrettyGuardianItem.CANDY_APPLE.get());

                output.accept(PrettyGuardianItem.CHOCOLATE_CROISSANT.get());
                output.accept(PrettyGuardianItem.VANILLA_CROISSANT.get());
                output.accept(PrettyGuardianItem.STRAWBERRY_CROISSANT.get());
                output.accept(PrettyGuardianItem.PISTACHIO_CROISSANT.get());
                output.accept(PrettyGuardianItem.RHUM_BOTTLE.get());

                output.accept(PrettyGuardianItem.JUICE_GLASS.get());
                output.accept(PrettyGuardianItem.ICE_CREAM_CUP.get());

//                output.accept(PrettyGuardianItem.RAW_PINK_SAPPHIRE.get());
                output.accept(PrettyGuardianItem.PINK_SAPPHIRE.get());
                output.accept(PrettyGuardianBlock.PINK_SAPPHIRE_BLOCK.get());
                output.accept(PrettyGuardianBlock.PINK_SAPPHIRE_ORE.get());
                output.accept(PrettyGuardianBlock.DEEPSLATE_PINK_SAPPHIRE_ORE.get());

                output.accept(PrettyGuardianItem.PINK_SAPPHIRE_SWORD.get());
                output.accept(PrettyGuardianItem.PINK_SAPPHIRE_PICKAXE.get());
                output.accept(PrettyGuardianItem.PINK_SAPPHIRE_AXE.get());
                output.accept(PrettyGuardianItem.PINK_SAPPHIRE_SHOVEL.get());
                output.accept(PrettyGuardianItem.PINK_SAPPHIRE_HOE.get());

                output.accept(PrettyGuardianItem.PINK_SAPPHIRE_HELMET.get());
                output.accept(PrettyGuardianItem.PINK_SAPPHIRE_CHESTPLATE.get());
                output.accept(PrettyGuardianItem.PINK_SAPPHIRE_LEGGINGS.get());
                output.accept(PrettyGuardianItem.PINK_SAPPHIRE_BOOTS.get());

                output.accept(PrettyGuardianItem.RUBY.get());
//                output.accept(PrettyGuardianItem.RAW_RUBY.get());
                output.accept(PrettyGuardianBlock.RUBY_BLOCK.get());
                output.accept(PrettyGuardianBlock.RUBY_ORE.get());
                output.accept(PrettyGuardianBlock.DEEPSLATE_RUBY_ORE.get());

                output.accept(PrettyGuardianItem.RUBY_SWORD.get());
                output.accept(PrettyGuardianItem.RUBY_PICKAXE.get());
                output.accept(PrettyGuardianItem.RUBY_AXE.get());
                output.accept(PrettyGuardianItem.RUBY_SHOVEL.get());
                output.accept(PrettyGuardianItem.RUBY_HOE.get());

                output.accept(PrettyGuardianItem.RUBY_HELMET.get());
                output.accept(PrettyGuardianItem.RUBY_CHESTPLATE.get());
                output.accept(PrettyGuardianItem.RUBY_LEGGINGS.get());
                output.accept(PrettyGuardianItem.RUBY_BOOTS.get());

                output.accept(PrettyGuardianItem.ETERNAL_SILVER_CRISTAL_STAFF.get());

                output.accept(PrettyGuardianItem.CUPIDON_BOW.get());
                output.accept(PrettyGuardianItem.HEART_ARROW.get());

                output.accept(PrettyGuardianItem.CUTIE_MOON_ROD.get());
                output.accept(PrettyGuardianItem.ETERNAL_TIARE.get());
                output.accept(PrettyGuardianItem.MOON_KALEIDOSCOPE.get());
                output.accept(PrettyGuardianItem.MOON_STICK.get());
                output.accept(PrettyGuardianItem.MOON_STICK_PEARL.get());
                output.accept(PrettyGuardianItem.SPIRAL_HEART_MOON_ROD.get());

                output.accept(PrettyGuardianItem.PLUTONS_KEY.get());
                output.accept(PrettyGuardianItem.NEPTUNES_MIRROR.get());
                output.accept(PrettyGuardianItem.SPACE_SWORD.get());

                output.accept(PrettyGuardianBlock.PICNIC_BASKET.get());
                output.accept(PrettyGuardianItem.SAILORMOON_OST_MUSIC_DISC.get());
                output.accept(PrettyGuardianItem.FIREFLIES_MUSIC_DISC.get());
                output.accept(PrettyGuardianItem.LOFI_MUSIC_DISC.get());
                output.accept(PrettyGuardianItem.TAVERN_MUSIC_DISC.get());
                output.accept(PrettyGuardianItem.THE_LANTERN_FAIR_MUSIC_DISC.get());

                output.accept(PrettyGuardianBlock.CHOCOLATE_BLOCK.get());
                output.accept(PrettyGuardianBlock.MARSHMELLO_BLOCK.get());
                output.accept(PrettyGuardianBlock.ROASTED_MARSHMELLO_BLOCK.get());

                output.accept(PrettyGuardianBlock.PISTACHIO_LEAVES_CROP.get());
                output.accept(PrettyGuardianBlock.PISTACHIO_SAPLING.get());

                output.accept(PrettyGuardianBlock.LEMON_LEAVES_CROP.get());
                output.accept(PrettyGuardianBlock.LEMON_SAPLING.get());

                output.accept(PrettyGuardianBlock.BOBA_LEAVES_CROP.get());
                output.accept(PrettyGuardianBlock.BOBA_SAPLING.get());

                output.accept(PrettyGuardianBlock.MYMELODY_PLUSH.get());
                output.accept(PrettyGuardianBlock.KUROMI_PLUSH.get());
                output.accept(PrettyGuardianBlock.CAVALIER_PLUSH.get());
                output.accept(PrettyGuardianBlock.TEDDYBEAR_PLUSH.get());
                output.accept(PrettyGuardianBlock.RABBIT_PLUSH.get());
                output.accept(PrettyGuardianBlock.COW_PLUSH.get());
                output.accept(PrettyGuardianBlock.CINNAMOROLL_PLUSH.get());
                output.accept(PrettyGuardianBlock.BABYYODA_PLUSH.get());
                output.accept(PrettyGuardianBlock.CAPPUCCINO_PLUSH.get());
                output.accept(PrettyGuardianBlock.CHIFFON_PLUSH.get());
                output.accept(PrettyGuardianBlock.EXPRESSO_PLUSH.get());
                output.accept(PrettyGuardianBlock.MOCHA_PLUSH.get());
                output.accept(PrettyGuardianBlock.PRINCESS_PORON_PLUSH.get());
                output.accept(PrettyGuardianBlock.PLUSH_BEAR_HUGE.get());
                output.accept(PrettyGuardianBlock.RANDOM_PLUSH_BOX.get());


                output.accept(PrettyGuardianBlock.LANTERN_JAPANESE.get());
                output.accept(PrettyGuardianBlock.LANTERN_JAPANESE_SAKURA.get());
                output.accept(PrettyGuardianBlock.LANTERN_JAPANESE_FESTIVAL.get());

                output.accept(PrettyGuardianBlock.LANTERN_JAPANESE_BIG.get());
                output.accept(PrettyGuardianBlock.LANTERN_JAPANESE_SAKURA_BIG.get());
                output.accept(PrettyGuardianBlock.LANTERN_JAPANESE_FESTIVAL_BIG.get());

                output.accept(PrettyGuardianBlock.LANTERN_HUGE_JAPANESE.get());
                output.accept(PrettyGuardianBlock.LANTERN_SAKURA_HUGE_JAPANESE.get());
                output.accept(PrettyGuardianBlock.LANTERN_FESTIVAL_HUGE_JAPANESE.get());

                output.accept(PrettyGuardianBlock.LAMP_JAPANESE_OAK.get());
                output.accept(PrettyGuardianBlock.LAMP_JAPANESE_BIRCH.get());
                output.accept(PrettyGuardianBlock.LAMP_JAPANESE_SPRUCE.get());
                output.accept(PrettyGuardianBlock.LAMP_JAPANESE_JUNGLE.get());
                output.accept(PrettyGuardianBlock.LAMP_JAPANESE_ACACIA.get());
                output.accept(PrettyGuardianBlock.LAMP_JAPANESE_DARK_OAK.get());
                output.accept(PrettyGuardianBlock.LAMP_JAPANESE_MANGROVE.get());
                output.accept(PrettyGuardianBlock.LAMP_JAPANESE_CHERRY.get());

                output.accept(PrettyGuardianBlock.SCREEN_JAPANESE_CHERRY_PLANK.get());
                output.accept(PrettyGuardianBlock.SCREEN_JAPANESE_CHERRY_LOG.get());
                output.accept(PrettyGuardianBlock.SCREEN_JAPANESE_BIRCH.get());

                output.accept(PrettyGuardianBlock.SHOJI_BIRCH.get());
                output.accept(PrettyGuardianBlock.SHOJI_BIRCH_BOTTOM.get());
                output.accept(PrettyGuardianBlock.SHOJI_BIRCH_SMALL.get());
                output.accept(PrettyGuardianBlock.SHOJI_BIRCH_SMALL_BOTTOM.get());

                output.accept(PrettyGuardianBlock.SHOJI_BLOSSOM.get());
                output.accept(PrettyGuardianBlock.SHOJI_BLOSSOM_BOTTOM.get());
                output.accept(PrettyGuardianBlock.SHOJI_BLOSSOM_SMALL.get());
                output.accept(PrettyGuardianBlock.SHOJI_BLOSSOM_SMALL_BOTTOM.get());

                output.accept(PrettyGuardianBlock.SHOJI_CHERRY.get());
                output.accept(PrettyGuardianBlock.SHOJI_CHERRY_BOTTOM.get());
                output.accept(PrettyGuardianBlock.SHOJI_CHERRY_SMALL.get());
                output.accept(PrettyGuardianBlock.SHOJI_CHERRY_SMALL_BOTTOM.get());

                output.accept(PrettyGuardianBlock.SCROLL_JAPANESE_AXOLOTL.get());
                output.accept(PrettyGuardianBlock.SCROLL_JAPANESE_BEE.get());
                output.accept(PrettyGuardianBlock.SCROLL_JAPANESE_CINNAMON_ROLL.get());
                output.accept(PrettyGuardianBlock.SCROLL_JAPANESE_MUSHROOM.get());
                output.accept(PrettyGuardianBlock.SCROLL_JAPANESE_DAY.get());
                output.accept(PrettyGuardianBlock.SCROLL_JAPANESE_NIGHT.get());
                output.accept(PrettyGuardianBlock.SCROLL_JAPANESE_DAY_2.get());
                output.accept(PrettyGuardianBlock.SCROLL_JAPANESE_SUNFLOWER.get());

                output.accept(PrettyGuardianBlock.DOOR_SHOJI_BLOSSOM.get());
                output.accept(PrettyGuardianBlock.DOOR_SHOJI_BLOSSOM_SMALL.get());
                output.accept(PrettyGuardianBlock.DOOR_SHOJI_BIRCH.get());
                output.accept(PrettyGuardianBlock.DOOR_SHOJI_BIRCH_SMALL.get());
                output.accept(PrettyGuardianBlock.DOOR_SHOJI_CHERRY.get());
                output.accept(PrettyGuardianBlock.DOOR_SHOJI_CHERRY_SMALL.get());

                output.accept(PrettyGuardianBlock.TABLE_JAPANESE_OAK.get());
                output.accept(PrettyGuardianBlock.TABLE_JAPANESE_SPRUCE.get());
                output.accept(PrettyGuardianBlock.TABLE_JAPANESE_BIRCH.get());
                output.accept(PrettyGuardianBlock.TABLE_JAPANESE_CHERRY_PLANK.get());
                output.accept(PrettyGuardianBlock.TABLE_JAPANESE_CHERRY_LOG.get());

                output.accept(PrettyGuardianBlock.CHAIR_JAPANESE_OAK.get());
                output.accept(PrettyGuardianBlock.CHAIR_JAPANESE_SPRUCE.get());
                output.accept(PrettyGuardianBlock.CHAIR_JAPANESE_BIRCH.get());
                output.accept(PrettyGuardianBlock.CHAIR_JAPANESE_CHERRY_PLANK.get());
                output.accept(PrettyGuardianBlock.CHAIR_JAPANESE_CHERRY_LOG.get());

                output.accept(PrettyGuardianBlock.BONZAI_CHERRY.get());
                output.accept(PrettyGuardianBlock.LUCKY_NEKO.get());

                output.accept(PrettyGuardianBlock.SEA_SHELL.get());

                output.accept(PrettyGuardianItem.FAIRY_DUST.get());
                output.accept(PrettyGuardianItem.GIFT_BOX.get());
                output.accept(PrettyGuardianItem.LOVE_LETTER.get());
                output.accept(PrettyGuardianItem.RUBY_TEMPLATE_UPGRADE.get());
//                output.accept(PrettyGuardianBlock.GEM_POLISHING_STATION.get());
                output.accept(PrettyGuardianBlock.MOON_ALTAR.get());

                output.accept(PrettyGuardianItem.BUTTERFLY_NET.get());
                output.accept(PrettyGuardianItem.ADMIRAL_BUTTERFLY_EGG.get());
                output.accept(PrettyGuardianItem.APOLLO_BUTTERFLY_EGG.get());
                output.accept(PrettyGuardianItem.DUSK_BUTTERFLY_EGG.get());
                output.accept(PrettyGuardianItem.LEMON_BUTTERFLY_EGG.get());
                output.accept(PrettyGuardianItem.MORPHO_BUTTERFLY_EGG.get());
                output.accept(PrettyGuardianItem.ORCHID_BUTTERFLY_EGG.get());
                output.accept(PrettyGuardianItem.PEACOCK_BUTTERFLY_EGG.get());
                output.accept(PrettyGuardianItem.PINK_BUTTERFLY_EGG.get());
                output.accept(PrettyGuardianItem.SKIPPER_BUTTERFLY_EGG.get());
                output.accept(PrettyGuardianItem.VIOLETTE_BUTTERFLY_EGG.get());
                output.accept(PrettyGuardianItem.FAIRY_EGG.get());
                output.accept(PrettyGuardianItem.STRAWBERRY_COW_EGG.get());
                output.accept(PrettyGuardianItem.CELESTIAL_RABBIT_EGG.get());

                output.accept(PrettyGuardianBlock.SILVER_CRYSTAL.get());
            }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
