package fuzs.permanentsponges.common.data;

import fuzs.permanentsponges.common.init.ModRegistry;
import fuzs.puzzleslib.common.api.data.v3.recipes.AbstractRecipeProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;

public class ModRecipeProvider extends AbstractRecipeProvider {

    public ModRecipeProvider(BootstrapContext<Recipe<?>> recipeOutput, BootstrapContext<Advancement> advancementOutput) {
        super(recipeOutput, advancementOutput);
    }

    @Override
    public void buildRecipes() {
        ShapedRecipeBuilder.shaped(this.items,
                        RecipeCategory.BUILDING_BLOCKS,
                        ModRegistry.AQUEOUS_SPONGE_BLOCK.value())
                .define('@', ItemTags.WOOL)
                .define('#', Items.SLIME_BALL)
                .pattern(" # ")
                .pattern("#@#")
                .pattern(" # ")
                .unlockedBy(getHasName(Items.SLIME_BALL), this.has(Items.SLIME_BALL))
                .save(this.output);
        ShapedRecipeBuilder.shaped(this.items,
                        RecipeCategory.BUILDING_BLOCKS,
                        ModRegistry.MAGMATIC_SPONGE_BLOCK.value())
                .define('@', ItemTags.WOOL)
                .define('#', Items.MAGMA_CREAM)
                .pattern(" # ")
                .pattern("#@#")
                .pattern(" # ")
                .unlockedBy(getHasName(Items.MAGMA_CREAM), this.has(Items.MAGMA_CREAM))
                .save(this.output);
        ShapelessRecipeBuilder.shapeless(this.items,
                        RecipeCategory.TOOLS,
                        ModRegistry.AQUEOUS_SPONGE_ITEM.value())
                .requires(Items.STICK)
                .requires(ModRegistry.AQUEOUS_SPONGE_BLOCK.value())
                .unlockedBy(getHasName(ModRegistry.AQUEOUS_SPONGE_BLOCK.value()),
                        this.has(ModRegistry.AQUEOUS_SPONGE_BLOCK.value()))
                .save(this.output);
        ShapelessRecipeBuilder.shapeless(this.items,
                        RecipeCategory.TOOLS,
                        ModRegistry.MAGMATIC_SPONGE_ITEM.value())
                .requires(Items.STICK)
                .requires(ModRegistry.MAGMATIC_SPONGE_BLOCK.value())
                .unlockedBy(getHasName(ModRegistry.MAGMATIC_SPONGE_BLOCK.value()),
                        this.has(ModRegistry.MAGMATIC_SPONGE_BLOCK.value()))
                .save(this.output);
    }
}
