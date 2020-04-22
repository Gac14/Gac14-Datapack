package tech.gac14.datapack.gens.functions;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.util.ResourceLocation;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class FunctionProvider implements IDataProvider {
    private final DataGenerator gen;
    private final Map<ResourceLocation,FunctionBuilder> fns;
    public FunctionProvider(DataGenerator gen) {
        this.gen = gen;
        this.fns = new HashMap<>();
    }

    @Override
    public void act(DirectoryCache directoryCache) throws IOException {
        try {
            fns.forEach((key, fn) -> {
                Path p = gen.getOutputFolder().resolve(Paths.get("data", key.getNamespace(), "functions", key.getPath()));
                try {
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(p)));
                    fn.write(writer);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }catch(RuntimeException e){
            Throwable t = e.getCause();
            if(t instanceof IOException)
                throw (IOException)t;
            throw e;
        }
    }
    public void registerFunctions(){

    }

    public void registerFunction(FunctionBuilder fn){
        if(fns.putIfAbsent(fn.getName(),fn)!=null)
            throw new IllegalArgumentException("Registering Multiple Functions with the same name");
    }
    @Override
    public String getName() {
        return "functions";
    }
}
