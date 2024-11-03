package com.max.prettyguardian.item;

import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class ModArmorMaterials {
    private ModArmorMaterials() {}

    public static final Holder<ArmorMaterial> PINK_SAPPHIRE;
    public static final Holder<ArmorMaterial> RUBY;

    private static Holder<ArmorMaterial> register(String name, EnumMap<ArmorItem.Type, Integer> integer, int i, Supplier<Ingredient> ingredientSupplier, List<ArmorMaterial.Layer> layers) {
        EnumMap<ArmorItem.Type, Integer> enumMap = new EnumMap<>(ArmorItem.Type.class);
        ArmorItem.Type[] values = ArmorItem.Type.values();

        for (ArmorItem.Type type : values) {
            enumMap.put(type, integer.get(type));
        }

        return Registry.registerForHolder(BuiltInRegistries.ARMOR_MATERIAL, new ResourceLocation(name), new ArmorMaterial(enumMap, i, SoundEvents.ARMOR_EQUIP_GOLD, ingredientSupplier, layers, (float) 0.0, (float) 0.0));
    }

    public static final String PINK_SAPPHIRE_STRING = "pink_sapphire";

    static {
        PINK_SAPPHIRE = register(PINK_SAPPHIRE_STRING, Util.make(new EnumMap<>(ArmorItem.Type.class), enumMap -> {
            enumMap.put(ArmorItem.Type.BOOTS, 4);
            enumMap.put(ArmorItem.Type.LEGGINGS, 9);
            enumMap.put(ArmorItem.Type.CHESTPLATE, 6);
            enumMap.put(ArmorItem.Type.HELMET, 4);
            enumMap.put(ArmorItem.Type.BODY, 15);
        }), 15,
                () -> Ingredient.of(PrettyGuardianItem.PINK_SAPPHIRE.get()),
                List.of(
                        new ArmorMaterial.Layer(new ResourceLocation(PINK_SAPPHIRE_STRING), "", true),
                        new ArmorMaterial.Layer(new ResourceLocation(PINK_SAPPHIRE_STRING), "_overlay", false)
                )
        );

        RUBY = register("ruby", Util.make(new EnumMap<>(ArmorItem.Type.class), enumMap -> {
            enumMap.put(ArmorItem.Type.BOOTS, 5);
            enumMap.put(ArmorItem.Type.LEGGINGS, 10);
            enumMap.put(ArmorItem.Type.CHESTPLATE, 7);
            enumMap.put(ArmorItem.Type.HELMET, 5);
            enumMap.put(ArmorItem.Type.BODY, 17);
        }), 17,
                () -> Ingredient.of(PrettyGuardianItem.RUBY.get()),
                List.of(
                        new ArmorMaterial.Layer(new ResourceLocation("ruby"), "", true),
                        new ArmorMaterial.Layer(new ResourceLocation("ruby"), "_overlay", false)
                )
        );
    }
}
