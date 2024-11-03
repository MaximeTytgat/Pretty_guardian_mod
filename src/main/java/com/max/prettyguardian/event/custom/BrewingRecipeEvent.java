package com.max.prettyguardian.event.custom;

import com.max.prettyguardian.item.PrettyGuardianItem;
import com.max.prettyguardian.potion.ModPotions;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.event.brewing.BrewingRecipeRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class BrewingRecipeEvent {
    private BrewingRecipeEvent() {}
    @SubscribeEvent
    public static void onBrewingRecipeEvent(BrewingRecipeRegisterEvent event) {
        event.getBuilder().addMix(Potions.WATER, PrettyGuardianItem.FAIRY_DUST.get(), ModPotions.LOVE_POTION.getHolder().get());
    }
}