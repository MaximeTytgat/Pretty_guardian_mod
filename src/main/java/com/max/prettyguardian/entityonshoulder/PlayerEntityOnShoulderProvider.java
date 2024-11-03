package com.max.prettyguardian.entityonshoulder;

import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerEntityOnShoulderProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static final Capability<PlayerEntityOnShoulder> PLAYER_ENTITY_ON_SHOULDER_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {});
    private PlayerEntityOnShoulder playerEntityOnShoulder = null;
    private final LazyOptional<PlayerEntityOnShoulder> lazyOptional = LazyOptional.of(this::createPlayerEntityOnShoulder);

    private PlayerEntityOnShoulder createPlayerEntityOnShoulder() {
        if (playerEntityOnShoulder == null) {
            this.playerEntityOnShoulder = new PlayerEntityOnShoulder();
        }

        return this.playerEntityOnShoulder;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction direction) {
        if (capability == PLAYER_ENTITY_ON_SHOULDER_CAPABILITY) {
            return lazyOptional.cast();
        }

        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag tag = new CompoundTag();
        createPlayerEntityOnShoulder().saveNBTData(tag, provider);
        return tag;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag compoundTag ) {
        createPlayerEntityOnShoulder().loadNBTData(compoundTag, provider);
    }
}
