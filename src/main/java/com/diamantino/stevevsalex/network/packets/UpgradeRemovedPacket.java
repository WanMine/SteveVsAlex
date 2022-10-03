package com.diamantino.stevevsalex.network.packets;

import com.diamantino.stevevsalex.entities.base.PlaneEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class UpgradeRemovedPacket {

    private final ResourceLocation upgradeID;
    private final int planeEntityID;

    public UpgradeRemovedPacket(ResourceLocation upgradeID, int planeEntityID) {
        this.upgradeID = upgradeID;
        this.planeEntityID = planeEntityID;
    }

    public UpgradeRemovedPacket(FriendlyByteBuf buffer) {
        upgradeID = buffer.readResourceLocation();
        planeEntityID = buffer.readVarInt();
    }

    public void toBytes(FriendlyByteBuf buffer) {
        buffer.writeResourceLocation(upgradeID);
        buffer.writeVarInt(planeEntityID);
    }

    public void handle(Supplier<NetworkEvent.Context> ctxSup) {
        NetworkEvent.Context ctx = ctxSup.get();
        ctx.enqueueWork(() -> {
            ClientLevel clientWorld = Minecraft.getInstance().level;
            ((PlaneEntity) clientWorld.getEntity(planeEntityID)).removeUpgrade(upgradeID);
        });
        ctx.setPacketHandled(true);
    }
}