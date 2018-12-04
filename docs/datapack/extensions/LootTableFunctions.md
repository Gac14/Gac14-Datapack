<h1>Loot Table Function Extensions</h1>

Various mods add functions to loot tables, to provide additional functionality which is difficult or impossible to implement in pure vanilla loot tables (many things which are difficult, but not impossible, to create are implemented in loot categories, rather than extensions to vanilla). 

<h2>Run Function</h2>

```
{
	"function":"run_function",
	"target":<A string which forms a valid resource location that names a function>
}
```

A special loot function that doesn't affect the item generated. 
Instead, when the item is generated, it pushes a new stack frame, followed by the generated item, then runs the function named by `target` as though the player that triggered the generation ran it with `/system doPrivileged <target>`, and `<target>` is tagged with `gac14:doPrivileged`. The frame is popped when the function completes. 
If a player did not trigger the generation (an entity was killed and has no killing player), then the function is run as the server instead. 
As functions are applied in declaration order, the item that is pushed is the one that all functions up to this point generated, even if more functions declared after would further change the item. As such, it is usually a good idea to declare this function last. 
However, loot functions that conflict with each other can be used to run multiple functions this way, passing different arguments to each (in the form of NBT Data or count). 

<h2>Envoy Items</h2>

```
{
	"function":"create_envoy",
	"envoy_loot_table":<A string which forms a valid resource location that names a loot table>
}
```

Generates an envoy associated with the loot table named by `envoy_loot_table`.


<h2>Functional Item</h2>

```
{
	"function":"create_function_item",
	"target":<A string which forms a valid resource location that names a function>
}
```

Generates a functional item that runs the function named by `target`. The function is run as though the user executed `/system doPrivileged <target>`, and `<target>` is tagged with `gac14:doPrivileged`. 




