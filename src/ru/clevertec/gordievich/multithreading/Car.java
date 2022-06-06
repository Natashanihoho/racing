package ru.clevertec.gordievich.multithreading;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Car implements Runnable {

    private static final int INTERVAL = 1;
    private final int speed;
    private final String name;
    private final Race race;
    private int distance;

    public Car(String name,Race race) {
        this.name = name;
        this.race = race;
        this.speed = new Random().nextInt(30) + 10;
    }

    @Override
    public void run() {
        try {
            while (!race.isWinnerExist()) {
                race.run(this);
                TimeUnit.SECONDS.sleep(INTERVAL);
                distance += speed * INTERVAL;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getDistance() {
        return distance;
    }

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }
}
