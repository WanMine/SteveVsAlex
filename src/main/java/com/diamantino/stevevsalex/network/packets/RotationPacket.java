package com.diamantino.stevevsalex.network.packets;

import com.diamantino.stevevsalex.registries.SVADataSerializers;
import com.diamantino.stevevsalex.entities.base.PlaneEntity;
import com.diamantino.stevevsalex.utils.MathUtils;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.FriendlyByteBuf;
import com.mojang.math.Quaternion;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class RotationPacket {

    private final Quaternion quaternion;

    public RotationPacket(Quaternion quaternion) {
        this.quaternion = quaternion;
    }

    public RotationPacket(FriendlyByteBuf buffer) {
        this.quaternion = SVADataSerializers.QUATERNION_SERIALIZER_ENTRY.get().read(buffer);
    }

    public void toBytes(FriendlyByteBuf buffer) {
        SVADataSerializers.QUATERNION_SERIALIZER_ENTRY.get().write(buffer, quaternion);
    }

    public void handle(Supplier<NetworkEvent.Context> ctxSup) {
        NetworkEvent.Context ctx = ctxSup.get();
        ctx.enqueueWork(() -> {
            ServerPlayer sender = ctx.getSender();
            if (sender != null && sender.getVehicle() instanceof PlaneEntity planeEntity) {
                planeEntity.setQ(quaternion);
                MathUtils.EulerAngles eulerAngles = MathUtils.toEulerAngles(quaternion);
                planeEntity.setYRot((float) eulerAngles.yaw);
                planeEntity.setXRot((float) eulerAngles.pitch);
                planeEntity.rotationRoll = (float) eulerAngles.roll;
                planeEntity.setQ_Client(quaternion);
            }
        });
        ctx.setPacketHandled(true);
    }
}