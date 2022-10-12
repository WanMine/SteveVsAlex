package com.diamantino.stevevsalex.blocks;

import com.diamantino.stevevsalex.blockentitytypes.AmmoWorkbenchBlockEntityType;
import com.diamantino.stevevsalex.containers.AmmoWorkbenchContainer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

import static com.diamantino.stevevsalex.SteveVsAlex.MODID;

public class AmmoWorkbenchBlock extends Block implements EntityBlock {

    public static final Component CONTAINER_NAME = Component.translatable(MODID + ".container.ammo_workbench");

    public AmmoWorkbenchBlock(Properties properties) {
        super(properties);
    }

    @SuppressWarnings("deprecation")
    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, Level level, @NotNull BlockPos blockPos, @NotNull Player playerIn, @NotNull InteractionHand handIn, @NotNull BlockHitResult hit) {
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            AmmoWorkbenchBlockEntityType planeWorkbenchBlockEntity = (AmmoWorkbenchBlockEntityType) level.getBlockEntity(blockPos);
            if (planeWorkbenchBlockEntity != null) {
                NetworkHooks.openScreen((ServerPlayer) playerIn, new SimpleMenuProvider((id, inventory, player) ->
                        new AmmoWorkbenchContainer(id, inventory, blockPos, planeWorkbenchBlockEntity.itemStackHandler, planeWorkbenchBlockEntity.selectedRecipe), CONTAINER_NAME));
            }
            return InteractionResult.CONSUME;
        }
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState) {
        return new AmmoWorkbenchBlockEntityType(blockPos, blockState);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onRemove(BlockState state, @NotNull Level level, @NotNull BlockPos blockPos, BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            AmmoWorkbenchBlockEntityType blockEntity = (AmmoWorkbenchBlockEntityType) level.getBlockEntity(blockPos);
            if (blockEntity != null) {
                Containers.dropItemStack(level, blockPos.getX(), blockPos.getY(), blockPos.getZ(), blockEntity.itemStackHandler.getStackInSlot(0));
                Containers.dropItemStack(level, blockPos.getX(), blockPos.getY(), blockPos.getZ(), blockEntity.itemStackHandler.getStackInSlot(1));
            }
        }
        super.onRemove(state, level, blockPos, newState, isMoving);
    }
}