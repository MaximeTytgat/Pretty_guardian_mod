package com.max.prettyguardian.component;

import com.max.prettyguardian.PrettyGuardian;
import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.function.UnaryOperator;

public class ModDataComponentTypes {
    private ModDataComponentTypes() {}
    private static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPES =
            DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, PrettyGuardian.MOD_ID);

    public static final RegistryObject<DataComponentType<String>> LOVE_LETTER_AUTHOR = register("love_letter_author",
            builder -> builder
                    .persistent(Codec.STRING)
                    .networkSynchronized(ByteBufCodecs.STRING_UTF8)
    );

    public static final RegistryObject<DataComponentType<String>> LOVE_LETTER_TEXT = register("love_letter_text",
            builder -> builder
                    .persistent(Codec.STRING)
                    .networkSynchronized(ByteBufCodecs.STRING_UTF8)
    );

    public static final RegistryObject<DataComponentType<ItemStack>> GIFT_BOX_INVENTORY = register("gift_box_inventory",
            builder -> builder
                    .persistent(ItemStack.OPTIONAL_CODEC)
                    .networkSynchronized(ItemStack.OPTIONAL_STREAM_CODEC)
                    .cacheEncoding()
    );

    public static final RegistryObject<DataComponentType<List<ItemStack>>> PICNIC_BASKET_INVENTORY = register("picnic_basket_inventory",
            builder -> builder
                    .persistent(ItemStack.OPTIONAL_CODEC.listOf())
                    .networkSynchronized(ItemStack.OPTIONAL_STREAM_CODEC.apply(ByteBufCodecs.list()))
                    .cacheEncoding()
    );

    public static final RegistryObject<DataComponentType<Boolean>> PLAYER_ENTITY_ON_SHOULDER = register("player_entity_on_shoulder",
            builder -> builder
                    .persistent(Codec.BOOL)
                    .networkSynchronized(ByteBufCodecs.BOOL)
    );

    private static <T> RegistryObject<DataComponentType<T>> register(final String name, final UnaryOperator<DataComponentType.Builder<T>> builder) {
        return DATA_COMPONENT_TYPES.register(name, () -> builder.apply(DataComponentType.builder()).build());
    }

    public static void register(IEventBus eventBus) {
        DATA_COMPONENT_TYPES.register(eventBus);
    }
}
