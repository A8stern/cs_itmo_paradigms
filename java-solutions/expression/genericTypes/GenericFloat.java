package expression.genericTypes;

import expression.GenericsInterface;

public class GenericFloat implements GenericsInterface<Float> {

    @Override
    public Float add(Float a, Float b) {
        return a + b;
    }

    @Override
    public Float subtract(Float a, Float b) {
        return a - b;
    }

    @Override
    public Float multiply(Float a, Float b) {
        return a * b;
    }

    @Override
    public Float divide(Float a, Float b) {
        return a / b;
    }

    @Override
    public Float unaryMinus(Float a) {
        return -a;
    }

    @Override
    public Float parseFromInt(int a) {
        return (float) a;
    }

    @Override
    public Float parseFromString(String a) {
        return Float.valueOf(a);
    }
}


