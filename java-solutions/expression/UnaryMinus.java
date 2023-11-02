package expression;

public class UnaryMinus extends UnaryOperations{

    protected UnaryMinus(InterfaceCreate value) {super("-", value);}

    @Override
    public int function(int value) {
        return -(value);
    }
}
