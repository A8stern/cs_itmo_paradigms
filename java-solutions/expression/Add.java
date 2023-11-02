package expression;

public class Add extends BinaryOperations {
    public Add(InterfaceCreate left, InterfaceCreate right) {super("+", left, right);}
    @Override
    public int function(int left, int right) {
        return left + right;
    }
}

