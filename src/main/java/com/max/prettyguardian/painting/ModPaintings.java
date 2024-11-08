package com.max.prettyguardian.painting;

import com.max.prettyguardian.PrettyGuardian;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModPaintings {
    private ModPaintings() {}

    public static final DeferredRegister<PaintingVariant> PAINTING_VARIANTS =
            DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, PrettyGuardian.MOD_ID);

    public static final RegistryObject<PaintingVariant> RAINBOW = PAINTING_VARIANTS.register("rainbow", () -> new PaintingVariant(16, 16));
    public static final RegistryObject<PaintingVariant> SMALL_PUSHEEN = PAINTING_VARIANTS.register("small_pusheen", () -> new PaintingVariant(16, 16));
    public static final RegistryObject<PaintingVariant> LUNA = PAINTING_VARIANTS.register("luna", () -> new PaintingVariant(16, 16));
    public static final RegistryObject<PaintingVariant> GUDETAMA = PAINTING_VARIANTS.register("gudetama", () -> new PaintingVariant(16, 16));
    public static final RegistryObject<PaintingVariant> KUROMI = PAINTING_VARIANTS.register("kuromi", () -> new PaintingVariant(32, 16));
    public static final RegistryObject<PaintingVariant> MYMELODY = PAINTING_VARIANTS.register("mymelody", () -> new PaintingVariant(32, 16));
    public static final RegistryObject<PaintingVariant> POMPOMPURIN = PAINTING_VARIANTS.register("pompompurin", () -> new PaintingVariant(16, 32));
    public static final RegistryObject<PaintingVariant> HELLOKITTY = PAINTING_VARIANTS.register("hellokitty", () -> new PaintingVariant(16, 16));
    public static final RegistryObject<PaintingVariant> KEROPPI = PAINTING_VARIANTS.register("keroppi", () -> new PaintingVariant(32, 32));
    public static final RegistryObject<PaintingVariant> SANRIO = PAINTING_VARIANTS.register("sanrio", () -> new PaintingVariant(32, 32));
    public static final RegistryObject<PaintingVariant> CWEEPER = PAINTING_VARIANTS.register("cweeper", () -> new PaintingVariant(16, 16));
    public static final RegistryObject<PaintingVariant> LITTLE_TWIN_STARS = PAINTING_VARIANTS.register("little_twin_stars", () -> new PaintingVariant(32, 16));
    public static final RegistryObject<PaintingVariant> HELLOKITTY_2 = PAINTING_VARIANTS.register("hellokitty2", () -> new PaintingVariant(32, 16));
    public static final RegistryObject<PaintingVariant> KUMA = PAINTING_VARIANTS.register("kuma", () -> new PaintingVariant(48, 32));
    public static final RegistryObject<PaintingVariant> PUSHEEN_RIGHT = PAINTING_VARIANTS.register("pusheen_right", () -> new PaintingVariant(32, 32));
    public static final RegistryObject<PaintingVariant> RILAKKUMA = PAINTING_VARIANTS.register("rilakkuma", () -> new PaintingVariant(32, 32));
    public static final RegistryObject<PaintingVariant> NYANCAT = PAINTING_VARIANTS.register("nyancat", () -> new PaintingVariant(32, 16));
    public static final RegistryObject<PaintingVariant> PUSHEEN_LEFT = PAINTING_VARIANTS.register("pusheen_left", () -> new PaintingVariant(32, 32));
    public static final RegistryObject<PaintingVariant> MINITOTORO = PAINTING_VARIANTS.register("minitotoro", () -> new PaintingVariant(16, 32));
    public static final RegistryObject<PaintingVariant> KORILAKKUMA = PAINTING_VARIANTS.register("korilakkuma", () -> new PaintingVariant(32, 32));
    public static final RegistryObject<PaintingVariant> BUTTERFLY = PAINTING_VARIANTS.register("butterfly", () -> new PaintingVariant(32, 16));
    public static final RegistryObject<PaintingVariant> BUTTERFLY_2 = PAINTING_VARIANTS.register("butterfly2", () -> new PaintingVariant(32, 16));
    public static final RegistryObject<PaintingVariant> CHERRY_BIRCH = PAINTING_VARIANTS.register("cherry_birch", () -> new PaintingVariant(32, 32));
    public static final RegistryObject<PaintingVariant> DAY_FLOWERS = PAINTING_VARIANTS.register("day_flowers", () -> new PaintingVariant(48, 32));
    public static final RegistryObject<PaintingVariant> NIGHT_FLOWERS = PAINTING_VARIANTS.register("night_flowers", () -> new PaintingVariant(48, 32));

    public static void register(IEventBus eventBus) {
        PAINTING_VARIANTS.register(eventBus);
    }
}
