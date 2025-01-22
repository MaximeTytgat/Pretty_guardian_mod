package com.max.prettyguardian.datagen;

import com.max.prettyguardian.PrettyGuardian;
import com.max.prettyguardian.blocks.PrettyGuardianBlock;
import com.max.prettyguardian.item.PrettyGuardianItem;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.LinkedHashMap;

public class ModItemModelProvider extends ItemModelProvider {
    private static final String MOD_ID = PrettyGuardian.MOD_ID;
    private static final String ITEM_FOLDER = "item/";
    private static final String ITEM_GENERATED = "item/generated";
    private static final String ITEM_HANDHELD = "item/handheld";
    private static final String LAYER0 = "layer0";
    private static final String LAYER1 = "layer1";

    private static final LinkedHashMap<ResourceKey<TrimMaterial>, Float> trimMaterials = new LinkedHashMap<>();

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MOD_ID, existingFileHelper);
    }

    private void simpleItem(RegistryObject<Item> item) {
        withExistingParent(item.getId().getPath(),
                ResourceLocation.withDefaultNamespace(ITEM_GENERATED)).texture(LAYER0,
                ResourceLocation.fromNamespaceAndPath(MOD_ID, ITEM_FOLDER + item.getId().getPath()));
    }

    private void simpleItemForBlock(RegistryObject<Block> block) {
        withExistingParent(block.getId().getPath(),
                ResourceLocation.withDefaultNamespace(ITEM_GENERATED)).texture(LAYER0,
                ResourceLocation.fromNamespaceAndPath(MOD_ID, ITEM_FOLDER + block.getId().getPath()));
    }

    private void handheldItem(RegistryObject<Item> item) {
        withExistingParent(item.getId().getPath(),
                ResourceLocation.withDefaultNamespace(ITEM_HANDHELD)).texture(LAYER0,
                ResourceLocation.fromNamespaceAndPath(MOD_ID, ITEM_FOLDER + item.getId().getPath()));
    }

    @Override
    protected void registerModels() {
        simpleItem(PrettyGuardianItem.STRAWBERRY_SEEDS);
        simpleItem(PrettyGuardianItem.VANILLA_SEEDS);
        simpleItem(PrettyGuardianItem.MINT_SEEDS);
        simpleItemForBlock(PrettyGuardianBlock.CHOCOLATE_CAKE);
        simpleItemForBlock(PrettyGuardianBlock.CREAM_CAKE);
        simpleItemForBlock(PrettyGuardianBlock.RHUM_CAKE);
        simpleItemForBlock(PrettyGuardianBlock.STRAWBERRY_CAKE);
        simpleItemForBlock(PrettyGuardianBlock.BERRY_STRAWBERRY_CAKE);
        simpleItemForBlock(PrettyGuardianBlock.VELVET_CAKE);
        simpleItemForBlock(PrettyGuardianBlock.CREAM_STRAWBERRY_CAKE);
        simpleItemForBlock(PrettyGuardianBlock.STRAWBERRY_CHOCO_CAKE);
        simpleItemForBlock(PrettyGuardianBlock.THREE_VELVET_CAKE);
        simpleItemForBlock(PrettyGuardianBlock.THREE_CHOCO_CAKE);
        simpleItemForBlock(PrettyGuardianBlock.THREE_STRAWBERRY_CAKE);
        simpleItemForBlock(PrettyGuardianBlock.THREE_STRAWBERRY_CHOCO_CAKE);
        simpleItemForBlock(PrettyGuardianBlock.APPLE_PIE);
        simpleItemForBlock(PrettyGuardianBlock.CHOCOLATE_PIE);
        simpleItemForBlock(PrettyGuardianBlock.MAGIC_PIE);
        simpleItemForBlock(PrettyGuardianBlock.LEMON_PIE);
        simpleItemForBlock(PrettyGuardianBlock.STRAWBERRY_PIE);

        simpleItem(PrettyGuardianItem.STRAWBERRY);
        simpleItem(PrettyGuardianItem.VANILLA);
        simpleItem(PrettyGuardianItem.MINT);
        simpleItem(PrettyGuardianItem.PISTACHIO);
        simpleItem(PrettyGuardianItem.LEMON);
        simpleItem(PrettyGuardianItem.BOBA);
        simpleItem(PrettyGuardianItem.RAW_BOBA);
        simpleItem(PrettyGuardianItem.AGARAGAR);
        simpleItem(PrettyGuardianItem.CREAM);
        simpleItem(PrettyGuardianItem.RAW_SQUID);
        simpleItem(PrettyGuardianItem.SQUID_STICK);
        simpleItem(PrettyGuardianItem.SQUID_COOKED);

        simpleItem(PrettyGuardianItem.CHOCOLATE_STRAWBERRY);
        simpleItem(PrettyGuardianItem.SECRET_DONUT);
        simpleItem(PrettyGuardianItem.DONUT);
        simpleItem(PrettyGuardianItem.STRAWBERRY_DONUT);
        simpleItem(PrettyGuardianItem.CHOCOLATE_DONUT);
        simpleItem(PrettyGuardianItem.SECRET_MOCHI_DONUT);
        simpleItem(PrettyGuardianItem.MOCHI_DONUT);
        simpleItem(PrettyGuardianItem.STRAWBERRY_MOCHI_DONUT);
        simpleItem(PrettyGuardianItem.CHOCOLATE_MOCHI_DONUT);
        simpleItem(PrettyGuardianItem.CARAMEL);
        simpleItem(PrettyGuardianItem.CHOCOLATE);
        simpleItem(PrettyGuardianItem.STRAWBERRY_MILK_BUCKET);
        simpleItem(PrettyGuardianItem.CHOCOLATE_MILK_BUCKET);
        simpleItem(PrettyGuardianItem.VANILLA_MILK_BUCKET);
        simpleItem(PrettyGuardianItem.STRAWBERRY_MILK_CARTON);
        simpleItem(PrettyGuardianItem.CHOCOLATE_MILK_CARTON);
        simpleItem(PrettyGuardianItem.VANILLA_MILK_CARTON);
        simpleItem(PrettyGuardianItem.PISTACHIO_ICE_CREAM);
        simpleItem(PrettyGuardianItem.VANILLA_ICE_CREAM);
        simpleItem(PrettyGuardianItem.CHOCOLATE_ICE_CREAM);
        simpleItem(PrettyGuardianItem.STRAWBERRY_ICE_CREAM);
        simpleItem(PrettyGuardianItem.POKKY_ICE_CREAM);
        simpleItem(PrettyGuardianItem.APPLE_MOJITO);
        simpleItem(PrettyGuardianItem.MINT_MOJITO);
        simpleItem(PrettyGuardianItem.STRAWBERRY_MOJITO);
        simpleItem(PrettyGuardianItem.BUBBLETEA_MELON);
        simpleItem(PrettyGuardianItem.BUBBLETEA_STRAWBERRY);
        simpleItem(PrettyGuardianItem.BUBBLETEA_CARAMEL);
        simpleItem(PrettyGuardianItem.WAFFLE);
        simpleItem(PrettyGuardianItem.FISH_WAFFLE);
        simpleItem(PrettyGuardianItem.CHOCOLATE_WAFFLE);
        simpleItem(PrettyGuardianItem.ICE_CREAM_WAFFLE_VANILLA);
        simpleItem(PrettyGuardianItem.ICE_CREAM_WAFFLE_CHOCOLATE);
        simpleItem(PrettyGuardianItem.ICE_CREAM_WAFFLE_STRAWBERRY);
        simpleItem(PrettyGuardianItem.ICE_CREAM_WAFFLE_PISTACHIO);
        simpleItem(PrettyGuardianItem.CHOCOLATE_POCKY);
        simpleItem(PrettyGuardianItem.STRAWBERRY_POCKY);
        simpleItem(PrettyGuardianItem.TAKOYAKI);
        simpleItem(PrettyGuardianItem.DANGO_CARAMEL);
        simpleItem(PrettyGuardianItem.TRICOLOR_DANGO);
        simpleItem(PrettyGuardianItem.POPSICLE);
        simpleItem(PrettyGuardianItem.CREAM_POPSICLE);
        simpleItem(PrettyGuardianItem.CHOCOLATE_POPSICLE);
        simpleItem(PrettyGuardianItem.STRAWBERRY_POPSICLE);
        simpleItem(PrettyGuardianItem.CARAMEL_PUDDING);
        simpleItem(PrettyGuardianItem.CHOCOLATE_PUDDING);
        simpleItem(PrettyGuardianItem.STRAWBERRY_PUDDING);
        simpleItem(PrettyGuardianItem.PISTACHIO_PUDDING);
        simpleItem(PrettyGuardianItem.MARSHMALLOW);
        simpleItem(PrettyGuardianItem.MARSHMALLOW_STICK);
        simpleItem(PrettyGuardianItem.ROASTED_MARSHMALLOW_STICK);
        simpleItem(PrettyGuardianItem.SMORE);
        simpleItem(PrettyGuardianItem.MARSHMELLOW_STRAWBERRY_BURGER);
        simpleItem(PrettyGuardianItem.CANDY_APPLE);
        simpleItem(PrettyGuardianItem.CHOCOLATE_CROISSANT);
        simpleItem(PrettyGuardianItem.VANILLA_CROISSANT);
        simpleItem(PrettyGuardianItem.STRAWBERRY_CROISSANT);
        simpleItem(PrettyGuardianItem.PISTACHIO_CROISSANT);
        simpleItem(PrettyGuardianItem.RHUM_BOTTLE);
        simpleItem(PrettyGuardianItem.JUICE_GLASS);
        simpleItem(PrettyGuardianItem.ICE_CREAM_CUP);

        simpleItem(PrettyGuardianItem.PINK_SAPPHIRE);
        simpleItem(PrettyGuardianItem.HEART_ARROW);

        simpleItem(PrettyGuardianItem.CUTIE_MOON_ROD);
        simpleItem(PrettyGuardianItem.ETERNAL_TIARE);
        simpleItem(PrettyGuardianItem.MOON_KALEIDOSCOPE);
        simpleItem(PrettyGuardianItem.MOON_STICK);
        simpleItem(PrettyGuardianItem.MOON_STICK_PEARL);
        simpleItem(PrettyGuardianItem.SPIRAL_HEART_MOON_ROD);

        simpleItem(PrettyGuardianItem.PLUTONS_KEY);
        handheldItem(PrettyGuardianItem.PINK_SAPPHIRE_AXE);
        handheldItem(PrettyGuardianItem.PINK_SAPPHIRE_PICKAXE);
        handheldItem(PrettyGuardianItem.PINK_SAPPHIRE_SHOVEL);
        handheldItem(PrettyGuardianItem.PINK_SAPPHIRE_HOE);
        handheldItem(PrettyGuardianItem.PINK_SAPPHIRE_SWORD);

        trimmedArmorItem(PrettyGuardianItem.PINK_SAPPHIRE_HELMET);
        trimmedArmorItem(PrettyGuardianItem.PINK_SAPPHIRE_CHESTPLATE);
        trimmedArmorItem(PrettyGuardianItem.PINK_SAPPHIRE_LEGGINGS);
        trimmedArmorItem(PrettyGuardianItem.PINK_SAPPHIRE_BOOTS);

        simpleItem(PrettyGuardianItem.RUBY);
        simpleItem(PrettyGuardianItem.RAW_RUBY);
        handheldItem(PrettyGuardianItem.RUBY_AXE);
        handheldItem(PrettyGuardianItem.RUBY_PICKAXE);
        handheldItem(PrettyGuardianItem.RUBY_SHOVEL);
        handheldItem(PrettyGuardianItem.RUBY_HOE);
        handheldItem(PrettyGuardianItem.RUBY_SWORD);

        simpleItem(PrettyGuardianItem.SAILORMOON_OST_MUSIC_DISC);
        simpleItem(PrettyGuardianItem.FIREFLIES_MUSIC_DISC);
        simpleItem(PrettyGuardianItem.LOFI_MUSIC_DISC);
        simpleItem(PrettyGuardianItem.TAVERN_MUSIC_DISC);
        simpleItem(PrettyGuardianItem.THE_LANTERN_FAIR_MUSIC_DISC);

        simpleItem(PrettyGuardianItem.BUTTERFLY_NET);

        trimmedArmorItem(PrettyGuardianItem.RUBY_HELMET);
        trimmedArmorItem(PrettyGuardianItem.RUBY_CHESTPLATE);
        trimmedArmorItem(PrettyGuardianItem.RUBY_LEGGINGS);
        trimmedArmorItem(PrettyGuardianItem.RUBY_BOOTS);

        saplingItem(PrettyGuardianBlock.PISTACHIO_SAPLING);
        saplingItem(PrettyGuardianBlock.LEMON_SAPLING);
        saplingItem(PrettyGuardianBlock.BOBA_SAPLING);

        simpleBlockItemBlockTexture();

        String templatePath = "item/template_spawn_egg";

        withExistingParent(PrettyGuardianItem.STRAWBERRY_COW_EGG.getId().getPath(),
                mcLoc(templatePath));
        withExistingParent(PrettyGuardianItem.CELESTIAL_RABBIT_EGG.getId().getPath(),
                mcLoc(templatePath));
        withExistingParent(PrettyGuardianItem.FAIRY_EGG.getId().getPath(),
                mcLoc(templatePath));

        simpleItem(PrettyGuardianItem.FAIRY_DUST);
        simpleItem(PrettyGuardianItem.GIFT_BOX);
        simpleItem(PrettyGuardianItem.LOVE_LETTER);
        simpleItem(PrettyGuardianItem.RUBY_TEMPLATE_UPGRADE);
    }


    private void saplingItem(RegistryObject<Block> item) {
        withExistingParent(item.getId().getPath(),
                ResourceLocation.withDefaultNamespace(ITEM_GENERATED)).texture(LAYER0,
                ResourceLocation.fromNamespaceAndPath(MOD_ID, "block/" + item.getId().getPath()));
    }

    private void trimmedArmorItem(RegistryObject<Item> itemRegistryObject) {

        if(itemRegistryObject.get() instanceof ArmorItem armorItem) {
            trimMaterials.forEach((trimMaterial, value) -> {

                float trimValue = value;

                String armorType = switch (armorItem.getEquipmentSlot()) {
                    case HEAD -> "helmet";
                    case CHEST -> "chestplate";
                    case LEGS -> "leggings";
                    case FEET -> "boots";
                    default -> "";
                };

                String armorItemPath = ITEM_FOLDER + armorItem;
                String trimPath = "trims/items/" + armorType + "_trim_" + trimMaterial.location().getPath();
                String currentTrimName = armorItemPath + "_" + trimMaterial.location().getPath() + "_trim";
                ResourceLocation armorItemResLoc = ResourceLocation.fromNamespaceAndPath(MOD_ID, armorItemPath);
                ResourceLocation trimResLoc = ResourceLocation.fromNamespaceAndPath(MOD_ID, trimPath);
                ResourceLocation trimNameResLoc = ResourceLocation.fromNamespaceAndPath(MOD_ID, currentTrimName);

                existingFileHelper.trackGenerated(trimResLoc, PackType.CLIENT_RESOURCES, ".png", "textures");

                getBuilder(currentTrimName)
                        .parent(new ModelFile.UncheckedModelFile(ITEM_GENERATED))
                        .texture(LAYER0, armorItemResLoc)
                        .texture(LAYER1, trimResLoc);

                this.withExistingParent(itemRegistryObject.getId().getPath(),
                                mcLoc(ITEM_GENERATED))
                        .override()
                        .model(new ModelFile.UncheckedModelFile(trimNameResLoc))
                        .predicate(mcLoc("trim_type"), trimValue).end()
                        .texture(LAYER0,
                                ResourceLocation.fromNamespaceAndPath(MOD_ID,
                                        ITEM_FOLDER + itemRegistryObject.getId().getPath()));
            });
        }
    }

    private void simpleBlockItemBlockTexture() {
        withExistingParent(PrettyGuardianBlock.STRAWBERRY_CROP_FLOWER.getId().getPath(),
                ResourceLocation.withDefaultNamespace(ITEM_GENERATED)).texture(LAYER0,
                ResourceLocation.fromNamespaceAndPath(MOD_ID, "block/" + PrettyGuardianBlock.STRAWBERRY_CROP_FLOWER.getId().getPath()));
    }

    static {
        trimMaterials.put(TrimMaterials.QUARTZ, 0.1F);
        trimMaterials.put(TrimMaterials.IRON, 0.2F);
        trimMaterials.put(TrimMaterials.NETHERITE, 0.3F);
        trimMaterials.put(TrimMaterials.REDSTONE, 0.4F);
        trimMaterials.put(TrimMaterials.COPPER, 0.5F);
        trimMaterials.put(TrimMaterials.GOLD, 0.6F);
        trimMaterials.put(TrimMaterials.EMERALD, 0.7F);
        trimMaterials.put(TrimMaterials.DIAMOND, 0.8F);
        trimMaterials.put(TrimMaterials.LAPIS, 0.9F);
        trimMaterials.put(TrimMaterials.AMETHYST, 1.0F);
    }
}
