# Datapack Compatibility Features #
Various Features which were added in the 1.14 snapshots are added as datapack extensions in gac14. These features include entity_type tags, various loot table extensions, loot tables for the Wither, and player loot tables. 

## Entity Type Tags ##
In Minecraft 1.14 Entity types can recieve tags. In Gac14, this feature is implemented as an extension. Tags for entity types can be used in enchantments, in entity predicates, and in target selectors. 
Some mods may also reference specific entity tags. 

## Wither Loot Table ##
In Minecraft 1.13.1, the Wither entity has a fixed loot table, however this changed in Minecraft 1.14. 
In Gac14, withers get both a global loot table, and handling for the DeathLootTable tag. 

By default, withers generate there death loot using `gac14:entites/wither`. If a DeathLootTable NBT Tag is provided, then the loot table specified is used instead. 
If the specified loot table does not exist, and no DeathLootTable is provided, then it defaults to the vanilla (hardcoded) behavior. 

## Player Loot Tables ##

Players can now have loot tables, ish. By default, player loot is controlled by `gac14:entities/player`. However, if a player with uuid `<uuid>` is killed, and there is a loot table named `gac14:players/<uuid>`, then that loot table is used instead of the default loot table. 
Note that the default loot table is what causes the player to drop there inventory, so the specialized loot table should reference this in a separate pool, to prevent the inventory contents from being lost. 
If no general loot table or specialized loot table is provided for a player, then the vanilla (hardcoded) behavior is used instead. 
This may have unintended side effects with certain mods that may kill a player without dropping anything. 

## Loot Table Extensions ##
The following changes were made to loot tables:

Added the `dynamic` and `tag` entry types. 
`tag` entries generate an item specified by a tag with equal probability each. 
`dynamic` entries do something special. 
Presently there are 2 `dynamic` entries which are supported, placed in the `name` part of the entry. 
`content` is presently only used in player loot tables, and causes the player to drop the contents of their inventory, handling the keepInventory gamerule and Curse of Vanishing Items properly. 
`head` is used on any entity loot tables, and drops the player head for player loot tables, and the head associated with the entity for other entities. 

Block Loot Tables are neither supported, nor planned for 1.13 Gac14. 

## Enchantment Proposal ##
Enchantments in Datapacks are neither a confirmed nor planned feature for Minecraft 1.14. However, Gac14 will support custom enchantments based on the proposal at [Minecraft Proposals/Custom Enchantments](https://github.com/chorman0773/Minecraft-Proposals/tree/master/Custom%20Enchantments). 
The proposal is modified as follows:

* The vanilla enchantment tags specified are included in the gac14 namespace rather than the minecraft namespace. 
* The item slot tags are similarly included in the gac14 namespace rather than the minecraft namespace. 
* Enchantment Slots are not applicable on Gac14. Enchantments are allowed to provide a `enchanting_level_slot` as specified in the proposal, but they have no effect. 
* `max_table_level` is similarly useless, but still entirely valid. 
* The `none` trigger is not provided because the only custom enchantment that would use it (`gac14:tank`) is provided by alternative means. 
* Sequence Order is not guaranteed with Vanilla Enchantments, but is guaranteed with custom enchantments, including `gac14:tank`.
* As stated in the proposal, enchantment effects that run functions run as the sender as though the server used execute sudo as *user* *at* run function `<function>`. 



## Function Tweaks Proposal ##

A Future proposal that coaleces a couple of suggestions posted under commands at <feedback.minecraft.net> is implemented that tweaks functions. As well as enforces sequence order of certain situations. 

The gamerules *commandChainMax* and *functionCommandLimit* are merged into *commandChainMax*, and the upper-limit is removed (aside from the hard limit of Integer.MAX_VALUE/32-bit signed integer limit). 

If a command results in a function being run indirectly, it counts to the current command chain. This includes commands that trigger directly trigger advancements, enchantment effects, and loot table generation that specify running a function. 
If a command results in a function being run indirectly, the sequence order is well defined between other commands in the same chain. Commands run in different command chains within the same tick are unsequenced relative to each other, and as a result the sequence order is undefined between the chains. 

Note that commands that indirectly trigger an advancement, enchantment effect, or loot table generation result in functions that are run by those to be in a different command chain. However sequence order is well defined between these functions. The same applies for effects that in some way indirectly runs a function. 

### Handling Infinite Loops ###

An Infinite Loop is defined as a recursive execution of a function that nets no change in the game state. 

Reguardless of the source, these infinite loops are considered to be `dead` loops. The behavior of executing such a loop is undefined. 

Command chains are considered atomic operations. Observeble changes in game state caused by a command chain do not reflect other command chains, except chains that start execution after the first chain is completed, or when the chains have a well defined sequence order. 

For example, if a score is set by one command chain and read by annother that is executed without guaranteed sequencing, it is unspecified if the state of the score is the one prior to the first chain started, or after the first chain completed, but will never be an intermidiate state. This is the case even if the second command chain starts after the first one is executing. 

If sequencing is guaranteed, then the changes in game state caused by that command chain is given by the sequence guarantee. (Hense the fact that game state manipulations caused in one tick are guaranteed to be reflected in commands run the next tick). 

A command run in a chain is guaranteed in-line sequencing with other commands in the chain. All commands that preceed it in the chain will complete before that command is run, and all commands that follow the chain will not be run until that command completes. 

A separate chain run because of an indirect command source that results from an observeable effect of a command chain is sequenced after the command chain that caused it completes. 

A command chain is sequenced in order with all other command chains that execute in previous ticks and all command chains that will execute in subsequent ticks. 

Any command run by a client is sequenced after all command chains which have observable effects that are reflected in that client. Commands run by clients are indeterminately sequenced with respect to any command chains with observable effects that have not been reflected in the client (due to latency and other issues that can affect command timing). 


