package com.max.prettyguardian.client;


import java.util.ArrayList;
import java.util.List;

public class ClientPlayerEntityOnShoulderData {
    private ClientPlayerEntityOnShoulderData() {}
    private static final List<String> playerIds = new ArrayList<>();

    public static void setEntityOnShoulder(String playerId) {
        ClientPlayerEntityOnShoulderData.playerIds.add(playerId);
    }

    public static void letGoEntity(String playerId) {
        ClientPlayerEntityOnShoulderData.playerIds.remove(playerId);
    }

    public static boolean hasEntityOnShoulder(String playerId) {
        return playerIds.contains(playerId);
    }
}
