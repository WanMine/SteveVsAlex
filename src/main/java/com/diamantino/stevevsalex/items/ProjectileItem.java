package com.diamantino.stevevsalex.items;

import com.diamantino.stevevsalex.entities.SteveOmbEntity;
import com.diamantino.stevevsalex.entities.base.ProjectileEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class ProjectileItem extends Item {

    private static final Predicate<Entity> ENTITY_PREDICATE = EntitySelector.NO_SPECTATORS.and(Entity::isPickable);
    private final Supplier<? extends EntityType<? extends ProjectileEntity>> bombEntityType;

    public ProjectileItem(Properties properties, Supplier<? extends EntityType<? extends ProjectileEntity>> bombEntityType) {
        super(properties.stacksTo(1));
        this.bombEntityType = bombEntityType;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        HitResult hitResult = getPlayerPOVHitResult(worldIn, playerIn, ClipContext.Fluid.ANY);
        if (hitResult.getType() == HitResult.Type.MISS) {
            return InteractionResultHolder.pass(itemstack);
        } else {
            Vec3 vec3d = playerIn.getViewVector(1.0F);
            List<Entity> list = worldIn.getEntities(playerIn, playerIn.getBoundingBox().expandTowards(vec3d.scale(5.0D)).inflate(1.0D), ENTITY_PREDICATE);
            if (!list.isEmpty()) {
                Vec3 vec3d1 = playerIn.getEyePosition(1.0F);

                for (Entity entity : list) {
                    AABB aabb = entity.getBoundingBox().inflate(entity.getPickRadius());
                    if (aabb.contains(vec3d1)) {
                        return InteractionResultHolder.pass(itemstack);
                    }
                }
            }

            if (hitResult.getType() == HitResult.Type.BLOCK) {
                ProjectileEntity projectileEntity = bombEntityType.get().create(worldIn);

                projectileEntity.setPos(hitResult.getLocation().x(), hitResult.getLocation().y(), hitResult.getLocation().z());
                projectileEntity.setYRot(playerIn.getYRot());
                projectileEntity.yRotO = playerIn.yRotO;
                projectileEntity.setCustomName(itemstack.getHoverName());
                CompoundTag entityTag = itemstack.getTagElement("EntityTag");
                if (entityTag != null) {
                    projectileEntity.readAdditionalSaveData(entityTag);
                }
                if (!worldIn.noCollision(projectileEntity, projectileEntity.getBoundingBox().inflate(-0.1D))) {
                    return InteractionResultHolder.fail(itemstack);
                } else {
                    if (!worldIn.isClientSide) {
                        worldIn.addFreshEntity(projectileEntity);
                        if (!playerIn.getAbilities().instabuild) {
                            itemstack.shrink(1);
                        }
                    }
                    playerIn.awardStat(Stats.ITEM_USED.get(this));
                    return InteractionResultHolder.success(itemstack);
                }
            } else {
                return InteractionResultHolder.pass(itemstack);
            }
        }
    }
}