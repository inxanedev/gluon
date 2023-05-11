package com.inxane.gluon.mixin.longer_neutral_piglins;

import com.inxane.gluon.Gluon;
import com.inxane.gluon.cooldowns.PiglinAngerCooldowns;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.entity.mob.PiglinEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Optional;

@Mixin(PiglinBrain.class)
public class PiglinBrainMixin {
    @Inject(at = @At("HEAD"), method = "getPreferredTarget", cancellable = true)
    private static void onBecomeAngryWithEntity(PiglinEntity piglin, CallbackInfoReturnable<Optional<? extends LivingEntity>> cir) {
        if (Gluon.CONFIG.afterBarterPiglinAngerCooldown() != 0) {
            if (PiglinAngerCooldowns.cooldowns.containsKey(piglin.getUuid())) {
                cir.setReturnValue(Optional.empty());
            }
        }
    }

    private static void common(PiglinEntity piglin) {
        int cooldown = Gluon.CONFIG.afterBarterPiglinAngerCooldown();
        if (cooldown == 0) return;
        PiglinAngerCooldowns.cooldowns.put(piglin.getUuid(), new PiglinAngerCooldowns.Cooldown(cooldown));
    }

    @Inject(at = @At("TAIL"), method = "dropBarteredItem(Lnet/minecraft/entity/mob/PiglinEntity;Ljava/util/List;)V")
    private static void onDropBarteredItem(PiglinEntity piglin, List<ItemStack> items, CallbackInfo ci) {
        common(piglin);
    }

    @Inject(at = @At("TAIL"), method = "dropBarteredItem(Lnet/minecraft/entity/mob/PiglinEntity;Lnet/minecraft/entity/player/PlayerEntity;Ljava/util/List;)V")
    private static void onDropBarteredItemToPlayer(PiglinEntity piglin, PlayerEntity player, List<ItemStack> items, CallbackInfo ci) {
        common(piglin);
    }
}
