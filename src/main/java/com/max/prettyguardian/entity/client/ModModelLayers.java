package com.max.prettyguardian.entity.client;

import com.max.prettyguardian.PrettyGuardian;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class ModModelLayers {
    private ModModelLayers() {}
    public static final ModelLayerLocation STRAWBERRY_COW_LAYER = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(PrettyGuardian.MOD_ID, "strawberry_cow_layer"), "main");

    public static final ModelLayerLocation CELESTIAL_RABBIT_LAYER = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(PrettyGuardian.MOD_ID, "celestial_rabbit_layer"), "main");

    public static final ModelLayerLocation BUTTERFLY_LAYER = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(PrettyGuardian.MOD_ID, "butterfly_layer"), "main");

    public static final ModelLayerLocation FAIRY_LAYER = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(PrettyGuardian.MOD_ID, "fairy_layer"), "main");
}
