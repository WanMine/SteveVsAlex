package com.diamantino.stevevsalex.network;

import com.diamantino.stevevsalex.network.packets.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.Optional;

import static com.diamantino.stevevsalex.SteveVsAlex.MODID;

public class SVANetworking {

    private static final String PROTOCOL_VERSION = "8";
    public static SimpleChannel INSTANCE;

    public static void init() {
        INSTANCE = NetworkRegistry.newSimpleChannel(
                new ResourceLocation(MODID, "main"),
                () -> PROTOCOL_VERSION,
                PROTOCOL_VERSION::equals,
                PROTOCOL_VERSION::equals
        );

        int id = -1;

        INSTANCE.registerMessage(
                ++id,
                RotationPacket.class,
                RotationPacket::toBytes,
                RotationPacket::new,
                RotationPacket::handle,
                Optional.of(NetworkDirection.PLAY_TO_SERVER)
        );

        INSTANCE.registerMessage(
                ++id,
                MoveHeliUpPacket.class,
                MoveHeliUpPacket::toBytes,
                MoveHeliUpPacket::new,
                MoveHeliUpPacket::handle,
                Optional.of(NetworkDirection.PLAY_TO_SERVER)
        );

        INSTANCE.registerMessage(
                ++id,
                OpenInventoryPacket.class,
                OpenInventoryPacket::toBytes,
                OpenInventoryPacket::new,
                OpenInventoryPacket::handle,
                Optional.of(NetworkDirection.PLAY_TO_SERVER)
        );

        INSTANCE.registerMessage(
                ++id,
                CycleItemsPacket.class,
                CycleItemsPacket::toBytes,
                CycleItemsPacket::new,
                CycleItemsPacket::handle,
                Optional.of(NetworkDirection.PLAY_TO_SERVER)
        );

        INSTANCE.registerMessage(
                ++id,
                UpdateUpgradePacket.class,
                UpdateUpgradePacket::toBytes,
                UpdateUpgradePacket::new,
                UpdateUpgradePacket::handle,
                Optional.of(NetworkDirection.PLAY_TO_CLIENT)
        );

        INSTANCE.registerMessage(
                ++id,
                OpenPlaneInventoryPacket.class,
                OpenPlaneInventoryPacket::toBytes,
                OpenPlaneInventoryPacket::new,
                OpenPlaneInventoryPacket::handle,
                Optional.of(NetworkDirection.PLAY_TO_SERVER)
        );

        INSTANCE.registerMessage(
                ++id,
                RemoveUpgradePacket.class,
                RemoveUpgradePacket::toBytes,
                RemoveUpgradePacket::new,
                RemoveUpgradePacket::handle,
                Optional.of(NetworkDirection.PLAY_TO_SERVER)
        );

        INSTANCE.registerMessage(
                ++id,
                UpgradeRemovedPacket.class,
                UpgradeRemovedPacket::toBytes,
                UpgradeRemovedPacket::new,
                UpgradeRemovedPacket::handle,
                Optional.of(NetworkDirection.PLAY_TO_CLIENT)
        );

        INSTANCE.registerMessage(
                ++id,
                JukeboxPacket.class,
                JukeboxPacket::toBytes,
                JukeboxPacket::new,
                JukeboxPacket::handle,
                Optional.of(NetworkDirection.PLAY_TO_CLIENT)
        );

        INSTANCE.registerMessage(
                ++id,
                ChangeThrottlePacket.class,
                ChangeThrottlePacket::toBytes,
                ChangeThrottlePacket::new,
                ChangeThrottlePacket::handle,
                Optional.of(NetworkDirection.PLAY_TO_SERVER)
        );

        INSTANCE.registerMessage(
                ++id,
                PitchPacket.class,
                PitchPacket::toBytes,
                PitchPacket::new,
                PitchPacket::handle,
                Optional.of(NetworkDirection.PLAY_TO_SERVER)
        );
    }
}