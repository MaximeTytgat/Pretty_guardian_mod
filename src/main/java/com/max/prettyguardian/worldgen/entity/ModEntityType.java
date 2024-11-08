package com.max.prettyguardian.worldgen.entity;

import com.max.prettyguardian.PrettyGuardian;
import com.max.prettyguardian.event.custom.JapChairEvent;
import com.max.prettyguardian.worldgen.entity.projectile.BubbleEntity;
import com.max.prettyguardian.worldgen.entity.projectile.CuteArrowEntity;
import com.max.prettyguardian.worldgen.entity.projectile.HeartEntity;
import com.max.prettyguardian.worldgen.entity.projectile.StarLightEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntityType {
    private ModEntityType() {}

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, PrettyGuardian.MOD_ID);

    public static final RegistryObject<EntityType<CuteArrowEntity>> HEART_ARROW =
            ENTITY_TYPES.register("heart_arrow", () -> EntityType.Builder.of((EntityType.EntityFactory<CuteArrowEntity>) CuteArrowEntity::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).build("heart_arrow"));

    public static final RegistryObject<EntityType<HeartEntity>> HEART =
            ENTITY_TYPES.register("heart", () -> EntityType.Builder.of((EntityType.EntityFactory<HeartEntity>) HeartEntity::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).build("heart"));
    public static final RegistryObject<EntityType<BubbleEntity>> BUBBLE =
            ENTITY_TYPES.register("bubble", () -> EntityType.Builder.of(BubbleEntity::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).build("bubble"));

    public static final RegistryObject<EntityType<StarLightEntity>> STAR_LIGHT =
            ENTITY_TYPES.register("star_light", () -> EntityType.Builder.of((EntityType.EntityFactory<StarLightEntity>) StarLightEntity::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).build("star_light"));

    public static final RegistryObject<EntityType<JapChairEvent.SeatJapChairEntity>> SEAT_JAP_CHAIR =
            ENTITY_TYPES.register("seat_jap_chair", () -> EntityType.Builder.of((EntityType.EntityFactory<JapChairEvent.SeatJapChairEntity>) JapChairEvent.SeatJapChairEntity::new, MobCategory.MISC)
                    .sized(0F, 0F).clientTrackingRange(4).updateInterval(20).build("seat_jap_chair"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
