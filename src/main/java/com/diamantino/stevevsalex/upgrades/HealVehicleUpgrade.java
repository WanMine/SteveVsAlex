package com.diamantino.stevevsalex.upgrades;

import com.diamantino.stevevsalex.entities.base.PlaneEntity;
import com.diamantino.stevevsalex.registries.SVAItems;
import com.diamantino.stevevsalex.registries.SVAUpgrades;
import com.diamantino.stevevsalex.upgrades.base.Upgrade;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;

public class HealVehicleUpgrade extends Upgrade {

    public HealVehicleUpgrade(PlaneEntity planeEntity) {
        super(SVAUpgrades.HEAL_VEHICLE.get(), planeEntity);
    }

    private int cooldown = 10;

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag compoundNBT = new CompoundTag();
        compoundNBT.putInt("cooldown", cooldown);
        return compoundNBT;
    }

    @Override
    public void deserializeNBT(CompoundTag compoundNBT) {
        cooldown = compoundNBT.getInt("cooldown");
    }

    @Override
    public void tick() {
        if (cooldown > 0) {
            --cooldown;
        } else {
            remove();
        }
    }

    @Override
    public void onApply(ItemStack itemStack, Player playerEntity) {
        int health = planeEntity.getHealth();
        int m = planeEntity.getMaxHealth() * 2;
        if (health < m) {
            int heal = planeEntity.getOnGround() ? 2 : 1;
            planeEntity.setHealth(Math.min(health + heal, m));
        }
        planeEntity.goldenHeartsTimeout = 0;
    }

    @Override
    public void writePacket(FriendlyByteBuf buffer) {}

    @Override
    public void readPacket(FriendlyByteBuf buffer) {}

    @Override
    public void onRemoved() {
        planeEntity.spawnAtLocation(SVAItems.HEAL_VEHICLE.get().getDefaultInstance());
    }
}