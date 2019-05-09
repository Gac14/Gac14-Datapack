# Execute Command Modifications

Gac14 expands upon the vanilla execute command, provided by Minecraft, in order to enable the manipulation of permissions provided by Gac14. 

None of Minecraft vanilla subcommands of the execute command are removed by these modifications. 

/execute must be run with minecraft.generic.execute or the command fails. 

## Additional Subcommands

```
/execute sudo -> /execute (1)
/execute with permissions <permission list...> -> /execute (2)
/execute with permissions of <entity selector>|G:<group name> -> /execute (3)
/execute as root|00000000-0000-0000-0000-000000000000 -> /execute (4)
/execute if|unless <entity> has <permission> -> /execute (5)
/execute if|unless <entity> is <group> -> /execute (6)
```

1. The sender must be a member of gac14:sudo or the command must be run in an elevated context. The remainder of the command is run in an elevated context, unless a new context is introduced.
2. If no restricted context was introduced by any previous chained subcommands, then the remainder of the command is run in a restricted context, where the permissions that apply to the context are given by <permission list...>. Otherwise, adds the permissions given by <permission list...> to the list of permissions that apply. If for any permission in the list, an imaginary command called `/test` were to be introduced, which succeeds unconditionally and requires that permission, running that command in the context of the current command, or an elevated context if a previous subcommand of the current command was sudo, would fail, then the command fails.
3. Same as (2), except the permissions list is obtained from the permissions that *entity selector* has, or that are implied by G:*group name*.
4. Runs the command as the root principal. Does not alter permissions. This subcommand must be run in an elevated context or the command fails. 
5. If an imaginary command called `/test` were introduced, which succeeds unconditionally, and requires *permission*, run in the context of *entity* or the current subcommand if *entity* is @s would fail, the remainder of the command is(for if) or is not(for unless) ignored. 
6. If an imaginary command called `/test` were introduced, which succeeds unconditionally, and requires the sender to be a member of *group*, run in the context of *entity* or the current subcommand if *entity* is @s would fail, the remainder of the command is(for if) or is not(for unless) ignored.

 




