package com.max.prettyguardian.world.entity.ai.poi;

import com.max.prettyguardian.PrettyGuardian;
import com.max.prettyguardian.blocks.PrettyGuardianBlock;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import com.google.common.collect.ImmutableSet;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class ModPoiTypes {
    private ModPoiTypes() {}

    public static final DeferredRegister<PoiType> POI_TYPES =
            DeferredRegister.create(ForgeRegistries.POI_TYPES, PrettyGuardian.MOD_ID);

    public static final RegistryObject<PoiType> MOON_TEMPLE = POI_TYPES.register("moon_temple",
            () -> new PoiType(ImmutableSet.copyOf(PrettyGuardianBlock.MOON_ALTAR.get().getStateDefinition().getPossibleStates()), 1, 1)
    );

    public static void register(IEventBus eventBus) {
        POI_TYPES.register(eventBus);
    }
}
