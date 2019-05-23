//Gives the player diamond rank.
//Need to mute the player during the function to avoid race conditions within chatex
chat players @s mute in *
chat players @s set prefix in gac14:chat to [{"text":"[","bold":true},{"text":"Diamond","color":"dark_blue","bold":true},{"text":"]","bold":true}]
pem groups gac14:ranks/diamond members add @s
chat players @s unmute in *