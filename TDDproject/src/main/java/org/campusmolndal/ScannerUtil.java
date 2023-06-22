package org.campusmolndal;

import java.util.Scanner;

public class ScannerUtil {
    private static Scanner scanner;
    private static ScannerUtil theInput;

    // The class is a Utility class and should not be instantiated, therefore the constructor is private.
    private ScannerUtil(Scanner scanner) {
        ScannerUtil.scanner = scanner;
    }

    public static ScannerUtil getTheInput() {
        if (null == theInput) {
            ScannerUtil.theInput = new ScannerUtil(new Scanner(System.in));
        }
        return ScannerUtil.theInput;
    }

    // Method to set another Scanner
    // ONLY for use in testing, not used in program code!
    static void setScanner(Scanner scanner) {
        ScannerUtil.scanner = scanner;
    }

    public int nextInt() {
        while (true) {
            try {
                return ScannerUtil.scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter an integer: ");
                ScannerUtil.scanner.nextLine();
            }
        }
    }

    public double nextDouble() {
        while (true) {
            try {
                return ScannerUtil.scanner.nextDouble();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a double: ");
                ScannerUtil.scanner.nextLine();
            }
        }
    }

    public String nextLine() {
        String result;
        do {
            result = ScannerUtil.scanner.nextLine();
            if (result.isEmpty()) {
                System.out.println("Please enter a non-empty string: ");
            }
        } while (result.isEmpty());
        return result;
    }
}
