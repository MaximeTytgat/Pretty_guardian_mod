package com.max.prettyguardian.client.gui.sreens.inventory;

import com.max.prettyguardian.PrettyGuardian;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {
    private ModMenuTypes() {}
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, PrettyGuardian.MOD_ID);

    public static final RegistryObject<MenuType<PicnicBasketMenu>> PICNIC_BASKET_MENU =
            registerMenuType("picnic_basket_menu", PicnicBasketMenu::new);

    public static final RegistryObject<MenuType<GemPolishingStationMenu>> GEM_POLISHING_MENU =
            registerMenuType("gem_polishing_menu", GemPolishingStationMenu::new);

    public static final RegistryObject<MenuType<MoonAltarMenu>> STAFF_MAGIC_TABLE_MENU =
            registerMenuType("staff_magic_table_menu", MoonAltarMenu::new);

    public static final RegistryObject<MenuType<GiftBoxMenu>> GIFT_BOX_MENU =
            registerMenuType("gift_box_menu", GiftBoxMenu::new);

    public static final RegistryObject<MenuType<FakeLoveLetterMenu>> LETTER_EDITOR_MENU =
            registerMenuType("letter_editor_menu", FakeLoveLetterMenu::new);

    private static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>> registerMenuType(String name, IContainerFactory<T> factory) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
