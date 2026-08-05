package Alone818.com.bread_of_amx.recipe;

import Alone818.com.bread_of_amx.Bread_of_amx;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Bread_of_amx.MODID);

    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES =
            DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, Bread_of_amx.MODID);

    public static final RegistryObject<RecipeSerializer<ToasterRecipe>> TOASTER_RECIPE_SERIALIZER =
            SERIALIZERS.register("toaster_recipe", () -> new ToasterRecipe.Serializer());

    public static final RegistryObject<RecipeType<ToasterRecipe>> TOASTER_RECIPE_TYPE =
            RECIPE_TYPES.register("toaster_recipe", () -> new RecipeType<>() {
                @Override
                public String toString() {
                    return "toaster_recipe";
                }
            });

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
        RECIPE_TYPES.register(eventBus);
    }
}