package com.diamantino.stevevsalex.entities.alex;

import com.diamantino.stevevsalex.entities.base.HelicopterEntity;
import com.diamantino.stevevsalex.entities.base.ProjectileType;
import com.diamantino.stevevsalex.entities.base.Teams;
import com.diamantino.stevevsalex.entities.base.WeaponType;
import com.mojang.math.Vector3f;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class AlexCopterEntity extends HelicopterEntity {
    public AlexCopterEntity(EntityType<? extends HelicopterEntity> p_20966_, Level p_20967_) {
        super(p_20966_, p_20967_, "alex_copter", WeaponType.MINIGUN, Teams.ALEX, ProjectileType.ARROW);
    }

    @Override
    public void positionRider(@NotNull Entity passenger) {
        positionRiderGeneric(passenger);

        Vector3f pos = transformPos(new Vector3f(0, (float) (getPassengersRidingOffset() + passenger.getMyRidingOffset()) - 0.3f, -0.5f));
        passenger.setPos(getX() + pos.x(), getY() + pos.y(), getZ() + pos.z());
    }
}
