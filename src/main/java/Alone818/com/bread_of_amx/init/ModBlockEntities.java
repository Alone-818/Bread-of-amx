package Alone818.com.bread_of_amx.init;

import Alone818.com.bread_of_amx.BlockEntities.toast_feeding_range_BlockEntity;
import Alone818.com.bread_of_amx.Bread_of_amx;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Bread_of_amx.MODID);

    /**
     * toast_feeding_range 方块的 BlockEntityType。
     */
    public static final RegistryObject<BlockEntityType<toast_feeding_range_BlockEntity>> TOAST_FEEDING_RANGE_BLOCKENTITY =
            BLOCK_ENTITIES.register("toast_feeding_range_block_entity", () ->
                    BlockEntityType.Builder.of(
                            toast_feeding_range_BlockEntity::new,
                            ModBlocks.TOAST_FEEDING_RANGE.get()
                    ).build(null)
            );

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
