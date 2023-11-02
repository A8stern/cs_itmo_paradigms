package expression;

import java.util.Objects;

public abstract class BinaryOperations implements InterfaceCreate {
    protected final String operation;

    protected InterfaceCreate left;
    protected InterfaceCreate right;
    protected BinaryOperations(String operation, InterfaceCreate left, InterfaceCreate right){
        this.operation = operation;
        this.left = left;
        this.right = right;
    }
    @Override
    public int evaluate(int n) {
        return function(left.evaluate(n),right.evaluate(n));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return function(left.evaluate(x, y, z), right.evaluate(x, y, z));
    }

    public abstract int function(int left, int right);

    @Override
    public String toString() {
        return "(" + left.toString() + " " + this.operation + " " + right.toString() + ")";
    }

    @Override
    public boolean equals(Object o){
        if (o == null){
            return false;
        }
        return this.getClass() == o.getClass() && (left.equals(((BinaryOperations) o).left) && right.equals(((BinaryOperations) o).right));
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.left, this.right, this.operation);
    }
}
