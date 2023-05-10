package com.inxane.gluon.mixin.use_through;

import com.inxane.gluon.Gluon;
import net.minecraft.block.BlockState;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemFrameEntity.class)
public class ItemFrameEntityMixin {
    @Inject(at = @At("HEAD"), method = "interact", cancellable = true)
    public void t(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        if (player.isSneaking() || !Gluon.CONFIG.useThrough.throughItemFrames() || player.getWorld().isClient()) return;

        ItemFrameEntity itemFrame = (ItemFrameEntity)(Object)this;
        BlockPos behindPos = itemFrame.getBlockPos().offset(itemFrame.getHorizontalFacing().getOpposite());
        BlockState behind = player.getWorld().getBlockState(behindPos);

        cir.setReturnValue(ActionResult.PASS);
        behind.onUse(player.getWorld(), player, hand, new BlockHitResult(new Vec3d(behindPos.getX(), behindPos.getY(), behindPos.getZ()), itemFrame.getHorizontalFacing().getOpposite(), behindPos, false));
    }
}
