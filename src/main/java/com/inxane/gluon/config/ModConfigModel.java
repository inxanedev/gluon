package com.inxane.gluon.config;

import io.wispforest.owo.config.Option;
import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Modmenu;
import io.wispforest.owo.config.annotation.Nest;
import io.wispforest.owo.config.annotation.Sync;

@Modmenu(modId = "gluon")
@Config(name = "gluon", wrapperName = "ModConfig")
@SuppressWarnings("unused")
public class ModConfigModel {
    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    public boolean leadableVillagers = true;
    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    public boolean unbreakableLeads = true;

    @Nest
    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    public PiglinBrutesDropNetheriteScrap piglinBrutesDropNetheriteScrap = new PiglinBrutesDropNetheriteScrap();
    public static class PiglinBrutesDropNetheriteScrap {
        @Sync(Option.SyncMode.OVERRIDE_CLIENT)
        public boolean enabled = true;
        @Sync(Option.SyncMode.OVERRIDE_CLIENT)
        public float chance = 55.0f;
        @Sync(Option.SyncMode.OVERRIDE_CLIENT)
        public float additionalChancePerLootingLevel = 15.0f;
    }

    @Nest
    public UseThrough useThrough = new UseThrough();
    public static class UseThrough {
        public boolean throughSigns = true;
        public boolean throughItemFrames = true;
    }

    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    public int afterBarterPiglinAngerCooldown = 20 * 15;
    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    public boolean spawnParticlesOnPiglinAnger = true;
}
