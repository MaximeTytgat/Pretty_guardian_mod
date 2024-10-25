package com.max.prettyguardian.datagen;

import com.max.prettyguardian.PrettyGuardian;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.PaintingVariantTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.PaintingVariantTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModPaintingVariantTagProvider extends PaintingVariantTagsProvider {
    public ModPaintingVariantTagProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> providerCompletableFuture, @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, providerCompletableFuture, PrettyGuardian.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(@NotNull HolderLookup.Provider provider) {
        this.tag(PaintingVariantTags.PLACEABLE)
                .addOptional(new ResourceLocation(PrettyGuardian.MOD_ID, "rainbow"))
                .addOptional(new ResourceLocation(PrettyGuardian.MOD_ID, "small_pusheen"))
                .addOptional(new ResourceLocation(PrettyGuardian.MOD_ID, "luna"))
                .addOptional(new ResourceLocation(PrettyGuardian.MOD_ID, "gudetama"))
                .addOptional(new ResourceLocation(PrettyGuardian.MOD_ID, "kuromi"))
                .addOptional(new ResourceLocation(PrettyGuardian.MOD_ID, "mymelody"))
                .addOptional(new ResourceLocation(PrettyGuardian.MOD_ID, "pompompurin"))
                .addOptional(new ResourceLocation(PrettyGuardian.MOD_ID, "hellokitty"))
                .addOptional(new ResourceLocation(PrettyGuardian.MOD_ID, "keroppi"))
                .addOptional(new ResourceLocation(PrettyGuardian.MOD_ID, "sanrio"))
                .addOptional(new ResourceLocation(PrettyGuardian.MOD_ID, "cweeper"))
                .addOptional(new ResourceLocation(PrettyGuardian.MOD_ID, "little_twin_stars"))
                .addOptional(new ResourceLocation(PrettyGuardian.MOD_ID, "hellokitty2"))
                .addOptional(new ResourceLocation(PrettyGuardian.MOD_ID, "kuma"))
                .addOptional(new ResourceLocation(PrettyGuardian.MOD_ID, "pusheen_right"))
                .addOptional(new ResourceLocation(PrettyGuardian.MOD_ID, "rilakkuma"))
                .addOptional(new ResourceLocation(PrettyGuardian.MOD_ID, "nyancat"))
                .addOptional(new ResourceLocation(PrettyGuardian.MOD_ID, "pusheen_left"))
                .addOptional(new ResourceLocation(PrettyGuardian.MOD_ID, "minitotoro"))
                .addOptional(new ResourceLocation(PrettyGuardian.MOD_ID, "korilakkuma"))
                .addOptional(new ResourceLocation(PrettyGuardian.MOD_ID, "butterfly"))
                .addOptional(new ResourceLocation(PrettyGuardian.MOD_ID, "butterfly2"))
                .addOptional(new ResourceLocation(PrettyGuardian.MOD_ID, "cherry_birch"))
                .addOptional(new ResourceLocation(PrettyGuardian.MOD_ID, "day_flowers"))
                .addOptional(new ResourceLocation(PrettyGuardian.MOD_ID, "night_flowers"));
    }
}
