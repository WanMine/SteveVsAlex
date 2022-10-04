package com.diamantino.stevevsalex.upgrades;

import com.diamantino.stevevsalex.entities.base.PlaneEntity;
import com.diamantino.stevevsalex.registries.SVAItems;
import com.diamantino.stevevsalex.registries.SVAUpgrades;
import com.diamantino.stevevsalex.upgrades.base.Upgrade;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;

public class ReinforcedArmorUpgrade extends Upgrade {

    private int protectionLevel = 0;

    public ReinforcedArmorUpgrade(PlaneEntity planeEntity) {
        super(SVAUpgrades.REINFORCED_ARMOR_UPGRADE.get(), planeEntity);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onApply(ItemStack itemStack, Player playerEntity) {
        ListTag listtag = itemStack.getEnchantmentTags();

        for(int i = 0; i < listtag.size(); ++i) {
            CompoundTag compoundtag = listtag.getCompound(i);
            Registry.ENCHANTMENT.getOptional(EnchantmentHelper.getEnchantmentId(compoundtag)).ifPresent((enchantment) -> {
                if (enchantment == Enchantments.ALL_DAMAGE_PROTECTION) {
                    protectionLevel = EnchantmentHelper.getEnchantmentLevel(compoundtag);
                }
            });
        }
    }

    @Override
    public void writePacket(FriendlyByteBuf buffer) {
        buffer.writeByte(protectionLevel);
    }

    @Override
    public void readPacket(FriendlyByteBuf buffer) {
        protectionLevel = buffer.readByte();
    }

    @Override
    public void onRemoved() {
        ItemStack itemStack = SVAItems.REINFORCED_ARMOR_UPGRADE.get().getDefaultInstance();
        if (protectionLevel > 0) {
            itemStack.enchant(Enchantments.ALL_DAMAGE_PROTECTION, protectionLevel);
        }
        planeEntity.spawnAtLocation(itemStack);
    }

    public float getReducedDamage(float amount) {
        return amount * (1.0f - (0.04f * getArmorValue()));
    }

    public int getArmorValue() {
        return 15 + (protectionLevel * 2);
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag compoundTag = new CompoundTag();
        compoundTag.putByte("protection", (byte) protectionLevel);
        return compoundTag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        protectionLevel = nbt.getByte("protection");
    }
}