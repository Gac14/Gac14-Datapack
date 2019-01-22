# Advancement Triggers [pack.ext.advance] #
Minecraft 1.12 added advancements, which are basically event handlers. They replaced the achievement system, and were fully extensible. 

Several triggers are available in vanilla, and Gac14 adds some more to interact with mod components. All Custom triggers are in the gac14 namespace. 

## Magic [pack.ext.advance.magic] ##

Added By: Gac14 Magic

### Cast Spell [pack.ext.advance.magic.cast] ###

`gac14:magic/cast_spell`

Triggered whenever a player casts a spell. 

Available Conditions:


* conditions:
    * caster (object): Checks the spellcaster before the spell was cast (tags common to all spellcasters)
        * mana (number): Checks the mana the spellcaster had before casting the spell 
        * mana (object): Checks the mana the spellcaster had before casting the spell
            * min (number): The minimum mana the spellcaster had
            * max (number): The maximum mana the spellcaster had
        * manaRegen (number): Checks the mana regen rate of the spellcaster
        * manaRegen (object): Checks the mana regen rate of the spellcaster
            * min (number): The minimum mana regen rate of the spellcaster
            * max (number): The maximum mana regen rate.
        * species (string): The species the caster chose or a tag that matches a set of species
        * species (list): A list of species names or tags. Matches if the caster is at least one species in this list
        * class (string): The class the caster chose or a tag that matches a set of classes
        * class (list): A list of class names or tags.
        * entity (object): Checks the entity that is bound to this caster (tags common to all entities)
        * level (integer): The magic level of the spellcaster before the spell was cast
        * level (object): The magic level of the spellcaster before the spell was cast
            * min (integer): The minimum level
            * max (integer): The maximum level
    * spell (object): Checks the spell that was cast (tags common to all spells)
        * spell (string): The name of the spell that was cast or a tag
        * spell (list): A list of spell names or tags.
        * warmup (number): The time (in ticks) the spell takes to warm up.
        * warmup (object): The time (in ticks) the spell takes to warp up.
            * min (number): The minimum time
            * max (number): The maximum time
        * cooldown (number): The time (in ticks) the spell takes to cool down.
        * cooldown (object): The time (in ticks) the spell takes to cool down
            * min (number): The minimum time
            * max (number): The maximum time
        * delay (number): The time (in ticks) before the caster can cast spells with the same name (id) again
        * delay (object): The time (in ticks) before the caster can cast spells with the same name (id) again
            * min (number): The minimum time
            * max (number): The maximum time
         * manaCost (number): The mana that the spell cost
         * manaCost (object): The mana that the spell cost
            * min (number): The minimum cost
            * max (number): The maximum cost
         * manaCostF (number): The ratio between the mana cost and the caster's maximum mana
         * manaCostF (object): The ratio between the mana cost and the caster's maximum mana
             * min (number): The minimum cost ratio
             * max (number): The maximum cost ratio
    * affinity (number): The spellcaster's affinity to the spell
    * affinity (object): The spellcaster's affinity to the spell
        * min (number): The minimum affinity
        * max (number): The maximum affinity
    * remainingMana (number): The mana value remaining for the spellcaster
    * remainingMana (object): The mana value remaining for the spellcaster
        * min (number): The minimum mana remaining
        * max (number): The maximum mana remaining
    * remainingManaF (number): The mana percentage remaining for the spellcaster
    * remainingManaF (number): The mana percentage remaining for the spellcaster
        * min (number): The minimum mana percentage remaining
        * max (number): The maximum mana percentage remaining
    * manaDelta (number): The ammount of mana the spell actually cost
    * manaDelta (object): The ammount of mana the spell actually cost
        * min (number): The minimum delta
        * max (number): The maximum delta
    * manaDeltaF (number): The ratio between the actually cost and the caster's maximum mana
        * min (number): The minimum delta ratio
        * max (number): The maximum delta ratio
    
### Activate Spell [pack.ext.advance.magic.active] ###

`gac14:magic/spell_activates`

Triggers when a spell's `on_activate` trigger applies. 

* conditions (object): Available conditions
    * caster (object): The spellcaster (tags common to all spellcasters)
    * spell (object): The spell that was cast
    * affectedBlocks (list): A list of blocks that were affected by the spell, before its effect is applied. All blocks in this list must be affected
       * (a block state) (object): An affected block (tags common to all block states)
    * affectedEntities (list): A list of entities that were affected by the spell, before its effect is applied. All entities in this list must be affected
       * (an entity) (object): An affected entity (tags common to all entities)
    
    
## Dungeons [pack.ext.advance.dungeon] ##

### Conquer Dungeon [pack.ext.advance.dungeon.defeat] ###

Checked when a player leaves the dungeon Bounding Box after the boss is spawned, as long as the player was within a 20-block radius sphere from the boss when it was initially spawned, and also within the dungeon's bounding box, and at least 2 of the following is true:

* The player spawned the dungeon boss
* The player dealt damage to the dungeon boss
* The player was dealt damage by the dungeon boss
* The player opened at least 1 dungeon chest since the dungeon boss was spawned
* The player is a friend of a player for which this trigger is checked, for that particular dungeon.
 


    
    
    