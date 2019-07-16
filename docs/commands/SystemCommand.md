# System Command [cmd.sys]

The system command is an internal command that provides a bridge from the high-level function interface of the Gac14 Datapack to the low-level interfaces of Gac14 Mods. 

## Disclaimer 

_This section is for informative purposes only, and does not define any concepts, constructs, or contracts within the specification._

The command's documented here are intended soley for use within the Gac14 Datapack. 
Many commands will make assumptions about the context they are run in, and the arguments they receive and will usually not validate these assumptions (as such, if an assumption fails the result is usually undefined behavior). 
Additionally, these commands are subject to change without notice. As such, any person who uses the commands documented here does so AT THERE OWN RISK. No person who provided this documentation to you, or provided any mod that adds a subcommand, documented or not, to the system command, shall be held liable under any circumstances for any damage of any kind, direct or indirect, pertaining to your use and/or misuse of the system command. 

## Syntax [cmd.sys.syntax] 

```
/system <subcommand>
```

Executes an internal command from an implementation defined set of subcommands. The command is allowed to make any assumptions about it's use that it wants, and is not required to validate those assumptions. If any command is run and the assumptions it makes do not hold, the behavior is undefined. 

Asside from one subcommand (`/system doPrivileged <function>`), the command must be run in an elevated context or the command fails. It is unspecified if this function will appear in the help command. 

## Subcommands [cmd.sys.sub]

### doPrivileged [cmd.sys.sub.privileged] ##

```
/system doPrivileged <function>
```

Runs `<function>` in an elevated context. This function can be executed by any user. The execution of the comands in the function are guaranteed sequence order as though the command was `/function <function>`. 


`<function>` must be tagged with `#gac14:doPrivileged` or the command must be run in an elevated context otherwise the command fails. Note that some functions are unsuitable for being run in this manner. If `<function>` is a function tag and not all functions selected by the tag are also tagged with `#gac14:doPrivileged`, it is unspecified if any functions are run, which functions are run, or the command simply fails. If any functions are run, only the functions which could be run individually with this command may be run, but it is not required that all functions are required to run as such. 


All functions that are run by gac14 as a user are run as though by `/system doPrivileged <function>` and that `<function>` is tagged with `#gac14:doPrivileged`. This does not actually cause `<function>` to become tagged, and if it is not already tagged as such, external attempts to run via `/system doPrivileged` will fail. This is simply an exposition statement that defines that `<function>` is run in an elevated context. 

### stack [cmd.sys.sub.stack] ###

Manipulates the function stack. 
This command may only be run from within a function or the behavior is undefined. In addition, if a frame is pushed by a function, that frame must be popped before that function completes. 
Note that it does not need to be popped from within the function (it can be popped from a separate function, provided that that function executes before the completion of the function). 

```
/system stack push ... //(1)
/system stack push frame //(2)
/system stack pop //(3)
/system stack pop frame //(4)
/system stack get <n> //(5)
/system stack set <n> -> /system stack push //(6)
/system stack ... //(7)
```

1. pushes a value onto the stack. See below for valid subcommands
2. Pushes a frame onto the stack. /system stack get 0 now refers to the first value pushed in this frame. When this frame is popped, any values that were pushed onto the stack that have not been popped are. 
3. Pops a value from the stack. The result is the popped value. There must be at least one value in frame or the behavior is undefined. 
4. Pops a frame from the stack that was pushed with /system stack push frame
5. Gets the nth value in the stack frame. Negative values refer to values from a previous frame. 
6. Sets the value of the nth value on the stack. 
7. Executes a command that is sensitive to stack values.

#### Types of Values on the Stack [cmd.sys.sub.stack.types] ####

Each value on the stack may be one of the following:

* An entity specification in the form `<entity id> [tag]`
* An item specification in the form `<item id>[tag]`
* A block specification, in the form `<block id>[states][tag]`
* An entity reference, which is analougus to a target selector.
* A block reference, which refers to a block at a position.
* An item reference, which refers to an item in a block/entity
* A json text component, interpreted as though by `/tellraw`
* A number value
* A String value


