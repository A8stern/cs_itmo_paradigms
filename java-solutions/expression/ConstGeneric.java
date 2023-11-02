package expression;

public class ConstGeneric<T> implements GenericExpression<T> {
    private final T constant;

    public ConstGeneric(T constant) {
        this.constant = constant;
    }

    @Override
    public T evaluate(int x, int y, int z) {
        return this.constant;
    }

    @Override
    public String toString() {
        return String.valueOf(this.constant);
    }

}
