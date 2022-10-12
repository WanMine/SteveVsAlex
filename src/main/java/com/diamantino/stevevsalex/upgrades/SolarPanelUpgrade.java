package com.diamantino.stevevsalex.upgrades;

import com.diamantino.stevevsalex.entities.base.PlaneEntity;
import com.diamantino.stevevsalex.registries.SVAItems;
import com.diamantino.stevevsalex.registries.SVAUpgrades;
import com.diamantino.stevevsalex.upgrades.base.Upgrade;
import com.diamantino.stevevsalex.upgrades.engines.ElectricVehicleEngineUpgrade;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class SolarPanelUpgrade extends Upgrade {

    private int efficiencyLevel = 1;
    private final short MAX_PER_TICK;

    public SolarPanelUpgrade(PlaneEntity planeEntity) {
        super(SVAUpgrades.SOLAR_GENERATOR_UPGRADE.get(), planeEntity);

        MAX_PER_TICK = 10;
    }

    @Override
    public void tick() {
        PlaneEntity entity = getPlaneEntity();
        Level world = entity.getLevel();
        if (canSeeSun(world, entity.getOnPos().above())) {
            float brightness = MAX_PER_TICK * efficiencyLevel * getSunBrightness(entity.getLevel(), 1.0F);
            if (entity.vehicleEngineUpgrade instanceof ElectricVehicleEngineUpgrade engine) {
                engine.energyStorage.receiveEnergy((int) brightness, false);
            }
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onApply(ItemStack itemStack, Player playerEntity) {
        ListTag listtag = itemStack.getEnchantmentTags();

        for(int i = 0; i < listtag.size(); ++i) {
            CompoundTag compoundtag = listtag.getCompound(i);
            Registry.ENCHANTMENT.getOptional(EnchantmentHelper.getEnchantmentId(compoundtag)).ifPresent((enchantment) -> {
                if (enchantment == Enchantments.BLOCK_EFFICIENCY) {
                    efficiencyLevel = EnchantmentHelper.getEnchantmentLevel(compoundtag);
                }
            });
        }
    }

    @Override
    public void writePacket(FriendlyByteBuf buffer) {
        buffer.writeByte(efficiencyLevel);
    }

    @Override
    public void readPacket(FriendlyByteBuf buffer) {
        efficiencyLevel = buffer.readByte();
    }

    @Override
    public void onRemoved() {
        planeEntity.spawnAtLocation(SVAItems.SOLAR_GENERATOR_UPGRADE.get().getDefaultInstance());
    }

    private static boolean canSeeSun(@Nullable Level level, BlockPos pos) {
        return level != null && level.dimensionType().hasSkyLight() && level.getSkyDarken() < 4 && level.canSeeSky(pos);
    }

    public static float getSunBrightness(Level world, float partialTicks) {
        float f = world.getTimeOfDay(partialTicks);
        float f1 = 1.0F - (Mth.cos(f * ((float) Math.PI * 2F)) * 2.0F + 0.2F);
        f1 = Mth.clamp(f1, 0.0F, 1.0F);
        f1 = 1.0F - f1;
        f1 = (float) (f1 * (1.0D - world.getRainLevel(partialTicks) * 5.0F / 16.0D));
        f1 = (float) (f1 * (1.0D - world.getThunderLevel(partialTicks) * 5.0F / 16.0D));
        return f1 * 0.8F;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag compoundTag = new CompoundTag();
        compoundTag.putByte("efficiency", (byte) efficiencyLevel);
        return compoundTag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        efficiencyLevel = nbt.getByte("efficiency");
    }
}