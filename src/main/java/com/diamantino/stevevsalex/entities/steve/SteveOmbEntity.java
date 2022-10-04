package com.diamantino.stevevsalex.entities.steve;

import com.diamantino.stevevsalex.entities.base.BombEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class SteveOmbEntity extends BombEntity {
    public SteveOmbEntity(EntityType<? extends SteveOmbEntity> p_19870_, Level p_19871_) {
        super(p_19870_, p_19871_, 5, "steve_omb");
    }

    public SteveOmbEntity(EntityType<? extends SteveOmbEntity> p_19870_, Level p_19871_, int explosionPower) {
        super(p_19870_, p_19871_, explosionPower, "steve_omb");
    }
}
