package org.campusmolndal;

import java.util.Scanner;

public class ScannerUtil {
    private static Scanner scanner;
    private static ScannerUtil theInput;

    // Klassen är en Utility-klass och ska inte instansieras, därför är konstruktorn privat.
    private ScannerUtil(Scanner scanner) {
        this.scanner = scanner;
    }

    public static ScannerUtil getTheInput() {
            if (theInput == null) {
                theInput = new ScannerUtil(new Scanner(System.in));
            }
            return theInput;
    }

    // Metod för att ställa in en annan Scanner
    // ONLY för användning vid testning, används inte i programkoden!
    static void setScanner(Scanner scanner) {
        ScannerUtil.scanner = scanner;
    }

    public int nextInt() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter an integer: ");
                scanner.nextLine();
            }
        }
    }

    public double nextDouble() {
        while (true) {
            try {
                return scanner.nextDouble();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a double: ");
                scanner.nextLine();
            }
        }
    }

    public String nextLine() {
        String result;
        do {
            result = scanner.nextLine();
            if (result.isEmpty()) {
                System.out.println("Invalid input. Please enter a non-empty string: ");
            }
        } while (result.isEmpty());

        return result;
    }
}
