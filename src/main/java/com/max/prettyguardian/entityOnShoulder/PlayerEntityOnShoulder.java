package com.max.prettyguardian.entityOnShoulder;

import com.max.prettyguardian.entity.custom.CelestialRabbitEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.DyeColor;

public class PlayerEntityOnShoulder {

    private static final String ID = null;
    public static final String ENTITY_ON_SHOULDER = "entity_on_shoulder";
    public static final String COLLAR_COLOR = "collar_color";
    public static final String IS_IN_SITTING_POSE = "is_in_sitting_pose";
    private EntityType<?> entityType = null;
    private DyeColor collarColor = null;
    private Component name = null;
    private boolean isInSittingPose = false;

    public void setEntityOnShoulder(EntityType<?> entityType, DyeColor collarColor, Component name, boolean isInSittingPose) {
        this.entityType = entityType;
        this.collarColor = collarColor;
        this.name = name;
        this.isInSittingPose = isInSittingPose;
    }

    public void letGoEntity() {
        entityType = null;
        collarColor = null;
        name = null;
        isInSittingPose = false;
    }

    public String getId() {
        return ID;
    }
    public EntityType<?> getEntityType() {
        return entityType;
    }
    public DyeColor getCollarColor() {
        return collarColor;
    }
    public Component getName() {
        return name;
    }
    public boolean isInSittingPose() {
        return isInSittingPose;
    }

    public void copyFrom(PlayerEntityOnShoulder source) {
        entityType = source.entityType;
        collarColor = source.collarColor;
        name = source.name;
        isInSittingPose = source.isInSittingPose;
    }

    public void saveNBTData(CompoundTag tag) {
        if (entityType != null) tag.putString(ENTITY_ON_SHOULDER, EntityType.getKey(entityType).toString());
        if (collarColor != null) tag.putString(COLLAR_COLOR, collarColor.getName());
        if (name != null) tag.putString("name", Component.Serializer.toJson(name));
        tag.putBoolean(IS_IN_SITTING_POSE, isInSittingPose);
    }

    public void loadNBTData(CompoundTag tag) {
        if (tag.contains(ENTITY_ON_SHOULDER)) entityType = EntityType.byString(tag.getString(ENTITY_ON_SHOULDER)).orElse(null);
        if (tag.contains(COLLAR_COLOR)) collarColor = DyeColor.byName(tag.getString(COLLAR_COLOR), null);
        if (tag.contains("name")) name = Component.Serializer.fromJson(tag.getString("name"));
        if (tag.contains(IS_IN_SITTING_POSE)) isInSittingPose = tag.getBoolean(IS_IN_SITTING_POSE);
    }
}