All values can be printed in chat, possibly with a tooltip that appears when it hovered over (it is implementation defined for each type if the tooltip is shown and what gets shown):

* If the value is an item specification, block specification, or entity specification, prints the representation of that specification. It is implementation defined if any optional components are printed if that optional component is empty or the default.
* If the value is an entity reference, that refers to a player, prints the name of the the player.
* If the value is an entity reference that refers to any other entity, prints the uuid of the entity selected by the reference, followed by (in parentheses) the specification of the entity. 
* If the value is a block reference, prints the position of the block, followed by the block specification. 
* If the value is an item reference, prints the entity reference or block reference that the item is located in, the slot the item is located in, and the item specification. 
* If the value is a json text component, it prints as is
* If the value is a string, it is printed as though interpreted literally as the text of a json text component with no style or extra
* If the value is a number, it is printed as the value of the number. 

##### Reference Values [cmd.sys.sub.stack.types.ref] #####

Reference values 

#### stack push [cmd.sys.sub.stack.push] ####

```
/system stack push blockref <pos> //(1)
/system stack push entityref <selector> //(2)
/system stack push itemref entity <selector> <slot> //(3)
/system stack push itemref entity get <n> <slot> //(4)
/system stack push itemref block <pos> <slot> //(5)
/system stack push itemref block get <n> <slot> //(6)
/system stack push block <block> //(7)
/system stack push block get <n> //(8)
/system stack push entity <entity> [nbt] //(9)
/system stack push entity get <n> //(10)
/system stack push item <item> //(11)
/system stack push item get <n> //(12)
/system stack push text <text> //(13)
/system stack push 
```

#### stack get [cmd.sys.sub.stack.get] ####

```cpp
/system stack get <n>
```

Gets the value at the nth position on the stack. 
If n is positive, returns the nth value pushed onto the stack in the current frame (indexed from 0). 
If n is negative, returns the nth last value pushed onto the stack in the previous frame. 

If for some reason, the value on the stack cannot be accessed, then the behavior is undefined (ie., there is less than n values pushed onto the stack in the current frame, or there is not a previous frame for negative accessing). 

In any given subcommand of `/system stack`, if `get <n>` is a valid argument, it shall act as defined above, and use the value received as specified in the subcommand. 

When `/system stack get <n>` is used on its own, it displays the value returned as its result message. 

The result of the command depends on 


### player [cmd.sys.sub.player] ###

```
/system player <player> limits <key> set|add|subtract <value> (1)
/system player <player> actions <key> enable|disable (1)
/system player <player> reset (2)
```

(1): Sets a limit for a player, or enables/disables certain actions take by a player.

The keys that are defined are unspecified. If a key that does not exist is referenced or is referenced improperly (an action key used in the limits subcommand or vice versa), the behavior is undefined. 

Each limit key has an unspecified initial value, and may have an unspecified maximum and minimum. 
If a limit key would be set to a value above its maximum (if such exists), the key is set to the maximum value instead. 
If a limit key would be set to a value below its minimum (if such exists), the key is set to the minimum value instead. 
Each action key has an unspecified initial state. 

If an action affected by this command is concurrently taken by `<player>`, the behavior is undefined unless the action has guaranteed sequence order with this command. 

Use of the `enable` verb of the actions subcommand synchronizes with any use of the `disable` verb for the same key on `player` and vice versa. Additionally, use of the actions subcommand for a given `<key>` synchronizes with the player action associated with `<key>` taken by `<player>` (this results in indeterminate sequence order even if the associated effects are unsequenced, with an unspecified sequencing guarantee, this allows for you to modify with limits command without fear of invoking undefined behavior). 


(2): Clears a player's inventory, ender-chest, player vaults (if they exist), removes the player from all groups, removes all permissions, deletes the associated spawn point etc., and executes any other unspecified actions. The player is then killed if online ignoring all death triggers and player loot tables.
If the player is offline, this is equivalent to deleting the player.dat file and associated player information folder. 

If this function is run as `<player>` the behavior is undefined. 


### Additional Subcommands [cmd.sys.sub.additional] ###

Mods may add additional, implementation-defined subcommands to the system command. 

