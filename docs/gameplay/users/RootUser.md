# Root User [concepts.users.root]

The Root User, referred to in this specification as root, is a symbolic user with the name `internal//root` and that has the UUID `00000000-0000-0000-0000-000000000000`. 

## Handling of Commands by Root [concepts.user.root.cmdhandle]

All commands issued to the server by the root user SHALL be run in an elevated context. 
These commands shall meet all permission level requirements `[cmd.permission.legacy]`, and all named permission requirements `[cmd.permission.req]`. 
