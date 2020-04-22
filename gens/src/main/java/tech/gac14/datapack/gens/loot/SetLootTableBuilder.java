package tech.gac14.datapack.gens.loot;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.conditions.ILootCondition;
import net.minecraft.world.storage.loot.functions.ILootFunction;
import net.minecraft.world.storage.loot.functions.SetLootTable;

import java.util.LinkedList;
import java.util.List;

public class SetLootTableBuilder implements ILootFunction.IBuilder {
    private SetLootTableBuilder(ResourceLocation name){
        this.name = name;
    }
    private final List<ILootCondition.IBuilder> conditions = new LinkedList<>();
    private final ResourceLocation name;
    private long seed;

    public SetLootTableBuilder acceptCondition(ILootCondition.IBuilder builder){
        conditions.add(builder);
        return this;
    }

    public SetLootTableBuilder setSeed(long seed){
        this.seed = seed;
        return this;
    }

    public static SetLootTableBuilder builder(ResourceLocation name){
        return new SetLootTableBuilder(name);
    }

    @Override
    public ILootFunction build() {
        return new SetLootTable(conditions.stream().map(ILootCondition.IBuilder::build).toArray(ILootCondition[]::new),name,seed);
    }
}
