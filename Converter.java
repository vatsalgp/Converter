import java.util.Scanner;

public class Converter {
    final private static Scanner scanner = new Scanner(System.in);
    private static double num;
    private static int radix;

    public static void main(final String[] args) {
        try {
            getInput();
            printOutput();
        } catch (Exception e) {
            System.out.println("error");
        }
    }

    private static void printOutput() {
        final StringBuilder out = new StringBuilder();
        int integer = (int) num;
        if (radix == 1)
            for (int i = 0; i < integer; i++)
                out.append("1");
        else if (radix == 10)
            out.append(Double.toString(num));
        else {
            out.append(Integer.toString(integer, radix)).append(".");
            num -= integer;
            for (int i = 0; i < 5; i++) {
                num *= radix;
                integer = (int) num;
                num -= integer;
                out.append(Integer.toString(integer, radix));
            }
        }
        System.out.println(out);
    }

    private static void getInput() throws Exception {
        int sourceRadix = 0;
        final String first = scanner.nextLine();
        if (validRadix(first))
            sourceRadix = Integer.parseInt(first);
        else
            throw new Exception();
        final String second = scanner.nextLine();
        if (sourceRadix == 1)
            num = second.trim().length();
        else if (sourceRadix == 10)
            num = Double.parseDouble(second);
        else {
            final String[] parts = second.split("\\.");
            num = Integer.parseInt(parts[0], sourceRadix);
            if (parts.length > 1) {
                int count = 1;
                for (final String ch : parts[1].split("")) {
                    final double d = Integer.parseInt(ch, sourceRadix);
                    num += d / Math.pow(sourceRadix, count);
                    count++;
                }
            }
        }
        final String third = scanner.nextLine();
        if (validRadix(third))
            radix = Integer.parseInt(third);
        else
            throw new Exception();
    }

    private static boolean validRadix(String string) {
        try {
            int num = Integer.parseInt(string);
            return num > 0 && num < 37;
        } catch (Exception e) {
            return false;
        }
    }
}
