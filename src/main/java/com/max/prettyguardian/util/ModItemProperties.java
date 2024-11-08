package com.max.prettyguardian.util;

import com.max.prettyguardian.PrettyGuardian;
import com.max.prettyguardian.item.PrettyGuardianItem;
import net.mehvahdjukaar.moonlight.api.platform.ForgeHelper;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class ModItemProperties {

        public static void addCustomProperties() {
            makeBow(PrettyGuardianItem.CUPIDON_BOW.get());

            ItemProperties.register(PrettyGuardianItem.NEPTUNES_MIRROR.get(), new ResourceLocation(PrettyGuardian.MOD_ID, "using"),
                    (stack, world, entity, s) -> entity != null && entity.isUsingItem() && ForgeHelper.areStacksEqual(stack, entity.getUseItem(), true) ? 1.0F : 0.0F);
        }


        private static void makeBow(Item item) {
            ItemProperties.register(item, new ResourceLocation("pull"), (itemStack, clientLevel, livingEntity, i) -> {
                if (livingEntity == null) {
                    return 0.0F;
                } else {
                    return livingEntity.getUseItem() != itemStack ? 0.0F : (float)(itemStack.getUseDuration() - livingEntity.getUseItemRemainingTicks()) / 20.0F;
                }
            });
            ItemProperties.register(
                    item,
                    new ResourceLocation("pulling"),
                    (itemStack, clientLevel, livingEntity, i) ->
                        livingEntity != null && livingEntity.isUsingItem() && livingEntity.getUseItem() == itemStack ? 1.0F : 0.0F
            );
        }


}
