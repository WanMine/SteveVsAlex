package com.diamantino.stevevsalex.registries;

import com.diamantino.stevevsalex.upgrades.*;
import com.diamantino.stevevsalex.upgrades.base.UpgradeType;
import com.diamantino.stevevsalex.upgrades.engines.ElectricVehicleEngineUpgrade;
import com.diamantino.stevevsalex.upgrades.engines.FurnaceVehicleEngineUpgrade;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.diamantino.stevevsalex.SteveVsAlex.MODID;

public class SVAUpgrades {
    private static final DeferredRegister<UpgradeType> UPGRADE_TYPES = DeferredRegister.create(SVARegistries.UPGRADE_TYPES_NAME, MODID);
    private static final Map<Item, UpgradeType> ITEM_UPGRADE_MAP = new HashMap<>();

    public static void init() {
        UPGRADE_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static void registerUpgradeItem(Item item, UpgradeType upgradeType) {
        ITEM_UPGRADE_MAP.put(item, upgradeType);
    }

    public static Optional<UpgradeType> getUpgradeFromItem(Item item) {
        if (ITEM_UPGRADE_MAP.containsKey(item)) {
            return Optional.of(ITEM_UPGRADE_MAP.get(item));
        }
        return Optional.empty();
    }

    public static final RegistryObject<UpgradeType> FLOATING_PAD_UPGRADE = UPGRADE_TYPES.register("floating_pad_upgrade", () -> new UpgradeType(FloatingPadUpgrade::new));
    public static final RegistryObject<UpgradeType> ROCKET_BOOSTER_UPGRADE = UPGRADE_TYPES.register("rocket_booster_upgrade", () -> new UpgradeType(RocketBoosterUpgrade::new));
    public static final RegistryObject<UpgradeType> HEAL_VEHICLE = UPGRADE_TYPES.register("heal_vehicle", () -> new UpgradeType(HealVehicleUpgrade::new));
    public static final RegistryObject<UpgradeType> REINFORCED_ARMOR_UPGRADE = UPGRADE_TYPES.register("reinforced_armor_upgrade", () -> new UpgradeType(ReinforcedArmorUpgrade::new));
    public static final RegistryObject<UpgradeType> SOLAR_GENERATOR_UPGRADE = UPGRADE_TYPES.register("solar_generator_upgrade", () -> new UpgradeType(SolarPanelUpgrade::new));
    public static final RegistryObject<UpgradeType> COMBUSTION_VEHICLE_ENGINE = UPGRADE_TYPES.register("combustion_vehicle_engine", () -> new UpgradeType(FurnaceVehicleEngineUpgrade::new, true));
    public static final RegistryObject<UpgradeType> ELECTRIC_VEHICLE_ENGINE = UPGRADE_TYPES.register("electric_vehicle_engine", () -> new UpgradeType(ElectricVehicleEngineUpgrade::new, true));
    public static final RegistryObject<UpgradeType> STORAGE_UPGRADE = UPGRADE_TYPES.register("storage_upgrade", () -> new UpgradeType(StorageUpgrade::new));
    public static final RegistryObject<UpgradeType> JUKEBOX_UPGRADE = UPGRADE_TYPES.register("jukebox_upgrade", () -> new UpgradeType(JukeboxUpgrade::new));
}
