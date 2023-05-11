package com.inxane.gluon.config;

import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Modmenu;
import io.wispforest.owo.config.annotation.Nest;

@Modmenu(modId = "gluon")
@Config(name = "gluon", wrapperName = "ModConfig")
@SuppressWarnings("unused")
public class ModConfigModel {
    public boolean leadableVillagers = true;
    public boolean unbreakableLeads = true;

    @Nest
    public PiglinBrutesDropNetheriteScrap piglinBrutesDropNetheriteScrap = new PiglinBrutesDropNetheriteScrap();
    public static class PiglinBrutesDropNetheriteScrap {
        public boolean enabled = true;
        public float chance = 55.0f;
        public float additionalChancePerLootingLevel = 15.0f;
    }

    @Nest
    public UseThrough useThrough = new UseThrough();
    public static class UseThrough {
        public boolean throughSigns = true;
        public boolean throughItemFrames = true;
    }

    public int afterBarterPiglinAngerCooldown = 20 * 15;
}
