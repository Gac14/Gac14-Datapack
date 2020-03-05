package tech.gac14.datapack.gens.loot;

import net.minecraft.entity.EntityType;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.functions.SetNBT;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class Spawners {
    private static ResourceLocation mapSpawnerName(ResourceLocation entityName) {
        String domain = entityName.getNamespace();
        String path = entityName.getPath();
        String newPath = String.join("/", "loot", "categories", "spawners", "names", domain, path);
        return new ResourceLocation("gac14", newPath);
    }

    /**
     * Registers a Spawner with special properties
     * @param name the name of the special spawner (not the loot category)
     * @param type The type of the entity the spawner spawns
     * @param formatter An operator which takes unformatted text, and produces styled text in some form.
     * @param spawnData A supplier which produces the Entity Data (without the entity id)
     * @return A loot category which can supply new entries
     */
    public static LootCategory registerSpecialSpawnerName(ResourceLocation name, EntityType<?> type, UnaryOperator<ITextComponent> formatter, Supplier<CompoundNBT> spawnData) {
        ResourceLocation loc = mapSpawnerName(name);
        ITextComponent unformatted = type.getName().deepCopy();
        unformatted.appendSibling(new StringTextComponent(" Spawner"));
        ITextComponent formatted = formatter.apply(unformatted);
        CompoundNBT tag = new CompoundNBT();
        CompoundNBT tileEntityData = new CompoundNBT();
        tileEntityData.putInt("Delay", -1);
        tileEntityData.putInt("MinSpawnDelay", 1);
        tileEntityData.putInt("MaxSpawnDelay", 1000);
        CompoundNBT data = spawnData.get();
        data.putString("id", Objects.requireNonNull(type.getRegistryName()).toString());
        tileEntityData.put("SpawnData", data);
        tag.put("TileEntityData", tileEntityData);
        LootTable.Builder builder = LootTable.builder();
        builder.addLootPool(LootPool.builder().addEntry(ItemLootEntry.builder(() -> Items.SPAWNER)
                .acceptFunction(SetNBT.func_215952_a(tag))
                .acceptFunction(SetNameBuilder.builder(formatted))));
        LootCategories.registerLootCategory(loc, builder);
        return () -> TableLootEntry.builder(loc);
    }

    /**
     * Registers a regular spawner name loot category. This is equivalent to registerSpecialSpawnerName, where the spawner name is the entity type name.
     *  and with no additional data
     * @see #registerSpecialSpawnerName(ResourceLocation, EntityType, UnaryOperator, Supplier)
     */
    public static LootCategory registerSpawnerName(EntityType<?> type, UnaryOperator<ITextComponent> formatter) {
        return registerSpecialSpawnerName(Objects.requireNonNull(type.getRegistryName()), type, formatter, CompoundNBT::new);
    }

    /**
     * Special case of registerSpawnerName that sets the display name to the unformatted name.
     */
    public static LootCategory registerSpawnerName(EntityType<?> type) {
        return registerSpawnerName(type, UnaryOperator.identity());
    }

    /**
     * Registers a new group of spawners containing the given spawner names
     * @param loc The name of the spawner group. This is transformed from &lt;domain&gt;:&ltpath...&gt to &lt;domain&gt;:/loot/categories/spawners/groups/&ltpath...&gt
     * @param names The spawner name categories to bind
     * @return A loot category which generates exactly one of the specified spawners with equal distribution.
     */
    public static LootCategory registerSpawnerGroup(ResourceLocation loc,int weight,LootCategory... names){
        ResourceLocation name = new ResourceLocation(loc.getNamespace(),String.join("/","loot","categores","spawners","groups",loc.getPath()));
        LootPool.Builder pool = LootPool.builder();
        Arrays.stream(names).map(LootCategory::get).forEachOrdered(pool::addEntry);
        LootCategories.registerLootCategory(name,LootTable.builder().addLootPool(pool));
        SPAWNER_GROUP_WEIGHTS.put(name,weight);
        return () -> TableLootEntry.builder(name);
    }

    public static UnaryOperator<ITextComponent> setColor(TextFormatting formatting) {
        return t -> t.applyTextStyle(formatting);
    }

    public static void init() {
        LootPool.Builder pool = LootPool.builder();
        SPAWNER_GROUP_WEIGHTS
                .forEach((name,weight)->
                        pool.addEntry(TableLootEntry.builder(name).weight(weight)));
        LootCategories.registerLootCategory(new ResourceLocation("gac14",String.join("/","")),LootTable.builder().addLootPool(pool));
    }

    public static final LootCategory BLAZE = registerSpawnerName(EntityType.BLAZE, setColor(TextFormatting.GOLD));
    public static final LootCategory CHICKEN = registerSpawnerName(EntityType.CHICKEN);
    public static final LootCategory COW = registerSpawnerName(EntityType.COW);
    public static final LootCategory CREEPER = registerSpawnerName(EntityType.CREEPER, setColor(TextFormatting.DARK_GREEN));
    public static final LootCategory HUSK = registerSpawnerName(EntityType.HUSK, setColor(TextFormatting.DARK_GRAY));
    public static final LootCategory IRON_GOLEM = registerSpawnerName(EntityType.IRON_GOLEM,setColor(TextFormatting.GRAY));
    public static final LootCategory PIG = registerSpawnerName(EntityType.PIG);
    public static final LootCategory SKELETON = registerSpawnerName(EntityType.SKELETON);
    public static final LootCategory STRAY = registerSpawnerName(EntityType.STRAY);
    public static final LootCategory WITCH = registerSpawnerName(EntityType.WITCH);
    public static final LootCategory WITHER_SKELETON = registerSpawnerName(EntityType.WITHER_SKELETON,setColor(TextFormatting.BLACK));
    public static final LootCategory ZOMBIE = registerSpawnerName(EntityType.ZOMBIE,setColor(TextFormatting.AQUA));
    public static final LootCategory ZOMBIE_PIGMAN = registerSpawnerName(EntityType.ZOMBIE_PIGMAN,setColor(TextFormatting.RED));
    public static final LootCategory VINDICATOR = registerSpawnerName(EntityType.VINDICATOR,setColor(TextFormatting.GREEN));
    public static final LootCategory EVOKER = registerSpawnerName(EntityType.EVOKER,setColor(TextFormatting.DARK_PURPLE));
    public static final LootCategory PILLAGER = registerSpawnerName(EntityType.PILLAGER,setColor(TextFormatting.GREEN));

    private static final Map<ResourceLocation,Integer> SPAWNER_GROUP_WEIGHTS = new HashMap<>();

    public static final LootCategory CHARGED_CREEPER = registerSpecialSpawnerName(new ResourceLocation("gac14","creeper/powered"),EntityType.CREEPER,setColor(TextFormatting.DARK_PURPLE),()->{
        CompoundNBT tag = new CompoundNBT();
        tag.putBoolean("powered",true);
        return tag;
    });

    public static final LootCategory ANY = ()->TableLootEntry.builder(new ResourceLocation("gac14",String.join("/","")));

    public static final LootCategory COMMON = registerSpawnerGroup(new ResourceLocation("gac14","common"),16, CHICKEN,COW,PIG,ZOMBIE);
    public static final LootCategory UNCOMMON = registerSpawnerGroup(new ResourceLocation("gac14","uncommon"),8,SKELETON,HUSK,STRAY,WITHER_SKELETON,ZOMBIE_PIGMAN);
    public static final LootCategory RARE = registerSpawnerGroup(new ResourceLocation("gac14","rare"),4,WITCH,BLAZE,VINDICATOR,PILLAGER,CREEPER);
    public static final LootCategory VERY_RARE = registerSpawnerGroup(new ResourceLocation("gac14","very_rare"),1,IRON_GOLEM,EVOKER,CHARGED_CREEPER);

}
