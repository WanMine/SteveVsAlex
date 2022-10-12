package com.diamantino.stevevsalex.upgrades.engines;

import com.diamantino.stevevsalex.client.gui.VehicleInventoryScreen;
import com.diamantino.stevevsalex.entities.base.PlaneEntity;
import com.diamantino.stevevsalex.registries.SVAItems;
import com.diamantino.stevevsalex.registries.SVAUpgrades;
import com.diamantino.stevevsalex.upgrades.VehicleEngineUpgrade;
import com.diamantino.stevevsalex.utils.ClientUtils;
import com.diamantino.stevevsalex.utils.EnergyStorageWithSet;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.EnergyStorage;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.diamantino.stevevsalex.SteveVsAlex.MODID;

public class ElectricVehicleEngineUpgrade extends VehicleEngineUpgrade {

    public static final int CAPACITY = 1_500_000;

    public final EnergyStorageWithSet energyStorage = new EnergyStorageWithSet(CAPACITY);
    public final LazyOptional<EnergyStorage> energyStorageLazyOptional = LazyOptional.of(() -> energyStorage);

    public ElectricVehicleEngineUpgrade(PlaneEntity planeEntity) {
        super(SVAUpgrades.ELECTRIC_VEHICLE_ENGINE.get(), planeEntity);
    }

    @Override
    public void tick() {
        if (planeEntity.getThrottle() > 0) {
            if (energyStorage.extractEnergy(12 * planeEntity.getFuelCost(), false) > 0) {
                updateClient();
            }
        }
    }

    @Override
    public boolean isPowered() {
        return energyStorage.getEnergyStored() > 12 * planeEntity.getFuelCost();
    }

    @Override
    public void renderPowerHUD(PoseStack matrixStack, HumanoidArm side, int scaledWidth, int scaledHeight, float partialTicks) {
        int i = scaledWidth / 2;
        if (side == HumanoidArm.LEFT) {
            ClientUtils.blit(matrixStack, -90, i - 91 - 29, scaledHeight - 22, 38, 44, 22, 21);
        } else {
            ClientUtils.blit(matrixStack, -90, i + 91, scaledHeight - 22, 38, 44, 22, 21);
        }

        int energy = energyStorage.getEnergyStored();

        if (energy > 0) {
            int energyScaled = energy * 15 / CAPACITY;
            if (side == HumanoidArm.LEFT) {
                ClientUtils.blit(matrixStack, -90, i - 91 - 29 + 3, scaledHeight - 22 + 16 - energyScaled, 60, 57 - energyScaled, 16, energyScaled + 2);
            } else {
                ClientUtils.blit(matrixStack, -90, i + 91 + 3, scaledHeight - 22 + 16 - energyScaled, 60, 57 - energyScaled, 16, energyScaled + 2);
            }
        }
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        energyStorageLazyOptional.invalidate();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag compoundNBT = new CompoundTag();
        compoundNBT.putInt("energy", energyStorage.getEnergyStored());
        return compoundNBT;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        int energy = nbt.getInt("energy");
        energyStorage.setEnergy(Math.min(energy, CAPACITY));
    }

    @Override
    public void writePacket(FriendlyByteBuf buffer) {
        buffer.writeVarInt(energyStorage.getEnergyStored());
    }

    @Override
    public void readPacket(FriendlyByteBuf buffer) {
        energyStorage.setEnergy(buffer.readVarInt());
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ENERGY) {
            return energyStorageLazyOptional.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void onRemoved() {
        planeEntity.spawnAtLocation(SVAItems.ELECTRIC_VEHICLE_ENGINE.get());
    }

    @Override
    public void renderScreen(PoseStack poseStack, int mouseX, int mouseY, float partialTicks, VehicleInventoryScreen vehicleInventoryScreen) {
        if (vehicleInventoryScreen.isHovering(152, 7, 16, 72, mouseX, mouseY)) {
            vehicleInventoryScreen.renderTooltip(poseStack, Component.translatable(MODID + ".gui.energy", energyStorage.getEnergyStored()), mouseX, mouseY);
        }
    }

    @Override
    public void renderScreenBg(PoseStack poseStack, int x, int y, float partialTicks, VehicleInventoryScreen screen) {
        screen.blit(poseStack, screen.getGuiLeft() + 152, screen.getGuiTop() + 7, 176, 0, 16, 72);

        int energy = energyStorage.getEnergyStored();
        if (energy > 0) {
            int energyScaled = energy * 71 / CAPACITY;
            screen.blit(poseStack, screen.getGuiLeft() + 152, screen.getGuiTop() + 78 - energyScaled, 192, 71 - energyScaled, 16, energyScaled + 1);
        }
    }
}