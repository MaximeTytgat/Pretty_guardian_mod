package com.max.prettyguardian.util;

import com.max.prettyguardian.PrettyGuardian;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    private ModTags() {}

    public static class Blocks {
        private Blocks() {}

        public static final TagKey<Block> CAKE = tag("cake");
        public static final TagKey<Block> JAP_TABLE = tag("jap_table");

        public static final TagKey<Block> NEEDS_PINK_SAPPHIRE_TOOL = tag("needs_pink_sapphire_tool");
        public static final TagKey<Block> INCORRECT_PINK_SAPPHIRE_TOOL = tag("incorrect_pink_sapphire_tool");

        public static final TagKey<Block> NEEDS_SPACE_SWORD_TOOL = tag("needs_space_sword_tool");
        public static final TagKey<Block> INCORRECT_SPACE_SWORD_TOOL = tag("incorrect_space_sword_tool");

        public static final TagKey<Block> NEEDS_RUBY_TOOL = tag("needs_ruby_tool");
        public static final TagKey<Block> INCORRECT_RUBY_TOOL = tag("incorrect_ruby_tool");

        public static final TagKey<Block> SAPLING = tag("sapling");

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(PrettyGuardian.MOD_ID, name));
        }
    }
    public static class Items {
        private Items() {}

        public static final TagKey<Item> HEART_ARROWS = tag("heart_arrows");
        public static final TagKey<Item> STAFF_ITEM = tag("staff_item");

        public static final TagKey<Item> CAKE = tag("cake");

        public static final TagKey<Item> SPACE_SWORD = tag("space_sword");
        public static final TagKey<Item> SPACE_SWORD_ENCHANTABLE = tag("space_sword_enchantable");

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(PrettyGuardian.MOD_ID, name));
        }
    }

    public static class PoiTypeTags {
        private PoiTypeTags() {}

        public static final TagKey<PoiType> MOON_TEMPLE = tag();

        private static TagKey<PoiType> tag() {
            return create();
        }

        private static TagKey<PoiType> create() {
            return TagKey.create(Registries.POINT_OF_INTEREST_TYPE, new ResourceLocation("moon_temple"));
        }
    }
}
