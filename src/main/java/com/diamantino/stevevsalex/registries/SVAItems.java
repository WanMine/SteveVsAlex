package com.diamantino.stevevsalex.registries;

import com.diamantino.stevevsalex.items.*;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.function.Supplier;

import static com.diamantino.stevevsalex.SteveVsAlex.MODID;

public class SVAItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static Dictionary<String, RegistryObject<PlaneItem>> airVehichlesItems = new Hashtable<>();

    //Vehicles
    public static final RegistryObject<PlaneItem> STEVE_COPTER_ITEM = registerAirVehicle("steve_copter", () -> new PlaneItem(new Item.Properties().tab(SVACreativeTabs.ITEMS), SVAEntityTypes.STEVE_COPTER));
    public static final RegistryObject<PlaneItem> STEVE_ANE_ITEM = registerAirVehicle("steve_ane", () -> new PlaneItem(new Item.Properties().tab(SVACreativeTabs.ITEMS), SVAEntityTypes.STEVE_ANE));
    public static final RegistryObject<PlaneItem> ALEX_COPTER_ITEM = registerAirVehicle("alex_copter", () -> new PlaneItem(new Item.Properties().tab(SVACreativeTabs.ITEMS), SVAEntityTypes.ALEX_COPTER));
    public static final RegistryObject<PlaneItem> ALEX_ANE_ITEM = registerAirVehicle("alex_ane", () -> new PlaneItem(new Item.Properties().tab(SVACreativeTabs.ITEMS), SVAEntityTypes.ALEX_ANE));

    //Upgrades
    public static final RegistryObject<Item> FLOATING_PAD_UPGRADE = ITEMS.register("floating_pad_upgrade", () -> new Item(new Item.Properties().tab(SVACreativeTabs.ITEMS).stacksTo(1)));
    public static final RegistryObject<Item> ROCKET_BOOSTER_UPGRADE = ITEMS.register("rocket_booster_upgrade", () -> new Item(new Item.Properties().tab(SVACreativeTabs.ITEMS).stacksTo(1)));
    public static final RegistryObject<Item> HEAL_VEHICLE = ITEMS.register("heal_vehicle", () -> new Item(new Item.Properties().tab(SVACreativeTabs.ITEMS)));
    public static final RegistryObject<Item> REINFORCED_ARMOR_UPGRADE = ITEMS.register("reinforced_armor_upgrade", () -> new ArmorUpgradeItem(new Item.Properties().tab(SVACreativeTabs.ITEMS).stacksTo(1)));
    public static final RegistryObject<Item> SOLAR_GENERATOR_UPGRADE = ITEMS.register("solar_generator_upgrade", () -> new Item(new Item.Properties().tab(SVACreativeTabs.ITEMS).stacksTo(1)));
    public static final RegistryObject<Item> JUKEBOX_UPGRADE = ITEMS.register("jukebox_upgrade", () -> new Item(new Item.Properties().tab(SVACreativeTabs.ITEMS).stacksTo(1)));
    public static final RegistryObject<Item> STORAGE_UPGRADE = ITEMS.register("storage_upgrade", () -> new Item(new Item.Properties().tab(SVACreativeTabs.ITEMS).stacksTo(1)));

    //Engines
    public static final RegistryObject<Item> ELECTRIC_VEHICLE_ENGINE = ITEMS.register("electric_vehicle_engine", () -> new DescriptionItem(new Item.Properties().tab(SVACreativeTabs.ITEMS), Component.translatable(MODID + ".press_key", Component.keybind("key.plane_inventory_open.desc"))));
    public static final RegistryObject<Item> COMBUSTION_VEHICLE_ENGINE = ITEMS.register("combustion_vehicle_engine", () -> new DescriptionItem(new Item.Properties().tab(SVACreativeTabs.ITEMS), Component.translatable(MODID + ".press_key", Component.keybind("key.plane_inventory_open.desc"))));

    //Items
    public static final RegistryObject<Item> WRENCH = ITEMS.register("wrench", () -> new Item(new Item.Properties().tab(SVACreativeTabs.ITEMS)));
    public static final RegistryObject<Item> STEVE_ARROW_MAG_ITEM = ITEMS.register("steve_arrow_mag", () -> new Item(new Item.Properties().tab(SVACreativeTabs.ITEMS)));
    public static final RegistryObject<Item> STEVE_OMB_ITEM = ITEMS.register("steve_omb", () -> new Item(new Item.Properties().tab(SVACreativeTabs.ITEMS)));
    public static final RegistryObject<Item> STEVE_ARROW_ITEM = ITEMS.register("steve_arrow", () -> new Item(new Item.Properties().tab(SVACreativeTabs.ITEMS)));
    public static final RegistryObject<Item> ALEX_ARROW_MAG_ITEM = ITEMS.register("alex_arrow_mag", () -> new Item(new Item.Properties().tab(SVACreativeTabs.ITEMS)));
    public static final RegistryObject<Item> ALEX_OMB_ITEM = ITEMS.register("alex_omb", () -> new Item(new Item.Properties().tab(SVACreativeTabs.ITEMS)));
    public static final RegistryObject<Item> ALEX_ARROW_ITEM = ITEMS.register("alex_arrow", () -> new Item(new Item.Properties().tab(SVACreativeTabs.ITEMS)));

    public static RegistryObject<PlaneItem> registerAirVehicle(String name, Supplier<? extends PlaneItem> supplier) {
        RegistryObject<PlaneItem> item = ITEMS.register(name, supplier);

        airVehichlesItems.put(name, item);

        return item;
    }

    public static void init() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
