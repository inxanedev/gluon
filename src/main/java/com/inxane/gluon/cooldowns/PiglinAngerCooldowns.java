package com.inxane.gluon.cooldowns;

import java.util.HashMap;
import java.util.UUID;

public class PiglinAngerCooldowns {
    public static class Cooldown {
        public int ticks;

        public Cooldown(int ticks) {
            this.ticks = ticks;
        }
    }
    public static final HashMap<UUID, Cooldown> cooldowns = new HashMap<>();

    public static void update() {
        cooldowns.entrySet().removeIf(entry -> --entry.getValue().ticks == 0);
    }
}
