package com.diamantino.stevevsalex.network.packets;

import com.diamantino.stevevsalex.entities.base.PlaneEntity;
import com.diamantino.stevevsalex.upgrades.base.Upgrade;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

@SuppressWarnings({"unused", "EmptyMethod"})
public class OpenInventoryPacket {

    public OpenInventoryPacket() {}
    public OpenInventoryPacket(FriendlyByteBuf buffer) {}
    public void toBytes(FriendlyByteBuf buffer) {}

    public void handle(Supplier<NetworkEvent.Context> ctxSup) {
        NetworkEvent.Context ctx = ctxSup.get();
        ctx.enqueueWork(() -> {
            ServerPlayer player = ctx.getSender();
            if (player != null && player.getVehicle() instanceof PlaneEntity plane) {
                for (Upgrade upgrade : plane.upgrades.values()) {
                    if (upgrade.hasStorage()) {
                        upgrade.openStorageGui(player);
                        break;
                    }
                }
            }
        });
        ctx.setPacketHandled(true);
    }
}