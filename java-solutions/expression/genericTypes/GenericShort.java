package expression.genericTypes;

import expression.GenericsInterface;

public class GenericShort implements GenericsInterface<Short> {

    @Override
    public Short add(Short a, Short b) {
        return (short) (a + b);
    }

    @Override
    public Short subtract(Short a, Short b) {
        return (short) (a - b);
    }

    @Override
    public Short multiply(Short a, Short b) {
        return (short) (a * b);
    }

    @Override
    public Short divide(Short a, Short b) {
        return (short) (a / b);
    }

    @Override
    public Short unaryMinus(Short a) {
        return (short) (-a);
    }

    @Override
    public Short parseFromInt(int a) {
        return (short) a;
    }

    @Override
    public Short parseFromString(String a) {
        return parseFromInt(Integer.parseInt(a));
    }
}
