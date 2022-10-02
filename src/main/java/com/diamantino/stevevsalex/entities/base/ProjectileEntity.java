package com.diamantino.stevevsalex.entities.base;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public abstract class ProjectileEntity extends AbstractArrow {
    public String projectileName;

    protected ProjectileEntity(EntityType<? extends ProjectileEntity> p_36721_, Level p_36722_, String projectileName) {
        super(p_36721_, p_36722_);

        this.projectileName = projectileName;

        setBaseDamage(4);
    }

    public ProjectileEntity(EntityType<? extends ProjectileEntity> p_36721_, Level p_36861_, double p_36862_, double p_36863_, double p_36864_, String projectileName) {
        super(p_36721_, p_36862_, p_36863_, p_36864_, p_36861_);

        this.projectileName = projectileName;

        setBaseDamage(4);
    }

    @Override
    protected ItemStack getPickupItem() {
        return null;
    }
}
