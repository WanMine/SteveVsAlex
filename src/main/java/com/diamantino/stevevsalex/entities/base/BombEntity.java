package com.diamantino.stevevsalex.entities.base;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.NetworkHooks;

public abstract class BombEntity extends AbstractArrow {
    public static final EntityDataAccessor<Integer> EXPLOSION_POWER = SynchedEntityData.defineId(BombEntity.class, EntityDataSerializers.INT);

    public float bombRotationOld;
    public float bombRotationNew;
    public String bombName;

    public BombEntity(EntityType<? extends BombEntity> p_19870_, Level p_19871_, int explosionPower, String bombName) {
        super(p_19870_, p_19871_);

        bombRotationNew = 0;
        setInvulnerable(true);

        this.bombName = bombName;
        setExplosionPower(explosionPower);
    }

    public float getExplosionPower() {
        return entityData.get(EXPLOSION_POWER);
    }

    public void setExplosionPower(int explosionPower) {
        entityData.set(EXPLOSION_POWER, explosionPower);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(EXPLOSION_POWER, 0);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        if (compound.contains("explosion_power")) {
            entityData.set(EXPLOSION_POWER, compound.getInt("explosion_power"));
        }
    }

    @Override
    protected ItemStack getPickupItem() {
        return null;
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        compound.putInt("explosion_power", entityData.get(EXPLOSION_POWER));
    }

    @Override
    public void tick() {
        super.tick();

        if (level.isClientSide) {
            if (bombRotationNew >= -89.5f) {
                bombRotationOld = bombRotationNew;

                bombRotationNew -= 0.5f;
            }
        }
    }

    @Override
    protected void onHit(HitResult p_37260_) {
        if (!level.isClientSide())
            explode();
    }

    public void explode() {
        level.explode(this, getX(), getY(), getZ(), getExplosionPower(), Explosion.BlockInteraction.BREAK);

        kill();
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
