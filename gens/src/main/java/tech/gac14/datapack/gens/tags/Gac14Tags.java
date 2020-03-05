package tech.gac14.datapack.gens.tags;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

public class Gac14Tags{
    public static class Blocks{
        private static Tag<Block> tag(String name){
            return new BlockTags.Wrapper(new ResourceLocation("gac14",name));
        }

        public static Tag<Block> ORE = tag("ore");
        public static class Raids{
            public static Tag<Block> DAMAGEABLE = tag("raids/damageable");
            public static Tag<Block> BEDROCK = tag("raids/bedrock");
        }
    }

    public static class Items{
        private static Tag<Item> tag(String name){
            return new ItemTags.Wrapper(new ResourceLocation("gac14",name));
        }
        private static Tag<Item> forge_tag(String name){
            return new ItemTags.Wrapper(new ResourceLocation("forge",name));
        }
        public static class ForgeHelpers{
            public static final Tag<Item> ARMOR_BOOTS = forge_tag("armor/boots");
            public static final Tag<Item> ARMOR_LEGGINGS = forge_tag("armor/leggings");
            public static final Tag<Item> ARMOR_CHESTPLATES = forge_tag("armor/chestplates");
            public static final Tag<Item> ARMOR_HELMETS = forge_tag("armor/helmets");
            public static final Tag<Item> ARMOR = forge_tag("armor");

            public static final Tag<Item> TOOLS_AXES = forge_tag("tools/axes");
            public static final Tag<Item> TOOLS_HOES = forge_tag("tools/hoes");
            public static final Tag<Item> TOOLS_PICKAXES = forge_tag("tools/pickaxes");
            public static final Tag<Item> TOOLS_SHOVELS = forge_tag("tools/shovels");
            public static final Tag<Item> TOOLS_SHEARS = forge_tag("tools/shears");
            public static final Tag<Item> TOOLS = forge_tag("tools");

            public static final Tag<Item> WEAPONS_SWORDS = forge_tag("weapons/swords");
            public static final Tag<Item> WEAPONS_BOWS = forge_tag("weapons/bows");
            public static final Tag<Item> WEAPONS_RANGED = forge_tag("weapons/ranged");
            public static final Tag<Item> WEAPONS_TRIDENTS = forge_tag("weapons/tridents");
            public static final Tag<Item> WEAPONS = forge_tag("weapons");

            public static final Tag<Item> ELYTRA = forge_tag("elytra");
        }

        public static final Tag<Item> HEAD = tag("head");
        public static final Tag<Item> HEADGEAR = tag("headgear");
        public static final Tag<Item> WEARABLE = tag("wearable");
        public static final Tag<Item> DAMAGABLE = tag("damagable");

        public static class Eco{
            public static final Tag<Item> AH_BLACKLIST = tag("eco/ah_blacklist");
            public static final Tag<Item> FIXED_EXCHANGE = tag("eco/fixed_exchange");
        }
    }

    public static class EnchantmentTags{
        private static Tag<Enchantment> tag(String name){
            return new EnchantmentTagsWrapper(new ResourceLocation("gac14",name));
        }
    }
}
