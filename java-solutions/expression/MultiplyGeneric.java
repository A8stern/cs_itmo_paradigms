package expression;

import expression.GenericExpression;
import expression.GenericsInterface;
import expression.BinaryGeneric;

public class MultiplyGeneric<T> extends BinaryGeneric<T> {

    protected MultiplyGeneric(GenericExpression<T> left, GenericExpression<T> right, GenericsInterface<T> type) {
        super("*", left, right, type);
    }

    @Override
    public T function(T left, T right) {
        return type.multiply(left, right);
    }
}
