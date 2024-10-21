package com.max.prettyguardian.blocks.entity;

import com.max.prettyguardian.PrettyGuardian;
import com.max.prettyguardian.blocks.PrettyGuardianBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    private ModBlockEntities() {
        throw new IllegalStateException("Register class");
    }

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, PrettyGuardian.MOD_ID);

    public static final RegistryObject<BlockEntityType<PicnicBasketBlockEntity>> PICNIC_BASKET_BE =
            BLOCK_ENTITIES.register("picnic_basket_be", () ->
                    BlockEntityType.Builder.of(PicnicBasketBlockEntity::new,
                            PrettyGuardianBlock.PICNIC_BASKET.get()).build(null));

    public static final RegistryObject<BlockEntityType<GemPolishingStationBlockEntity>> GEM_POLISHING_BE =
            BLOCK_ENTITIES.register("gem_polishing_station_be", () ->
                    BlockEntityType.Builder.of(GemPolishingStationBlockEntity::new,
                            PrettyGuardianBlock.GEM_POLISHING_STATION.get()).build(null));

    public static final RegistryObject<BlockEntityType<MoonAltarBlockEntity>> MOON_ALTAR_BE =
            BLOCK_ENTITIES.register("moon_altar_be", () ->
                    BlockEntityType.Builder.of(MoonAltarBlockEntity::new,
                            PrettyGuardianBlock.MOON_ALTAR.get()).build(null));

    public static final RegistryObject<BlockEntityType<SilverCrystalBlockEntity>> SILVER_CRYSTAL_BE =
            BLOCK_ENTITIES.register("silver_crystal_be", () ->
                    BlockEntityType.Builder.of(SilverCrystalBlockEntity::new,
                            PrettyGuardianBlock.SILVER_CRYSTAL.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
