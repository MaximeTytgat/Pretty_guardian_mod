package com.max.prettyguardian.networking.packet;

import com.max.prettyguardian.client.ClientPlayerEntityOnShoulderData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.event.network.CustomPayloadEvent;

import java.util.function.Supplier;

public class PlayerEntityOnShoulderDataSCPacket {
    private final String playerId;
    private final boolean isEntityOnShoulder;

    public PlayerEntityOnShoulderDataSCPacket(String playerId, boolean isEntityOnShoulder) {
        this.playerId = playerId;
        this.isEntityOnShoulder = isEntityOnShoulder;
    }

    public PlayerEntityOnShoulderDataSCPacket(FriendlyByteBuf buf) {
        this.playerId = buf.readUtf(32767);
        this.isEntityOnShoulder = buf.readBoolean();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeUtf(this.playerId);
        buf.writeBoolean(this.isEntityOnShoulder);
    }

    public void handle(CustomPayloadEvent.Context context) {
        context.enqueueWork(() -> {
            // Here we are in the client
            if (isEntityOnShoulder)
                ClientPlayerEntityOnShoulderData.setEntityOnShoulder(this.playerId);
            else
                ClientPlayerEntityOnShoulderData.letGoEntity(this.playerId);
        });
    }
}
