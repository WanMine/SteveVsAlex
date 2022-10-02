package com.diamantino.stevevsalex.events;

import com.diamantino.stevevsalex.client.gui.PlaneInventoryScreen;
import com.diamantino.stevevsalex.entities.base.LargePlaneEntity;
import com.diamantino.stevevsalex.entities.base.PlaneEntity;
import com.diamantino.stevevsalex.network.SVANetworking;
import com.diamantino.stevevsalex.network.packets.ChangeThrottlePacket;
import com.diamantino.stevevsalex.network.packets.MoveHeliUpPacket;
import com.diamantino.stevevsalex.network.packets.OpenPlaneInventoryPacket;
import com.diamantino.stevevsalex.network.packets.PitchPacket;
import com.diamantino.stevevsalex.registries.SVAContainers;
import com.diamantino.stevevsalex.utils.ClientUtils;
import com.diamantino.stevevsalex.utils.MathUtils;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import net.minecraft.client.Camera;
import net.minecraft.client.CameraType;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.*;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.lwjgl.glfw.GLFW;

import static com.diamantino.stevevsalex.SteveVsAlex.MODID;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class ClientEventHandler {

    public static final ResourceLocation HUD_TEXTURE = new ResourceLocation(MODID, "textures/gui/plane_hud.png");

    public static KeyMapping moveHeliUpKey;
    public static KeyMapping openPlaneInventoryKey;
    public static KeyMapping throttleUp;
    public static KeyMapping throttleDown;
    public static KeyMapping pitchUp;
    public static KeyMapping pitchDown;

    static {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        eventBus.addListener(ClientEventHandler::registerKeyBindings);
        eventBus.addListener(ClientEventHandler::registerHUDOverlay);
    }

    public static void clientSetup() {
        MenuScreens.register(SVAContainers.PLANE_INVENTORY.get(), PlaneInventoryScreen::new);
    }

    public static void registerKeyBindings(RegisterKeyMappingsEvent event) {
        moveHeliUpKey = new KeyMapping("key.move_heli_up.desc", GLFW.GLFW_KEY_SPACE, "key.stevevsalex.category");
        openPlaneInventoryKey = new KeyMapping("key.plane_inventory_open.desc", GLFW.GLFW_KEY_X, "key.stevevsalex.category");
        throttleUp = new KeyMapping("key.plane_throttle_up.desc", GLFW.GLFW_KEY_UP, "key.stevevsalex.category");
        throttleDown = new KeyMapping("key.plane_throttle_down.desc", GLFW.GLFW_KEY_DOWN, "key.stevevsalex.category");
        pitchUp = new KeyMapping("key.plane_pitch_up.desc", GLFW.GLFW_KEY_W, "key.stevevsalex.category");
        pitchDown = new KeyMapping("key.plane_pitch_down.desc", GLFW.GLFW_KEY_S, "key.stevevsalex.category");
        event.register(moveHeliUpKey);
        event.register(openPlaneInventoryKey);
        event.register(throttleUp);
        event.register(throttleDown);
        event.register(pitchUp);
        event.register(pitchDown);
    }

    public static void registerHUDOverlay(RegisterGuiOverlaysEvent event) {
        event.registerAboveAll("plane_hud", (gui, matrixStack, partialTicks, screenWidth, screenHeight) -> {
            Minecraft mc = Minecraft.getInstance();

            if (mc.gui instanceof ForgeGui forgeGui) {
                int scaledWidth = mc.getWindow().getGuiScaledWidth();
                int scaledHeight = mc.getWindow().getGuiScaledHeight();

                if (mc.player.getVehicle() instanceof PlaneEntity planeEntity) {
                    RenderSystem.setShader(GameRenderer::getPositionTexShader);
                    RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                    RenderSystem.setShaderTexture(0, HUD_TEXTURE);

                    int left_align = scaledWidth / 2 + 91;

                    int health = (int) Math.ceil(planeEntity.getHealth());
                    float healthMax = planeEntity.getMaxHealth();
                    int hearts = (int) (healthMax);

                    if (hearts > 10) hearts = 10;

                    final int FULL = 0;
                    final int EMPTY = 16;
                    final int GOLD = 32;
                    int max_row_size = 5;

                    for (int heart = 0; hearts > 0; heart += max_row_size) {
                        int top = scaledHeight - forgeGui.rightHeight;

                        int rowCount = Math.min(hearts, max_row_size);
                        hearts -= rowCount;

                        for (int i = 0; i < rowCount; ++i) {
                            int x = left_align - i * 16 - 16;
                            int vOffset = 35;
                            if (i + heart + 10 < health)
                                ClientUtils.blit(matrixStack, 0, x, top, GOLD, vOffset, 16, 9);
                            else if (i + heart < health)
                                ClientUtils.blit(matrixStack, 0, x, top, FULL, vOffset, 16, 9);
                            else
                                ClientUtils.blit(matrixStack, 0, x, top, EMPTY, vOffset, 16, 9);
                        }
                        forgeGui.rightHeight += 10;
                    }

                    ClientUtils.blit(matrixStack, -90, scaledWidth - 24, scaledHeight - 42, 0, 84, 22, 40);
                    int throttle = planeEntity.getThrottle();
                    if (throttle > 0) {
                        int throttleScaled = throttle * 28 / 12;
                        ClientUtils.blit(matrixStack, -90, scaledWidth - 24 + 10, scaledHeight - 42 + 6 + 28 - throttleScaled, 22, 90 + 28 - throttleScaled, 2, throttleScaled);
                    }

                    ItemStack offhandStack = mc.player.getOffhandItem();
                    HumanoidArm primaryHand = mc.player.getMainArm();
                    //planeEntity.engineUpgrade.renderPowerHUD(matrixStack, (primaryHand == HumanoidArm.LEFT || offhandStack.isEmpty()) ? HumanoidArm.LEFT : HumanoidArm.RIGHT, scaledWidth, scaledHeight, partialTicks);
                }
            }
        });
    }

    private static boolean playerRotationNeedToPop = false;

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onRenderPre(RenderLivingEvent.Pre<LivingEntity, ?> event) {
        LivingEntity livingEntity = event.getEntity();
        Entity entity = livingEntity.getRootVehicle();
        if (entity instanceof PlaneEntity planeEntity) {
            PoseStack matrixStack = event.getPoseStack();
            matrixStack.pushPose();
            playerRotationNeedToPop = true;

            matrixStack.translate(0, 0.375, 0);
            Quaternion quaternion = MathUtils.lerpQ(event.getPartialTick(), planeEntity.getQ_Prev(), planeEntity.getQ_Client());
            quaternion.set(quaternion.i(), -quaternion.j(), -quaternion.k(), quaternion.r());
            matrixStack.mulPose(quaternion);
            float rotationYaw = MathUtils.lerpAngle(event.getPartialTick(), entity.yRotO, entity.getYRot());

            matrixStack.mulPose(Vector3f.YP.rotationDegrees(rotationYaw));
            matrixStack.translate(0, -0.375, 0);
            if (MathUtils.degreesDifferenceAbs(planeEntity.rotationRoll, 0) > 90) {
                livingEntity.yHeadRot = planeEntity.getYRot() * 2 - livingEntity.yHeadRot;
            }
            if (MathUtils.degreesDifferenceAbs(planeEntity.prevRotationRoll, 0) > 90) {
                livingEntity.yHeadRotO = planeEntity.yRotO * 2 - livingEntity.yHeadRotO;
            }
        }
    }

    @SuppressWarnings("rawtypes")
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onRenderPost(RenderLivingEvent.Post event) {
        if (playerRotationNeedToPop) {
            playerRotationNeedToPop = false;
            event.getPoseStack().popPose();
            Entity entity = event.getEntity().getRootVehicle();
            PlaneEntity planeEntity = (PlaneEntity) entity;

            if (MathUtils.degreesDifferenceAbs(planeEntity.rotationRoll, 0) > 90) {
                event.getEntity().yHeadRot = planeEntity.getYRot() * 2 - event.getEntity().yHeadRot;
            }
            if (MathUtils.degreesDifferenceAbs(planeEntity.prevRotationRoll, 0) > 90) {
                event.getEntity().yHeadRotO = planeEntity.yRotO * 2 - event.getEntity().yHeadRotO;
            }
        }
    }

    private static boolean oldMoveHeliUpState = false;
    private static boolean oldPitchUpState = false;
    private static boolean oldPitchDownState = false;

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onClientPlayerTick(TickEvent.PlayerTickEvent event) {
        final Player player = event.player;
        if ((event.phase == TickEvent.Phase.END) && (player instanceof LocalPlayer)) {
            if (player.getVehicle() instanceof PlaneEntity planeEntity) {
                Minecraft mc = Minecraft.getInstance();
                if (mc.options.cameraType != CameraType.FIRST_PERSON) {
                    planeEntity.applyYawToEntity(player);
                }

                if (mc.screen == null && mc.getOverlay() == null && openPlaneInventoryKey.consumeClick()) {
                    SVANetworking.INSTANCE.sendToServer(new OpenPlaneInventoryPacket());
                }

                if (throttleUp.consumeClick()) {
                    SVANetworking.INSTANCE.sendToServer(new ChangeThrottlePacket(ChangeThrottlePacket.Type.UP));
                } else if (throttleDown.consumeClick()) {
                    SVANetworking.INSTANCE.sendToServer(new ChangeThrottlePacket(ChangeThrottlePacket.Type.DOWN));
                }

                boolean isMoveHeliUp = moveHeliUpKey.isDown();
                boolean isPitchUp = pitchUp.isDown();
                boolean isPitchDown = pitchDown.isDown();
                if (isMoveHeliUp != oldMoveHeliUpState) {
                    SVANetworking.INSTANCE.sendToServer(new MoveHeliUpPacket(isMoveHeliUp));
                }

                if (isPitchUp != oldPitchUpState || isPitchDown != oldPitchDownState) {
                    SVANetworking.INSTANCE.sendToServer(new PitchPacket((byte) Boolean.compare(isPitchUp, isPitchDown)));
                }

                oldMoveHeliUpState = isMoveHeliUp;
                oldPitchUpState = isPitchUp;
                oldPitchDownState = isPitchDown;
            } else {
                oldMoveHeliUpState = false;
                oldPitchUpState = false;
                oldPitchDownState = false;
            }
        }
    }

    //TODO: make it so player rotation variables correspond to what he is actually looking at, so that guns etc. shoot in the right direction
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onCameraSetup(ViewportEvent.ComputeCameraAngles event) {
        Camera camera = event.getCamera();
        Entity player = camera.getEntity();
        if (player.getVehicle() instanceof PlaneEntity planeEntity) {
            if (camera.isDetached()) {
                camera.move(-camera.getMaxZoom(4.0D * (planeEntity.getCameraDistanceMultiplayer() - 1.0)), 0.0D, 0.0D);
            } else {
                float heightDiff = 0;
                if (planeEntity instanceof LargePlaneEntity) {
                    heightDiff = -0.1f;
                }

                double partialTicks = event.getPartialTick();

                Quaternion qPrev = planeEntity.getQ_Prev();
                Quaternion qNow = planeEntity.getQ_Client();

                Vector3f eyePrev = new Vector3f(0, Player.DEFAULT_EYE_HEIGHT + heightDiff, 0);
                Vector3f eyeNow = eyePrev.copy();
                eyePrev.transform(qPrev);
                eyeNow.transform(qNow);
                camera.setPosition(new Vec3(Mth.lerp(partialTicks, player.xo - eyePrev.x(), player.getX() - eyeNow.x()),
                        Mth.lerp(partialTicks, player.yo + eyePrev.y(), player.getY() + eyeNow.y()) + 0.375,
                        Mth.lerp(partialTicks, player.zo + eyePrev.z(), player.getZ() + eyeNow.z())));

                qPrev.mul(Vector3f.YP.rotationDegrees(player.yRotO));
                qPrev.mul(Vector3f.XP.rotationDegrees(event.getPitch()));
                MathUtils.EulerAngles eulerAnglesPrev = MathUtils.toEulerAngles(qPrev);

                qNow.mul(Vector3f.YP.rotationDegrees(player.getYRot()));
                qNow.mul(Vector3f.XP.rotationDegrees(event.getPitch()));
                MathUtils.EulerAngles eulerAnglesNow = MathUtils.toEulerAngles(qNow);

                event.setPitch(-(float) MathUtils.lerpAngle(partialTicks, eulerAnglesPrev.pitch, eulerAnglesNow.pitch));
                event.setYaw((float) MathUtils.lerpAngle(partialTicks, eulerAnglesPrev.yaw, eulerAnglesNow.yaw));
                event.setRoll(-(float) MathUtils.lerpAngle(partialTicks, eulerAnglesPrev.roll, eulerAnglesNow.roll));
            }
        }
    }
}