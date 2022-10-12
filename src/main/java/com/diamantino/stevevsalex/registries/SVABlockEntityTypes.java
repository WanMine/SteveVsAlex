package com.diamantino.stevevsalex.registries;

import com.diamantino.stevevsalex.blockentitytypes.AmmoWorkbenchBlockEntityType;
import com.diamantino.stevevsalex.blockentitytypes.VehicleChargerBlockEntityType;
import com.diamantino.stevevsalex.blockentitytypes.VehicleWorkbenchBlockEntityType;
import com.google.common.collect.ImmutableSet;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.diamantino.stevevsalex.SteveVsAlex.MODID;
import static com.diamantino.stevevsalex.registries.SVABlocks.*;

@SuppressWarnings("unused")
public class SVABlockEntityTypes {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MODID);

    public static void init() {
        BLOCK_ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final RegistryObject<BlockEntityType<VehicleWorkbenchBlockEntityType>> VEHICLE_WORKBENCH_TILE = BLOCK_ENTITIES.register("vehicle_workbench", () -> new BlockEntityType<>(VehicleWorkbenchBlockEntityType::new, ImmutableSet.of(VEHICLE_WORKBENCH_BLOCK.get()), null));
    public static final RegistryObject<BlockEntityType<AmmoWorkbenchBlockEntityType>> AMMO_WORKBENCH_TILE = BLOCK_ENTITIES.register("ammo_workbench", () -> new BlockEntityType<>(AmmoWorkbenchBlockEntityType::new, ImmutableSet.of(AMMO_WORKBENCH_BLOCK.get()), null));
    public static final RegistryObject<BlockEntityType<VehicleChargerBlockEntityType>> VEHICLE_CHARGER_TILE = BLOCK_ENTITIES.register("vehicle_charger", () -> new BlockEntityType<>(VehicleChargerBlockEntityType::new, ImmutableSet.of(VEHICLE_CHARGER_BLOCK.get()), null));
}
