package com.copy_item.item.datagen;

import com.copy_item.item.CopyItem;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {
    
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider);
    }
    
    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CopyItem.COPYITEM_BLOCK_ITEM.get())
                .pattern("III")
                .pattern("CAB")
                .pattern("ISI")
                .define('I', Items.IRON_INGOT)
                .define('C', Items.COAL)
                .define('A', Items.CHEST)
                .define('B', Items.BUCKET)
                .define('S', Items.REDSTONE)
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .save(recipeOutput);
        
        ResourceLocation steelInIngotLocation = ResourceLocation.fromNamespaceAndPath("immersiveengineering", "ingot_steel");
        Item steelIngot = BuiltInRegistries.ITEM.get(steelInIngotLocation);
        if (steelIngot != Items.AIR) {
            Ingredient steelIngredient = Ingredient.of(steelIngot);
            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CopyItem.COPYITEM_BLOCK_ITEM.get())
                    .pattern("III")
                    .pattern("CAB")
                    .pattern("ISI")
                    .define('I', steelIngredient)
                    .define('C', Items.COAL)
                    .define('A', Items.CHEST)
                    .define('B', Items.BUCKET)
                    .define('S', Items.REDSTONE)
                    .unlockedBy("has_ie_steel_ingot", has(steelIngot))
                    .save(recipeOutput, "copyitem/ie_metal");
        }
    }
}
