package Alone818.com.bread_of_amx.init;

import Alone818.com.bread_of_amx.Blocks.machine.toast_feeding_range;
import Alone818.com.bread_of_amx.Bread_of_amx;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static net.minecraft.world.item.Items.registerBlock;

public class ModBlocks {
 public static final DeferredRegister<Block> BLOCKS =
 DeferredRegister.create(ForgeRegistries.BLOCKS, Bread_of_amx.MODID);
 public static final RegistryObject<Block> PACKED_TOAST =
 BLOCKS.register("packed_toast", () -> new Block(BlockBehaviour.Properties.of().strength(1.0F, 1.0F).noOcclusion()));
 public static final RegistryObject<Block> TOAST_FEEDING_RANGE =
 BLOCKS.register("toast_feeding_range",()->new toast_feeding_range(BlockBehaviour.Properties.of()));
 // 将本类中的注册器挂载到 Mod 事件总线。
 // 只有调用此方法后，方块才会在加载阶段被真正注册到游戏中。
 public static void register(IEventBus eventBus) {
 BLOCKS.register(eventBus);
 }

}
