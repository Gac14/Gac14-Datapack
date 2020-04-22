package tech.gac14.datapack.gens.loot;

import net.minecraft.world.storage.loot.LootEntry;

import java.util.function.Supplier;

@FunctionalInterface
public interface LootCategory extends Supplier<LootEntry.Builder<?>> {

}
