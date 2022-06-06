package ru.clevertec.gordievich.multithreading;

import java.util.Scanner;

public class RaceRunner {

    private static int length;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int carsNumber = scanner.nextInt();
        length = scanner.nextInt();
        StarterThread starterThread = new StarterThread(carsNumber,length);
        starterThread.run();
    }
}
