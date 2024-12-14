package com.max.prettyguardian.item.client;

import com.max.prettyguardian.PrettyGuardian;
import com.max.prettyguardian.item.custom.RubyArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class RubyArmorModel extends GeoModel<RubyArmorItem> {
    @Override
    public ResourceLocation getModelResource(RubyArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(PrettyGuardian.MOD_ID, "geo/ruby_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(RubyArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(PrettyGuardian.MOD_ID, "textures/armor/ruby_armor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(RubyArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(PrettyGuardian.MOD_ID, "animations/ruby_armor.animation.json");
    }
}
