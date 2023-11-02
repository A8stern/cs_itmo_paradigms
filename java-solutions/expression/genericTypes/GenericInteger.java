package expression.genericTypes;

import expression.GenericsInterface;
import expression.OverflowException;

public class GenericInteger implements GenericsInterface<Integer> {

    boolean needCheck;

    public GenericInteger(boolean needCheck) {
        this.needCheck = needCheck;
    }

    @Override
    public Integer add(Integer a, Integer b) {
        if (needCheck) {
            if ((a < 0 && b < 0 && Integer.MIN_VALUE - b > a)
                    || (a > 0 && b > 0 && Integer.MAX_VALUE - b < a)) {
                throw new OverflowException("Error");
            }
        }
        return a + b;
    }

    @Override
    public Integer subtract(Integer a, Integer b) {
        if (needCheck) {
            if ((a <= 0 && b >= 0 && Integer.MIN_VALUE + b > a)
                    || (a >= 0 && b <= 0 && Integer.MAX_VALUE + b < a)) {
                throw new OverflowException("Error");
            }
        }
        return a - b;
    }

    @Override
    public Integer multiply(Integer a, Integer b) {
        if (needCheck) {
            if (a != 0 && b != 0) {
                if (a * b / b != a || a * b / a != b) {
                    throw new OverflowException("Error");
                }
            }
        }
        return a * b;
    }

    @Override
    public Integer divide(Integer a, Integer b) {
        if (needCheck) {
            if (a == Integer.MIN_VALUE && b == -1) {
                throw new OverflowException("Error");
            }
        }
        return a / b;
    }

    @Override
    public Integer unaryMinus(Integer a) {
        if (needCheck) {
            if (a.equals(Integer.MIN_VALUE)) {
                throw new OverflowException("Error");
            }
        }
        return -a;
    }

    @Override
    public Integer parseFromInt(int a) {
        return a;
    }

    @Override
    public Integer parseFromString(String a) {
        return Integer.valueOf(a);
    }
}
