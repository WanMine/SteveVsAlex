package com.diamantino.stevevsalex.upgrades;

import com.diamantino.stevevsalex.entities.base.PlaneEntity;
import com.diamantino.stevevsalex.upgrades.base.Upgrade;
import com.diamantino.stevevsalex.upgrades.base.UpgradeType;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.world.entity.HumanoidArm;

public abstract class VehicleEngineUpgrade extends Upgrade {

    public VehicleEngineUpgrade(UpgradeType type, PlaneEntity planeEntity) {
        super(type, planeEntity);
    }

    @Override
    public void remove() {
        super.remove();
        planeEntity.vehicleEngineUpgrade = null;
    }

    public abstract boolean isPowered();
    public abstract void renderPowerHUD(PoseStack matrixStack, HumanoidArm side, int scaledWidth, int scaledHeight, float partialTicks);
}