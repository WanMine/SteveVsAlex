package com.diamantino.stevevsalex.registries;

import com.diamantino.stevevsalex.items.BombItem;
import com.diamantino.stevevsalex.items.PlaneItem;
import com.diamantino.stevevsalex.items.ProjectileItem;
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
    public static Dictionary<String, RegistryObject<BombItem>> bombItems = new Hashtable<>();
    public static Dictionary<String, RegistryObject<ProjectileItem>> projectileItems = new Hashtable<>();

    public static final RegistryObject<PlaneItem> STEVE_COPTER_ITEM = registerAirVehicle("steve_copter", () -> new PlaneItem(new Item.Properties().tab(SVACreativeTabs.ITEMS), SVAEntityTypes.STEVE_COPTER));
    public static final RegistryObject<PlaneItem> STEVE_ANE_ITEM = registerAirVehicle("steve_ane", () -> new PlaneItem(new Item.Properties().tab(SVACreativeTabs.ITEMS), SVAEntityTypes.STEVE_ANE));
    public static final RegistryObject<BombItem> STEVE_OMB_ITEM = registerBomb("steve_omb", () -> new BombItem(new Item.Properties().tab(SVACreativeTabs.ITEMS), SVAEntityTypes.STEVE_OMB));
    public static final RegistryObject<ProjectileItem> STEVE_ARROW_ITEM = registerProjectile("steve_arrow", () -> new ProjectileItem(new Item.Properties().tab(SVACreativeTabs.ITEMS), SVAEntityTypes.STEVE_ARROW));

    public static RegistryObject<PlaneItem> registerAirVehicle(String name, Supplier<? extends PlaneItem> supplier) {
        RegistryObject<PlaneItem> item = ITEMS.register(name, supplier);

        airVehichlesItems.put(name, item);

        return item;
    }

    public static RegistryObject<BombItem> registerBomb(String name, Supplier<? extends BombItem> supplier) {
        RegistryObject<BombItem> item = ITEMS.register(name, supplier);

        bombItems.put(name, item);

        return item;
    }

    public static RegistryObject<ProjectileItem> registerProjectile(String name, Supplier<? extends ProjectileItem> supplier) {
        RegistryObject<ProjectileItem> item = ITEMS.register(name, supplier);

        projectileItems.put(name, item);

        return item;
    }

    public static void init() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
