//Gives the player iron rank.
//Need to mute the player during the function to avoid race conditions within chatex
//More important in 1.14
chat players @s mute in *
chat players @s set prefix in gac14:chat to [{"text":"[","bold":true},{"text":"Iron","color":"gray","bold":true},{"text":"]","bold":true}]
pem groups gac14:ranks/iron members add @s
chat players @s unmute in *