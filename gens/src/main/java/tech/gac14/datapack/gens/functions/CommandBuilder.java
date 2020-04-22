package tech.gac14.datapack.gens.functions;

import com.mojang.brigadier.tree.ArgumentCommandNode;
import com.mojang.brigadier.tree.CommandNode;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.minecraft.command.CommandSource;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class CommandBuilder {
    private final CommandNode<CommandSource> root;
    private final List<String> args;

    private CommandBuilder(CommandNode<CommandSource> root) {
        this.root = root;
        this.args = new LinkedList<>();
    }

    public static CommandBuilder builder(CommandNode<CommandSource> root){
        return new CommandBuilder(root);
    }


    public void write(BufferedWriter writer) throws IOException {
        writer.write(root.getName());
        for(String arg: args)
            writer.write(" "+arg);
    }

    public CommandBuilder argument(String val){
        this.args.add(val);
        return this;
    }

    public CommandBuilder argument(int val){
        this.args.add(String.valueOf(val));
        return this;
    }

    public CommandBuilder argument(LiteralCommandNode<CommandSource> node){
        this.args.add(node.getName());
        return this;
    }
}
