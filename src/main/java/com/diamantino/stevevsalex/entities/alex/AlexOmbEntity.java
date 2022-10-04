package com.diamantino.stevevsalex.entities.alex;

import com.diamantino.stevevsalex.entities.base.BombEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class AlexOmbEntity extends BombEntity {
    public AlexOmbEntity(EntityType<? extends AlexOmbEntity> p_19870_, Level p_19871_) {
        super(p_19870_, p_19871_, 5, "alex_omb");
    }

    public AlexOmbEntity(EntityType<? extends AlexOmbEntity> p_19870_, Level p_19871_, int explosionPower) {
        super(p_19870_, p_19871_, explosionPower, "alex_omb");
    }
}
