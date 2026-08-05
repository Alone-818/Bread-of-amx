package Alone818.com.bread_of_amx.Blocks.machine;

import Alone818.com.bread_of_amx.BlockEntities.toast_feeding_range_BlockEntity;
import Alone818.com.bread_of_amx.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;

public class toast_feeding_range extends Block implements EntityBlock {
    public toast_feeding_range(Properties properties) {
        super(properties);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {

        /**
         * 当方块被放置到世界中时调用。
         *
         * 这个方法负责告诉游戏：
         * “这个方块在该位置应该创建哪一种 BlockEntity。”
         *
         * 每一个拥有 BlockEntity 的方块，都必须实现这个方法，
         * 否则即使注册了 BlockEntityType，世界中也不会真正生成实体。
         */
        return new toast_feeding_range_BlockEntity(blockPos, blockState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(
            Level pLevel,
            BlockState pState,
            BlockEntityType<T> pBlockEntityType) {

        /**
         * ticker 用于把 BlockEntity 接入游戏的 tick 循环。
         *
         * Minecraft 每游戏刻都会询问方块：
         * “这个位置的 BlockEntity 需要执行更新逻辑吗？”
         *
         * 如果返回一个 ticker，游戏就会每 tick 调用它；
         * 返回 null，则表示该方块实体不需要更新。
         */

        return pBlockEntityType == ModBlockEntities.TOAST_FEEDING_RANGE_BLOCKENTITY.get()
                // 类型匹配时，每 tick 调用我们的 BlockEntity.tick()
                ? (lvl, pos, state, be) ->
                ((toast_feeding_range_BlockEntity) be).tick()
                : null;
    }

    @Override
    public InteractionResult use(BlockState state, Level level,
                                 BlockPos pos, Player player,
                                 InteractionHand hand, BlockHitResult hit) {

        /**
         * 玩家右键方块时触发。
         *
         * 在本章中我们还没有 GUI，因此使用聊天信息作为
         * 最简单的“可视化调试方式”，用于观察 BlockEntity 内部数据。
         */

        // 只在服务端执行，避免客户端与服务端重复发送消息
        if (!level.isClientSide) {

            // 获取当前位置的 BlockEntity
            BlockEntity be = level.getBlockEntity(pos);

            // 判断是否为我们的工业处理单元实体
            if (be instanceof toast_feeding_range_BlockEntity machine) {

                // 输出当前进度信息，用于验证 tick 与 NBT 是否正常工作
                player.sendSystemMessage(machine.getDebugMessages());
            }
        }

        return InteractionResult.SUCCESS;
    }
}
