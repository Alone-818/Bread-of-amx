package Alone818.com.bread_of_amx.itemtools;

import Alone818.com.bread_of_amx.init.ModItems;
import Alone818.com.bread_of_amx.init.Modfoods;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class breadshield extends ShieldItem {

    public breadshield() {
        super(new Item.Properties()
                .durability(336)      // 耐久度
        );
    }

    // -------- 食物属性（必须保留，用于 player.eat()） --------
    @Override
    public FoodProperties getFoodProperties(ItemStack stack, @Nullable LivingEntity entity) {
        return Modfoods.BREADSHIELD;
    }

    @Override
    public boolean isValidRepairItem(ItemStack stack, ItemStack repairItem) {
        // 修理材料必须是吐司
        return repairItem.is(ModItems.TOAST.get());
    }
    // 附魔能力
    @Override
    public int getEnchantmentValue() {
        return 15;
    }

    // ============ 潜行进食核心实现 ============

    // 右键行为分支：潜行时开始进食，否则举盾
    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        // 潜行时且物品可食用，则进入进食模式
        if (player.isCrouching() && this.getFoodProperties(stack, player) != null) {
            // 标记为进食状态，用于后续 getUseDuration 和动画
            stack.getOrCreateTag().putBoolean("Eating", true);
            player.startUsingItem(hand);
            return InteractionResultHolder.consume(stack);
        }

        // 否则保持原版盾牌行为
        return super.use(world, player, hand);
    }

    // 根据进食标记动态返回使用时长：进食32tick，举盾72000tick
    @Override
    public int getUseDuration(ItemStack stack) {
        if (stack.hasTag() && stack.getTag().getBoolean("Eating")) {
            return 32; // 标准进食时间
        }
        return super.getUseDuration(stack);
    }

    // 根据进食标记返回动画：EAT（吃）或 BLOCK（格挡）
    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        if (stack.hasTag() && stack.getTag().getBoolean("Eating")) {
            return UseAnim.EAT;
        }
        return super.getUseAnimation(stack);
    }

    // 使用结束时处理：进食则应用食物效果并消耗耐久，否则原版盾牌结束
    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entity) {
        if (stack.hasTag() && stack.getTag().getBoolean("Eating")) {
            // 应用食物属性
            if (entity instanceof Player player) {
                FoodProperties food = this.getFoodProperties(stack, player);
                if (food != null) {
                    player.getFoodData().eat(food.getNutrition(), food.getSaturationModifier());
                    // 处理食物附带的效果
                    for (var pair : food.getEffects()) {
                        if (!world.isClientSide && pair.getFirst() != null && world.random.nextFloat() < pair.getSecond()) {
                            player.addEffect(new net.minecraft.world.effect.MobEffectInstance(pair.getFirst()));
                        }
                    }
                }
                // 播放进食音效
                player.playSound(SoundEvents.PLAYER_BURP, 0.5F, world.random.nextFloat() * 0.1F + 0.9F);
            }

            // 消耗1点耐久（被吃掉一口）
            stack.hurtAndBreak(1, entity, (e) -> e.broadcastBreakEvent(entity.getUsedItemHand()));

            // 清除进食标记
            stack.getTag().remove("Eating");
            if (stack.getTag().isEmpty()) {
                stack.setTag(null);
            }
            return stack;
        }

        // 非进食状态，走原版盾牌逻辑
        return super.finishUsingItem(stack, world, entity);
    }

    // 如果玩家在进食过程中松开右键，也清除标记，避免残留
    @Override
    public void releaseUsing(ItemStack stack, Level world, LivingEntity entity, int timeLeft) {
        if (stack.hasTag() && stack.getTag().contains("Eating")) {
            stack.getTag().remove("Eating");
            if (stack.getTag().isEmpty()) {
                stack.setTag(null);
            }
        }
        super.releaseUsing(stack, world, entity, timeLeft);
    }
}