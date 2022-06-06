package ru.clevertec.gordievich.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class StarterThread {

    private final int count;
    private final ExecutorService executor;
    private final Race race;

    public StarterThread(int countCar, int distanceLength) {
        this.count = countCar;
        this.executor = Executors.newFixedThreadPool(countCar);
        this.race = new Race(distanceLength, executor);
    }

    public void run() {
        IntStream.rangeClosed(1, count)
                .forEach(
                        i -> executor.execute(
                                new Car(
                                        "Car#" + i,
                                        race
                                )
                        )
                );
        executor.shutdown();
    }
}
