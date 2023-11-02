package expression;

public class Multiply extends BinaryOperations {
    public Multiply(InterfaceCreate left, InterfaceCreate right) {super("*", left, right);}
    @Override
    public int function(int left, int right) {
        return left * right;
    }
}
