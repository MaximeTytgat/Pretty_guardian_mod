package com.max.prettyguardian.networking;

import com.max.prettyguardian.networking.packet.PlayerEntityOnShoulderDataSCPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.*;

import static com.max.prettyguardian.PrettyGuardian.MOD_ID;

public class ModMessages {
    private ModMessages() {}

    private static final int PROTOCOL_VERSION = 1004000;
    public static final ResourceLocation MAIN_CHANNEL_LOCATION = ResourceLocation.withDefaultNamespace(MOD_ID, "main");
    public static final SimpleChannel NETWORK = ChannelBuilder.named(MAIN_CHANNEL_LOCATION).networkProtocolVersion(PROTOCOL_VERSION).optional().simpleChannel();
    private static int packetId = 0;

    private static int id() {
        return packetId++;
    }

    public static void register() {
        NETWORK.messageBuilder(PlayerEntityOnShoulderDataSCPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(PlayerEntityOnShoulderDataSCPacket::new)
                .encoder(PlayerEntityOnShoulderDataSCPacket::toBytes)
                .consumerMainThread(PlayerEntityOnShoulderDataSCPacket::handle)
                .add();
    }

    public static void sendToServer(Object packet) {
        NETWORK.send(packet, PacketDistributor.SERVER.noArg());
    }

    public static void sendToPlayer(ServerPlayer player, Object packet) {
        NETWORK.send(packet, PacketDistributor.PLAYER.with(player));
    }

    public static void sendToAllPlayers(Object packet) {
        NETWORK.send(packet, PacketDistributor.ALL.noArg());
    }
}