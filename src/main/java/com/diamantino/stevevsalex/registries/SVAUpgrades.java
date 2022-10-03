package com.diamantino.stevevsalex.registries;

import com.diamantino.stevevsalex.upgrades.*;
import com.diamantino.stevevsalex.upgrades.base.UpgradeType;
import com.diamantino.stevevsalex.upgrades.engines.ElectricEngineUpgrade;
import com.diamantino.stevevsalex.upgrades.engines.FurnaceEngineUpgrade;
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

    public static final RegistryObject<UpgradeType> FLOATY_BEDDING = UPGRADE_TYPES.register("floaty_bedding", () -> new UpgradeType(FloatingUpgrade::new));
    public static final RegistryObject<UpgradeType> BOOSTER = UPGRADE_TYPES.register("booster", () -> new UpgradeType(BoosterUpgrade::new));
    public static final RegistryObject<UpgradeType> HEALING = UPGRADE_TYPES.register("healing", () -> new UpgradeType(HealingUpgrade::new));
    public static final RegistryObject<UpgradeType> ARMOR = UPGRADE_TYPES.register("armor", () -> new UpgradeType(ArmorUpgrade::new));
    public static final RegistryObject<UpgradeType> SOLAR_PANEL = UPGRADE_TYPES.register("solar_panel", () -> new UpgradeType(SolarPanelUpgrade::new));
    public static final RegistryObject<UpgradeType> FURNACE_ENGINE = UPGRADE_TYPES.register("furnace_engine", () -> new UpgradeType(FurnaceEngineUpgrade::new, true));
    public static final RegistryObject<UpgradeType> ELECTRIC_ENGINE = UPGRADE_TYPES.register("electric_engine", () -> new UpgradeType(ElectricEngineUpgrade::new, true));
    public static final RegistryObject<UpgradeType> CHEST = UPGRADE_TYPES.register("chest", () -> new UpgradeType(StorageUpgrade::new));
    public static final RegistryObject<UpgradeType> JUKEBOX = UPGRADE_TYPES.register("jukebox", () -> new UpgradeType(JukeboxUpgrade::new));
}
