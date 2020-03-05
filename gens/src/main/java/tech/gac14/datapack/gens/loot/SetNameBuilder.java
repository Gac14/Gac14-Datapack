package tech.gac14.datapack.gens.loot;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.conditions.ILootCondition;
import net.minecraft.world.storage.loot.functions.ILootFunction;
import net.minecraft.world.storage.loot.functions.SetName;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class SetNameBuilder implements ILootFunction.IBuilder {

    private final ITextComponent name;
    private List<ILootCondition.IBuilder> conditions = new ArrayList<>();
    private LootContext.EntityTarget entity;

    private SetNameBuilder(@Nonnull ITextComponent name){
        this.name = name;
    }

    public static SetNameBuilder builder(ITextComponent name){
        return new SetNameBuilder(name);
    }

    public SetNameBuilder addCondition(ILootCondition.IBuilder builder){
        conditions.add(builder);
        return this;
    }

    public SetNameBuilder setEntityTarget(LootContext.EntityTarget target){
        this.entity = target;
        return this;
    }

    @Override
    public ILootFunction build() {
        return new SetName(conditions.stream().map(ILootCondition.IBuilder::build).toArray(ILootCondition[]::new),name,entity);
    }
}
