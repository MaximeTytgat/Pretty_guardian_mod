package com.max.prettyguardian.event;

import com.max.prettyguardian.PrettyGuardian;
import com.max.prettyguardian.blocks.PrettyGuardianBlock;
import com.max.prettyguardian.entity.ModEntities;
import com.max.prettyguardian.entity.custom.CelestialRabbitEntity;
import com.max.prettyguardian.entityonshoulder.PlayerEntityOnShoulder;
import com.max.prettyguardian.entityonshoulder.PlayerEntityOnShoulderProvider;
import com.max.prettyguardian.item.PrettyGuardianItem;
import com.max.prettyguardian.networking.ModMessages;
import com.max.prettyguardian.networking.packet.PlayerEntityOnShoulderDataSCPacket;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.Objects;

@Mod.EventBusSubscriber(modid = PrettyGuardian.MOD_ID)
public class ModEvents {
    private ModEvents() {}
    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if(
                event.getObject() instanceof Player
                && !event
                        .getObject()
                        .getCapability(PlayerEntityOnShoulderProvider.PLAYER_ENTITY_ON_SHOULDER_CAPABILITY)
                        .isPresent()
        ) {
                event.addCapability(new ResourceLocation(PrettyGuardian.MOD_ID, "properties"), new PlayerEntityOnShoulderProvider());
            }

    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if(event.isWasDeath()) {
            event.getOriginal()
                    .getCapability(PlayerEntityOnShoulderProvider.PLAYER_ENTITY_ON_SHOULDER_CAPABILITY)
                    .ifPresent(oldStore ->
                            event.getOriginal()
                                    .getCapability(PlayerEntityOnShoulderProvider.PLAYER_ENTITY_ON_SHOULDER_CAPABILITY)
                                    .ifPresent(newStore -> newStore.copyFrom(oldStore)
                                    ));
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

            if (validPlayer(player)) {
                return;
            }

            if (hasAllConditionsForProcess(event, player)) {
                player.getCapability(PlayerEntityOnShoulderProvider.PLAYER_ENTITY_ON_SHOULDER_CAPABILITY).ifPresent(entityOnShoulder -> {
                    if (entityOnShoulder.getEntityType() != null && entityOnShoulder.getEntityType() == ModEntities.CELESTIAL_RABBIT.get()) {
                        bringTheAnimalDownFromTheShoulder(event, entityOnShoulder, player);
                    }
                });
            }

        }
    }

    private static void bringTheAnimalDownFromTheShoulder(PlayerInteractEvent.RightClickBlock event, PlayerEntityOnShoulder entityOnShoulder, Player player) {
        CelestialRabbitEntity newRabbit = new CelestialRabbitEntity(ModEntities.CELESTIAL_RABBIT.get(), player.level());

        Direction direction = event.getFace();
        switch (direction) {
            case UP:
                newRabbit.setPos(event.getPos().getX() + 0.5, event.getPos().getY() + 1, event.getPos().getZ() + 0.5);
                break;
            case DOWN:
                newRabbit.setPos(event.getPos().getX() + 0.5, event.getPos().getY() - 1, event.getPos().getZ() + 0.5);
                break;
            case NORTH:
                newRabbit.setPos(event.getPos().getX() + 0.5, event.getPos().getY(), event.getPos().getZ() - 0.5);
                break;
            case SOUTH:
                newRabbit.setPos(event.getPos().getX() + 0.5, event.getPos().getY(), event.getPos().getZ() + 1.5);
                break;
            case WEST:
                newRabbit.setPos(event.getPos().getX() - 0.5, event.getPos().getY(), event.getPos().getZ() + 0.5);
                break;
            case EAST:
                newRabbit.setPos(event.getPos().getX() + 1.5, event.getPos().getY(), event.getPos().getZ() + 0.5);
                break;
            case null:
                break;
        }

        newRabbit.setCollarColor(entityOnShoulder.getCollarColor());
        newRabbit.setOrderedToSit(entityOnShoulder.isInSittingPose());

        if (entityOnShoulder.getName() != null) newRabbit.setCustomName(entityOnShoulder.getName());
        newRabbit.tame(player);

        player.level().addFreshEntity(newRabbit);

        entityOnShoulder.letGoEntity();

        ModMessages.sendToAllPlayer(
                new PlayerEntityOnShoulderDataSCPacket(
                        player.getStringUUID(),
                        false
                )
        );
    }

    private static boolean hasAllConditionsForProcess(PlayerInteractEvent.RightClickBlock event, Player player) {
        return player.isShiftKeyDown()
                && event.getHand() == InteractionHand.MAIN_HAND
                && player.getItemInHand(InteractionHand.MAIN_HAND).getItem() == Items.AIR
                && event.getSide() == LogicalSide.SERVER;
    }

    private static boolean validPlayer(Player player) {
        return player == null || player.isSpectator() || player.getVehicle() != null;
    }

    @SubscribeEvent
    public static void onEntityInteract(PlayerInteractEvent.EntityInteract event) {
        Player player = event.getEntity();

        if (
            event.getTarget() instanceof LivingEntity livingEntity
            && !player.isSpectator()
            && player.getVehicle() == null
            && !player.level().isClientSide
            && livingEntity instanceof CelestialRabbitEntity celestialRabbit
            && player.isShiftKeyDown()
            && celestialRabbit.isTame()
            &&  (Objects.equals(Objects.requireNonNull(celestialRabbit.getOwnerUUID()).toString(), player.getUUID().toString()))
            && event.getSide() == LogicalSide.SERVER
        ) {
            player.getCapability(PlayerEntityOnShoulderProvider.PLAYER_ENTITY_ON_SHOULDER_CAPABILITY).ifPresent(entityOnShoulder -> {
                if(entityOnShoulder.getEntityType() == null) {
                    DyeColor collarColor = celestialRabbit.getCollarColor();
                    Component name = celestialRabbit.hasCustomName() ? celestialRabbit.getCustomName() : null;
                    boolean isInSittingPose = celestialRabbit.isInSittingPose();

                    entityOnShoulder.setEntityOnShoulder(ModEntities.CELESTIAL_RABBIT.get(), collarColor, name, isInSittingPose);

                    livingEntity.discard();

                    ModMessages.sendToAllPlayer(
                            new PlayerEntityOnShoulderDataSCPacket(
                                    player.getStringUUID(),
                                    true
                            )
                    );
                }
            });
        }
    }


    @SubscribeEvent
    public static void onPlayerJoinWorld(EntityJoinLevelEvent event) {
        if(
                !event.getLevel().isClientSide()
                && event.getEntity() instanceof ServerPlayer player
        ) {
                player.getCapability(PlayerEntityOnShoulderProvider.PLAYER_ENTITY_ON_SHOULDER_CAPABILITY).ifPresent(entityOnShoulder -> {
                    if (entityOnShoulder.getEntityType() != null) {
                        ModMessages.sendToAllPlayer(new PlayerEntityOnShoulderDataSCPacket(
                                player.getStringUUID(),
                                true
                        ));
                    }
                });
            }

    }

    @SubscribeEvent
    public static void onEntityDeath(LivingDeathEvent event) {
        if(event.getEntity() instanceof Player player) {
            player.getCapability(PlayerEntityOnShoulderProvider.PLAYER_ENTITY_ON_SHOULDER_CAPABILITY).ifPresent(entityOnShoulder -> {
                if(entityOnShoulder.getEntityType() != null) {
                    CelestialRabbitEntity newRabbit = new CelestialRabbitEntity(ModEntities.CELESTIAL_RABBIT.get(), player.level());
                    newRabbit.setPos(player.getX(), player.getY() + 1.5, player.getZ());
                    newRabbit.setCollarColor(entityOnShoulder.getCollarColor());
                    newRabbit.setOrderedToSit(false);
                    if (entityOnShoulder.getName() != null) newRabbit.setCustomName(entityOnShoulder.getName());
                    newRabbit.tame(player);

                    player.level().addFreshEntity(newRabbit);

                    entityOnShoulder.letGoEntity();

                    ModMessages.sendToAllPlayer(
                            new PlayerEntityOnShoulderDataSCPacket(
                                    player.getStringUUID(),
                                    false
                            )
                    );
                }
            });
        }
    }

    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {
        if (event.getType() == VillagerProfession.FARMER) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack itemStack = new ItemStack(Items.EMERALD, 1);
            int villagerLevel = 1;

            trades.get(villagerLevel).add((trader, random) -> new MerchantOffer(
                    new ItemCost(PrettyGuardianItem.STRAWBERRY.get(), 7),
                    itemStack,
                    12,
                    2,
                    0.1F
            ));

            trades.get(villagerLevel).add((trader, random) -> new MerchantOffer(
                    new ItemCost(PrettyGuardianItem.MINT.get(), 7),
                    itemStack,
                    12,
                    2,
                    0.1F
            ));

            trades.get(villagerLevel).add((trader, random) -> new MerchantOffer(
                    new ItemCost(PrettyGuardianItem.VANILLA.get(), 5),
                    itemStack,
                    12,
                    2,
                    0.1F
            ));

            trades.get(3).add((trader, random) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 1),
                    new ItemStack(PrettyGuardianBlock.CREAM_STRAWBERRY_CAKE.get(), 1),
                    8,
                    8,
                    0.35F
            ));

            trades.get(3).add((trader, random) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 1),
                    new ItemStack(PrettyGuardianBlock.STRAWBERRY_CHOCO_CAKE.get(), 1),
                    8,
                    8,
                    0.35F
            ));

            trades.get(3).add((trader, random) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 1),
                    new ItemStack(PrettyGuardianBlock.CREAM_CAKE.get(), 1),
                    8,
                    8,
                    0.35F
            ));

            trades.get(3).add((trader, random) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 1),
                    new ItemStack(PrettyGuardianBlock.BERRY_STRAWBERRY_CAKE.get(), 1),
                    8,
                    8,
                    0.35F
            ));

            trades.get(3).add((trader, random) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 1),
                    new ItemStack(PrettyGuardianBlock.STRAWBERRY_CAKE.get(), 1),
                    8,
                    8,
                    0.35F
            ));

            trades.get(3).add((trader, random) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 1),
                    new ItemStack(PrettyGuardianBlock.RHUM_CAKE.get(), 1),
                    8,
                    8,
                    0.35F
            ));

            trades.get(3).add((trader, random) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 1),
                    new ItemStack(PrettyGuardianBlock.CHOCOLATE_CAKE.get(), 1),
                    8,
                    8,
                    0.35F
            ));

            trades.get(3).add((trader, random) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 1),
                    new ItemStack(PrettyGuardianBlock.VELVET_CAKE.get(), 1),
                    8,
                    8,
                    0.35F
            ));
        }
    }
}
