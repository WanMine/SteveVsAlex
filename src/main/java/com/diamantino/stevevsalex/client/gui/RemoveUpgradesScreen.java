package com.diamantino.stevevsalex.client.gui;

import com.diamantino.stevevsalex.client.gui.buttons.UpgradeButtonsList;
import com.diamantino.stevevsalex.containers.RemoveUpgradesContainer;
import com.diamantino.stevevsalex.entities.base.PlaneEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.network.chat.Component;

public class RemoveUpgradesScreen extends AbstractContainerScreen<RemoveUpgradesContainer> {

    private UpgradeButtonsList buttonsList;

    public RemoveUpgradesScreen(RemoveUpgradesContainer screenContainer, Inventory inv, Component titleIn) {
        super(screenContainer, inv, titleIn);
    }

    @Override
    protected void init() {
        super.init();
        Entity entity = minecraft.level.getEntity(menu.planeID);
        if (entity instanceof PlaneEntity) {
            buttonsList = new UpgradeButtonsList(minecraft, 240, height, 32, height - 61, 20, (PlaneEntity) entity);
            addRenderableWidget(buttonsList);
        } else {
            onClose();
        }
    }

    @Override
    protected void renderBg(PoseStack matrixStack, float partialTicks, int x, int y) {}
    @Override
    protected void renderLabels(PoseStack matrixStack, int x, int y) {}

    @Override
    public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        buttonsList.render(matrixStack, mouseX, mouseY, partialTicks);
    }
}