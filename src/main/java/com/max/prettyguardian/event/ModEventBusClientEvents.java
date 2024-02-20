package com.max.prettyguardian.event;

import com.max.prettyguardian.PrettyGuardian;
import com.max.prettyguardian.blocks.entity.ModBlockEntities;
import com.max.prettyguardian.blocks.entity.renderer.MoonAltarBlockEntityRenderer;
import com.max.prettyguardian.entity.client.butterfly.ButterflyModel;
import com.max.prettyguardian.entity.client.celestialrabbit.CelestialRabbitModel;
import com.max.prettyguardian.entity.client.ModModelLayers;
import com.max.prettyguardian.entity.client.fairy.FairyModel;
import com.max.prettyguardian.particle.ModParticles;
import com.max.prettyguardian.particle.custom.BubbleParticles;
import com.max.prettyguardian.particle.custom.PinkCritParticles;
import com.max.prettyguardian.particle.custom.PinkHeartParticles;
import com.max.prettyguardian.particle.custom.StarLightParticles;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.CowModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PrettyGuardian.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {

    @SubscribeEvent
    public static void registerParticleFactories(final RegisterParticleProvidersEvent event) {
        Minecraft.getInstance().particleEngine.register(ModParticles.PINK_CRIT_PARTICLES.get(),
                PinkCritParticles.Provider::new);

        Minecraft.getInstance().particleEngine.register(ModParticles.STAR_LIGHT_PARTICLES.get(),
                StarLightParticles.Provider::new);

        Minecraft.getInstance().particleEngine.register(ModParticles.PINK_HEART_PARTICLES.get(),
                PinkHeartParticles.Provider::new);

        Minecraft.getInstance().particleEngine.register(ModParticles.BUBBLE.get(),
                BubbleParticles.Provider::new);
    }

    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.STRAWBERRY_COW_LAYER, CowModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.CELESTIAL_RABBIT_LAYER, CelestialRabbitModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.BUTTERFLY_LAYER, ButterflyModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.FAIRY_LAYER, FairyModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.MOON_ALTAR_BE.get(), MoonAltarBlockEntityRenderer::new);
    }
}