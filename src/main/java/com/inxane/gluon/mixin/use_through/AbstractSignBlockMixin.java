package com.inxane.gluon.mixin.use_through;

import com.inxane.gluon.Gluon;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractSignBlock.class)
public class AbstractSignBlockMixin {
    @Inject(at = @At("HEAD"), method = "onUse", cancellable = true)
    private void onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, CallbackInfoReturnable<ActionResult> cir) {
        if (!Gluon.CONFIG.useThrough.throughSigns() || !world.isClient() && player.isSneaking()) return;
        if (state.getBlock() instanceof WallSignBlock) {
            BlockPos behindPos = pos.offset(state.get(Properties.HORIZONTAL_FACING).getOpposite(), 1);
            BlockState behind = world.getBlockState(behindPos);

            Gluon.LOGGER.info(behind.getBlock().toString());

            /*if (behind.getBlock() instanceof ChestBlock chest) {
                cir.setReturnValue(ActionResult.SUCCESS);
                chest.onUse(behind, world, behindPos, player, hand, hit);
            }*/

            cir.setReturnValue(ActionResult.SUCCESS);
            behind.onUse(world, player, hand, new BlockHitResult(new Vec3d(behindPos.getX(), behindPos.getY(), behindPos.getZ()), hit.getSide().getOpposite(), behindPos, false));
        }
    }
}
