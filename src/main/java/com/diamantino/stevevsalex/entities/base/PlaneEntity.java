package com.diamantino.stevevsalex.entities.base;

import com.diamantino.stevevsalex.client.sounds.PlaneSound;
import com.diamantino.stevevsalex.container.RemoveUpgradesContainer;
import com.diamantino.stevevsalex.entities.steve.SteveArrowEntity;
import com.diamantino.stevevsalex.entities.steve.SteveOmbEntity;
import com.diamantino.stevevsalex.network.packets.UpdateUpgradePacket;
import com.diamantino.stevevsalex.network.packets.UpgradeRemovedPacket;
import com.diamantino.stevevsalex.registries.*;
import com.diamantino.stevevsalex.network.SVANetworking;
import com.diamantino.stevevsalex.network.packets.ChangeThrottlePacket;
import com.diamantino.stevevsalex.network.packets.RotationPacket;
import com.diamantino.stevevsalex.upgrades.ReinforcedArmorUpgrade;
import com.diamantino.stevevsalex.upgrades.RocketBoosterUpgrade;
import com.diamantino.stevevsalex.upgrades.StorageUpgrade;
import com.diamantino.stevevsalex.upgrades.VehicleEngineUpgrade;
import com.diamantino.stevevsalex.upgrades.base.Upgrade;
import com.diamantino.stevevsalex.upgrades.base.UpgradeType;
import com.diamantino.stevevsalex.utils.MathUtils;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.entity.IEntityAdditionalSpawnData;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PacketDistributor;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

import static com.diamantino.stevevsalex.SteveVsAlex.MODID;
import static com.diamantino.stevevsalex.utils.MathUtils.*;
import static net.minecraft.util.Mth.degreesDifferenceAbs;
import static net.minecraft.util.Mth.wrapDegrees;

