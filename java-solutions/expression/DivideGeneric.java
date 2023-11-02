package expression;

public class DivideGeneric<T> extends BinaryGeneric<T> {

    protected DivideGeneric(GenericExpression<T> left, GenericExpression<T> right, GenericsInterface<T> type) {
        super("/", left, right, type);
    }

    @Override
    public T function(T left, T right) {
        return type.divide(left, right);
    }
}
