# System Command #

The system command is an internal command that provides a bridge from the high-level function interface of the Gac14 Datapack to the low-level interfaces of Gac14 Mods. 

## Disclaimer ##
The command's documented here are for use within the Gac14 Datapack. They are for expert use only. 
Many commands will make assumptions about the context they are run in, and the arguments they receive and will usually not validate these assumptions. 
Additionally, these commands are subject to change without notice. As such, any person who uses the commands documented here does so AT THERE OWN RISK. No person who provided this documentation to your, or provided any mod that adds a subcommand, documented or not, to the system command, shall be held liable for any damage of any kind under any circumstances, direct or indirect, pertaining to your use and/or misuse of the system command. 

## Syntax ##

```
/system <subcommand>
```

Executes an internal command from an implementation defined set of subcommands. The command is allowed to make any assumptions about it use that it wants, and is not required to validate those assumptions. If any command is run and the assumption does not hold, the behavior is undefined. 

Asside from one subcommand (`/system doPrivileged <function>`), the command must be run in an elevated context or the command fails. It is unspecified if this function will appear in the help command. 

## Subcommands ##

### doPrivileged ##

```
/system doPrivileged <function>
```

Runs `<function>` in an elevated context. This function can be executed by any user. The execution of the comands in the function are guaranteed sequence order as though the command was `/function <function>`. 


`<function>` must be tagged with `#gac14:doPrivileged` or the command must be run in an elevated context otherwise the command fails. Note that some functions are unsuitable for being run in this manner. If `<function>` is a function tag and not all functions selected by the tag are also tagged with `#gac14:doPrivileged`, it is unspecified if any functions are run, which functions are run, or the command simply fails. If any functions are run, only the functions which could be run individually with this command may be run, but it is not required that all functions are required to run as such. 


All functions that are run by gac14 as a user are run as though by `/system doPrivileged <function>` and that `<function>` is tagged with `#gac14:doPrivileged`. This does not actually cause `<function>` to become tagged, and if it is not already tagged as such, external attempts to run via `/system doPrivileged` will fail. This is simply an exposition statement that defines that `<function>` is run in an elevated context. 

### stack ###

Manipulates the function stack. 
This command may only be run from within a function or the behavior is undefined. In addition, if a frame is pushed by a function, that frame must be popped before that function completes.

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

#### Types of Values on the Stack ####

Each value on the stack may be one of the following:

* An entity specification in the form `<entity id> [tag]`
* An item specification in the form `<item id>[tag]`
* A block specification, in the form `<block id>[states][tag]`
* An entity reference, which is analougus to a target selector.
* A block reference, which refers to a block at a position.
* An item reference, which refers to an item in a block
* A json text component, interpreted as though by `/tellraw`
* A number value
* A String value

All values can be printed in chat, possibly with a tooltip that appears when it hovered over (it is implementation defined for each type if the tooltip is shown and what gets shown):

* If the value is an item specification, block specification, or entity specification, prints the form representation of the block. It is implementation defined if any optional components are printed if that optional component is empty or the default.
* If the value is an entity reference, that refers to a player, prints the name of the the player.
* If the value is an entity reference that refers to any other entity, prints the uuid of the entity selected by the reference, followed by (in parentheses) the specification of the entity. 
* If the value is a block reference, prints the position of the block, followed by the block specification. 
* If the value is an item reference, prints the entity reference or block reference that the item is located in, the slot the item is located in, and the item specification. 
* If the value is a json text component, it prints as is
* If the value is a string, it is printed as though interpreted literally as the text of a json text component with no style or extra
* If the value is a number, it is printed as the value of the number. 




