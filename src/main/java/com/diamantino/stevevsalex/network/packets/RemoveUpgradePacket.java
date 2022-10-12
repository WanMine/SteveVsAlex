package com.diamantino.stevevsalex.network.packets;

import com.diamantino.stevevsalex.containers.RemoveUpgradesContainer;
import com.diamantino.stevevsalex.entities.base.PlaneEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class RemoveUpgradePacket {

    private final ResourceLocation upgradeID;

    public RemoveUpgradePacket(ResourceLocation upgradeID) {
        this.upgradeID = upgradeID;
    }

    public RemoveUpgradePacket(FriendlyByteBuf buffer) {
        upgradeID = buffer.readResourceLocation();
    }

    public void toBytes(FriendlyByteBuf buffer) {
        buffer.writeResourceLocation(upgradeID);
    }

    public void handle(Supplier<NetworkEvent.Context> ctxSup) {
        NetworkEvent.Context ctx = ctxSup.get();
        ctx.enqueueWork(() -> {
            ServerPlayer sender = ctx.getSender();
            if (sender.containerMenu instanceof RemoveUpgradesContainer container) {
                Entity entity = sender.level.getEntity(container.planeID);
                if (entity instanceof PlaneEntity) {
                    ((PlaneEntity) entity).removeUpgrade(upgradeID);
                }
            }
        });
        ctx.setPacketHandled(true);
    }
}