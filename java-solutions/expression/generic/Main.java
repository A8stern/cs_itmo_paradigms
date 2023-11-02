package expression.generic;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        GenericTabulator gb = new GenericTabulator();
        StringBuilder line = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
            line.append(args[i]);
        }
        Object[][][] arr = gb.tabulate(args[0].substring(1), line.toString(), -2, 2, -2, 2, -2, 2);
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                for (int k = 0; k < arr[i][j].length; k++) {
                    System.out.println("value = " + arr[i][j][k] + ", x = " + (i - 2) + ", y = " + (j - 2) + ", z = " + (k - 2));
                }
            }
        }
    }
}
