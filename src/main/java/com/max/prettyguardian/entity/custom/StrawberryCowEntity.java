package com.max.prettyguardian.entity.custom;

import com.max.prettyguardian.entity.ModEntities;
import com.max.prettyguardian.item.PrettyGuardianItem;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.MushroomCow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class StrawberryCowEntity extends MushroomCow {
    public StrawberryCowEntity(EntityType<? extends MushroomCow> entityType, Level level) {
        super(entityType, level);
    }


    @Nullable
    @Override
    public MushroomCow getBreedOffspring(@NotNull ServerLevel serverLevel, @NotNull AgeableMob ageableMob) {
        return ModEntities.STRAWBERRY_COW.get().create(serverLevel);
    }

    @Override
    public @NotNull MushroomType getVariant() {
        return super.getVariant();
    }

    @Override
    public @NotNull InteractionResult mobInteract(Player player, @NotNull InteractionHand interactionHand) {
        ItemStack itemstack = player.getItemInHand(interactionHand);
        if (itemstack.is(Items.BOWL) && !this.isBaby()) {
            return InteractionResult.sidedSuccess(this.level().isClientSide);
        } else if (this.getVariant() == MushroomType.BROWN && itemstack.is(ItemTags.SMALL_FLOWERS)) {
            return InteractionResult.sidedSuccess(this.level().isClientSide);
        } else if (itemstack.is(Items.BUCKET) && !this.isBaby()) {
            player.playSound(SoundEvents.COW_MILK, 1.0F, 1.0F);
            ItemStack filledResult = ItemUtils.createFilledResult(itemstack, player, PrettyGuardianItem.STRAWBERRY_MILK_BUCKET.get().getDefaultInstance());
            player.setItemInHand(interactionHand, filledResult);
            return InteractionResult.sidedSuccess(this.level().isClientSide);
        } else {
            return super.mobInteract(player, interactionHand);
        }
    }
}
