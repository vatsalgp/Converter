import java.util.Scanner;

public class Converter {
    private static StringBuilder sourceString = new StringBuilder();
    private static StringBuilder destString = new StringBuilder();
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
        int integer = (int) Double.parseDouble(sourceString.toString());
        if (destRadix == 1)
            for (int i = 0; i < integer; i++)
                destString.append("1");
        else if (destRadix == 10)
            destString.append((sourceString));
        else {
            destString.append(Integer.toString(integer, destRadix)).append(".");
            double number = Double.parseDouble(sourceString.toString());
            number -= integer;
            for (int i = 0; i < 5; i++) {
                number *= destRadix;
                integer = (int) number;
                number -= integer;
                destString.append(Integer.toString(integer, destRadix));
            }
        }
        System.out.println(destString);
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
                sourceString.append(Integer.toString(second.trim().length()));
                break;
            case 10:
                sourceString.append(second);
                break;
            default:
                final String[] parts = second.split("\\.");
                double number = Integer.parseInt(parts[0], sourceRadix);
                if (parts.length > 1) {
                    int count = 1;
                    for (final String ch : parts[1].split("")) {
                        final double d = Integer.parseInt(ch, sourceRadix);
                        number += d / Math.pow(sourceRadix, count);
                        count++;
                    }
                }
                sourceString.append(Double.toString(number));
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
