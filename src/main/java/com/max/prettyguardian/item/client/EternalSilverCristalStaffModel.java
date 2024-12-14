package com.max.prettyguardian.item.client;

import com.max.prettyguardian.PrettyGuardian;
import com.max.prettyguardian.item.custom.tool.EternalSilverCristalStaffitem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class EternalSilverCristalStaffModel extends GeoModel<EternalSilverCristalStaffitem> {
    @Override
    public ResourceLocation getModelResource(EternalSilverCristalStaffitem animatable) {
        return ResourceLocation.fromNamespaceAndPath(PrettyGuardian.MOD_ID, "geo/eternal_silver_cristal_staff.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EternalSilverCristalStaffitem animatable) {
        return ResourceLocation.fromNamespaceAndPath(PrettyGuardian.MOD_ID, "textures/item/eternal_silver_cristal_staff.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EternalSilverCristalStaffitem animatable) {
        return ResourceLocation.fromNamespaceAndPath(PrettyGuardian.MOD_ID, "animations/eternal_silver_cristal_staff.animation.json");
    }
}
