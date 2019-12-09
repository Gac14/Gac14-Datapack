# Worldguard Principal [concepts.worldguard.principal]

A Worldguard Principal is some entity which can be resolved to a set of objects. 
A Principal is said to match an object if that object is a member of the principal's associated set. 

## Principal Provider [concepts.worldguard.principal.provider]

A Principal Provider is named entity which can provide principals. A Principal is Provided by a given Principal Provider if that provide elects to define its properties, serialized form, and content. 

There are several predefined providers in the gac14 specification, most will be defined in there own section. 

## User Principal [concepts.worldguard.principal.user]

A User Principal is a principal for which the associated set of players contains 0 or 1 players. 

A User Principal symbolically contains a reference to the player it matches. The serialized form of a user principal is the binary representation of the UUID of the player. 

There is a special user principal which contains no such reference and does not match any players. The serialized form of this specific user principal is the nil uuid (00000000-0000-0000-0000-000000000000). It can also be said that this principal matches `root` or the server. 

The name of the provider for user principals is `gac14:worldguard/user`. 

