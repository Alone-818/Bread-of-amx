package Alone818.com.bread_of_amx.init;

import Alone818.com.bread_of_amx.Bread_of_amx;
import Alone818.com.bread_of_amx.itemcuiros.*;
import Alone818.com.bread_of_amx.itemtools.*;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ShieldItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Bread_of_amx.MODID);
    public static final RegistryObject<Item> TOAST=
            ITEMS.register("toast",()->new Item(new Item.Properties().food(Modfoods.TOAST))) ;
    public  static final RegistryObject<Item> PACKED_TOAST =
            ITEMS.register("packed_toast",()-> new BlockItem(ModBlocks.PACKED_TOAST.get(),new Item.Properties()));
    public static final RegistryObject<Item> DRIEST_BREAD =
            ITEMS.register("driest_bread", () -> new driest_bread());
    public static final RegistryObject<Item> BREADSHIELD =
            ITEMS.register("breadshield", breadshield::new);
    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
