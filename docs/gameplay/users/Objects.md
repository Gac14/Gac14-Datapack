# Objects [concepts.users.obj]

An Object is something that can be manipulated by Gac14 Commands, and specifically, can have membership in groups, 
and attached permissions. An Object may either be a User, a group, or some symbolic object defined here. 

An Object can be the target of a Restricted Command Context (`[cmd.ctx.restrict]`). 

## Object Name [concepts.users.obj.name]

All Objects have at least one and at most two names, called its Unqualified Name or its Qualified Object Name. 
All Objects have a Qualified Object Name. Not all Objects have an Unqualified Name. 

An Object's Qualified Object Name uniquely identifies it. 
There exists at most one object with any given Qualified Object Name, with one explicit exception. 
An Objects Unqualified Name may not uniquely identify it, and may identify other types of objects. 
There exists at most one object in any Object Domain, including User Domain, with any given name, 
with the same exception noted above. 

## Object Domain [concepts.users.obj.domain]

All Objects belong to exactly one domain. The domain describes the kind of object, and the grouping of that object. 

A Domain may contain zero or more objects. Note that a Domain may, including by design, contain no objects. 

Like Objects, all domains have a Qualified Domain Name which uniquely identifies it. 
Some domains may also have an Unqualified Name. Like Objects, the Unqualified Name of a Domain may not unique.

Note that objects can be named by a resource location. The domain of that resource location is not the same as that
object's Object Domain. The domain of the resource location is referred here as its Resource Domain. 
The Unqualified Name of the Domain is the same as the Resource Domain. 
The Qualified Name shall be the Resource Domain prefixed with some identifier, called the qualifier, followed by `@`. 

The Qualifier of a Object Domain's Qualified name SHOULD start with a capital letter, and SHOULD be meaningful to the 
type of Object, as the Resource Domain will not usually convey the type. 

## Groups [concepts.users.obj.groups]

Groups are Objects within The Gac14 Specification, and can be identified by a Resource Location. 

The Object Domain of groups use the `Group` qualifier and follow standard naming for the Object Domain of Objects 
which are named by a resource location. 

Groups do not have an unqualified name. The qualified name of a group is its resource location, 
optionally using the Object Domain in place of its Resource Domain. 

Groups may have members, which are other Objects. An Object is said to have membership in a group 
if the group's members contains that Object.  
Membership of groups is transitive, if an Object `o` has membership in group `a`
 and group `a` has membership in group `b`, then `o` has membership in `b`. 
 
## Users [concepts.users.obj.user]

Users are Objects within The Gac14 Specification, and are bound to actual players within the game. 

The Object Domain of users is called the User Domain. The Gac14 Specification Defines 4 Standard User Domains. 
Implementations may add additional implementation-defined User Domains. 

User Domains are named by their Unqualified Name. The Qualified Domain Name is the User Domains assigned ID, 
prefixed by the qualifier UserDomain, followed by `@`, similar to groups. 

The Standard User Domains are:
* The Minecraft User Domain (id `00000000-0000-0000-0000-000000000000`)
* The Offline User Domain (id `01b05cb6-756e-56e7-b82e-69a9de8b220c`)
* The FakeUsers User Domain (id `5cb58ce7-b247-54c9-aaec-5b24dc8f5fee`)
* The Internal User Domain (id `91d7bc97-6447-5259-947b-b8c7afa2450b`)

All Users in the standard domains have an Unqualified name. It is implementation-defined if users in addititional 
User Domains have Unqualified Names. 
The Unqualified name of users in the Minecraft or Offline UserDomains is the Username.

The qualified name of any user is any one of the following:
* The User's UUID, which is determined by the User Domain
* The User's UUID, prefixed by the qualified or Unqualified name of the User Domain, followed by `//`.
* The Unqualified Name of the user, prefixed by the qualified or Unqualified name of the User Domain, followed by `//`.

### User Lookup [concepts.users.obj.user.lookup]

Whenever a user is referenced by its unqualified name, it is looked up in a standard search order, 
to resolve the qualified name. In order, the unqualified name is checked against each User Domain, 
that participates in this search order. If the check is successful, then that qualified name is used. 

The Standard Search Order for User Lookup is:
* Users in the Minecraft Domain. The check passes if the server is in Online Mode, and a check against Mojang's Official Minecraft Account Servers finds exactly one Minecraft Account with the name of that user.
* Users in the Offline Domain. The check passes if at least one user with the name which has previously logged on to the server, while the server is in offline mode
* Users in the FakeUsers Domain. The check passes if a user with that name has been created by some command that instructs the server to create a Fake User, and has not be removed by a command which instructs the server to remove that Fake User.
* Users in unspecified additional domains, searched in an unspecified order. Whether the check passes for any given domain is implementation-defined.
* Users in the Internal Domain. It is implementation-defined if the check passes, except that this check always passes for the user with the unqualified name `root`.


## Command Context [concepts.users.obj.context]

Command Contexts are Objects. 
Command Contexts have an unspecified Qualified Object name, and belong to an Unspecified Object Domain. 
A Command Context can only be referred to by the target selector `@s`, 
and only from a command which is run in that particular context. 
Command Contexts do not have an Unqualified Object Name. 

 

