package com.max.prettyguardian.entity;

import com.max.prettyguardian.PrettyGuardian;
import com.max.prettyguardian.entity.custom.ButterflyEntity;
import com.max.prettyguardian.entity.custom.CelestialRabbitEntity;
import com.max.prettyguardian.entity.custom.FairyEntity;
import com.max.prettyguardian.entity.custom.StrawberryCowEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    private ModEntities() {}
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, PrettyGuardian.MOD_ID);

    public static final RegistryObject<EntityType<StrawberryCowEntity>> STRAWBERRY_COW =
            ENTITY_TYPES.register("strawberry_cow", () -> EntityType.Builder.of(StrawberryCowEntity::new, MobCategory.CREATURE)
                    .sized(0.9F, 1.4F).build("strawberry_cow"));
    public static final RegistryObject<EntityType<CelestialRabbitEntity>> CELESTIAL_RABBIT =
            ENTITY_TYPES.register("celestial_rabbit", () -> EntityType.Builder.of(CelestialRabbitEntity::new, MobCategory.CREATURE)
                    .sized(0.5F, 0.8F).build("celestial_rabbit"));
    public static final RegistryObject<EntityType<ButterflyEntity>> BUTTERFLY =
            ENTITY_TYPES.register("butterfly", () -> EntityType.Builder.of(ButterflyEntity::new, MobCategory.CREATURE)
                    .sized(0.5F, 0.35F).build("butterfly"));
    public static final RegistryObject<EntityType<FairyEntity>> FAIRY =
            ENTITY_TYPES.register("fairy", () -> EntityType.Builder.of(FairyEntity::new, MobCategory.CREATURE)
                    .sized(0.3F, 0.3F).build("fairy"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
