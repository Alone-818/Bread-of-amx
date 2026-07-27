package Alone818.com.bread_of_amx.itemcuiros;

import Alone818.com.bread_of_amx.init.Modfoods;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;  // 导入正确接口

import java.util.UUID;

public class driest_bread extends Item implements ICurioItem {  // 实现 ICurioItem

    public driest_bread(Properties properties) {
        super(properties);
        properties.food(Modfoods.DRIESTBREAD);
    }

    // 1️⃣ 核心 Tick 逻辑（每帧执行，已匹配方法签名）
    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        System.out.println("onEquip called for " + stack.getDisplayName().getString());
        // ... 原有代码
    }
    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modifiers = HashMultimap.create();
        // 添加护甲值（例如 +5 点护甲）
        modifiers.put(Attributes.ARMOR, new AttributeModifier(uuid, "driest_bread_armor", 5.0, AttributeModifier.Operation.ADDITION));
        // 可选：添加盔甲韧性（例如 +2 点韧性）
        modifiers.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(uuid, "driest_bread_toughness", 2.0, AttributeModifier.Operation.ADDITION));
        return modifiers;
    }
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        this.equipmentTick(slotContext.entity());
        // ... 原有代码
    }
    protected void equipmentTick(LivingEntity livingEntity) {
        livingEntity.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 400, 0, false, false, true));
    }

    // 其他可选重写：onUnequip, canEquip, canUnequip 等
}