package expression;

import expression.GenericsInterface;
import expression.GenericExpression;

public class VariableGeneric<T> implements GenericExpression<T> {
    private final String var;
    private final GenericsInterface<T> type;

    public VariableGeneric(String var, GenericsInterface<T> type) {

        this.var = var;
        this.type = type;
    }


    @Override
    public T evaluate(int x, int y, int z) {
        return switch (this.var) {
            case "x" -> type.parseFromInt(x);
            case "-x" -> type.parseFromInt(-x);
            case "y" -> type.parseFromInt(y);
            case "-y" -> type.parseFromInt(-y);
            case "z" -> type.parseFromInt(z);
            case "-z" -> type.parseFromInt(-z);
            default -> throw new NullPointerException();
        };
    }

    @Override
    public String toString() {
        return var;
    }


}