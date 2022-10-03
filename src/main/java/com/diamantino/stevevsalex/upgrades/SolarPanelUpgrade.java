package com.diamantino.stevevsalex.upgrades;

import com.diamantino.stevevsalex.entities.base.PlaneEntity;
import com.diamantino.stevevsalex.registries.SVAItems;
import com.diamantino.stevevsalex.registries.SVAUpgrades;
import com.diamantino.stevevsalex.upgrades.base.Upgrade;
import com.diamantino.stevevsalex.upgrades.engines.ElectricEngineUpgrade;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class SolarPanelUpgrade extends Upgrade {

    private final short MAX_PER_TICK;

    public SolarPanelUpgrade(PlaneEntity planeEntity) {
        super(SVAUpgrades.SOLAR_PANEL.get(), planeEntity);

        MAX_PER_TICK = 10;
    }

    @Override
    public void tick() {
        PlaneEntity entity = getPlaneEntity();
        Level world = entity.getLevel();
        if (canSeeSun(world, entity.getOnPos().above())) {
            float brightness = MAX_PER_TICK * getSunBrightness(entity.getLevel(), 1.0F);
            if (entity.engineUpgrade instanceof ElectricEngineUpgrade engine) {
                engine.energyStorage.receiveEnergy((int) brightness, false);
            }
        }
    }

    @Override
    public void writePacket(FriendlyByteBuf buffer) {

    }

    @Override
    public void readPacket(FriendlyByteBuf buffer) {

    }

    @Override
    public void onRemoved() {
        planeEntity.spawnAtLocation(SVAItems.SOLAR_PANEL.get().getDefaultInstance());
    }

    private static boolean canSeeSun(@Nullable Level level, BlockPos pos) {
        return level != null && level.dimensionType().hasSkyLight() && level.getSkyDarken() < 4 && level.canSeeSky(pos);
    }

    public static float getSunBrightness(Level world, float partialTicks) {
        float f = world.getTimeOfDay(partialTicks);
        float f1 = 1.0F - (Mth.cos(f * ((float) Math.PI * 2F)) * 2.0F + 0.2F);
        f1 = Mth.clamp(f1, 0.0F, 1.0F);
        f1 = 1.0F - f1;
        f1 = (float) (f1 * (1.0D - world.getRainLevel(partialTicks) * 5.0F / 16.0D));
        f1 = (float) (f1 * (1.0D - world.getThunderLevel(partialTicks) * 5.0F / 16.0D));
        return f1 * 0.8F;
    }

}