@SuppressWarnings({"ConstantConditions", "deprecation"})
public abstract class PlaneEntity extends Entity implements IEntityAdditionalSpawnData {
    public static final EntityDataAccessor<Integer> MAX_HEALTH = SynchedEntityData.defineId(PlaneEntity.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> HEALTH = SynchedEntityData.defineId(PlaneEntity.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Float> MAX_SPEED = SynchedEntityData.defineId(PlaneEntity.class, EntityDataSerializers.FLOAT);
    public static final EntityDataAccessor<Integer> TIME_SINCE_HIT = SynchedEntityData.defineId(PlaneEntity.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Float> DAMAGE_TAKEN = SynchedEntityData.defineId(PlaneEntity.class, EntityDataSerializers.FLOAT);
    public static final EntityDataAccessor<Quaternion> Q = SynchedEntityData.defineId(PlaneEntity.class, SVADataSerializers.QUATERNION_SERIALIZER_ENTRY.get());
    public static final EntityDataAccessor<Integer> THROTTLE = SynchedEntityData.defineId(PlaneEntity.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Byte> PITCH_UP = SynchedEntityData.defineId(PlaneEntity.class, EntityDataSerializers.BYTE);
    public static final EntityDataAccessor<Boolean> IS_SHOOTING = SynchedEntityData.defineId(PlaneEntity.class, EntityDataSerializers.BOOLEAN);
    public static final int MAX_THROTTLE = 6;
    public final String vehichleName;
    public Quaternion Q_Client = new Quaternion(Quaternion.ONE);
    public Quaternion Q_Prev = new Quaternion(Quaternion.ONE);

    public Teams team;

    private final WeaponType weaponType;

    private int onGroundTicks;
    public final HashMap<ResourceLocation, Upgrade> upgrades = new HashMap<>();
    public VehicleEngineUpgrade vehicleEngineUpgrade = null;

    public float rotationRoll;
    public float prevRotationRoll;
    private float deltaRotation;
    private float deltaRotationLeft;
    private int deltaRotationTicks;

    private int damageTimeout;
    public int notMovingTime;
    public int projectilesToShoot;
    public int goldenHeartsTimeout = 0;

    private final int networkUpdateInterval;
    protected boolean hasConsumedAmmo;

    public float propellerRotationOld;
    public float propellerRotationNew;

    public float minigunRotationOld;
    public float minigunRotationNew;

    public PlaneEntity(EntityType<? extends PlaneEntity> entityTypeIn, Level worldIn, String name, WeaponType weaponType, Teams team) {
        super(entityTypeIn, worldIn);

        this.team = team;
        maxUpStep = 0.9999f;
        networkUpdateInterval = entityTypeIn.updateInterval();
        setMaxSpeed(3f);
        vehichleName = name;
        this.weaponType = weaponType;
    }

    @Override
    protected void defineSynchedData() {
        entityData.define(MAX_HEALTH, 10);
        entityData.define(HEALTH, 10);
        entityData.define(Q, Quaternion.ONE);
        entityData.define(MAX_SPEED, 0.25f);
        entityData.define(TIME_SINCE_HIT, 0);
        entityData.define(DAMAGE_TAKEN, 0f);
        entityData.define(THROTTLE, 0);
        entityData.define(PITCH_UP, (byte) 0);
        entityData.define(IS_SHOOTING, false);
    }

    public float getMaxSpeed() {
        return entityData.get(MAX_SPEED);
    }

    public void setMaxSpeed(float maxSpeed) {
        entityData.set(MAX_SPEED, maxSpeed);
    }

    public boolean getIsShooting() {
        return entityData.get(IS_SHOOTING);
    }

    public void setIsShooting(boolean isShooting) {
        entityData.set(IS_SHOOTING, isShooting);
    }

    public Quaternion getQ() {
        return new Quaternion(entityData.get(Q));
    }

    public void setQ(Quaternion q) {
        entityData.set(Q, q);
    }

    public Quaternion getQ_Client() {
        return new Quaternion(Q_Client);
    }

    public void setQ_Client(Quaternion q) {
        Q_Client = q;
    }

    public Quaternion getQ_Prev() {
        return Q_Prev.copy();
    }

    public void setQ_prev(Quaternion q) {
        Q_Prev = q;
    }

    public void setHealth(int health) {
        entityData.set(HEALTH, Math.max(health, 0));
    }

    public int getHealth() {
        return entityData.get(HEALTH);
    }

    public int getMaxHealth() {
        return entityData.get(MAX_HEALTH);
    }

    @Override
    public ItemStack getPickedResult(HitResult target) {
        return getItemStack();
    }

    public static final TagKey<DimensionType> BLACKLISTED_DIMENSIONS_TAG = TagKey.create(Registry.DIMENSION_TYPE_REGISTRY, new ResourceLocation(MODID, "blacklisted_dimensions"));

    public boolean isPowered() {
        return isAlive() && !level.dimensionTypeRegistration().is(BLACKLISTED_DIMENSIONS_TAG) && (isCreative() || (vehicleEngineUpgrade != null && vehicleEngineUpgrade.isPowered()));
    }

    @Override
    protected boolean canAddPassenger(@NotNull Entity passenger) {
        List<Entity> passengers = getPassengers();

        return passengers.size() < 3;
    }

    @Override
    public @NotNull InteractionResult interact(Player player, @NotNull InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        if (player.isShiftKeyDown() && itemStack.isEmpty()) {
            boolean hasPlayer = false;
            for (Entity passenger : getPassengers()) {
                if ((passenger instanceof Player)) {
                    hasPlayer = true;
                    break;
                }
            }
            if ((!hasPlayer)) {
                ejectPassengers();
            }
            return InteractionResult.SUCCESS;
        }

        if (itemStack.getItem() == SVAItems.WRENCH.get()) {
            if (!level.isClientSide) {
                NetworkHooks.openScreen((ServerPlayer) player, new SimpleMenuProvider((id, inv, p) -> new RemoveUpgradesContainer(id, getId()), Component.empty()), buf -> buf.writeVarInt(getId()));
                return InteractionResult.CONSUME;
            }
            return InteractionResult.SUCCESS;
        }

        if (tryToAddUpgrade(player, itemStack)) {
            return InteractionResult.SUCCESS;
        }

        if (!level.isClientSide) {
            return player.startRiding(this) ? InteractionResult.CONSUME : InteractionResult.FAIL;
        } else {
            return player.getRootVehicle() == getRootVehicle() ? InteractionResult.FAIL : InteractionResult.SUCCESS;
        }
    }

    protected StorageUpgrade getStorageUpgrade() {
        Upgrade upgrade = upgrades.get(SVAUpgrades.STORAGE_UPGRADE.getId());

        if (upgrade instanceof StorageUpgrade storageUpgrade) {
            return storageUpgrade;
        }

        return null;
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        Entity entity = source.getDirectEntity();
        if (entity == getControllingPassenger() && entity instanceof Player && !getIsShooting()) {
            if (getStorageUpgrade() != null) {
                switch (weaponType) {
                    case GUN -> {}
                    case MINIGUN -> {
                        projectilesToShoot = 16;

                        setIsShooting(true);
                    }
                    case BOMB -> {
                        setIsShooting(true);

                        if (consumeAmmo(getStorageUpgrade(), (Player) getControllingPassenger(), Teams.getBombItemForTeam(team))) {

                            BombEntity bombEntity = Teams.createBombForTeam(team, level, 100);

                            bombEntity.setPos(this.position());

                            level.addFreshEntity(bombEntity);
                        }

                        setIsShooting(false);
                    }
                }
            }

            return false;
        }

        if (getOnGround() && entity instanceof Player) {
            amount *= 3;
        } else {
            Upgrade upgrade = upgrades.get(SVAUpgrades.REINFORCED_ARMOR_UPGRADE.getId());
            if (upgrade instanceof ReinforcedArmorUpgrade armorUpgrade) {
                amount = armorUpgrade.getReducedDamage(amount);
            }
        }

        setTimeSinceHit(20);
        setDamageTaken(getDamageTaken() + 10 * amount);

        if (isInvulnerableTo(source) || damageTimeout > 0) {
            return false;
        }
        if (level.isClientSide || isRemoved()) {
            return false;
        }
        int health = getHealth();
        if (health < 0) {
            return false;
        }

        setHealth((int) (health - amount));
        damageTimeout = 10;
        boolean isPlayer = source.getDirectEntity() instanceof Player;
        boolean creativePlayer = isPlayer && ((Player) source.getEntity()).getAbilities().instabuild;
        if (creativePlayer) {
            kill();
        } else if (source == SVADamageSources.DAMAGE_SOURCE_PLANE_CRASH) {
            explode();
            kill();
            if (level.getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
                dropItem();
            }
        } else if (getOnGround() && getHealth() <= 0) {
            kill();
            if (level.getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
                dropItem();
            }
        }
        return true;
    }

    protected boolean consumeAmmo(StorageUpgrade storageUpgrade, Player player, Item itemToConsume) {
        for (int i = 0; i < storageUpgrade.itemStackHandler.getSlots(); i++) {
            if (storageUpgrade.itemStackHandler.getStackInSlot(i).getItem() == itemToConsume) {
                hasConsumedAmmo = true;

                if (!player.isCreative()) {
                    storageUpgrade.itemStackHandler.extractItem(i, 1, false);
                }

                return true;
            }
        }

        return false;
    }

    private void explode() {
        ((ServerLevel) level).sendParticles(ParticleTypes.SMOKE,
                getX(),
                getY(),
                getZ(),
                5, 1, 1, 1, 2);
        ((ServerLevel) level).sendParticles(ParticleTypes.POOF,
                getX(),
                getY(),
                getZ(),
                10, 1, 1, 1, 1);
        level.explode(this, getX(), getY(), getZ(), 4.0F, Explosion.BlockInteraction.BREAK);
    }

    protected void dropItem() {
        ItemStack itemStack = getItemStack();
        spawnAtLocation(itemStack).setInvulnerable(true);
    }

    public boolean isPickable() {
        return true;
    }

    @Override
    public void tick() {
        super.tick();

        if (Double.isNaN(getDeltaMovement().length())) {
            setDeltaMovement(Vec3.ZERO);
        }
        yRotO = getYRot();
        xRotO = getXRot();
        prevRotationRoll = rotationRoll;
        if (level.isClientSide) {
            propellerRotationOld = propellerRotationNew;
            if (isPowered()) {
                int throttle = getThrottle();
                propellerRotationNew += throttle * 0.1;
            }
        }

        if (level.isClientSide && getHealth() <= 0) {
            level.addAlwaysVisibleParticle(ParticleTypes.LARGE_SMOKE, true, getX(), getY(), getZ(), 0.0, 0.005, 0.0);
        }

        if (level.isClientSide && getTimeSinceHit() > 0) {
            setTimeSinceHit(getTimeSinceHit() - 1);
        }

        if (level.isClientSide && !isControlledByLocalInstance()) {
            tickLerp();
            setDeltaMovement(Vec3.ZERO);
            tickDeltaRotation(getQ_Client());
            return;
        }
        markHurt();

        TempMotionVars tempMotionVars = getMotionVars();
        if (isNoGravity()) {
            tempMotionVars.gravity = 0;
            tempMotionVars.maxLift = 0;
            tempMotionVars.push = 0.00f;
            tempMotionVars.passiveEnginePush = 0;
        }
        Entity controllingPassenger = getControllingPassenger();
        if (controllingPassenger instanceof Player playerEntity) {
            tempMotionVars.moveForward = getMoveForward(playerEntity);
            tempMotionVars.moveStrafing = playerEntity.xxa;
        } else {
            tempMotionVars.moveForward = 0;
            tempMotionVars.moveStrafing = 0;
            setSprinting(false);
        }
        tempMotionVars.turnThreshold = SVAConfigs.TURN_THRESHOLD.get() / 100d;

        if (Math.abs(tempMotionVars.moveStrafing) < tempMotionVars.turnThreshold) {
            tempMotionVars.moveStrafing = 0;
        }

        Quaternion q;
        if (level.isClientSide) {
            q = getQ_Client();
        } else {
            q = getQ();
        }

        EulerAngles anglesOld = toEulerAngles(q).copy();

        Vec3 oldMotion = getDeltaMovement();

        if (level.isClientSide && isPowered() && getThrottle() > 0) {
            PlaneSound.tryToPlay(this);
        }

        tempMotionVars.push = 0.00625f * getThrottle();

        //motion and rotation interpolation + lift.
        if (getDeltaMovement().length() > 0.05) {
            q = tickRotateMotion(tempMotionVars, q, getDeltaMovement());
        }
        boolean doPitch = true;
        //pitch + movement speed
        if (getOnGround() || isOnWater()) {
            doPitch = tickOnGround(tempMotionVars);
        } else {
            onGroundTicks--;
        }
        if (doPitch) {
            tickPitch(tempMotionVars);
        }

        tickMotion(tempMotionVars);

        //roll + yaw
        tickRoll(tempMotionVars);

        tickUpgrades();

        //made so plane fully stops when moves slow, removing the slipperiness effect
        if (onGroundTicks > -50 && oldMotion.length() < 0.002 && getDeltaMovement().length() < 0.002) {
            setDeltaMovement(Vec3.ZERO);
        }
        reapplyPosition();

        if (!onGround || getHorizontalDistanceSqr(getDeltaMovement()) > (double) 1.0E-5F || (tickCount + getId()) % 4 == 0) {
            double speedBefore = Math.sqrt(getHorizontalDistanceSqr(getDeltaMovement()));
            boolean onGroundOld = onGround;
            Vec3 motion = getDeltaMovement();
            if (motion.lengthSqr() > 0.25 || getPitchUp() != 0) {
                onGround = true;
            }
            move(MoverType.SELF, motion);
            onGround = ((motion.y()) == 0.0) ? onGroundOld : onGround;
            if (horizontalCollision && !level.isClientSide && onGroundTicks <= 0) {
                if (getHealth() <= 0) {
                    crash(16);
                } else {
                    double speedAfter = Math.sqrt(getHorizontalDistanceSqr(getDeltaMovement()));
                    double speedDiff = speedBefore - speedAfter;
                    float f2 = (float) (speedDiff * 10.0D - 5.0D);
                    if (f2 > 5.0F) {
                        crash(f2);
                    }
                }
            }
        }

        if (getHealth() <= 0 && !isRemoved()) {
            if (onGround)
                crash(16);
            else
                setThrottle(0);
        }

        //back to q
        q.mul(new Quaternion(Vector3f.ZP, ((float) (rotationRoll - anglesOld.roll)), true));
        q.mul(new Quaternion(Vector3f.XN, ((float) (getXRot() - anglesOld.pitch)), true));
        q.mul(new Quaternion(Vector3f.YP, ((float) (getYRot() - anglesOld.yaw)), true));

        q = normalizeQuaternion(q);

        setQ_prev(getQ_Client());
        setQ(q);
        tickDeltaRotation(q);

        if (level.isClientSide && isControlledByLocalInstance()) {
            setQ_Client(q);

            SVANetworking.INSTANCE.sendToServer(new RotationPacket(getQ()));
        } else {
            ServerPlayer player = (ServerPlayer) getPlayer();
            if (player != null) {
                player.connection.aboveGroundVehicleTickCount = 0;
            }
        }
        if (damageTimeout > 0) {
            --damageTimeout;
        }
        if (getDamageTaken() > 0.0F) {
            setDamageTaken(getDamageTaken() - 1.0F);
        }
        if (!level.isClientSide && getHealth() > getMaxHealth() & goldenHeartsTimeout > (getOnGround() ? 300 : 100)) {
            setHealth(getHealth() - 1);
            goldenHeartsTimeout = 0;
        }
        if (goldenHeartsTimeout < 1000 && isPowered()) {
            goldenHeartsTimeout++;
        }

        tickLerp();
    }

    protected float getMoveForward(Player player) {
        return player.zza;
    }

    public void tickUpgrades() {
        List<ResourceLocation> upgradesToRemove = new ArrayList<>();
        List<ResourceLocation> upgradesToUpdate = new ArrayList<>();
        upgrades.forEach((rl, upgrade) -> {
            upgrade.tick();
            if (upgrade.removed) {
                upgradesToRemove.add(rl);
            } else if (upgrade.updateClient && !level.isClientSide) {
                upgradesToUpdate.add(rl);
                upgrade.updateClient = false;
            }
        });

        for (ResourceLocation name : upgradesToRemove) {
            upgrades.remove(name);
        }
        if (tickCount % networkUpdateInterval == 0) {
            for (ResourceLocation name : upgradesToUpdate) {
                SVANetworking.INSTANCE.send(PacketDistributor.TRACKING_ENTITY.with(() -> this), new UpdateUpgradePacket(name, getId(), (ServerLevel) level));
            }
        }
    }

    public int getFuelCost() {
        return SVAConfigs.PLANE_FUEL_COST.get();
    }

    protected TempMotionVars getMotionVars() {
        TEMP_MOTION_VARS.reset();
        TEMP_MOTION_VARS.maxPushSpeed = getMaxSpeed() * 10;
        return TEMP_MOTION_VARS;
    }

    protected void tickDeltaRotation(Quaternion q) {
        EulerAngles angles = toEulerAngles(q);
        setXRot((float) angles.pitch);
        setYRot((float) angles.yaw);
        rotationRoll = (float) angles.roll;

        float d = (float) wrapSubtractDegrees(yRotO, getYRot());
        if (rotationRoll >= 90 && prevRotationRoll <= 90) {
            d = 0;
        }
        int diff = 3;

        deltaRotationTicks = Math.min(10, Math.max((int) Math.abs(deltaRotationLeft) * 5, deltaRotationTicks));
        deltaRotationLeft *= 0.7;
        deltaRotationLeft += d;
        deltaRotationLeft = wrapDegrees(deltaRotationLeft);
        deltaRotation = Math.min(Math.abs(deltaRotationLeft), diff) * Math.signum(deltaRotationLeft);
        deltaRotationLeft -= deltaRotation;
        if (!(deltaRotation > 0)) {
            deltaRotationTicks--;
        }
    }

    protected float pitchSpeed = 0;

    protected void tickPitch(TempMotionVars tempMotionVars) {
        float pitch;
        if (getHealth() <= 0) {
            pitch = 10.0f;
        } else {
            if (getPitchUp() > 0) {
                pitchSpeed += 0.5f;
            } else if (getPitchUp() < 0) {
                pitchSpeed -= 0.5f;
            } else {
                if (pitchSpeed < 0) {
                    pitchSpeed += 0.5f;
                } else if (pitchSpeed > 0) {
                    pitchSpeed -= 0.5f;
                }
            }
            pitchSpeed = Mth.clamp(pitchSpeed, -5.0f, 5.0f);
            pitch = pitchSpeed;
        }
        setXRot(getXRot() + pitch);
    }

    protected float rollSpeed = 0;

    protected void tickRoll(TempMotionVars tempMotionVars) {
        if (getHealth() <= 0) {
            rotationRoll += getId() % 2 == 0 ? 10.0f : -10.0f;
            return;
        }

        double turn = 0;

        if (getOnGround() || isOnWater()) {
            turn = tempMotionVars.moveStrafing > 0 ? 3 : tempMotionVars.moveStrafing == 0 ? 0 : -3;
            rotationRoll = lerpAngle(0.1f, rotationRoll, 0);

        } else {
            if (tempMotionVars.moveStrafing > 0.0f) {
                rollSpeed += 0.5f;
            } else if (tempMotionVars.moveStrafing < 0.0f) {
                rollSpeed -= 0.5f;
            } else {
                if (rollSpeed < 0) {
                    rollSpeed += 0.5f;
                } else if (rollSpeed > 0) {
                    rollSpeed -= 0.5f;
                }
            }

            rollSpeed = Mth.clamp(rollSpeed, -5.0f, 5.0f);
            rotationRoll += rollSpeed;
        }

        setYRot((float) (getYRot() - turn));
    }

    protected void tickMotion(TempMotionVars tempMotionVars) {
        Vec3 motion;
        if (!isPowered()) {
            tempMotionVars.push = 0;
        }
        motion = getDeltaMovement();
        double speed = motion.length();
        double brakesMul = getThrottle() == 0 ? 5.0 : 1.0;
        speed -= (speed * speed * tempMotionVars.dragQuad + speed * tempMotionVars.dragMul + tempMotionVars.drag) * brakesMul;
        speed = Math.max(speed, 0);
        if (speed > tempMotionVars.maxSpeed) {
            speed = Mth.lerp(0.2, speed, tempMotionVars.maxSpeed);
        }

        if (speed == 0) {
            motion = Vec3.ZERO;
        }
        if (motion.length() > 0) {
            motion = motion.scale(speed / motion.length());
        }

        Vec3 pushVec = new Vec3(getTickPush(tempMotionVars));
        if (pushVec.length() != 0 && motion.length() > 0.1) {
            double dot = normalizedDotProduct(pushVec, motion);
            pushVec = pushVec.scale(Mth.clamp(1 - dot * speed / (tempMotionVars.maxPushSpeed * (tempMotionVars.push + 0.05)), 0, 2));
        }

        motion = motion.add(pushVec);

        motion = motion.add(0, tempMotionVars.gravity, 0);

        setDeltaMovement(motion);
    }

    protected Vector3f getTickPush(TempMotionVars tempMotionVars) {
        return transformPos(new Vector3f(0, 0, tempMotionVars.push));
    }

    protected boolean tickOnGround(TempMotionVars tempMotionVars) {
        if (getDeltaMovement().lengthSqr() < 0.01 && getOnGround()) {
            notMovingTime += 1;
        } else {
            notMovingTime = 0;
        }
        if (notMovingTime > 200 && getHealth() < getMaxHealth() && getPlayer() != null) {
            setHealth(getHealth() + 1);
            notMovingTime = 100;
        }

        boolean speedingUp = true;
        if (onGroundTicks < 0) {
            onGroundTicks = 5;
        } else {
            onGroundTicks--;
        }
        float pitch = getGroundPitch();
        if ((isPowered() && getPitchUp() > 0) || isOnWater()) {
            pitch = 0;
        } else if (getDeltaMovement().length() > tempMotionVars.takeOffSpeed) {
            pitch /= 2;
        }
        setXRot(lerpAngle(0.1f, getXRot(), pitch));

        if (degreesDifferenceAbs(getXRot(), 0) > 1 && getDeltaMovement().length() < 0.1) {
            tempMotionVars.push /= 5; //runs while the plane is taking off
        }
        if (getDeltaMovement().length() < tempMotionVars.takeOffSpeed) {
            //                rotationPitch = lerpAngle(0.2f, rotationPitch, pitch);
            speedingUp = false;
            //                push = 0;
        }
        if (getPitchUp() < 0) {
            tempMotionVars.push = -tempMotionVars.groundPush;
        } else if (getPitchUp() > 0 && tempMotionVars.push < tempMotionVars.groundPush) {
            tempMotionVars.push = tempMotionVars.groundPush;
        }
        if (!isPowered()) {
            tempMotionVars.push = 0;
        }
        float f;
        BlockPos pos = new BlockPos(getX(), getY() - 1.0D, getZ());
        f = level.getBlockState(pos).getFriction(level, pos, this);
        tempMotionVars.dragMul *= 20 * (3 - f);
        return speedingUp;
    }

    protected float getGroundPitch() {
        return 5;
    }

    protected Quaternion tickRotateMotion(TempMotionVars tempMotionVars, Quaternion q, Vec3 motion) {
        float yaw = MathUtils.getYaw(motion);
        float pitch = MathUtils.getPitch(motion);
        if (degreesDifferenceAbs(yaw, getYRot()) > 5 && (getOnGround() || isOnWater())) {
            setDeltaMovement(motion.scale(0.98));
        }

        float d = degreesDifferenceAbs(pitch, getXRot());
        if (d > 180) {
            d = d - 180;
        }

        d /= 60;
        d = Math.min(1, d);
        d *= d;
        d = 1 - d;

        double speed = getDeltaMovement().length();
        double lift = Math.min(speed * tempMotionVars.liftFactor, tempMotionVars.maxLift) * d;
        if (getHealth() <= 0) {
            lift = 0;
        }

        setDeltaMovement(rotationToVector(lerpAngle180(0.1f, yaw, getYRot()),
                lerpAngle180(tempMotionVars.pitchToMotion * d, pitch, getXRot()) + lift,
                speed));
        if (!getOnGround() && !isOnWater() && motion.length() > 0.1) {

            if (degreesDifferenceAbs(pitch, getXRot()) > 90) {
                pitch = wrapDegrees(pitch + 180);
            }
            if (Math.abs(getXRot()) < 85) {

                yaw = MathUtils.getYaw(getDeltaMovement());
                if (degreesDifferenceAbs(yaw, getYRot()) > 90) {
                    yaw = yaw - 180;
                }
                Quaternion q1 = toQuaternion(yaw, pitch, rotationRoll);
                q = lerpQ(tempMotionVars.motionToRotation, q, q1);
            }

        }
        return q;
    }

    public Vector3f transformPos(Vector3f relPos) {
        MathUtils.EulerAngles angles = toEulerAngles(getQ_Client());
        angles.yaw = -angles.yaw;
        angles.roll = -angles.roll;
        relPos.transform(toQuaternion(angles.yaw, angles.pitch, angles.roll));
        return relPos;
    }

    @Nullable
    public Entity getControllingPassenger() {
        List<Entity> list = getPassengers();
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        if (compound.contains("max_speed")) {
            entityData.set(MAX_SPEED, compound.getFloat("max_speed"));
        }

        if (compound.contains("max_health")) {
            int maxHealth = compound.getInt("max_health");
            if (maxHealth <= 0) {
                maxHealth = 20;
            }
            entityData.set(MAX_HEALTH, maxHealth);
        }

        if (compound.contains("health")) {
            int health = compound.getInt("health");
            entityData.set(HEALTH, health);
        }

        if (compound.contains("upgrades")) {
            CompoundTag upgradesNBT = compound.getCompound("upgrades");
            deserializeUpgrades(upgradesNBT);
        }

        setQ(new Quaternion(getXRot(), getYRot(), 0, true));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        compound.putInt("health", entityData.get(HEALTH));
        compound.putInt("max_health", entityData.get(MAX_HEALTH));
        compound.putFloat("max_speed", entityData.get(MAX_SPEED));
        compound.put("upgrades", getUpgradesNBT());
    }

    private void deserializeUpgrades(CompoundTag upgradesNBT) {
        for (String key : upgradesNBT.getAllKeys()) {
            ResourceLocation resourceLocation = new ResourceLocation(key);
            UpgradeType upgradeType = SVARegistries.UPGRADE_TYPES.get().getValue(resourceLocation);
            if (upgradeType != null) {
                Upgrade upgrade = upgradeType.instanceSupplier.apply(this);
                upgrade.deserializeNBT(upgradesNBT.getCompound(key));
                upgrades.put(resourceLocation, upgrade);
                if (upgradeType.isEngine) {
                    vehicleEngineUpgrade = (VehicleEngineUpgrade) upgrade;
                }
            }
        }
    }

    private CompoundTag getUpgradesNBT() {
        CompoundTag upgradesNBT = new CompoundTag();
        for (Upgrade upgrade : upgrades.values()) {
            upgradesNBT.put(SVARegistries.UPGRADE_TYPES.get().getKey(upgrade.getType()).toString(), upgrade.serializeNBT());
        }
        return upgradesNBT;
    }

    @Override
    protected boolean canRide(@NotNull Entity entityIn) {
        return true;
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    @Override
    public boolean canBeRiddenUnderFluidType(FluidType type, Entity rider) {
        if (type == ForgeMod.WATER_TYPE.get() && upgrades.containsKey(SVAUpgrades.FLOATING_PAD_UPGRADE.getId())) {
            return true;
        }

        return super.canBeRiddenUnderFluidType(type, rider);
    }

    public boolean canAddUpgrade(UpgradeType upgradeType) {
        if (upgradeType.isEngine && vehicleEngineUpgrade != null) {
            return false;
        }
        return !upgrades.containsKey(SVARegistries.UPGRADE_TYPES.get().getKey(upgradeType));
    }

    protected boolean tryToAddUpgrade(Player playerEntity, ItemStack itemStack) {
        Optional<UpgradeType> upgradeTypeOptional = SVAUpgrades.getUpgradeFromItem(itemStack.getItem());
        return upgradeTypeOptional.map(upgradeType -> {
            if (canAddUpgrade(upgradeType)) {
                Upgrade upgrade = upgradeType.instanceSupplier.apply(this);
                addUpgrade(playerEntity, itemStack, upgrade);
                return true;
            }
            return false;
        }).orElse(false);
    }

    protected void addUpgrade(Player playerEntity, ItemStack itemStack, Upgrade upgrade) {
        upgrade.onApply(itemStack, playerEntity);

        if (!playerEntity.isCreative()) {
            itemStack.shrink(1);
        }
        UpgradeType upgradeType = upgrade.getType();
        upgrades.put(SVARegistries.UPGRADE_TYPES.get().getKey(upgradeType), upgrade);
        if (upgradeType.isEngine) {
            vehicleEngineUpgrade = (VehicleEngineUpgrade) upgrade;
        }
        if (!level.isClientSide) {
            SVANetworking.INSTANCE.send(PacketDistributor.TRACKING_ENTITY.with(() -> this), new UpdateUpgradePacket(SVARegistries.UPGRADE_TYPES.get().getKey(upgradeType), getId(), (ServerLevel) level, true));
        }
    }

    public void writeUpdateUpgradePacket(ResourceLocation upgradeID, FriendlyByteBuf buffer) {
        buffer.writeVarInt(getId());
        buffer.writeResourceLocation(upgradeID);
        upgrades.get(upgradeID).writePacket(buffer);
    }

    @SuppressWarnings("ConstantConditions")
    public void readUpdateUpgradePacket(ResourceLocation upgradeID, FriendlyByteBuf buffer, boolean newUpgrade) {
        if (newUpgrade) {
            UpgradeType upgradeType = SVARegistries.UPGRADE_TYPES.get().getValue(upgradeID);
            Upgrade upgrade = upgradeType.instanceSupplier.apply(this);
            upgrades.put(upgradeID, upgrade);
            if (upgradeType.isEngine) {
                vehicleEngineUpgrade = (VehicleEngineUpgrade) upgrade;
            }
        }

        upgrades.get(upgradeID).readPacket(buffer);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (upgrades != null) {
            for (Upgrade upgrade : upgrades.values()) {
                LazyOptional<T> lazyOptional = upgrade.getCapability(cap, side);
                if (lazyOptional.isPresent()) {
                    return lazyOptional;
                }
            }
        }
        return super.getCapability(cap, side);
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void writeSpawnData(FriendlyByteBuf buffer) {
        Collection<Upgrade> upgrades = this.upgrades.values();
        buffer.writeVarInt(upgrades.size());
        for (Upgrade upgrade : upgrades) {
            ResourceLocation upgradeID = SVARegistries.UPGRADE_TYPES.get().getKey(upgrade.getType());
            buffer.writeResourceLocation(upgradeID);
            upgrade.writePacket(buffer);
        }
    }

    @Override
    public void readSpawnData(FriendlyByteBuf additionalData) {
        int upgradesSize = additionalData.readVarInt();
        for (int i = 0; i < upgradesSize; i++) {
            ResourceLocation upgradeID = additionalData.readResourceLocation();
            UpgradeType upgradeType = SVARegistries.UPGRADE_TYPES.get().getValue(upgradeID);
            Upgrade upgrade = upgradeType.instanceSupplier.apply(this);
            upgrades.put(upgradeID, upgrade);
            if (upgradeType.isEngine) {
                vehicleEngineUpgrade = (VehicleEngineUpgrade) upgrade;
            }
            upgrade.readPacket(additionalData);
        }
    }

    public void removeUpgrade(ResourceLocation upgradeID) {
        Upgrade upgrade = upgrades.remove(upgradeID);
        if (upgrade != null) {
            upgrade.onRemoved();
            upgrade.remove();

            if (!level.isClientSide) {
                SVANetworking.INSTANCE.send(PacketDistributor.TRACKING_ENTITY.with(() -> this), new UpgradeRemovedPacket(upgradeID, getId()));
            }
        }
    }

    @Override
    public @NotNull Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public void onSyncedDataUpdated(@NotNull EntityDataAccessor<?> key) {
        super.onSyncedDataUpdated(key);

        if (Q.equals(key) && level.isClientSide() && !isControlledByLocalInstance()) {
            if (firstTick) {
                lerpStepsQ = 0;
                setQ_Client(getQ());
                setQ_prev(getQ());
            } else {
                lerpStepsQ = 10;
            }
        }
    }

    @Override
    public double getPassengersRidingOffset() {
        return 0.5;
    }

    @Override
    public boolean isInvulnerableTo(DamageSource source) {
        if (source.isExplosion()) {
            return false;
        }
        if (source.isFire()) {
            return false;
        }
        if (source.getDirectEntity() != null && source.getDirectEntity().isPassengerOfSameVehicle(this)) {
            return true;
        }
        return super.isInvulnerableTo(source);
    }

    @Override
    protected void checkFallDamage(double y, boolean onGroundIn, @NotNull BlockState state, @NotNull BlockPos pos) {
        if ((onGroundIn || isOnWater())) {
            final double y1 = transformPos(new Vector3f(0, 1, 0)).y();
            if (y1 < Math.cos(Math.toRadians(getLandingAngle()))) {
                state.getBlock().fallOn(level, state, pos, this, (float) (getDeltaMovement().length() * 5));
            }
            fallDistance = 0.0F;
        }
    }

    protected int getLandingAngle() {
        return 30;
    }

    @Override
    public boolean causeFallDamage(float fallDistance, float damageMultiplier, @NotNull DamageSource p_146830_) {
        if (isVehicle()) {
            crash(fallDistance * damageMultiplier);
        }
        return false;
    }

    public void crash(float damage) {
        if (!level.isClientSide && isAlive()) {
            for (Entity entity : getPassengers()) {
                float damageMod = Math.min(1, 1 - ((float) getHealth() / getMaxHealth()));
                entity.hurt(SVADamageSources.DAMAGE_SOURCE_PLANE_CRASH, damage * damageMod);
            }
            hurt(SVADamageSources.DAMAGE_SOURCE_PLANE_CRASH, damage + 2);
        }
    }

    public boolean hasStorageUpgrade() {
        for (Upgrade upgrade : upgrades.values()) {
            if (upgrade.hasStorage())
                return true;
        }

        return false;
    }

    public boolean getOnGround() {
        return onGround || onGroundTicks > 1;
    }

    public boolean isOnWater() {
        return level.getBlockState(new BlockPos(position().add(0, 0.4, 0))).getBlock() == Blocks.WATER;
    }

    public boolean isCreative() {
        return getControllingPassenger() instanceof Player && ((Player) getControllingPassenger()).isCreative();
    }

    @Override
    public void positionRider(@NotNull Entity passenger) {
        positionRiderGeneric(passenger);

        int index = getPassengers().indexOf(passenger);
        if (index == 0) {
            Vector3f pos = transformPos(new Vector3f(0, (float) (getPassengersRidingOffset() + passenger.getMyRidingOffset()) - 0.2f, 5.8f));
            passenger.setPos(getX() + pos.x(), getY() + pos.y(), getZ() + pos.z());
        } else if (index == 1) {
            Vector3f pos = transformPos(new Vector3f(-1, (float) (getPassengersRidingOffset() + passenger.getMyRidingOffset()), -1.3f));
            passenger.setPos(getX() + pos.x(), getY() + pos.y(), getZ() + pos.z());
        } else if (index == 2) {
            Vector3f pos = transformPos(new Vector3f(1, (float) (getPassengersRidingOffset() + passenger.getMyRidingOffset()), -1.3f));
            passenger.setPos(getX() + pos.x(), getY() + pos.y(), getZ() + pos.z());
        }
    }

    protected void positionRiderGeneric(Entity passenger) {
        super.positionRider(passenger);
        boolean local = (passenger instanceof Player) && ((Player) passenger).isLocalPlayer();

        if (hasPassenger(passenger) && !local) {
            applyYawToEntity(passenger);
        }
    }

    public void applyYawToEntity(Entity entityToUpdate) {
        entityToUpdate.setYHeadRot(entityToUpdate.getYHeadRot() + deltaRotation);

        entityToUpdate.yRotO += deltaRotation;

        entityToUpdate.setYBodyRot(getYRot());

        entityToUpdate.setYHeadRot(entityToUpdate.getYRot());
    }

    public ItemStack getItemStack() {
        ItemStack itemStack = getItem().getDefaultInstance();
        CompoundTag compound = new CompoundTag();
        addAdditionalSaveData(compound);
        compound.putInt("health", entityData.get(MAX_HEALTH));
        compound.putBoolean("Used", true);
        itemStack.addTagElement("EntityTag", compound);
        return itemStack;
    }

    protected Item getItem() {
        return SVAItems.airVehichlesItems.get(vehichleName).get();
    }

    private int lerpSteps;
    private int lerpStepsQ;

    private double lerpX;
    private double lerpY;
    private double lerpZ;

    private void tickLerp() {
        if (isControlledByLocalInstance()) {
            lerpSteps = 0;
            lerpStepsQ = 0;
            syncPacketPositionCodec(getX(), getY(), getZ());
            return;
        }

        if (lerpSteps > 0) {
            double d0 = getX() + (lerpX - getX()) / (double) lerpSteps;
            double d1 = getY() + (lerpY - getY()) / (double) lerpSteps;
            double d2 = getZ() + (lerpZ - getZ()) / (double) lerpSteps;
            --lerpSteps;
            setPos(d0, d1, d2);
        }
        if (lerpStepsQ > 0) {
            setQ_prev(getQ_Client());
            setQ_Client(lerpQ(1f / lerpStepsQ, getQ_Client(), getQ()));
            --lerpStepsQ;
        } else if (lerpStepsQ == 0) {
            setQ_prev(getQ_Client());
            setQ_Client(getQ());
            --lerpStepsQ;
        }
    }

    public void lerpTo(double x, double y, double z, float yaw, float pitch, int posRotationIncrements, boolean teleport) {
        if (x == getX() && y == getY() && z == getZ()) {
            return;
        }
        lerpX = x;
        lerpY = y;
        lerpZ = z;
        lerpSteps = 10;
    }

    @Override
    public void absMoveTo(double x, double y, double z, float yaw, float pitch) {
        double d0 = Mth.clamp(x, -3.0E7D, 3.0E7D);
        double d1 = Mth.clamp(z, -3.0E7D, 3.0E7D);
        xOld = d0;
        yOld = y;
        zOld = d1;
        setPos(d0, y, d1);
        setYRot(yaw % 360.0F);
        setXRot(pitch % 360.0F);

        yRotO = getYRot();
        xRotO = getXRot();
    }

    @Override
    protected void addPassenger(@NotNull Entity passenger) {
        super.addPassenger(passenger);
        if (isControlledByLocalInstance()) {
            if (lerpSteps > 0) {
                lerpSteps = 0;
                absMoveTo(lerpX, lerpY, lerpZ, getYRot(), getXRot());
            }
        }
    }

    public Player getPlayer() {
        if (getControllingPassenger() instanceof Player) {
            return (Player) getControllingPassenger();
        }
        return null;
    }

    /**
     * Sets the time to count down from since the last time entity was hit.
     */
    public void setTimeSinceHit(int timeSinceHit) {
        entityData.set(TIME_SINCE_HIT, timeSinceHit);
    }

    /**
     * Gets the time since the last hit.
     */
    public int getTimeSinceHit() {
        return entityData.get(TIME_SINCE_HIT);
    }

    /**
     * Sets the damage taken from the last hit.
     */
    public void setDamageTaken(float damageTaken) {
        entityData.set(DAMAGE_TAKEN, damageTaken);
    }

    /**
     * Gets the damage taken from the last hit.
     */
    public float getDamageTaken() {
        return entityData.get(DAMAGE_TAKEN);
    }

    public double getCameraDistanceMultiplayer() {
        return SVAConfigs.PLANE_CAMERA_DISTANCE_MULTIPLIER.get();
    }

    private static final TempMotionVars TEMP_MOTION_VARS = new TempMotionVars();

    public void changeThrottle(ChangeThrottlePacket.Type type) {
        int throttle = getThrottle();
        if (type == ChangeThrottlePacket.Type.UP) {
            if (throttle < MAX_THROTTLE || (upgrades.containsKey(SVAUpgrades.ROCKET_BOOSTER_UPGRADE.getId()) && throttle < RocketBoosterUpgrade.MAX_THROTTLE)) {
                setThrottle(throttle + 1);
            }
        } else if (throttle > 0) {
            setThrottle(throttle - 1);
        }
    }

    public int getThrottle() {
        return entityData.get(THROTTLE);
    }

    public void setThrottle(int value) {
        entityData.set(THROTTLE, value);
    }

    public byte getPitchUp() {
        return entityData.get(PITCH_UP);
    }

    public void setPitchUp(byte pitchUp) {
        entityData.set(PITCH_UP, pitchUp);
    }

    protected static class TempMotionVars {
        public float moveForward; //TODO: move to HelicopterEntity?
        public double turnThreshold;
        public float moveStrafing;
        double maxSpeed;
        double maxPushSpeed;
        double takeOffSpeed;
        float maxLift;
        double liftFactor;
        double gravity;
        double drag;
        double dragMul;
        double dragQuad;
        float push;
        float groundPush;
        float passiveEnginePush;
        float motionToRotation;
        float pitchToMotion;
        float yawMultiplayer;

        public TempMotionVars() {
            reset();
        }

        public void reset() {
            moveForward = 0;
            turnThreshold = 0;
            moveStrafing = 0;
            maxSpeed = 3;
            takeOffSpeed = 0.3;
            maxLift = 2;
            liftFactor = 10;
            gravity = -0.03;
            drag = 0.001;
            dragMul = 0.0005;
            dragQuad = 0.001;
            push = 0.0f;
            groundPush = 0.01f;
            passiveEnginePush = 0.025f;
            motionToRotation = 0.05f;
            pitchToMotion = 0.2f;
            yawMultiplayer = 0.5f;
        }
    }
}