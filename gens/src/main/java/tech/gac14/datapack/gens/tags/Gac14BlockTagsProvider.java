package tech.gac14.datapack.gens.tags;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.TagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagCollection;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.common.Tags;

import java.nio.file.Path;

public class Gac14BlockTagsProvider extends TagsProvider<Block> {
    protected Gac14BlockTagsProvider(DataGenerator generatorIn) {
        super(generatorIn, Registry.BLOCK);
    }

    @Override
    protected void registerTags() {

    }

    @Override
    protected void setCollection(TagCollection<Block> collectionIn) {
        BlockTags.setCollection(collectionIn);
    }

    @Override
    protected Path makePath(ResourceLocation id) {
        return this.generator.getOutputFolder().resolve("data/" + id.getNamespace() + "/tags/blocks/" + id.getPath() + ".json");
    }

    @Override
    public String getName() {
        return "Gac14 Block Tags";
    }
}
