#Datapack Compatibility Features#
Various Features which were added in the 1.14 snapshots are added as datapack extensions in gac14. These features include entity_type tags, various loot table extensions, loot tables for the Wither, and player loot tables. 

##Entity Type Tags##
In Minecraft 1.14 Entity types can recieve tags. In Gac14, this feature is implemented as an extension. Tags for entity types can be used in enchantments, in entity predicates, and in target selectors. 
Some mods may also reference specific entity tags. 

##Wither Loot Table##
In Minecraft 1.13.1, the Wither entity has a fixed loot table, however this changed in Minecraft 1.14. 
In Gac14, withers get both a global loot table, and handling for the DeathLootTable tag. 

By default, withers generate there death loot using `gac14:entites/wither`. If a DeathLootTable NBT Tag is provided, then the loot table specified is used instead. 
If the specified loot table does not exist, and no DeathLootTable is provided, then it defaults to the vanilla (hardcoded) behavior. 

##Player Loot Tables##
Players can now have loot tables, ish. By default, player loot is controlled by `gac14:entities/player`. However, if a player with uuid `<uuid>` is killed, and there is a loot table named `gac14:players/<uuid>`, then that loot table is used instead of the default loot table. 
Note that the default loot table is what causes the player to drop there inventory, so the specialized loot table should reference this in a separate pool, to prevent the inventory contents from being lost. 
If no general loot table or specialized loot table is provided for a player, then the vanilla (hardcoded) behavior is used instead. 
This may have unintended side effects with certain mods that may kill a player without dropping anything. 

##Loot Table Extensions##
The following changes were made to loot tables:

Added the `dynamic` and `tag` entry types. 
`tag` entries generate an item specified by a tag with equal probability each. 
`dynamic` entries do something special. 
Presently there are 2 `dynamic` entries which are supported, placed in the `name` part of the entry. 
`content` is presently only used in player loot tables, and causes the player to drop the contents of their inventory, handling the keepInventory gamerule and Curse of Vanishing Items properly. 
`head` is used on any entity loot tables, and drops the player head for player loot tables, and the head associated with the entity for other entities. 

Block Loot Tables are neither supported, nor planned for 1.13 Gac14. 

##Enchantment Proposal##
Enchantments in Datapacks are neither a confirmed nor planned feature for Minecraft 1.14. However, Gac14 will support custom enchantments based on the proposal at [Minecraft Proposals/Custom Enchantments](https://github.com/chorman0773/Minecraft-Proposals/tree/master/Custom%20Enchantments). 
The proposal is modified as follows:

* The vanilla enchantment tag (`minecraft:curse`) specified is included in the gac14 namespace rather than the minecraft namespace. 
* The item slot tags are similarly included in the gac14 namespace rather than the minecraft namespace. 
* Enchantment Slots are not applicable on Gac14. Enchantments are allowed to provide a `enchanting_level_slot` as specified in the proposal, but they have no effect. 
* `max_table_level` is similarly useless, but still entirely valid. 



