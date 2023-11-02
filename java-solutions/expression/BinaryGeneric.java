package expression;

public abstract class BinaryGeneric<T> implements GenericExpression<T> {
    protected final String operation;
    protected GenericsInterface<T> type;
    protected GenericExpression<T> left;
    protected GenericExpression<T> right;

    protected BinaryGeneric(String operation, GenericExpression<T> left, GenericExpression<T> right, GenericsInterface<T> type) {
        this.operation = operation;
        this.left = left;
        this.right = right;
        this.type = type;
    }

    @Override
    public T evaluate(int x, int y, int z) {
        return function(left.evaluate(x, y, z), right.evaluate(x, y, z));
    }

    public abstract T function(T left, T right);

    @Override
    public String toString() {
        return "(" + left.toString() + " " + this.operation + " " + right.toString() + ")";
    }
}
