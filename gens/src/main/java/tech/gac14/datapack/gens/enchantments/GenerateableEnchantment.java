package tech.gac14.datapack.gens.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EquipmentSlotType;

import java.util.List;
import java.util.Set;

public class GenerateableEnchantment extends Enchantment {
    public static class Builder{

    }
    private GenerateableEnchantment(Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType[] slots) {
        super(rarityIn, typeIn, slots);
    }
}
