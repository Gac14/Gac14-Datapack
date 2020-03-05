package tech.gac14.datapack.gens;


import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import tech.gac14.datapack.gens.loot.Gac14LootProvider;

@Mod.EventBusSubscriber(modid="gac14-datapack-gen",bus= Mod.EventBusSubscriber.Bus.MOD)
public class GatherData {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event){
        event.getGenerator().addProvider(new Gac14LootProvider(event.getGenerator()));
    }
}
