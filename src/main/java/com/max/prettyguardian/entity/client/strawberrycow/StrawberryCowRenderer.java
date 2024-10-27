package com.max.prettyguardian.entity.client.strawberrycow;

import com.max.prettyguardian.PrettyGuardian;
import com.max.prettyguardian.entity.client.ModModelLayers;
import com.max.prettyguardian.entity.custom.StrawberryCowEntity;
import com.google.common.collect.Maps;
import net.minecraft.Util;
import net.minecraft.client.model.CowModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class StrawberryCowRenderer extends MobRenderer<StrawberryCowEntity, CowModel<StrawberryCowEntity>> {
    private static final Map<StrawberryCowEntity.MushroomType, ResourceLocation> TEXTURES = Util.make(Maps.newHashMap(), mushroomTypeResourceLocationHashMap -> {
        mushroomTypeResourceLocationHashMap.put(StrawberryCowEntity.MushroomType.BROWN, new ResourceLocation("textures/entity/cow/brown_mooshroom.png"));
        mushroomTypeResourceLocationHashMap.put(StrawberryCowEntity.MushroomType.RED, new ResourceLocation(PrettyGuardian.MOD_ID, "textures/entity/strawberry_cow.png"));
    });

    public StrawberryCowRenderer(EntityRendererProvider.Context context) {
        super(context, new CowModel<>(context.bakeLayer(ModModelLayers.STRAWBERRY_COW_LAYER)), 0.7F);
        this.addLayer(new StrawberryCowStrawberryLayer<>(this, context.getBlockRenderDispatcher()));
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(StrawberryCowEntity strawberryCowEntity) {
        return TEXTURES.get(strawberryCowEntity.getVariant());
    }
}