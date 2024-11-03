package com.max.prettyguardian.event.custom;

import com.max.prettyguardian.PrettyGuardian;
import com.max.prettyguardian.item.PrettyGuardianItem;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

import java.util.Date;
import java.util.function.Predicate;

@Mod.EventBusSubscriber(modid = PrettyGuardian.MOD_ID, value = Dist.CLIENT)
public class DoubleJumpEvent {
    private DoubleJumpEvent() {}
    private static final long COOLDOWN_MILLIS_DOUBLE_JUMP = 1300;
    private static Date lastJumpTime = new Date();

    @SubscribeEvent
    public static void onKeyPress(InputEvent.Key event) {
        Player player = Minecraft.getInstance().player;

        if (player == null) {
            return;
        }

        if (!hasCuteWandInInventory(player)) {
            return;
        }

        // Vérifier le cooldown basé sur le temps
        Date currentTime = new Date();
        long timeSinceLastJump = currentTime.getTime() - lastJumpTime.getTime();

        if (
                event.getKey() == GLFW.GLFW_KEY_SPACE
                && event.getAction() == GLFW.GLFW_PRESS
                && !player.onGround()
                && timeSinceLastJump >= COOLDOWN_MILLIS_DOUBLE_JUMP
        ) {
            player.jumpFromGround();
            lastJumpTime = currentTime;
        }
    }

    // Méthode pour vérifier si le joueur a l'item CuteWandItem dans son inventaire
    private static boolean hasCuteWandInInventory(Player player) {
        // Code pour vérifier la présence de l'item dans l'inventaire
        // Retourne true si l'item est présent, sinon false
        return player.getInventory().hasAnyMatching(ETERNAL_SILVER_CISTAL_STAFF_ONLY);
    }

    public static final Predicate<ItemStack> ETERNAL_SILVER_CISTAL_STAFF_ONLY = itemStack -> itemStack.is(PrettyGuardianItem.ETERNAL_SILVER_CRISTAL_STAFF.get());
}
