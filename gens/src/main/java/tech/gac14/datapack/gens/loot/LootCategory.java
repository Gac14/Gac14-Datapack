package tech.gac14.datapack.gens.loot;

import net.minecraft.world.storage.loot.LootEntry;

public interface LootCategory {
    public LootEntry.Builder<?> get();
}
