import java.util.Scanner;

public class Converter {
    private static class Number {
        StringBuilder string = new StringBuilder();
        int radix;
    }

    private static Number source = new Number();
    private static Number dest = new Number();
    private static String[] strings;

    public static void main(final String[] args) {
        try {
            getInput();
            parseInput();
            inputToBase10();
            base10ToDest();
            System.out.println(dest.string);
        } catch (final Exception e) {
            System.out.println("Error: Incorrect values");
        }
    }

    private static void getInput() {
        final Scanner scanner = new Scanner(System.in);
        strings = new String[] { scanner.nextLine(), scanner.nextLine(), scanner.nextLine() };
        scanner.close();
    }

    private static void parseInput() {
        source.radix = getRadix(strings[0]);
        dest.radix = getRadix(strings[2]);
    }

    private static int getRadix(final String string) {
        final int num = strToInt(string, 10);
        if (num > 0 && num < 37)
            return num;
        else
            throw new RuntimeException();
    }

    private static void inputToBase10() {
        switch (source.radix) {
            case 1:
                source.string.append(strings[1].trim().length());
                break;
            case 10:
                source.string.append(strings[1]);
                break;
            default:
                toBase10(strings[1]);
        }
    }

    private static void toBase10(final String second) {
        final String[] parts = second.split("\\.");
        double number = strToInt(parts[0], source.radix);
        if (parts.length > 1) {
            int count = 1;
            for (final String ch : parts[1].split(""))
                number += strToInt(ch, source.radix) / Math.pow(source.radix, count++);
        }
        source.string.append(number);
    }

    private static void base10ToDest() {
        final int integer = (int) strToDouble(source.string);
        switch (dest.radix) {
            case 1:
                for (int i = 0; i < integer; i++)
                    dest.string.append("1");
                break;
            case 10:
                dest.string.append(source.string);
                break;
            default:
                toDestRadix();
        }
    }

    private static void toDestRadix() {
        double number = strToDouble(source.string);
        int integer = (int) number;
        dest.string.append(intToStr(integer, dest.radix)).append(".");
        number -= integer;
        for (int i = 0; i < 5; i++) {
            number *= dest.radix;
            integer = (int) number;
            number -= integer;
            dest.string.append(intToStr(integer, dest.radix));
        }
    }

    private static String intToStr(int integer, int radix) {
        return Integer.toString(integer, radix);
    }

    private static int strToInt(String str, int radix) {
        return Integer.parseInt(str, radix);
    }

    private static double strToDouble(StringBuilder str) {
        return Double.parseDouble(str.toString());
    }
}
