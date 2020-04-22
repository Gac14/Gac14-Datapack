package tech.gac14.datapack.gens.loot;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextComponent;
import net.minecraft.world.storage.loot.ItemLootEntry;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.TableLootEntry;
import net.minecraft.world.storage.loot.functions.SetLootTable;
import net.minecraft.world.storage.loot.functions.SetNBT;

import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class Crates {
    public static CrateSpec makeCrate(ResourceLocation rarityType, Item keyItem, ITextComponent rarityName, UnaryOperator<ITextComponent> formatter, LootTable.Builder lootBuilder){
        ResourceLocation crate = new ResourceLocation(rarityType.getNamespace(),String.join("/","loot","categories","crates",rarityType.getPath()));
        ResourceLocation key = new ResourceLocation(rarityType.getNamespace(),String.join("/","loot","categories","keys",rarityType.getPath()));
        ResourceLocation loot = new ResourceLocation(rarityType.getNamespace(),String.join("/","crate",rarityType.getPath()));
        ITextComponent keyName = formatter.apply(rarityName.shallowCopy()
                .appendSibling(new StringTextComponent(" Crate Key"))).appendSibling(new StringTextComponent("\u200b"));
        ITextComponent crateName = formatter.apply(rarityName.shallowCopy()
                .appendSibling(new StringTextComponent(" Crate"))).appendSibling(new StringTextComponent("\u200b"));
        LootCategory lootTable = ()-> TableLootEntry.builder(loot);
        CompoundNBT tag = new CompoundNBT();
        tag.putString("CrateType",rarityType.toString());
        LootCategories.registerLootCategory(key,LootTable.builder()
                .addLootPool(LootPool.builder()
                .addEntry(ItemLootEntry.builder(()->keyItem)
                .acceptFunction(SetNBT.func_215952_a(tag))
                .acceptFunction(SetNameBuilder.builder(keyName))
                        )));

        CompoundNBT entity = new CompoundNBT();
        entity.putString("Lock",keyName.getString());
        tag.put("TileEntityData",entity);
        LootCategories.registerLootCategory(crate,LootTable.builder()
                .addLootPool(LootPool.builder()
                        .addEntry(ItemLootEntry.builder(()-> Items.CHEST)
                            .acceptFunction(SetNBT.func_215952_a(tag))
                            .acceptFunction(SetLootTableBuilder.builder(loot))
                            .acceptFunction(SetNameBuilder.builder(crateName))
                        )));
        LootCategories.registerLootCategory(loot,lootBuilder);
        return new CrateSpec(()-> TableLootEntry.builder(crate),()-> TableLootEntry.builder(key),lootTable);
    }
}
