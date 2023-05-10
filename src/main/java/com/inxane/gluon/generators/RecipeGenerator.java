package com.inxane.gluon.generators;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.RecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Items;

import java.util.function.Consumer;

public class RecipeGenerator extends FabricRecipeProvider {
    public RecipeGenerator(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void generateRecipes(Consumer<RecipeJsonProvider> exporter) {
        ShapedRecipeJsonBuilder.create(Items.LEATHER)
                .pattern("RR")
                .pattern("RR")
                .input('R', Items.ROTTEN_FLESH)
                .criterion(RecipeProvider.hasItem(Items.ROTTEN_FLESH), RecipeProvider.conditionsFromItem(Items.ROTTEN_FLESH))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(Items.NETHER_WART, 9)
                .input(Items.NETHER_WART_BLOCK)
                .criterion(RecipeProvider.hasItem(Items.NETHER_WART_BLOCK), RecipeProvider.conditionsFromItem(Items.NETHER_WART_BLOCK))
                .offerTo(exporter);
    }
}
