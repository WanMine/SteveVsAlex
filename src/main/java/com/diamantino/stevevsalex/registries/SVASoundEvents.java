package com.diamantino.stevevsalex.registries;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.diamantino.stevevsalex.SteveVsAlex.MODID;

public class SVASoundEvents {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MODID);

    public static final RegistryObject<SoundEvent> PLANE_LOOP_SOUND_EVENT = SOUND_EVENTS.register("plane_loop", () -> new SoundEvent(new ResourceLocation(MODID, "plane_loop")));

    public static void init() {
        SOUND_EVENTS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
