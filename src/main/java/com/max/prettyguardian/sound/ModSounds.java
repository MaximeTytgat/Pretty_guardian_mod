package com.max.prettyguardian.sound;

import com.max.prettyguardian.PrettyGuardian;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    private static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, "prettyguardian");

    public static final RegistryObject<SoundEvent> SAILORMOON_MOONPRIDE = registerSoundEvents("fireflies_music_disc");
    public static final RegistryObject<SoundEvent> TAVERN = registerSoundEvents("tavern_music_disc");
    public static final RegistryObject<SoundEvent> JAPANESE_FLUTE = registerSoundEvents("the_lantern_fair_music_disc");
    public static final RegistryObject<SoundEvent> LOFI = registerSoundEvents("lofi_music_disc");
    public static final RegistryObject<SoundEvent> SAILORMOON_OST = registerSoundEvents("sailormoon_ost_music_disc");
    public static final RegistryObject<SoundEvent> CUTE_WAND_SHOOT = registerSoundEvents("cute_want_shoot");
    public static final RegistryObject<SoundEvent> ETERNAL_SILVER_CRISTAL_STAFF_SHOOT = registerSoundEvents("eternal_silver_cristal_staff_shoot");

    private static RegistryObject<SoundEvent> registerSoundEvents(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(PrettyGuardian.MOD_ID, name)));
    }
    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
