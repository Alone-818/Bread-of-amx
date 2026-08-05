package Alone818.com.bread_of_amx.init;

import Alone818.com.bread_of_amx.Bread_of_amx;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import javax.swing.*;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
        DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Bread_of_amx.MODID);

    public static final RegistryObject<CreativeModeTab> BREADCRAFT_MATERIAL =
        CREATIVE_MODE_TABS.register("tab.bread_of_amx_material",
            () -> CreativeModeTab.builder()
                .icon(() -> new ItemStack(ModItems.TOAST.get()))
                .title(Component.translatable("tab.bread_of_amx_material"))
                .displayItems((params, output) -> {
                    output.accept(ModItems.TOAST.get());
                    output.accept(ModBlocks.PACKED_TOAST.get());
                    output.accept(ModItems.DRIEST_BREAD.get());
                })
                .build());
    public static final RegistryObject<CreativeModeTab> BREADCRAFT_TOOLS =
            CREATIVE_MODE_TABS.register("tab.bread_of_amx_tools" ,
                    ()-> CreativeModeTab.builder()
                            .icon(() -> new ItemStack(ModItems.TOAST.get()))
                            .title(Component.translatable("tab.bread_of_amx_tools"))
                            .displayItems((params, output) -> {
                                output.accept(ModItems.BREADSHIELD.get());
                            })
                            .build());
    public static final RegistryObject<CreativeModeTab> BREADCRAFT_MACHINES =
            CREATIVE_MODE_TABS.register("tab.bread_of_amx_machines" ,
                    ()-> CreativeModeTab.builder()
                            .icon(() -> new ItemStack(ModItems.TOAST.get()))
                            .title(Component.translatable("tab.bread_of_amx_machines"))
                            .displayItems((params, output) -> {
                                output.accept(ModItems.TOAST_FEEDING_RANGE.get());
                            })
                            .build());
    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
