package Alone818.com.bread_of_amx.init;

import Alone818.com.bread_of_amx.Bread_of_amx;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;


public class ModItemsTags {
    private static TagKey<Item> bind(String pName) {
        return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(Bread_of_amx.MODID, pName));
    }
}
