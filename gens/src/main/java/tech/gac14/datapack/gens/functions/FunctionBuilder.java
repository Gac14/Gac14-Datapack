package tech.gac14.datapack.gens.functions;

import net.minecraft.util.ResourceLocation;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class FunctionBuilder {
    private final ResourceLocation name;
    private final List<CommandBuilder> commands;
    private FunctionBuilder(ResourceLocation name) {
        this.name = name;
        this.commands = new LinkedList<>();
    }

    public static FunctionBuilder builder(ResourceLocation loc){
        return new FunctionBuilder(loc);
    }
    public static FunctionBuilder builder(String s){
        return new FunctionBuilder(ResourceLocation.tryCreate(s));
    }

    public FunctionBuilder addCommand(CommandBuilder cmd){
        commands.add(cmd);
        return this;
    }

    public ResourceLocation getName(){
        return name;
    }

    public void write(BufferedWriter writer) throws IOException {
        for(CommandBuilder cmd:commands){
            cmd.write(writer);
            writer.newLine();
        }
    }
}
