package tech.gac14.datapack.gens.tags;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.tags.Tag;
import net.minecraft.tags.TagCollection;
import net.minecraft.util.ResourceLocation;

import java.util.Optional;

public class EnchantmentTagsWrapper extends Tag<Enchantment> {
    public EnchantmentTagsWrapper(ResourceLocation resourceLocationIn) {
        super(resourceLocationIn);

    }
}
