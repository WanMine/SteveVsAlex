package com.diamantino.stevevsalex.entities.alex;

import com.diamantino.stevevsalex.entities.base.PlaneEntity;
import com.diamantino.stevevsalex.entities.base.Teams;
import com.diamantino.stevevsalex.entities.base.WeaponType;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class AlexAneEntity extends PlaneEntity {
    public AlexAneEntity(EntityType<? extends PlaneEntity> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn, "alex_ane", WeaponType.BOMB, Teams.ALEX);
    }
}
