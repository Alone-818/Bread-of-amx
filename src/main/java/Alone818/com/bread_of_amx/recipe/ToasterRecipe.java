package Alone818.com.bread_of_amx.recipe;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

public class ToasterRecipe implements Recipe<SimpleContainer> {
    private final ResourceLocation id;
    private final Ingredient ingredient;
    private final ItemStack result;
    private final int processingTime;
    private final int energyConsumption;

    public ToasterRecipe(ResourceLocation id, Ingredient ingredient, ItemStack result, int processingTime, int energyConsumption) {
        this.id = id;
        this.ingredient = ingredient;
        this.result = result;
        this.processingTime = processingTime;
        this.energyConsumption = energyConsumption;
    }

    @Override
    public boolean matches(SimpleContainer container, Level level) {
        return ingredient.test(container.getItem(0));
    }

    @Override
    public ItemStack assemble(SimpleContainer container, RegistryAccess registryAccess) {
        return result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        list.add(ingredient);
        return list;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess registryAccess) {
        return result.copy();
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.TOASTER_RECIPE_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.TOASTER_RECIPE_TYPE.get();
    }

    public int getProcessingTime() {
        return processingTime;
    }

    public int getEnergyConsumption() {
        return energyConsumption;
    }

    public static class Serializer implements RecipeSerializer<ToasterRecipe> {
        @Override
        public ToasterRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            Ingredient ingredient = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "ingredient"));
            ItemStack result = ItemStack.EMPTY;
            if (json.has("result")) {
                JsonObject resultJson = GsonHelper.getAsJsonObject(json, "result");
                String itemId = GsonHelper.getAsString(resultJson, "item");
                int count = GsonHelper.getAsInt(resultJson, "count", 1);
                result = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemId)), count);
            }
            int processingTime = GsonHelper.getAsInt(json, "processing_time", 200);
            int energyConsumption = GsonHelper.getAsInt(json, "energy_consumption", 10);
            return new ToasterRecipe(recipeId, ingredient, result, processingTime, energyConsumption);
        }

        @Override
        public ToasterRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            Ingredient ingredient = Ingredient.fromNetwork(buffer);
            ItemStack result = buffer.readItem();
            int processingTime = buffer.readInt();
            int energyConsumption = buffer.readInt();
            return new ToasterRecipe(recipeId, ingredient, result, processingTime, energyConsumption);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, ToasterRecipe recipe) {
            recipe.ingredient.toNetwork(buffer);
            buffer.writeItem(recipe.result);
            buffer.writeInt(recipe.processingTime);
            buffer.writeInt(recipe.energyConsumption);
        }
    }
}