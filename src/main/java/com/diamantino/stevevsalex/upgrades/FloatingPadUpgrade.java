package com.diamantino.stevevsalex.upgrades;

import com.diamantino.stevevsalex.entities.base.PlaneEntity;
import com.diamantino.stevevsalex.registries.SVAItems;
import com.diamantino.stevevsalex.registries.SVAUpgrades;
import com.diamantino.stevevsalex.upgrades.base.Upgrade;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;

public class FloatingPadUpgrade extends Upgrade {

    public FloatingPadUpgrade(PlaneEntity planeEntity) {
        super(SVAUpgrades.FLOATING_PAD_UPGRADE.get(), planeEntity);
    }

    @Override
    public void tick() {
        if (planeEntity.getHealth() > 0 && planeEntity.isOnWater()) {
            Vec3 motion = planeEntity.getDeltaMovement();
            double f = 1;
            double y = Mth.lerp(1, motion.y, Math.max(motion.y, 0));
            planeEntity.setDeltaMovement(motion.x * f, y, motion.z * f);
            if (planeEntity.level.getBlockState(new BlockPos(planeEntity.position().add(0, 0.5, 0))).getBlock() == Blocks.WATER) {
                planeEntity.setDeltaMovement(planeEntity.getDeltaMovement().add(0, 0.04, 0));
            }
        }
    }

    @Override
    public void writePacket(FriendlyByteBuf buffer) {}

    @Override
    public void readPacket(FriendlyByteBuf buffer) {}

    @Override
    public void onRemoved() {
        planeEntity.spawnAtLocation(SVAItems.FLOATING_PAD_UPGRADE.get());
    }
}