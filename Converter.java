import java.util.Scanner;

public class Converter {
    private static double number;
    private static int sourceRadix;
    private static int destRadix;

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
        int integer = (int) number;
        if (destRadix == 1)
            for (int i = 0; i < integer; i++)
                out.append("1");
        else if (destRadix == 10)
            out.append(Double.toString(number));
        else {
            out.append(Integer.toString(integer, destRadix)).append(".");
            number -= integer;
            for (int i = 0; i < 5; i++) {
                number *= destRadix;
                integer = (int) number;
                number -= integer;
                out.append(Integer.toString(integer, destRadix));
            }
        }
        System.out.println(out);
    }

    private static void getInput() throws Exception {
        final Scanner scanner = new Scanner(System.in);
        final String first = scanner.nextLine();
        final String second = scanner.nextLine();
        final String third = scanner.nextLine();
        scanner.close();
        sourceRadix = getRadix(first);
        destRadix = getRadix(third);
        switch (sourceRadix) {
            case 1:
                number = second.trim().length();
                break;
            case 10:
                number = Double.parseDouble(second);
                break;
            default:
                final String[] parts = second.split("\\.");
                number = Integer.parseInt(parts[0], sourceRadix);
                if (parts.length > 1) {
                    int count = 1;
                    for (final String ch : parts[1].split("")) {
                        final double d = Integer.parseInt(ch, sourceRadix);
                        number += d / Math.pow(sourceRadix, count);
                        count++;
                    }
                }
        }
    }

    private static int getRadix(String string) throws Exception {
        int num = Integer.parseInt(string);
        if (num > 0 && num < 37)
            return num;
        else
            throw new Exception();
    }
}
