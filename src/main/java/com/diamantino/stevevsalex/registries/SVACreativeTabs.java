package com.diamantino.stevevsalex.registries;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import static com.diamantino.stevevsalex.SteveVsAlex.MODID;

public class SVACreativeTabs {
    public static final CreativeModeTab VEHICLES = new CreativeModeTab(MODID + ".vehicles") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(SVAItems.STEVE_COPTER_ITEM.get());
        }
    };
    public static final CreativeModeTab UPGRADES = new CreativeModeTab(MODID + ".upgrades") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(SVAItems.WRENCH.get());
        }
    };
    public static final CreativeModeTab BLOCKS = new CreativeModeTab(MODID + ".blocks") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(SVAItems.VEHICLE_CHARGER.get());
        }
    };
    public static final CreativeModeTab AMMO = new CreativeModeTab(MODID + ".ammo") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(SVAItems.STEVE_OMB_ITEM.get());
        }
    };
    public static final CreativeModeTab ITEMS = new CreativeModeTab(MODID + ".items") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(SVAItems.STIVIUM_INGOT.get());
        }
    };
}
