package com.max.prettyguardian.datagen;

import com.max.prettyguardian.PrettyGuardian;
import com.max.prettyguardian.blocks.PrettyGuardianBlock;
import com.max.prettyguardian.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, PrettyGuardian.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(@NotNull HolderLookup.Provider provider) {
        this.tag(ModTags.Blocks.CAKE).add(
                PrettyGuardianBlock.CHOCOLATE_CAKE.get(),
                PrettyGuardianBlock.THREE_CHOCO_CAKE.get(),
                PrettyGuardianBlock.THREE_STRAWBERRY_CAKE.get(),
                PrettyGuardianBlock.THREE_STRAWBERRY_CHOCO_CAKE.get()
        );

        this.tag(ModTags.Blocks.JAP_TABLE).add(
                PrettyGuardianBlock.TABLE_JAPANESE_OAK.get(),
                PrettyGuardianBlock.TABLE_JAPANESE_BIRCH.get(),
                PrettyGuardianBlock.TABLE_JAPANESE_SPRUCE.get(),
                PrettyGuardianBlock.TABLE_JAPANESE_CHERRY_LOG.get(),
                PrettyGuardianBlock.TABLE_JAPANESE_CHERRY_PLANK.get()
        );

        this.tag(BlockTags.SAPLINGS).add(
                PrettyGuardianBlock.BOBA_SAPLING.get(),
                PrettyGuardianBlock.LEMON_SAPLING.get(),
                PrettyGuardianBlock.PISTACHIO_SAPLING.get()
        );

        this.tag(BlockTags.LEAVES).add(
                PrettyGuardianBlock.BOBA_LEAVES_CROP.get(),
                PrettyGuardianBlock.LEMON_LEAVES_CROP.get(),
                PrettyGuardianBlock.PISTACHIO_LEAVES_CROP.get()
        );
    }
}
