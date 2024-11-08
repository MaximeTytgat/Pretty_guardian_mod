package com.max.prettyguardian.worldgen.structure;

import com.max.prettyguardian.PrettyGuardian;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public abstract class ModStructures {
    private ModStructures() {}

    public static final DeferredRegister<StructureType<?>> MODDED_STRUCTURES = DeferredRegister.create(Registries.STRUCTURE_TYPE, PrettyGuardian.MOD_ID);

    public static final RegistryObject<StructureType<CorruptedCelestialTempleStructure>> CORRUPTED_CELESTIAL_TEMPLE =
            MODDED_STRUCTURES.register("corrupted_celestial_temple", () -> () -> CorruptedCelestialTempleStructure.CODEC);

    public static void register(IEventBus eventBus) { MODDED_STRUCTURES.register(eventBus); }
}