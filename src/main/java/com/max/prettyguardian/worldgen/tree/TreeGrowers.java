package com.max.prettyguardian.worldgen.tree;

import com.max.prettyguardian.worldgen.ModConfiguredFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class TreeGrowers {
    private TreeGrowers() {}

    public static final TreeGrower PISTACHIO = new TreeGrower(
            "pistachio",
            Optional.empty(),
            Optional.of(ModConfiguredFeatures.PISTACHIO_KEY),
            Optional.empty()
    );

    public static final TreeGrower LEMON = new TreeGrower(
            "lemon",
            Optional.empty(),
            Optional.of(ModConfiguredFeatures.LEMON_KEY),
            Optional.empty()
    );

    public static final TreeGrower BOBA = new TreeGrower(
            "boba",
            Optional.empty(),
            Optional.of(ModConfiguredFeatures.BOBA_KEY),
            Optional.empty()
    );
}
