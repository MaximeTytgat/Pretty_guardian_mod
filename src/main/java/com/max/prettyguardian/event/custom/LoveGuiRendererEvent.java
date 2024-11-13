package com.max.prettyguardian.event.custom;

import com.max.prettyguardian.client.gui.sreens.LoveEffectHubOverlay;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import com.max.prettyguardian.PrettyGuardian;
import net.minecraft.client.gui.LayeredDraw;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;

import java.lang.reflect.Field;

@Mod.EventBusSubscriber(modid = PrettyGuardian.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class LoveGuiRendererEvent {
    private LoveGuiRendererEvent() {}
    private static final Field LAYERS = ObfuscationReflectionHelper.findField(Gui.class, "layers");

    @SubscribeEvent
    public static void registerLayers(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            final var minecraft = Minecraft.getInstance();
            final var gui = minecraft.gui;

            try {
                final var layers = (LayeredDraw) LAYERS.get(gui);
                registerLayers(minecraft, layers);
            } catch (final IllegalAccessException e) {
                throw new RuntimeException("Failed to get Gui layers", e);
            }
        });
    }

    private static void registerLayers(final Minecraft minecraft, final LayeredDraw layers) {
        layers.add(new LoveEffectHubOverlay(minecraft));
    }
}