package tech.gac14.datapack.gens.functions;

public abstract class ObjectBuilder {
    static class RootObjectBuilder extends ObjectBuilder{
        @Override
        public String toString() {
            return "internal//root";
        }
    }

    public abstract String toString();
}
