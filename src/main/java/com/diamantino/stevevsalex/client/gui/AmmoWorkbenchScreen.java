package com.diamantino.stevevsalex.client.gui;

import com.diamantino.stevevsalex.containers.AmmoWorkbenchContainer;
import com.diamantino.stevevsalex.network.SVANetworking;
import com.diamantino.stevevsalex.network.packets.CycleItemsPacket;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

import static com.diamantino.stevevsalex.SteveVsAlex.MODID;

public class AmmoWorkbenchScreen extends AbstractContainerScreen<AmmoWorkbenchContainer> {
    public static final ResourceLocation GUI = new ResourceLocation(MODID, "textures/gui/ammo_workbench.png");
    private final String resultItem;
    private float rotation = 0f;

    public AmmoWorkbenchScreen(AmmoWorkbenchContainer screenContainer, Inventory inv, Component titleIn) {
        super(screenContainer, inv, titleIn);
        resultItem = screenContainer.recipeList.get(screenContainer.selectedRecipe.get()).getResultItem().toString();
    }

    @Override
    protected void init() {
        super.init();
        addRenderableWidget(new ImageButton(leftPos + 122, topPos + 47, 10, 15, 176, 0, 15, GUI,
                button -> SVANetworking.INSTANCE.sendToServer(new CycleItemsPacket(CycleItemsPacket.Type.CRAFTING_LEFT))));

        addRenderableWidget(new ImageButton(leftPos + 152, topPos + 47, 10, 15, 186, 0, 15, GUI,
                button -> SVANetworking.INSTANCE.sendToServer(new CycleItemsPacket(CycleItemsPacket.Type.CRAFTING_RIGHT))));
    }

    @Override
    public void render(@NotNull PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        renderTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void renderBg(@NotNull PoseStack matrixStack, float partialTicks, int x, int y) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, GUI);
        int i = this.leftPos;
        int j = (this.height - this.imageHeight) / 2;
        this.blit(matrixStack, i, j, 0, 0, this.imageWidth, this.imageHeight);
    }
}