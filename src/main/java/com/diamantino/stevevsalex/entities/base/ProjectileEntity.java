package com.diamantino.stevevsalex.entities.base;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ProjectileEntity extends AbstractArrow {
    protected ProjectileEntity(EntityType<? extends ProjectileEntity> p_36721_, Level p_36722_) {
        super(p_36721_, p_36722_);
    }

    public ProjectileEntity(EntityType<? extends ProjectileEntity> p_36721_, Level p_36861_, double p_36862_, double p_36863_, double p_36864_) {
        super(p_36721_, p_36862_, p_36863_, p_36864_, p_36861_);
    }

    @Override
    protected ItemStack getPickupItem() {
        return null;
    }
}
