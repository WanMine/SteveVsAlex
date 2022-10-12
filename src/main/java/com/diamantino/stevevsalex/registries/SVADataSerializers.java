package com.diamantino.stevevsalex.registries;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.syncher.EntityDataSerializer;
import com.mojang.math.Quaternion;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import static com.diamantino.stevevsalex.SteveVsAlex.MODID;

@SuppressWarnings("unused")
public class SVADataSerializers {
    public static final DeferredRegister<EntityDataSerializer<?>> DATA_SERIALIZERS = DeferredRegister.create(ForgeRegistries.Keys.ENTITY_DATA_SERIALIZERS, MODID);

    public static void init() {
        DATA_SERIALIZERS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final RegistryObject<EntityDataSerializer<Quaternion>> QUATERNION_SERIALIZER_ENTRY = DATA_SERIALIZERS
            .register("quaternion", () -> new EntityDataSerializer<>() {

                @Override
                public void write(@NotNull FriendlyByteBuf buf, @NotNull Quaternion q) {
                    buf.writeFloat(q.i());
                    buf.writeFloat(q.j());
                    buf.writeFloat(q.k());
                    buf.writeFloat(q.r());
                }

                @Override
                public @NotNull Quaternion read(@NotNull FriendlyByteBuf buf) {
                    try {
                        return new Quaternion(buf.readFloat(), buf.readFloat(), buf.readFloat(), buf.readFloat());
                    } catch (IndexOutOfBoundsException e) {
                        // This function would throw anyway, might as well wrap the error with more relevant info
                        throw new RuntimeException("packet buffer does not contain enough data to construct plane's Quaternion", e);
                    }
                }

                @Override
                public @NotNull Quaternion copy(@NotNull Quaternion q) {
                    return new Quaternion(q);
                }
            });
}
