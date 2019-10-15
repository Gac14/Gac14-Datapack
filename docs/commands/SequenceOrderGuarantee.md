# Command Sequence Order [cmd.seq]

In Minecraft Sequence Order of commands is unclear and primarily has no guarantees of any kind. 
Some commands are inheritly racy with other aspects of minecraft. 
For example, the give command can potentially lead to a race-condition if a player grabs an item from a chest and fills there inventory at the exact same time as the give command executes. 
A race-condition leads to undefined behavior, which in this case usually means possibly losing one or both of the items. 

In gac14, a future proposal is adopted to make everything nice and well defined, or at least explain when things can blow up in your face. (Undefined Behavior) 

## Observeable Behavior [cmd.seq.observe]

Commands, as well as command chains have a set of behavior that is observeable. 

The following actions consitute the Observeable behavior of a command:

1. Summoning an entity by that command
2. Placing a block or replacing an existing block with a new one.
3. Adjusting/Reseting the scoreboard of any entity
4. Killing an entity
5. Destroying an entity
6. Adding items to a players inventory, or the inventory of any entity or block or modifying such an item
7. Removing an item from a players inventory, or the inventory of an entity or block.
8. Changing the Block state of any block
9. Awarding or revoking any advancement
10. Creating a new scoreboard team, objective, or tag
11. Tagging any entity, or adding an entity to a scoreboard team
12. Changing the properties of any team
13. Changing any active scoreboard display
14. Adding a player to or removing a player from a group.
15. Modifying the players information table
16. Outputting a message in any channel, except gac14:book or any channel with no online members. 

The observeable behavior of a command chain is the net observeable behavior of each command in the chain. 
Note that only the observeable behavior of a command chain is required to be preserved. 
A command that would not affect the observeable behavior of a command need not be run or may be substituted with annother command with the same observeable behavior. 

For example, if a command chain summons a zombie, then subsequently kills that zombie, the zombie does not ever need to be summoned, instead simply its loot table (minecraft:entities/zombie) may be dropped on the ground. 
If the loot would not be dropped or would subsequently be killed, it may never even drop the loot. 

If a command run in a command chain has undefined behavior, then the observeable behavior of the entire chain does not need to be preserved. 

The observeable behavior of a command does not have to reflect any command which does not have guaranteed sequence order with that command. 

## Command Chain [cmd.seq.chain]
This document defines a single entity, called a command chain, which describes a sequence of commands run either within a function, or through chain command blocks. 
All commands run in a command chain have a guaranteed sequence order, defined as follows:

* For chain command blocks, the nth command block in the chain is sequenced after the command run by the nth-1 command block in the chain (if such a command block exists), and sequenced before the nth+1 command block in the chain (if such a command block exists. 
* For functions, the nth command written in that function is sequenced after the nth-1 command written in that function (if such a command exists), and is sequenced before nth+1 command written in that function.
* For the function command, the first command in the function run via the command is sequenced after all commands that the function command that runs it is sequenced after, and the last command run by the function is sequenced before any command that the function command that the function is sequenced after. 

The maximum number of commands that can be run as part of a single chain is defined by the gamerule maxCommandChainLength. If this gamerule is set to 0, an unspecified default is used instead. This gamerule has an unspecified maximum value that is at least 65536. 

If a command chain contains a command that would rerun a command in that chain, in such a way that any portion of the command chain would run without completion, the behavior is undefined. 

If a command run as part of a chain would start another command chain indirectly, such as via an advancement reward, then that command is part of a different chain. If this would result in the chain being run endlessly, without termination, the behavior is undefined. The new command chain is sequenced after the command that starts the chain, and unsequenced with all other commands in the chain. 

If a command, except one that would run a function, is run on its own from the server console, or a player entering the command in chat, it is run as part of a command chain of 1 command. It is unsequenced with every other command run this way. 

If a command is run from the server side (from the console, advancement, function, etc.) that runs during any given tick, it is guaranteed to be sequenced-after any command run in a previous tick, and sequenced-before any command run in a subsequent tick, notwithstanding the above rules

If a command is run from the client side (by a player, or through some player interaction that causes a command to be run on the server side), it is guaranteed to be sequenced after all commands which the observable side-effects of which can be observed by the player before the command is run. The command is indeterminately sequenced with every other command chain. 

All commands in a single command chain shall run in the same tick as the chain was started on the server. 

### Command Blocks [cmd.seq.chain.block]

If a command block, except a chain command block, in the world would start being active during a tick, the command chain that results from it is run that tick. Otherwise a command chain is not started for that command block, except for repeating command blocks. 

If a repeating command block is activate at the start of a tick and would not stop being activate during that tick, the command chain that results from it is run that tick. Otherwise, if the command block would not start being activate that tick, a command chain is not started for that command block. 


A Chain Command block that is being pointed-to by a command block that would run a command as part of a command-chain will run its command as part of the same chain, and is sequenced after the command run by that command block (called the previous command block), if it is activate or would start being activate that tick and it would not stop being active that tick. Otherwise the command chain terminates after the previous command block's chain. 

If the observable behavior of the command run by a command block would cause it to become inactive during that tick, the behavior is undefined. 



## Synchronization [cmd.seq.synchronize]

Certain actions synchronize-with certain other actions, if at least one action involved is caused as part of a command. (It is unspecified if this synchronization occurs in other circumstances). 

If 2 actions occur that synchronize-with each other, it is guaranteed to be sequenced either before or after that action. 
The action that is sequenced before will have its observeable behavior reflected by the action that is sequenced after. 
It is unspecified on a case-by-case basis which action occurs first. 

If more than 2 actions occur that synchronize-with each other, the actions execute as though the first actions synchronizes-with the bulk action consisting of each other action. 
Then the actions occuring in the bulk action become ordered as such.  

Synchronization only occurs if multiple actions that synchronize-with each other occur without guaranteed sequence order. Synchronization guarantees sequence order.  

If synchronization would occur, then that synchronization is part of the observeable behavior of a command. 
Observeable behavior of that such a command may not be elided, even if other commands in the chain would undo that behavior. 

For example, the give command synchronizes with any attempt to modify a players inventory. 
If a player grabs an item in the inventory concurrently with a give command, either grabbing the item occurs first or the give command occurs first. 

The observeable behavior of either action affects the action that did not occur first. 

