# Permission Entry Manager [cmd.pem]

The Permission Entry Manager is a command which can be used to manipulate the Gac14 Permission System. 

## Command Syntax [cmd.pem.syntax]

```
/pem <object>
```

### Permission Manipulation

```
/pem <object> join <group> (1)
/pem <object> leave <group> (2)
```

1. Causes *object* to join *group*. After this command *object* will have membership in *group*. 
2. Causes *object* to leave *group*. After this command *object* will not have membership in *group*, unless implied by a different group *object* has membership in. 



