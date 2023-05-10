package com.inxane.gluon.mixin.leadable_villagers;

import com.inxane.gluon.Gluon;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MerchantEntity.class)
public class MerchantEntityMixin {
    @Inject(at = @At(value = "TAIL"), method = "canBeLeashedBy", cancellable = true)
    public void canBeLeashedBy(PlayerEntity player, CallbackInfoReturnable<Boolean> cir) {
        if (Gluon.CONFIG.leadableVillagers()) {
            cir.setReturnValue(true);
        }
    }
}
