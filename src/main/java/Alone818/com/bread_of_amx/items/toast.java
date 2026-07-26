package Alone818.com.bread_of_amx.items;

import Alone818.com.bread_of_amx.init.Modfoods;
import net.minecraft.world.item.Item;

public class toast extends Item {
    public toast(Properties properties) {
        super(properties);
        properties.food(Modfoods.TOAST);
    }
}
