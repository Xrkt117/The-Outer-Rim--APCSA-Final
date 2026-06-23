package ConsoleUI;

import java.util.Scanner;


public final class ui {
    public static void delay(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    private ui() { }

    public static Scanner getScanner() {
        return scanner;
    }

    public static void println(String s) { System.out.println(s); }
    public static void print(String s) { System.out.print(s); }
    public static void blankLine() { System.out.println(); }

    public static void printSoundln(String s) {
        sound.playSound("Sounds/printSound.wav");
        System.out.println(s);
    }

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

    //waits for the user to press Enter. Use this to pause the game until the player continues.
    public static void pressAnyKey() {
        delay(500);
        sound.playSound("Sounds/continue.wav");
        System.out.println(" ");
        System.out.println("[Press any key to continue]");
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
    }

    //same as pressAnyKey but allows a custom prompt string.
    public static void pressAnyKey(String prompt) {
        System.out.println(prompt);
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
    }
}
