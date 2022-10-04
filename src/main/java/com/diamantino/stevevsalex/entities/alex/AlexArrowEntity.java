package com.diamantino.stevevsalex.entities.alex;

import com.diamantino.stevevsalex.entities.base.ProjectileEntity;
import com.diamantino.stevevsalex.registries.SVAEntityTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;

public class AlexArrowEntity extends ProjectileEntity {
    public boolean shouldDespawn = false;

    public AlexArrowEntity(EntityType<? extends AlexArrowEntity> p_36721_, Level p_36722_) {
        super(p_36721_, p_36722_, "alex_arrow");
    }

    public AlexArrowEntity(Level p_36861_, double p_36862_, double p_36863_, double p_36864_) {
        super(SVAEntityTypes.ALEX_ARROW.get(), p_36861_, p_36862_, p_36863_, p_36864_, "alex_arrow");
    }

    @Override
    public void tick() {
        super.tick();

        if (shouldDespawn) {
            kill();
        }
    }

    @Override
    protected void onHit(HitResult p_37260_) {
        super.onHit(p_37260_);

        shouldDespawn = true;
    }
}
