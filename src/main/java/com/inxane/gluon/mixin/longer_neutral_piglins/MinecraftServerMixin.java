package com.inxane.gluon.mixin.longer_neutral_piglins;

import com.inxane.gluon.cooldowns.PiglinAngerCooldowns;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.BooleanSupplier;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {

    @Inject(at = @At("TAIL"), method = "tick")
    public void onTick(BooleanSupplier shouldKeepTicking, CallbackInfo ci) {
        PiglinAngerCooldowns.update();
    }
}
