package com.max.prettyguardian.client.renderer.entity.projectiles;


import com.max.prettyguardian.PrettyGuardian;
import com.max.prettyguardian.worldgen.entity.projectile.CuteArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;


public class CuteArrowRenderer extends ArrowRenderer<CuteArrowEntity> {
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(PrettyGuardian.MOD_ID, "textures/entity/projectiles/heart_arrow.png");

    public CuteArrowRenderer(EntityRendererProvider.Context manager) {
        super(manager);
    }

    public @NotNull ResourceLocation getTextureLocation(@NotNull CuteArrowEntity arrow) {
        return TEXTURE;
    }
}