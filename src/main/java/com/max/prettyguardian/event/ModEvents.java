package com.max.prettyguardian.event;

import com.max.prettyguardian.PrettyGuardian;
import com.max.prettyguardian.blocks.PrettyGuardianBlock;
import com.max.prettyguardian.blocks.custom.food.BaseCake;
import com.max.prettyguardian.client.ClientPlayerEntityOnShoulderData;
import com.max.prettyguardian.entity.ModEntities;
import com.max.prettyguardian.entity.custom.CelestialRabbitEntity;
import com.max.prettyguardian.entityOnShoulder.PlayerEntityOnShoulder;
import com.max.prettyguardian.entityOnShoulder.PlayerEntityOnShoulderProvider;
import com.max.prettyguardian.item.PrettyGuardianItem;
import com.max.prettyguardian.networking.ModMessages;
import com.max.prettyguardian.networking.packet.PlayerEntityOnShoulderDataSCPacket;
import com.max.prettyguardian.particle.ModParticles;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.Objects;
import java.util.Random;

@Mod.EventBusSubscriber(modid = PrettyGuardian.MOD_ID)
public class ModEvents {
    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if(event.getObject() instanceof Player) {
            if(!event.getObject().getCapability(PlayerEntityOnShoulderProvider.PLAYER_ENTITY_ON_SHOULDER_CAPABILITY).isPresent()) {
                event.addCapability(new ResourceLocation(PrettyGuardian.MOD_ID, "properties"), new PlayerEntityOnShoulderProvider());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if(event.isWasDeath()) {
            event.getOriginal().getCapability(PlayerEntityOnShoulderProvider.PLAYER_ENTITY_ON_SHOULDER_CAPABILITY).ifPresent(oldStore -> {
                event.getOriginal().getCapability(PlayerEntityOnShoulderProvider.PLAYER_ENTITY_ON_SHOULDER_CAPABILITY).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
        }
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(PlayerEntityOnShoulder.class);
    }

    @SubscribeEvent
    public static void onIteractWithBlock(PlayerInteractEvent.RightClickBlock event) {
        if (!event.getSide().isClient()) {
            Player player = event.getEntity();

            if (player == null || player.isSpectator() || player.getVehicle() != null) {
                return;
            }

            if (player.isShiftKeyDown()) {
                if(event.getSide() == LogicalSide.SERVER) {
                    player.getCapability(PlayerEntityOnShoulderProvider.PLAYER_ENTITY_ON_SHOULDER_CAPABILITY).ifPresent(entityOnShoulder -> {
                        if(entityOnShoulder.getEntityType() != null && entityOnShoulder.getEntityType() == ModEntities.CELESTIAL_RABBIT.get()) {
                            CelestialRabbitEntity newRabbit = new CelestialRabbitEntity(ModEntities.CELESTIAL_RABBIT.get(), player.level());

                            newRabbit.setPos(event.getPos().getX(), event.getPos().getY() + 1.5, event.getPos().getZ());
                            newRabbit.setCollarColor(entityOnShoulder.getCollarColor());
                            newRabbit.setOrderedToSit(entityOnShoulder.isInSittingPose());

                            if (entityOnShoulder.getName() != null) newRabbit.setCustomName(entityOnShoulder.getName());
                            newRabbit.tame(player);

                            player.level().addFreshEntity(newRabbit);

                            entityOnShoulder.letGoEntity();

                            ModMessages.sendToPlayer(
                                    new PlayerEntityOnShoulderDataSCPacket(
                                            entityOnShoulder.getEntityType() != null,
                                            entityOnShoulder.getId() != null ? entityOnShoulder.getId() : ""
                                    ), (ServerPlayer) player);
                        }
                    });
                }
            }
        }
    }

    @SubscribeEvent
    public static void onEntityInteract(PlayerInteractEvent.EntityInteract event) {
        Player player = event.getEntity();

        if (event.getTarget() instanceof LivingEntity livingEntity && !player.isSpectator() && player.getVehicle() == null && !player.level().isClientSide) {
            if (livingEntity instanceof CelestialRabbitEntity celestialRabbit && player.isShiftKeyDown()) {
                if (celestialRabbit.isTame() &&  (Objects.equals(Objects.requireNonNull(celestialRabbit.getOwnerUUID()).toString(), player.getUUID().toString()))) {
                    if(event.getSide() == LogicalSide.SERVER) {
                        player.getCapability(PlayerEntityOnShoulderProvider.PLAYER_ENTITY_ON_SHOULDER_CAPABILITY).ifPresent(entityOnShoulder -> {
                            if(entityOnShoulder.getEntityType() == null) {
                                String id = celestialRabbit.getStringUUID();
                                DyeColor collarColor = celestialRabbit.getCollarColor();
                                Component name = celestialRabbit.hasCustomName() ? celestialRabbit.getCustomName() : null;
                                boolean isInSittingPose = celestialRabbit.isInSittingPose();

                                PrettyGuardian.LOGGER.info("ID of the rabbit: " + id);

                                entityOnShoulder.setEntityOnShoulder(id, ModEntities.CELESTIAL_RABBIT.get(), collarColor, name, isInSittingPose);

                                livingEntity.discard();

                                ModMessages.sendToPlayer(
                                        new PlayerEntityOnShoulderDataSCPacket(
                                                entityOnShoulder.getEntityType() != null,
                                                entityOnShoulder.getId() != null ? entityOnShoulder.getId() : ""
                                        ), (ServerPlayer) player);
                            }
                        });
                    }
                }
            }
        }
    }


    @SubscribeEvent
    public static void onPlayerJoinWorld(EntityJoinLevelEvent event) {
        if(!event.getLevel().isClientSide()) {
            if(event.getEntity() instanceof ServerPlayer player) {
                player.getCapability(PlayerEntityOnShoulderProvider.PLAYER_ENTITY_ON_SHOULDER_CAPABILITY).ifPresent(entityOnShoulder -> {
                    ModMessages.sendToPlayer(new PlayerEntityOnShoulderDataSCPacket(
                            entityOnShoulder.getEntityType() != null,
                            entityOnShoulder.getId() != null ? entityOnShoulder.getId() : ""
                    ), player);
                });
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.ClientTickEvent event) {
        Minecraft MC = Minecraft.getInstance();
        Player player = MC.player;
        if(player != null && player.tickCount % 10 == 0 && player.level().isClientSide() && !MC.isPaused()) {
            boolean hasEntityOnShoulder = ClientPlayerEntityOnShoulderData.getHasEntityOnShoulder();
            if (hasEntityOnShoulder) {
                Random random = new Random();
                Vec3 look = player.getLookAngle();
                double angleRadians = Math.atan2(look.z, look.x);
                double angleDegrees = Math.toDegrees(angleRadians);
                angleDegrees += 270;
                float distance = 0.4F;
                double offsetX = Math.cos(Math.toRadians(angleDegrees)) * distance;
                double offsetZ = Math.sin(Math.toRadians(angleDegrees)) * distance;

                for(int i = 0; i < 3; ++i) {
                    player.level().addParticle(ModParticles.CELESTIAL_RABBIT_PARTICLES.get(), player.getX() + offsetX, player.getY() + 1.6f, player.getZ() + offsetZ, (random.nextDouble() - 0.5) * 2.0, -random.nextDouble(), (random.nextDouble() - 0.5) * 2.0);
                }

            }
        }
    }

    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {
        if (event.getType() == VillagerProfession.FARMER) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack itemStack = new ItemStack(Items.EMERALD, 1);
            int villagerLevel = 1;

            trades.get(villagerLevel).add((trader, random) -> new MerchantOffer(
                    new ItemStack(PrettyGuardianItem.STRAWBERRY.get(), 7),
                    itemStack,
                    12,
                    2,
                    0.1F
            ));

            trades.get(villagerLevel).add((trader, random) -> new MerchantOffer(
                    new ItemStack(PrettyGuardianItem.MINT.get(), 7),
                    itemStack,
                    12,
                    2,
                    0.1F
            ));

            trades.get(villagerLevel).add((trader, random) -> new MerchantOffer(
                    new ItemStack(PrettyGuardianItem.VANILLA.get(), 5),
                    itemStack,
                    12,
                    2,
                    0.1F
            ));

            trades.get(3).add((trader, random) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 1),
                    new ItemStack(PrettyGuardianBlock.CREAM_STRAWBERRY_CAKE.get(), 1),
                    8,
                    8,
                    0.35F
            ));

            trades.get(3).add((trader, random) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 1),
                    new ItemStack(PrettyGuardianBlock.STRAWBERRY_CHOCO_CAKE.get(), 1),
                    8,
                    8,
                    0.35F
            ));

            trades.get(3).add((trader, random) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 1),
                    new ItemStack(PrettyGuardianBlock.CREAM_CAKE.get(), 1),
                    8,
                    8,
                    0.35F
            ));

            trades.get(3).add((trader, random) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 1),
                    new ItemStack(PrettyGuardianBlock.BERRY_STRAWBERRY_CAKE.get(), 1),
                    8,
                    8,
                    0.35F
            ));

            trades.get(3).add((trader, random) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 1),
                    new ItemStack(PrettyGuardianBlock.STRAWBERRY_CAKE.get(), 1),
                    8,
                    8,
                    0.35F
            ));

            trades.get(3).add((trader, random) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 1),
                    new ItemStack(PrettyGuardianBlock.RHUM_CAKE.get(), 1),
                    8,
                    8,
                    0.35F
            ));

            trades.get(3).add((trader, random) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 1),
                    new ItemStack(PrettyGuardianBlock.CHOCOLATE_CAKE.get(), 1),
                    8,
                    8,
                    0.35F
            ));

            trades.get(3).add((trader, random) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 1),
                    new ItemStack(PrettyGuardianBlock.VELVET_CAKE.get(), 1),
                    8,
                    8,
                    0.35F
            ));
        }
    }
}
