package utils;

import java.util.Scanner;

public class InputUtil {
    private static final Scanner sc = new Scanner(System.in);

    public static String readLine(String msg) {
        System.out.print(msg);
        return sc.nextLine().trim();
    }

    public static int readInt(String msg, int min, int max) {
        while (true) {
            try {
                System.out.print(msg);
                int n = Integer.parseInt(sc.nextLine().trim());
                if (n < min || n > max) {
                    System.out.println("nhập số từ  " + min + " đến " + max);
                    continue;
                }
                return n;
            } catch (Exception e) {
                System.out.println("không phải số");
            }
        }
    }

    public static double readDouble(String msg, double minExclusive) {
        while (true) {
            try {
                System.out.print(msg);
                double n = Double.parseDouble(sc.nextLine().trim());
                if (n <= minExclusive) {
                    System.out.println("phải trên" + minExclusive);
                    continue;
                }
                return n;
            } catch (Exception e) {
                System.out.println("không phải số");
            }
        }
    }
}
