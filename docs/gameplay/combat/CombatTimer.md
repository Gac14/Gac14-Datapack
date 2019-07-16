# Combat Timer [concepts.combat.timer]

The Combat Timer is a construct which imposes requirements on players actively engaged in PvP or PvE. It is OPTIONAL to provide. 

A player that disconnects from the server while in combat, is immediately killed unless that player is invulnerable or in creative or spectator mode. 
How the player is killed, and the message displayed in chat, if any, are both implementation-defined (it is expected that implementations provide these as a configuration point). 

Certain commands and actions which may otherwise be accessible to a certain player may be unavailable to that player while in combat. 
If a player has started a teleport, entering combat will cancel that teleport (this is a rare occurance, as most things that can put a player in combat will cancel a teleport anyways)

A player with a combat timer that has a non-zero length 

## Putting a Player In Combat [concepts.combat.timer.enter]

A Player is Put into combat when the player is not in combat, and one of the following events occurs:
* The player is dealt damage
* The player deals damage to another player.
* A Game Event forces the player to enter combat
* A command explicit sets the combat time for a player, which was not previously in combat, to a non-zero number of ticks.

When a player is put into combat, their combat timer is set to an implementation-defined number of ticks, unless explicitly set by a command. If one of these events occurs while the player is in combat, it is implementation-defined whether the timer is modified, except that commands that modify combat time MUST be observed. (It is intended that when such an event occurs, the timer is prolonged by some value). 
Implementations SHOULD inform players when they enter combat. (It is expected that the message sent to players, and the length of the timer are both provided as configuration points). 

A player leaves combat after their combat timer has expired, or when a command explicitly sets the combat time for that player to zero ticks. 

Combat Timers are MAY be processed either according to server time or real time. It is implementation-defined which one applies. 



