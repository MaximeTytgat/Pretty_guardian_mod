package com.max.prettyguardian.sound;

import com.max.prettyguardian.PrettyGuardian;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.JukeboxSong;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    private ModSounds() {}
    private static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, "prettyguardian");

    public static final RegistryObject<SoundEvent> SAILORMOON_MOONPRIDE = registerSoundEvents("fireflies_music_disc");
    public static final ResourceKey<JukeboxSong> SAILORMOON_MOONPRIDE_KEY = ResourceKey.create(Registries.JUKEBOX_SONG,
            ResourceLocation.fromNamespaceAndPath(PrettyGuardian.MOD_ID, "fireflies_music_disc"));

    public static final RegistryObject<SoundEvent> TAVERN = registerSoundEvents("tavern_music_disc");
    public static final ResourceKey<JukeboxSong> TAVERN_KEY = ResourceKey.create(Registries.JUKEBOX_SONG,
            ResourceLocation.fromNamespaceAndPath(PrettyGuardian.MOD_ID, "tavern_music_disc"));

    public static final RegistryObject<SoundEvent> JAPANESE_FLUTE = registerSoundEvents("the_lantern_fair_music_disc");
    public static final ResourceKey<JukeboxSong> JAPANESE_FLUTE_KEY = ResourceKey.create(Registries.JUKEBOX_SONG,
            ResourceLocation.fromNamespaceAndPath(PrettyGuardian.MOD_ID, "the_lantern_fair_music_disc"));

    public static final RegistryObject<SoundEvent> LOFI = registerSoundEvents("lofi_music_disc");
    public static final ResourceKey<JukeboxSong> LOFI_KEY = ResourceKey.create(Registries.JUKEBOX_SONG,
            ResourceLocation.fromNamespaceAndPath(PrettyGuardian.MOD_ID, "lofi_music_disc"));

    public static final RegistryObject<SoundEvent> SAILORMOON_OST = registerSoundEvents("sailormoon_ost_music_disc");
    public static final ResourceKey<JukeboxSong> SAILORMOON_OST_KEY = ResourceKey.create(Registries.JUKEBOX_SONG,
            ResourceLocation.fromNamespaceAndPath(PrettyGuardian.MOD_ID, "sailormoon_ost_music_disc"));

    public static final RegistryObject<SoundEvent> CUTE_WAND_SHOOT = registerSoundEvents("cute_want_shoot");
    public static final RegistryObject<SoundEvent> FAIRY = registerSoundEvents("fairy");
    public static final RegistryObject<SoundEvent> ETERNAL_SILVER_CRISTAL_STAFF_SHOOT = registerSoundEvents("eternal_silver_cristal_staff_shoot");
    public static final RegistryObject<SoundEvent> CELESTIAL_RABBIT_AMBIENT_SOUND_0 = registerSoundEvents("celestial_rabbit_ambient_sound_0");
    public static final RegistryObject<SoundEvent> CELESTIAL_RABBIT_AMBIENT_SOUND_1 = registerSoundEvents("celestial_rabbit_ambient_sound_1");
    public static final RegistryObject<SoundEvent> CELESTIAL_RABBIT_AMBIENT_SOUND_2 = registerSoundEvents("celestial_rabbit_ambient_sound_2");
    public static final RegistryObject<SoundEvent> CELESTIAL_RABBIT_AMBIENT_SOUND_3 = registerSoundEvents("celestial_rabbit_ambient_sound_3");
    public static final RegistryObject<SoundEvent> CELESTIAL_RABBIT_AMBIENT_SOUND_4 = registerSoundEvents("celestial_rabbit_ambient_sound_4");
    public static final RegistryObject<SoundEvent> CELESTIAL_RABBIT_SHOOT = registerSoundEvents("celestial_rabbit_shoot");
    public static final RegistryObject<SoundEvent> CELESTIAL_RABBIT_DEATH = registerSoundEvents("celestial_rabbit_death");

    private static RegistryObject<SoundEvent> registerSoundEvents(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(PrettyGuardian.MOD_ID, name)));
    }
    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
