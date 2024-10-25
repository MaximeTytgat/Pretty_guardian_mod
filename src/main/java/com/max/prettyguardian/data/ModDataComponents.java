package com.max.prettyguardian.data;

import com.max.prettyguardian.PrettyGuardian;
import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.UnaryOperator;

public class ModDataComponents {
    private ModDataComponents() {}
    private static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPES =
            DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, PrettyGuardian.MOD_ID);

    public static final DataComponentType<String> LOVE_LETTER_AUTHOR = register("love_letter_author", builder ->
            builder.persistent(Codec.STRING).networkSynchronized(ByteBufCodecs.STRING_UTF8)
    ).get();

    public static final DataComponentType<String> LOVE_LETTER_TEXT = register("love_letter_text", builder ->
            builder.persistent(Codec.STRING).networkSynchronized(ByteBufCodecs.STRING_UTF8)
    ).get();

    private static RegistryObject<DataComponentType<String>> register(final String name, final UnaryOperator<DataComponentType.Builder<String>> builder) {
        return DATA_COMPONENT_TYPES.register(name, () -> builder.apply(DataComponentType.builder()).build());
    }

    public static void register(IEventBus eventBus) {
        DATA_COMPONENT_TYPES.register(eventBus);
    }
}
