package com.max.prettyguardian.event;

import com.max.prettyguardian.PrettyGuardian;
import com.max.prettyguardian.client.ClientPlayerEntityOnShoulderData;
import com.max.prettyguardian.particle.ModParticles;
import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber(modid = PrettyGuardian.MOD_ID, value = Dist.CLIENT)
public class ModEventClient {

    public static final Random RANDOM = new Random();

    private ModEventClient() {}

    @SubscribeEvent
    // TODO: apply particle effects to all players with entities on their shoulders (we need to tick on all players in client side)
    public static void onPlayerTick(TickEvent.ClientTickEvent event) {
        Minecraft minecraft = Minecraft.getInstance();
        Player player = minecraft.player;
        MinecraftServer server = minecraft.getSingleplayerServer();
        if (server == null) return;
        if (player == null) return;
        server.getPlayerList().getPlayers().forEach(p -> {
            if(p != null && p.tickCount % 10 == 0 && p.level().isClientSide() && !minecraft.isPaused()) {
                boolean hasEntityOnShoulder = ClientPlayerEntityOnShoulderData.hasEntityOnShoulder(p.getStringUUID());
                if (hasEntityOnShoulder) {
                    Vec3 look = p.getLookAngle();
                    double angleRadians = Math.atan2(look.z, look.x);
                    double angleDegrees = Math.toDegrees(angleRadians);
                    angleDegrees += 270;
                    float distance = 0.4F;
                    double offsetX = Math.cos(Math.toRadians(angleDegrees)) * distance;
                    double offsetZ = Math.sin(Math.toRadians(angleDegrees)) * distance;

                    for(int i = 0; i < 3; ++i) {
                        p.level().addParticle(ModParticles.CELESTIAL_RABBIT_PARTICLES.get(), p.getX() + offsetX, p.getY() + 1.6f, p.getZ() + offsetZ, (RANDOM.nextDouble() - 0.5) * 2.0, -RANDOM.nextDouble(), (RANDOM.nextDouble() - 0.5) * 2.0);
                    }
                }
            }
        });
//        if(player != null && player.tickCount % 10 == 0 && player.level().isClientSide() && !MC.isPaused()) {
//            boolean hasEntityOnShoulder = ClientPlayerEntityOnShoulderData.hasEntityOnShoulder(player.getStringUUID());
//            if (hasEntityOnShoulder) {
//                Random random = new Random();
//                Vec3 look = player.getLookAngle();
//                double angleRadians = Math.atan2(look.z, look.x);
//                double angleDegrees = Math.toDegrees(angleRadians);
//                angleDegrees += 270;
//                float distance = 0.4F;
//                double offsetX = Math.cos(Math.toRadians(angleDegrees)) * distance;
//                double offsetZ = Math.sin(Math.toRadians(angleDegrees)) * distance;
//
//                for(int i = 0; i < 3; ++i) {
//                    player.level().addParticle(ModParticles.CELESTIAL_RABBIT_PARTICLES.get(), player.getX() + offsetX, player.getY() + 1.6f, player.getZ() + offsetZ, (random.nextDouble() - 0.5) * 2.0, -random.nextDouble(), (random.nextDouble() - 0.5) * 2.0);
//                }
//            }
//        }
    }
}
