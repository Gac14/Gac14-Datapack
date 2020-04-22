package tech.gac14.datapack.gens.loot;

public class CrateSpec {
    private final LootCategory crate;
    private final LootCategory key;
    private final LootCategory loot;

    public CrateSpec(LootCategory crate, LootCategory key, LootCategory loot) {
        this.crate = crate;
        this.key = key;
        this.loot = loot;
    }

    public LootCategory getCrate() {
        return crate;
    }

    public LootCategory getKey() {
        return key;
    }

    public LootCategory getLoot() {
        return loot;
    }
}
