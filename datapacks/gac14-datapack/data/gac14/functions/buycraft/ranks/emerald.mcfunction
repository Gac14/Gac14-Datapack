//Gives the player iron rank.
//Need to mute the player during the function to avoid race conditions within chatex
chat players @s mute in *
chat players @s set prefix in gac14:chat to [{"text":"[","bold":true},{"text":"Emerald","color":"green","bold":true},{"text":"]","bold":true}]
pem groups gac14:ranks/emerald members add @s
chat players @s unmute in *