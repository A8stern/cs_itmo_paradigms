package expression.genericTypes;

import expression.GenericsInterface;

public class GenericDouble implements GenericsInterface<Double> {
    @Override
    public Double add(Double a, Double b) {
        return a + b;
    }

    @Override
    public Double subtract(Double a, Double b) {
        return a - b;
    }

    @Override
    public Double multiply(Double a, Double b) {
        return a * b;
    }

    @Override
    public Double divide(Double a, Double b) {
        return a / b;
    }

    @Override
    public Double unaryMinus(Double a) {
        return -a;
    }

    @Override
    public Double parseFromInt(int a) {
        return (double) a;
    }

    @Override
    public Double parseFromString(String a) {
        return Double.valueOf(a);
    }
}
