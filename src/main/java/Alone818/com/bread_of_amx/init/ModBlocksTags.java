package Alone818.com.bread_of_amx.init;

import Alone818.com.bread_of_amx.Bread_of_amx;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModBlocksTags {
    private static TagKey<Block> create(String pName) {
        return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(Bread_of_amx.MODID, pName));
    }
}
