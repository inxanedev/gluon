package com.inxane.gluon.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.block.StonecutterBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.screen.StonecutterScreenHandler;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;

public class ModCommands {

    public static class MyStonecutterScreenHandler extends StonecutterScreenHandler {
        public MyStonecutterScreenHandler(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
            super(syncId, playerInventory, context);
        }

        @Override
        public boolean canUse(PlayerEntity player) {
            return true;
        }
    }

    public static void registerCommands() {
        CommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess, environment) -> dispatcher.register(CommandManager.literal("stonecutter").executes(context -> {
            ServerPlayerEntity player = context.getSource().getPlayer();
            if (player != null) {
                player.openHandledScreen(new SimpleNamedScreenHandlerFactory((syncId, playerInventory, p) -> new MyStonecutterScreenHandler(syncId, playerInventory, ScreenHandlerContext.create(player.getEntityWorld(), player.getBlockPos())), Text.translatable("container.stonecutter")));
                player.incrementStat(Stats.INTERACT_WITH_STONECUTTER);
            }
            return 1;
        }))));
    }
}
