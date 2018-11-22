//Gives the player gold rank.
//Need to mute the player during the function to avoid race conditions within chatex
chat players @s mute in *
chat players @s set prefix in gac14:chat to [{"text":"[","bold":true},{"text":"Gold","color":"gold","bold":true},{"text":"]","bold":true}]
pem groups gac14:ranks/gold members add @s
chat players @s unmute in *