package ConsoleUI;

import java.util.Scanner;

public final class ui {
    private static final Scanner scanner = new Scanner(System.in);

    private ui() { }

    public static void println(String s) { System.out.println(s); }
    public static void print(String s) { System.out.print(s); }
    public static void blankLine() { System.out.println(); }

    public static String prompt(String promptText) {
        System.out.print(promptText + " ");
        System.out.println("> ");
        return scanner.nextLine();
    }

    public static int promptInt(String promptText) {
        System.out.print(promptText + " ");
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a number: ");
            scanner.next();
        }
        int val = scanner.nextInt();
        scanner.nextLine();
        return val;
    }

    public static void flush() {
            System.out.print("\033[H\033[2J");
            System.out.flush();
    }

    /**
     * Waits for the user to press Enter. Use this to pause the game until the player continues.
     * This is the simplest cross-platform approach; it prompts the user and reads the next line.
     */
    public static void pressAnyKey() {
        System.out.println(" ");
        System.out.println("[Press any key to continue]");
        scanner.nextLine();
    }

    /**
     * Same as pressAnyKey but allows a custom prompt string.
     */
    public static void pressAnyKey(String prompt) {
        System.out.println(prompt);
        scanner.nextLine();
    }
}
