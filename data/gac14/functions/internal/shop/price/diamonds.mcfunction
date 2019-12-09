execute store result @s Info run eco txn start add minecraft:diamond 1 from internal//root balance with gac14:currency from internal//root dryrun get gac14:currency
title @s reset
title @s actionbar ["Diamonds are currently worth $",{"score":{"name":"@s","objective":"Info"}}]