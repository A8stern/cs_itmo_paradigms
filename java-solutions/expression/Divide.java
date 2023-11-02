package expression;

public class Divide extends BinaryOperations {
    public Divide(InterfaceCreate left, InterfaceCreate right) {super("/", left, right);}
    @Override
    public int function(int left, int right) {
        return left / right;
    }
}
