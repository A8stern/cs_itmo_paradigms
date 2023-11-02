package expression;

public class UnaryMinusGeneric<T> extends UnaryOperaGeneric<T> {

    protected UnaryMinusGeneric(GenericExpression<T> value, GenericsInterface<T> type) {
        super("-", value, type);
    }

    @Override
    public T function(T value) {
        return type.unaryMinus(value);
    }
}
