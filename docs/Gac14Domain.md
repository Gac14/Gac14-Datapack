# Gac14 Domain [gac14]

## Definition of the Gac14 Domain [gac14.def]

The Gac14 Domain (`gac14`) is a prefix applied to object identifiers for objects defined by either the Gac14 Datapack 
or the Gac14 Specification. 

The Gac14 Domain is owned by both the Gac14 Datapack and the Gac14 Specification. 
If a game modification, except for one which partially or fully implements the Gac14 Specification, 
or a Datapack except for the Gac14 Datapack adds entries in the Gac14 Domain, the behavior is undefined. 

Notwithstanding the above point,
 datapacks other than the Gac14 Data may replace or define any LootCategory Loot Table, 
 and may append to tags in the gac14 domain. 

(Replacing a Loot Table that is not a LootCategory
 or replacing any tag in the gac14 domain outside of the Gac14 Datapack is undefined behavior.
 There are limitations to datapacks replacing LootCategory Loot Tables, as well as defining new ones)

Game modifications may enclose internal datapacks. 
Such modifications MUST NOT append to the function tag `gac14:doprivilaged`.



## Reserved Identifiers [gac14.res]

All identifiers with a path component that starts with `__` in the `gac14` domain are reserved identifiers. 
They MUST NOT be accessed in userspace (via commands or similar interactions). 
If such a reserved identifier is accessed in this manner, the behavior is undefined. 

