package com.max.prettyguardian.item;

import com.max.prettyguardian.util.ModTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class ModToolTiers {
    private ModToolTiers() {}

    public static final Tier SPACE_SWORD = new ForgeTier(2500, 5f, 2f, 25,
            ModTags.Blocks.NEEDS_SPACE_SWORD_TOOL,
            () -> Ingredient.of(PrettyGuardianItem.PINK_SAPPHIRE.get()),
            ModTags.Blocks.INCORRECT_SPACE_SWORD_TOOL
    );

    public static final Tier PINK_SAPPHIRE = new ForgeTier(1500, 8.0F, 3.0F, 25,
            ModTags.Blocks.NEEDS_PINK_SAPPHIRE_TOOL,
            () -> Ingredient.of(PrettyGuardianItem.PINK_SAPPHIRE.get()),
            ModTags.Blocks.INCORRECT_PINK_SAPPHIRE_TOOL
    );

    public static final Tier RUBY = new ForgeTier(3000, 9.0F, 4.0F, 65,
            ModTags.Blocks.NEEDS_RUBY_TOOL,
            () -> Ingredient.of(PrettyGuardianItem.PINK_SAPPHIRE.get()),
            ModTags.Blocks.INCORRECT_RUBY_TOOL
    );
}
