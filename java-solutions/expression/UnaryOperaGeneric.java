package expression;

import expression.GenericsInterface;
import expression.GenericExpression;

public abstract class UnaryOperaGeneric<T> implements GenericExpression<T> {
    protected final String operation;
    protected final GenericsInterface<T> type;
    protected final GenericExpression<T> value;


    protected UnaryOperaGeneric(String operation, GenericExpression<T> value, GenericsInterface<T> type) {
        this.operation = operation;
        this.value = value;
        this.type = type;
    }


    @Override
    public T evaluate(int x, int y, int z) {
        return function(value.evaluate(x, y, z));
    }

    public abstract T function(T value);

    @Override
    public String toString() {
        return operation + "(" + value.toString() + ")";
    }

}

