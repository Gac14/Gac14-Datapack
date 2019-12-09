execute store success score @s Info run eco txn start headless add minecraft:diamond 64 from internal//root balance with gac14:currency from @s complete
execute unless score @s Info 0 == 1 run tellraw @s {"text":"You can't afford this","color":"red"}
