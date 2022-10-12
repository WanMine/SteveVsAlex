package com.diamantino.stevevsalex.registries;

import com.diamantino.stevevsalex.blocks.AmmoWorkbenchBlock;
import com.diamantino.stevevsalex.blocks.VehicleWorkbenchBlock;
import com.diamantino.stevevsalex.blocks.VehicleChargerBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.diamantino.stevevsalex.SteveVsAlex.MODID;

@SuppressWarnings("unused")
public class SVABlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);

    public static void init() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final RegistryObject<VehicleWorkbenchBlock> VEHICLE_WORKBENCH_BLOCK = BLOCKS.register("vehicle_workbench", () -> new VehicleWorkbenchBlock(BlockBehaviour.Properties.copy(Blocks.CRAFTING_TABLE)));
    public static final RegistryObject<AmmoWorkbenchBlock> AMMO_WORKBENCH_BLOCK = BLOCKS.register("ammo_workbench", () -> new AmmoWorkbenchBlock(BlockBehaviour.Properties.copy(Blocks.CRAFTING_TABLE)));
    public static final RegistryObject<VehicleChargerBlock> VEHICLE_CHARGER_BLOCK = BLOCKS.register("vehicle_charger", () -> new VehicleChargerBlock(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE)));
    public static final RegistryObject<Block> STIVIUM_ORE_BLOCK = BLOCKS.register("stivium_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_ORE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ALEXIUM_ORE_BLOCK = BLOCKS.register("alexium_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_ORE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> DEEPSLATE_STIVIUM_ORE_BLOCK = BLOCKS.register("deepslate_stivium_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> DEEPSLATE_ALEXIUM_ORE_BLOCK = BLOCKS.register("deepslate_alexium_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE).requiresCorrectToolForDrops()));
}
