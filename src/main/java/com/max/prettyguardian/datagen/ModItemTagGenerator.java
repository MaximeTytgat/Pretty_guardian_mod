package com.max.prettyguardian.datagen;

import com.max.prettyguardian.PrettyGuardian;
import com.max.prettyguardian.blocks.PrettyGuardianBlock;
import com.max.prettyguardian.item.PrettyGuardianItem;
import com.max.prettyguardian.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {
    public ModItemTagGenerator(
            PackOutput packOutput,
            CompletableFuture<HolderLookup.Provider> providerCompletableFuture,
            CompletableFuture<TagLookup<Block>> tagLookupCompletableFuture,
            @Nullable ExistingFileHelper existingFileHelper
    ) {
        super(packOutput, providerCompletableFuture, tagLookupCompletableFuture, PrettyGuardian.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider holderLookupProvider) {
        this.tag(ItemTags.TRIMMABLE_ARMOR)
                .add(
                        PrettyGuardianItem.PINK_SAPPHIRE_HELMET.get(),
                        PrettyGuardianItem.PINK_SAPPHIRE_CHESTPLATE.get(),
                        PrettyGuardianItem.PINK_SAPPHIRE_LEGGINGS.get(),
                        PrettyGuardianItem.PINK_SAPPHIRE_BOOTS.get(),
                        PrettyGuardianItem.RUBY_HELMET.get(),
                        PrettyGuardianItem.RUBY_CHESTPLATE.get(),
                        PrettyGuardianItem.RUBY_LEGGINGS.get(),
                        PrettyGuardianItem.RUBY_BOOTS.get()
                );

        this.tag(ModTags.Items.CAKE)
                .add(
                        PrettyGuardianBlock.CHOCOLATE_CAKE.get().asItem(),
                        PrettyGuardianBlock.CREAM_CAKE.get().asItem(),
                        PrettyGuardianBlock.RHUM_CAKE.get().asItem(),
                        PrettyGuardianBlock.STRAWBERRY_CAKE.get().asItem(),
                        PrettyGuardianBlock.BERRY_STRAWBERRY_CAKE.get().asItem(),
                        PrettyGuardianBlock.VELVET_CAKE.get().asItem(),
                        PrettyGuardianBlock.CREAM_STRAWBERRY_CAKE.get().asItem(),
                        PrettyGuardianBlock.STRAWBERRY_CHOCO_CAKE.get().asItem(),
                        PrettyGuardianBlock.THREE_STRAWBERRY_CAKE.get().asItem(),
                        PrettyGuardianBlock.THREE_STRAWBERRY_CHOCO_CAKE.get().asItem(),
                        PrettyGuardianBlock.THREE_CHOCO_CAKE.get().asItem(),
                        PrettyGuardianBlock.THREE_VELVET_CAKE.get().asItem()
                );

        this.tag(ModTags.Items.HEART_ARROWS).add(PrettyGuardianItem.HEART_ARROW.get());

        this.tag(ModTags.Items.STAFF_ITEM)
                .add(
                        PrettyGuardianItem.ETERNAL_SILVER_CRISTAL_STAFF.get(),
                        PrettyGuardianItem.CUTIE_MOON_ROD.get(),
                        PrettyGuardianItem.ETERNAL_TIARE.get(),
                        PrettyGuardianItem.MOON_KALEIDOSCOPE.get(),
                        PrettyGuardianItem.MOON_STICK.get(),
                        PrettyGuardianItem.MOON_STICK_PEARL.get(),
                        PrettyGuardianItem.SPIRAL_HEART_MOON_ROD.get()
                );

        this.tag(ItemTags.MUSIC_DISCS)
                .add(
                        PrettyGuardianItem.SAILORMOON_OST_MUSIC_DISC.get(),
                        PrettyGuardianItem.FIREFLIES_MUSIC_DISC.get(),
                        PrettyGuardianItem.LOFI_MUSIC_DISC.get(),
                        PrettyGuardianItem.TAVERN_MUSIC_DISC.get(),
                        PrettyGuardianItem.THE_LANTERN_FAIR_MUSIC_DISC.get()
                );

        this.tag(ItemTags.CREEPER_DROP_MUSIC_DISCS)
                .add(
                        PrettyGuardianItem.SAILORMOON_OST_MUSIC_DISC.get(),
                        PrettyGuardianItem.FIREFLIES_MUSIC_DISC.get(),
                        PrettyGuardianItem.LOFI_MUSIC_DISC.get(),
                        PrettyGuardianItem.TAVERN_MUSIC_DISC.get(),
                        PrettyGuardianItem.THE_LANTERN_FAIR_MUSIC_DISC.get()
                );

        this.tag(ItemTags.SAPLINGS)
                .add(
                        PrettyGuardianBlock.BOBA_SAPLING.get().asItem(),
                        PrettyGuardianBlock.LEMON_SAPLING.get().asItem(),
                        PrettyGuardianBlock.PISTACHIO_SAPLING.get().asItem()
                );
    }
}
