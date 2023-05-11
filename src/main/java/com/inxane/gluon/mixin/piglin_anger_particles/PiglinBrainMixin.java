package com.inxane.gluon.mixin.piglin_anger_particles;

import com.inxane.gluon.Gluon;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.AbstractPiglinEntity;
import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PiglinBrain.class)
public class PiglinBrainMixin {
    @Inject(at = @At("TAIL"), method = "becomeAngryWith")
    private static void onAnger(AbstractPiglinEntity piglin, LivingEntity target, CallbackInfo ci) {
        if (piglin.getWorld() instanceof ServerWorld world && Gluon.CONFIG.spawnParticlesOnPiglinAnger()) {
            for (int i = 0; i < 5; i++) {
                double d = piglin.getRandom().nextGaussian() * 0.02D;
                double e = piglin.getRandom().nextGaussian() * 0.02D;
                double f = piglin.getRandom().nextGaussian() * 0.02D;
                world.spawnParticles(ParticleTypes.ANGRY_VILLAGER, piglin.getParticleX(1), piglin.getRandomBodyY() + 1, piglin.getParticleZ(1), 1, d, e, f, 0);
            }
        }
    }
}
