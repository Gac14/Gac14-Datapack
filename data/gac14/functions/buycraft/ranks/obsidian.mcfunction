//Gives the player obsidian rank.
//Need to mute the player during the function to avoid race conditions within chatex
chat players @s mute in *
chat players @s set prefix in gac14:chat to [{"text":"[","bold":true},{"text":"Obsidian","color":"purple","bold":true},{"text":"]","bold":true}]
pem groups gac14:ranks/obsidian members add @s
chat players @s unmute in *