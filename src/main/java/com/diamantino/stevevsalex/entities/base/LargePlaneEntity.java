package com.diamantino.stevevsalex.entities.base;

import com.diamantino.stevevsalex.registries.SVAConfigs;
import com.diamantino.stevevsalex.registries.SVAItems;
import com.mojang.math.Vector3f;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class LargePlaneEntity extends PlaneEntity {

    public boolean hasLargeUpgrade = false;

    public LargePlaneEntity(EntityType<? extends LargePlaneEntity> entityTypeIn, Level worldIn, String name, WeaponType weaponType) {
        super(entityTypeIn, worldIn, name, weaponType);
    }

    @Override
    public void tick() {
        super.tick();

        List<Entity> list = level.getEntities(this, getBoundingBox().inflate(0.2F, -0.01F, 0.2F), EntitySelector.pushableBy(this));
        for (Entity entity : list) {
            if (!level.isClientSide && !(getControllingPassenger() instanceof Player) &&
                    !entity.hasPassenger(this) &&
                    !entity.isPassenger() && entity instanceof LivingEntity && !(entity instanceof Player)) {
                entity.startRiding(this);
            }
        }
    }

    @Override
    protected float getGroundPitch() {
        return 0;
    }

    @Override
    public int getFuelCost() {
        return SVAConfigs.LARGE_PLANE_FUEL_COST.get();
    }

    @Override
    protected boolean canAddPassenger(@NotNull Entity passenger) {
        List<Entity> passengers = getPassengers();
        if (passenger.getVehicle() == this || passenger instanceof PlaneEntity) {
            return false;
        }

        return hasLargeUpgrade ? passengers.size() < 3 : passengers.size() < 4;
    }

    @Override
    public void positionRider(@NotNull Entity passenger) {
        positionRiderGeneric(passenger);
        int index = getPassengers().indexOf(passenger);

        if (index == 0) {
//            passenger.setPos(passenger.getX(), getY() + getPassengersRidingOffset() + getEntityYOffset(passenger), passenger.getZ());
            Vector3f pos = transformPos(new Vector3f(0, (float) (getPassengersRidingOffset() + passenger.getMyRidingOffset()), 1));
            passenger.setPos(getX() + pos.x(), getY() + pos.y(), getZ() + pos.z());
        } else {
            if (hasLargeUpgrade) {
                index++;
            }
            switch (index) {
                case 1 -> {
                    Vector3f pos = transformPos(new Vector3f(0, (float) (getPassengersRidingOffset() + getEntityYOffset(passenger)), 0));
                    passenger.setPos(getX() + pos.x(), getY() + pos.y(), getZ() + pos.z());
                }
                case 2 -> {
                    Vector3f pos = transformPos(new Vector3f(0, (float) (getPassengersRidingOffset() + getEntityYOffset(passenger)), -1));
                    passenger.setPos(getX() + pos.x(), getY() + pos.y(), getZ() + pos.z());
                }
                case 3 -> {
                    Vector3f pos = transformPos(new Vector3f(0, (float) (getPassengersRidingOffset() + getEntityYOffset(passenger)), -1.8f));
                    passenger.setPos(getX() + pos.x(), getY() + pos.y(), getZ() + pos.z());
                }
            }
        }
    }

    public double getEntityYOffset(Entity passenger) {
        if (passenger instanceof Villager) {
            return ((Villager) passenger).isBaby() ? -0.1 : -0.3D;
        }
        return passenger.getMyRidingOffset();
    }

    @Override
    public double getCameraDistanceMultiplayer() {
        return SVAConfigs.LARGE_PLANE_CAMERA_DISTANCE_MULTIPLIER.get();
    }

    @Override
    protected Item getItem() {
        return SVAItems.airVehichlesItems.get(vehichleName).get();
    }
}