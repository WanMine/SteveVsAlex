package com.diamantino.stevevsalex.entities.steve;

import com.diamantino.stevevsalex.entities.base.PlaneEntity;
import com.diamantino.stevevsalex.entities.base.Teams;
import com.diamantino.stevevsalex.entities.base.WeaponType;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class SteveAneEntity extends PlaneEntity {
    public SteveAneEntity(EntityType<? extends PlaneEntity> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn, "steve_ane", WeaponType.BOMB, Teams.STEVE);
    }
}
