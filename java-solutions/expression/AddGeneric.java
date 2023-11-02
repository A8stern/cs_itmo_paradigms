package expression;

public class AddGeneric<T> extends BinaryGeneric<T> {
    protected AddGeneric(GenericExpression<T> left, GenericExpression<T> right, GenericsInterface<T> type) {
        super("+", left, right, type);
    }

    @Override
    public T function(T left, T right) {
        return type.add(left, right);
    }
}
