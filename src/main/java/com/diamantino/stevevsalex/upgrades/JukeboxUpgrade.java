package com.diamantino.stevevsalex.upgrades;

import com.diamantino.stevevsalex.client.sounds.MovingSound;
import com.diamantino.stevevsalex.entities.base.PlaneEntity;
import com.diamantino.stevevsalex.network.SVANetworking;
import com.diamantino.stevevsalex.network.packets.JukeboxPacket;
import com.diamantino.stevevsalex.registries.SVAUpgrades;
import com.diamantino.stevevsalex.upgrades.base.Upgrade;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.RecordItem;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.registries.ForgeRegistries;

public class JukeboxUpgrade extends Upgrade {

    private ItemStack record = ItemStack.EMPTY;

    public JukeboxUpgrade(PlaneEntity planeEntity) {
        super(SVAUpgrades.JUKEBOX.get(), planeEntity);
    }

    @Override
    public CompoundTag serializeNBT() {
        return record.save(new CompoundTag());
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        record = ItemStack.of(nbt);
    }

    @Override
    public void onItemRightClick(PlayerInteractEvent.RightClickItem event) {
        if (!planeEntity.level.isClientSide) {
            Player player = event.getEntity();
            ItemStack itemStack = player.getItemInHand(event.getHand());
            if (itemStack.getItem() instanceof RecordItem newRecordItem && newRecordItem != record.getItem()) {
                ItemStack oldRecord = record;
                record = itemStack.copy();
                if (!player.isCreative()) {
                    itemStack.shrink(1);
                }
                if (!oldRecord.isEmpty()) {
                    player.addItem(oldRecord);
                }
                player.awardStat(Stats.PLAY_RECORD);
                SVANetworking.INSTANCE.send(PacketDistributor.TRACKING_ENTITY.with(() -> planeEntity), new JukeboxPacket(planeEntity.getId(), ForgeRegistries.ITEMS.getKey(newRecordItem)));
            }
        }
    }

    @Override
    public void writePacket(FriendlyByteBuf buffer) {}

    @Override
    public void readPacket(FriendlyByteBuf buffer) {}

    @Override
    public void onRemoved() {
        planeEntity.spawnAtLocation(Items.JUKEBOX);
        planeEntity.spawnAtLocation(record);
        if (planeEntity.level.isClientSide) {
            MovingSound.remove(planeEntity);
        }
    }
}