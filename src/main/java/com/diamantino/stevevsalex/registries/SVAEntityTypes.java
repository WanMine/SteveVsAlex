package com.diamantino.stevevsalex.registries;

import com.diamantino.stevevsalex.entities.SteveAneEntity;
import com.diamantino.stevevsalex.entities.SteveCopterEntity;
import com.diamantino.stevevsalex.entities.SteveOmbEntity;
import com.google.common.collect.ImmutableSet;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.diamantino.stevevsalex.SteveVsAlex.MODID;

public class SVAEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MODID);

    public static final RegistryObject<EntityType<SteveCopterEntity>> STEVE_COPTER = ENTITY_TYPES.register("steve_copter", () -> createEntityType(SteveCopterEntity::new, EntityDimensions.scalable(2.5F, 2.2F)));
    public static final RegistryObject<EntityType<SteveAneEntity>> STEVE_ANE = ENTITY_TYPES.register("steve_ane", () -> createEntityType(SteveAneEntity::new, EntityDimensions.scalable(2.5F, 2.2F)));
    public static final RegistryObject<EntityType<SteveOmbEntity>> STEVE_OMB = ENTITY_TYPES.register("steve_omb", () -> createEntityType(SteveOmbEntity::new, EntityDimensions.scalable(2.5F, 2.2F)));

    private static <T extends Entity> EntityType<T> createEntityType(EntityType.EntityFactory<T> factory, EntityDimensions size) {
        return new EntityType<>(factory, MobCategory.MISC, true, true, false, true, ImmutableSet.of(), size, 5, 3);
    }

    public static void init() {
        ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
