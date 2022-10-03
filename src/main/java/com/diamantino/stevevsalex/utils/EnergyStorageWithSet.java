package com.diamantino.stevevsalex.utils;

import net.minecraftforge.energy.EnergyStorage;

public class EnergyStorageWithSet extends EnergyStorage {

    public EnergyStorageWithSet(int capacity) {
        super(capacity);
    }

    public void setEnergy(int energy) {
        this.energy = Math.min(energy, capacity);
    }
}