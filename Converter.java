import java.util.Scanner;

public class Converter {
    private static class Number {
        StringBuilder string = new StringBuilder();
        int radix;
    }

    private static Number source = new Number();
    private static Number dest = new Number();

    public static void main(final String[] args) {
        try {
            getInput();
            printOutput();
        } catch (Exception e) {
            System.out.println("error");
        }
    }

    private static void getInput() throws Exception {
        final Scanner scanner = new Scanner(System.in);
        final String first = scanner.nextLine();
        final String second = scanner.nextLine();
        final String third = scanner.nextLine();
        scanner.close();
        source.radix = getRadix(first);
        dest.radix = getRadix(third);
        switch (source.radix) {
            case 1:
                source.string.append(Integer.toString(second.trim().length()));
                break;
            case 10:
                source.string.append(second);
                break;
            default:
                final String[] parts = second.split("\\.");
                double number = Integer.parseInt(parts[0], source.radix);
                if (parts.length > 1) {
                    int count = 1;
                    for (final String ch : parts[1].split("")) {
                        final double d = Integer.parseInt(ch, source.radix);
                        number += d / Math.pow(source.radix, count);
                        count++;
                    }
                }
                source.string.append(Double.toString(number));
        }
    }

    private static void printOutput() {
        int integer = (int) Double.parseDouble(source.string.toString());
        if (dest.radix == 1)
            for (int i = 0; i < integer; i++)
                dest.string.append("1");
        else if (dest.radix == 10)
            dest.string.append((source.string));
        else {
            dest.string.append(Integer.toString(integer, dest.radix)).append(".");
            double number = Double.parseDouble(source.string.toString());
            number -= integer;
            for (int i = 0; i < 5; i++) {
                number *= dest.radix;
                integer = (int) number;
                number -= integer;
                dest.string.append(Integer.toString(integer, dest.radix));
            }
        }
        System.out.println(dest.string);
    }

    private static int getRadix(String string) throws Exception {
        int num = Integer.parseInt(string);
        if (num > 0 && num < 37)
            return num;
        else
            throw new Exception();
    }
}
