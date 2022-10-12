package com.diamantino.stevevsalex.items;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import org.jetbrains.annotations.NotNull;

public class SolarGeneratorUpgradeItem extends Item {
    public SolarGeneratorUpgradeItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isEnchantable(@NotNull ItemStack p_41456_) {
        return true;
    }

    @SuppressWarnings("deprecation")
    @Override
    public int getEnchantmentValue() {
        return 9;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        if (enchantment == Enchantments.BLOCK_EFFICIENCY) {
            return true;
        }
        return super.canApplyAtEnchantingTable(stack, enchantment);
    }
}