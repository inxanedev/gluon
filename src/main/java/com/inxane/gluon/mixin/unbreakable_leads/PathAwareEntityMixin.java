package com.inxane.gluon.mixin.unbreakable_leads;

import com.inxane.gluon.Gluon;
import net.minecraft.entity.mob.PathAwareEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PathAwareEntity.class)
public class PathAwareEntityMixin {
    @ModifyVariable(method = "updateLeash", at = @At("STORE"), ordinal = 0)
    private float distanceToResult(float distance) {
        if (distance > 10.0f && Gluon.CONFIG.unbreakableLeads()) {
            return 10.0f;
        }
        return distance;
    }
}
