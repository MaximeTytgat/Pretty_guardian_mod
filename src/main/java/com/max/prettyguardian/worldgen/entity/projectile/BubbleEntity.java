package com.max.prettyguardian.worldgen.entity.projectile;


import com.max.prettyguardian.particle.ModParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class BubbleEntity extends Projectile {
    public static final String POWER = "power";
    private double xPower;
    private double yPower;
    private double zPower;

    public BubbleEntity(EntityType<BubbleEntity> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    public boolean shouldRenderAtSqrDistance(double v) {
        double d0 = this.getBoundingBox().getSize() * 4.0D;
        if (Double.isNaN(d0)) {
            d0 = 4.0D;
        }

        d0 *= 64.0D;
        return v < d0 * d0;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.@NotNull Builder builder) {}

    @Override
    public void tick() {
        Entity entity = this.getOwner();
        if (this.level().isClientSide || (entity == null || !entity.isRemoved()) && this.level().hasChunkAt(this.blockPosition())) {
            super.tick();

            HitResult hitresult = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
            if (hitresult.getType() != HitResult.Type.MISS && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, hitresult)) {
                this.onHit(hitresult);
            }

            this.checkInsideBlocks();

            Vec3 vec3 = this.getDeltaMovement();
            if (this.xRotO == 0.0F && this.yRotO == 0.0F) {
                double d0 = vec3.horizontalDistance();
                this.setYRot((float)(Mth.atan2(vec3.x, vec3.z) * (180F / (float)Math.PI)));
                this.setXRot((float)(Mth.atan2(vec3.y, d0) * (180F / (float)Math.PI)));
                this.yRotO = this.getYRot();
                this.xRotO = this.getXRot();
            }

            this.xPower /= 2.0;
            this.yPower /= 2.0;
            this.zPower /= 2.0;



            double d5 = vec3.x;
            double d6 = vec3.y;
            double d1 = vec3.z;
            double d7 = this.getX() + d5;
            double d2 = this.getY() + d6;
            double d3 = this.getZ() + d1;
            this.setXRot(lerpRotation(this.xRotO, this.getXRot()));
            this.setYRot(lerpRotation(this.yRotO, this.getYRot()));

            Vec3 vec34 = this.getDeltaMovement();
            this.setDeltaMovement(vec34.x, vec34.y - 0.02F, vec34.z);

            this.setPos(d7, d2, d3);
            this.checkInsideBlocks();

            double randx = -0.5 + (random.nextDouble() * 1);
            double randy = -0.5 + (random.nextDouble() * 1);
            double randz = -0.5 + (random.nextDouble() * 1);

            this.level().addParticle(ModParticles.BUBBLE.get(),
                    (randx + this.getX() + d5),
                    (randy + this.getY() + d6),
                    (randz + this.getZ() + d1),
                    -d5/3, -d6/3, -d1/3);
        } else {
            this.discard();
        }
        this.baseTick();
    }

    @Override
    protected void onHit(HitResult hitresult) {
        HitResult.Type hitresultType = hitresult.getType();
        if (hitresultType == HitResult.Type.ENTITY) {
            this.onHitEntity((EntityHitResult)hitresult);
            this.level().gameEvent(GameEvent.PROJECTILE_LAND, hitresult.getLocation(), GameEvent.Context.of(this, null));
        } else if (hitresultType == HitResult.Type.BLOCK) {
            BlockHitResult blockhitresult = (BlockHitResult)hitresult;
            this.onHitBlock(blockhitresult);
            BlockPos blockpos = blockhitresult.getBlockPos();
            this.level().gameEvent(GameEvent.PROJECTILE_LAND, blockpos, GameEvent.Context.of(this, this.level().getBlockState(blockpos)));
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {
        if (entityHitResult.getEntity() == this.getOwner()) {
            return;
        }
        Entity entityHit = entityHitResult.getEntity();

        if (entityHit instanceof LivingEntity livingentity) {
            Vec3 position = this.position();
            Vec3 subtract = entityHit.getEyePosition().subtract(position);

            Vec3 normalize = subtract.normalize();

            double v = 0.5 * (0.5 - livingentity.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE));
            double v1 = 2.5 * (0.5 - livingentity.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE));

            livingentity.push(normalize.x() * v1, normalize.y() * v, normalize.z() * v1);
        }

        this.discard();
    }

    @Override
    protected void onHitBlock(@NotNull BlockHitResult blockHitResult) {
        this.discard();
    }

    @Override
    protected boolean canHitEntity(@NotNull Entity entity) {
        return super.canHitEntity(entity) && !entity.noPhysics;
    }

    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag compoundTag) {
        super.addAdditionalSaveData(compoundTag);
        compoundTag.put(POWER, this.newDoubleList(this.xPower, this.yPower, this.zPower));
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag compoundTag) {
        super.readAdditionalSaveData(compoundTag);
        if (compoundTag.contains(POWER, 9)) {
            ListTag listtag = compoundTag.getList(POWER, 6);
            if (listtag.size() == 3) {
                this.xPower = listtag.getDouble(0);
                this.yPower = listtag.getDouble(1);
                this.zPower = listtag.getDouble(2);
            }
        }

    }

    @Override
    public boolean isPickable() {
        return true;
    }

    @Override
    public float getPickRadius() {
        return 1.0F;
    }

    @Override
    public boolean hurt(@NotNull DamageSource damageSource, float v) {
        if (this.isInvulnerableTo(damageSource)) {
            return false;
        } else {
            this.markHurt();
            Entity entity = damageSource.getEntity();
            if (entity != null) {
                if (!this.level().isClientSide) {
                    Vec3 vec3 = entity.getLookAngle();
                    this.setDeltaMovement(vec3);
                    this.xPower = vec3.x * 0.1D;
                    this.yPower = vec3.y * 0.1D;
                    this.zPower = vec3.z * 0.1D;
                    this.setOwner(entity);
                }

                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public @NotNull Packet<ClientGamePacketListener> getAddEntityPacket(@NotNull ServerEntity serverEntity) {
        Entity entity = this.getOwner();
        int i = entity == null ? 0 : entity.getId();
        return new ClientboundAddEntityPacket(this.getId(), this.getUUID(), this.getX(), this.getY(), this.getZ(), this.getXRot(), this.getYRot(), this.getType(), i, new Vec3(this.xPower, this.yPower, this.zPower), 0.0D);
    }

    @Override
    public void recreateFromPacket(@NotNull ClientboundAddEntityPacket clientboundAddEntityPacket) {
        super.recreateFromPacket(clientboundAddEntityPacket);
        double d0 = clientboundAddEntityPacket.getXa();
        double d1 = clientboundAddEntityPacket.getYa();
        double d2 = clientboundAddEntityPacket.getZa();
        double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
        if (d3 != 0.0D) {
            this.xPower = d0 / d3 * 0.1D;
            this.yPower = d1 / d3 * 0.1D;
            this.zPower = d2 / d3 * 0.1D;
        }

    }
}