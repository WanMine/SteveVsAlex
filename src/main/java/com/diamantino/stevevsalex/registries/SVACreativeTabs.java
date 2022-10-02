package com.diamantino.stevevsalex.registries;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import static com.diamantino.stevevsalex.SteveVsAlex.MODID;

public class SVACreativeTabs {
    public static final CreativeModeTab ITEMS = new CreativeModeTab(MODID) {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(SVAItems.STEVE_COPTER_ITEM.get());
        }
    };
}
