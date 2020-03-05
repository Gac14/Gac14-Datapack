package tech.gac14.datapack.gens.loot;

import net.minecraft.entity.EntityType;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextComponent;
import net.minecraft.util.text.TextComponentUtils;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.functions.SetNBT;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.*;
import java.util.stream.Stream;

public class LootCategories {
    private static final Map<ResourceLocation,LootTable.Builder> categories = new HashMap<>();
    public static void registerLootCategory(ResourceLocation name,LootTable.Builder builder){
        categories.put(name,builder);
    }

    public static void registerLootCategories(BiConsumer<ResourceLocation, LootTable.Builder> cons){
        Spawners.init();
        categories.forEach(cons);
    }


}
