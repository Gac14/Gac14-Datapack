# Commands [cmd]

Commands are constructs provided by the Gac14 Specification which allow for the datapack and/or users to interact with various specified concepts. 

## Subsections

* [[cmd.ban]](https://chorman0773.github.io/Gac14-Datapack/commands/BanCommands.md)
* [[cmd.chat]](https://chorman0773.github.io/Gac14-Datapack/commands/ChatCommands.md)
* [[cmd.eco]](https://chorman0773.github.io/Gac14-Datapack/commands/Economy.md)
* [[cmd.ctx]](https://chorman0773.github.io/Gac14-Datapack/commands/EleveatedContext.md)
* [[cmd.exec]](https://chorman0773.github.io/Gac14-Datapack/commands/ExecuteCommand)
* [[cmd.seq]](https://chorman0773.github.io/Gac14-Datapack/commands/SequenceOrderGuarantee)
* [[cmd.sys]](https://chorman0773.github.io/Gac14-Datapack/commands/SystemCommand)

## Failure to Execute [cmd.fail]

A Command is said to fail if it cannot, will not, or must not perform its prescribed action, 
usually either because of a error in the command's syntax, or because the context of the command 
does not subsume the permissions the action demands. 

If a Command would fail, then all side-effects of the command MUST NOT be observed. 
An error message, usually a meaningful one, SHOULD be returned to the user. 

If the success or result value of a failling command is consumed, 0 is consumed instead.
