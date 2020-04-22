package tech.gac14.datapack.gens.functions;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.tree.CommandNode;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.minecraft.command.CommandSource;
import net.minecraft.util.ResourceLocation;

public class ShopFunctionBuilder {
    public static final CommandNode<CommandSource> eco = LiteralArgumentBuilder.<CommandSource>literal("eco").build();
    public static final LiteralCommandNode<CommandSource> txn = LiteralArgumentBuilder.<CommandSource>literal("txn").build();
    public static final LiteralCommandNode<CommandSource> commit = LiteralArgumentBuilder.<CommandSource>literal("commit").build();

    public static FunctionBuilder commit(ResourceLocation location){
        FunctionBuilder builder = FunctionBuilder.builder(location);
        builder.addCommand(CommandBuilder.builder(eco)
            .argument(txn)
            .argument(commit));
        return builder;
    }
}
