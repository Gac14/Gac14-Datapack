# Dungeons [pack.dungeon] #

A Dungeon in Gac14 is defined as a resource location `<domain>:<path>`, which is tagged with at least one dungeon tag, and either `<domain>:dungeons/<path>` or `<domain>:dungeons/<path>/root` names a structure. 

## Data Structure Blocks [pack.dungeon.structext] ##

Dungeons provided custom functionality to Data Structure Blocks. This allows certain features of dungeons to work without additional descriptor files being required. 

### DungeonBoss [pack.dungeon.structext.boss] ###

When a `DungeonBoss` data structure block is encountered during dungeon generation, a marker for the dungeon boss is placed in that location, then the structure block is removed. 

### DungeonChest [pack.dungeon.structext.chest] ###

When a `DungeonChest` data structure block is encountered during dungeon generation, there shall be a container block directly below that structure block. 
That block has its LootTable set to the dungeon loot table associated with the dungeon rarity. Its LootTableSeed is set based on the world seed. 

Then the Structure Block is removed. 

### DungeonPart [pack.dungeon.structext.part] ###

When a `DungeonPart` data structure block is encountered during dungeon generation, there shall be a structure block directly adjacent to exactly one side of that structure block. 
Additionally that additional block shall be a Load Structure Block. The Load structure block is activated and generates the next part, then both structure blocks are removed (if they were not replaced by the generation). 


## Dungeon Rarities [pack.dungeon.rarity] ##

The configuration file `gac14/dungeons/rarities.json` describes the list of tags  which denote the rarity of dungeons. 

If a tag used to denote rarity is given as `<domain>:rarities/<rarity>`, then the loot tables are given below, using `<domain>` and `<rarity>`. 

The use of other tags to denote a dungeon rarity is conditionally supported. If unsupported, the rarity tag is simply ignored. Otherwise the loot tables used are implementation defined.

The base loot tables are as follows:
* The dungeon loot table is `<domain>:dungeon/<rarity>`
* The loot table used to generate the bosses equipment are given by `<domain>:bosses/<rarity>/<equipment_type>`. 


