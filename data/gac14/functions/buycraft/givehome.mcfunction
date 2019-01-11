pem players permissions @s -!players.teleport.sethome players.teleport.home
//The disable command here synchronizes-with any attempts to use the /home command
//Otherwise the add operation on pmaxes.home would have a potential race condition 
//As /home and /sethome commands sent by players are indeterminately-sequenced.
system player @s actions ptele.home disable
system player @s limits pmaxes.home add 1
system player @s actions ptele.home enable
pem players permissions @s +players.teleport.sethome players.teleport.home