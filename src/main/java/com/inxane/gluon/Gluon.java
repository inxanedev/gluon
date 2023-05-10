package com.inxane.gluon;

import com.inxane.gluon.commands.ModCommands;
import com.inxane.gluon.config.ModConfig;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Gluon implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("gluon");
    public static final ModConfig CONFIG = ModConfig.createAndLoad();

    @Override
    public void onInitialize() {
        LOGGER.info("Initialized Gluon");
        ModCommands.registerCommands();
    }
}