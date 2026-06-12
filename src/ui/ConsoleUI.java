package ui;

import java.util.Scanner;

public final class ConsoleUI {
    private static final Scanner scanner = new Scanner(System.in);

    private ConsoleUI() { }

    public static void println(String s) { System.out.println(s); }
    public static void print(String s) { System.out.print(s); }
    public static void blankLine() { System.out.println(); }

    public static String prompt(String promptText) {
        System.out.print(promptText + " ");
        return scanner.nextLine();
    }

    public static int promptInt(String promptText) {
        System.out.print(promptText + " ");
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a number: ");
            scanner.next();
        }
        int val = scanner.nextInt();
        scanner.nextLine(); // consume newline
        return val;
    }

    public static void banner(String title) {
        System.out.println("=== " + title + " ===");
    }
}
