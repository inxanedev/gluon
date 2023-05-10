package com.inxane.gluon.mixin.netherite_scrap_piglin_brutes;

import com.inxane.gluon.Gluon;
import net.minecraft.client.MinecraftClient;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.PiglinBruteEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PiglinBruteEntity.class)
public class PiglinBruteEntityMixin {
    @Inject(at = @At("TAIL"), method = "damage")
    private void onDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (!Gluon.CONFIG.piglinBrutesDropNetheriteScrap.enabled()) return;

        PiglinBruteEntity brute = (PiglinBruteEntity)(Object)this;
        assert brute != null;
        if (brute.isDead()) {
            float chanceToDrop = Gluon.CONFIG.piglinBrutesDropNetheriteScrap.chance();

            if (source.getAttacker() instanceof PlayerEntity player) {
                chanceToDrop += Gluon.CONFIG.piglinBrutesDropNetheriteScrap.additionalChancePerLootingLevel() * EnchantmentHelper.getLevel(Enchantments.LOOTING, player.getInventory().getMainHandStack());
            }

            chanceToDrop /= 100;
            if (Math.random() <= chanceToDrop) {
                brute.dropStack(new ItemStack(Items.NETHERITE_SCRAP, 1));
            }
        }
    }
}
