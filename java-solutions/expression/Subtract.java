package expression;

public class Subtract extends BinaryOperations {
    public Subtract(InterfaceCreate left, InterfaceCreate right) {super("-", left, right);}
    @Override
    public int function(int left, int right) {
        return left - right;
    }
}
