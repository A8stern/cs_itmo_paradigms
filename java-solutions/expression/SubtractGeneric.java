package expression;

import expression.GenericExpression;
import expression.GenericsInterface;
import expression.BinaryGeneric;

public class SubtractGeneric<T> extends BinaryGeneric<T> {

    protected SubtractGeneric(GenericExpression<T> left, GenericExpression<T> right, GenericsInterface<T> type) {
        super("-", left, right, type);
    }

    @Override
    public T function(T left, T right) {
        return type.subtract(left, right);
    }
}
