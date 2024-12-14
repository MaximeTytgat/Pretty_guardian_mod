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
                .addOptional(ResourceLocation.fromNamespaceAndPath(PrettyGuardian.MOD_ID, "rainbow"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(PrettyGuardian.MOD_ID, "small_pusheen"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(PrettyGuardian.MOD_ID, "luna"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(PrettyGuardian.MOD_ID, "gudetama"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(PrettyGuardian.MOD_ID, "kuromi"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(PrettyGuardian.MOD_ID, "mymelody"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(PrettyGuardian.MOD_ID, "pompompurin"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(PrettyGuardian.MOD_ID, "hellokitty"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(PrettyGuardian.MOD_ID, "keroppi"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(PrettyGuardian.MOD_ID, "sanrio"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(PrettyGuardian.MOD_ID, "cweeper"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(PrettyGuardian.MOD_ID, "little_twin_stars"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(PrettyGuardian.MOD_ID, "hellokitty2"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(PrettyGuardian.MOD_ID, "kuma"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(PrettyGuardian.MOD_ID, "pusheen_right"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(PrettyGuardian.MOD_ID, "rilakkuma"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(PrettyGuardian.MOD_ID, "nyancat"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(PrettyGuardian.MOD_ID, "pusheen_left"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(PrettyGuardian.MOD_ID, "minitotoro"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(PrettyGuardian.MOD_ID, "korilakkuma"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(PrettyGuardian.MOD_ID, "butterfly"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(PrettyGuardian.MOD_ID, "butterfly2"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(PrettyGuardian.MOD_ID, "cherry_birch"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(PrettyGuardian.MOD_ID, "day_flowers"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(PrettyGuardian.MOD_ID, "night_flowers"));
    }
}
