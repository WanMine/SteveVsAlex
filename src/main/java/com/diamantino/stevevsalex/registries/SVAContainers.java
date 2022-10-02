package com.diamantino.stevevsalex.registries;

import com.diamantino.stevevsalex.container.PlaneInventoryContainer;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.diamantino.stevevsalex.SteveVsAlex.MODID;

public class SVAContainers {
    public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, MODID);

    public static final RegistryObject<MenuType<PlaneInventoryContainer>> PLANE_INVENTORY = CONTAINERS.register("plane_inventory", () -> IForgeMenuType.create(PlaneInventoryContainer::new));

    public static void init() {
        CONTAINERS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
