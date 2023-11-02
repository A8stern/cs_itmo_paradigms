package expression.genericTypes;

import expression.GenericsInterface;

import java.math.BigInteger;

public class GenericBigInt implements GenericsInterface<BigInteger> {
    @Override
    public BigInteger add(BigInteger a, BigInteger b) {
        return a.add(b);
    }

    @Override
    public BigInteger subtract(BigInteger a, BigInteger b) {
        return a.subtract(b);
    }

    @Override
    public BigInteger multiply(BigInteger a, BigInteger b) {
        return a.multiply(b);
    }

    @Override
    public BigInteger divide(BigInteger a, BigInteger b) {
        return a.divide(b);
    }

    @Override
    public BigInteger unaryMinus(BigInteger a) {
        return a.multiply(BigInteger.valueOf(-1));
    }

    @Override
    public BigInteger parseFromInt(int a) {
        return BigInteger.valueOf(a);
    }

    @Override
    public BigInteger parseFromString(String a) {
        return BigInteger.valueOf(Long.parseLong(a));
    }
}
