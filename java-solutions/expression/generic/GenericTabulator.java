package expression.generic;

import expression.*;
import expression.genericTypes.*;

import java.util.HashMap;

public class GenericTabulator implements Tabulator {
    private final static HashMap<String, GenericsInterface<?>> mapping = new HashMap<>();

    static {
        mapping.put("i", new GenericInteger(true));
        mapping.put("d", new GenericDouble());
        mapping.put("bi", new GenericBigInt());
        mapping.put("u", new GenericInteger(false));
        mapping.put("f", new GenericFloat());
        mapping.put("s", new GenericShort());
    }

    @Override
    public Object[][][] tabulate(String mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2) throws Exception {
        return tabulateImpl(mapping.get(mode), expression, x1, x2, y1, y2, z1, z2);
    }

    public <T> Object[][][] tabulateImpl(GenericsInterface<T> mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2) throws Exception {
        Object[][][] arr = new Object[x2 - x1 + 1][y2 - y1 + 1][z2 - z1 + 1];
        GenericParser<T> parser = new GenericParser<>(mode);
        for (int i = 0; i <= x2 - x1; i++) {
            for (int j = 0; j <= y2 - y1; j++) {
                for (int k = 0; k <= z2 - z1; k++) {
                    try {
                        arr[i][j][k] = parser.parse(expression).evaluate(i + x1, j + y1, k + z1);
                    } catch (OverflowException | ArithmeticException ignored) {
                    }
                }
            }
        }
        return arr;
    }


}
