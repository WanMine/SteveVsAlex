package com.diamantino.stevevsalex.registries;

import com.diamantino.stevevsalex.containers.*;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.diamantino.stevevsalex.SteveVsAlex.MODID;

public class SVAContainers {
    public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, MODID);

    public static final RegistryObject<MenuType<VehicleInventoryContainer>> VEHICLE_INVENTORY = CONTAINERS.register("vehicle_inventory", () -> IForgeMenuType.create(VehicleInventoryContainer::new));
    public static final RegistryObject<MenuType<StorageContainer>> STORAGE = CONTAINERS.register("storage", () -> IForgeMenuType.create(StorageContainer::new));
    public static final RegistryObject<MenuType<RemoveUpgradesContainer>> UPGRADES_REMOVAL = CONTAINERS.register("upgrades_removal", () -> IForgeMenuType.create(RemoveUpgradesContainer::new));
    public static final RegistryObject<MenuType<VehicleWorkbenchContainer>> VEHICLE_WORKBENCH = CONTAINERS.register("vehicle_workbench", () -> new MenuType<>(VehicleWorkbenchContainer::new));
    public static final RegistryObject<MenuType<AmmoWorkbenchContainer>> AMMO_WORKBENCH = CONTAINERS.register("ammo_workbench", () -> new MenuType<>(AmmoWorkbenchContainer::new));


    public static void init() {
        CONTAINERS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
