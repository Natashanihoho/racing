package ru.clevertec.gordievich.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

public class Race {

    private static Logger logger = Logger.getLogger(Race.class.getName());

    private final int length;
    private final ReentrantLock lock = new ReentrantLock(true);
    private final AtomicBoolean isWinner = new AtomicBoolean(false);
    private final ExecutorService executorService;

    public Race(int length, ExecutorService executorService) {
        this.length = length;
        this.executorService = executorService;
    }

    public void run(Car car) {
        lock.lock();
        try {
            if(!isWinnerExist()) {
                if (car.getDistance() >= length) {
                    isWinner.set(true);
                    logger.info(car.getName() + " is winner");
                } else {
                    int distance = length - car.getDistance();
                    logger.info(car.getName() + " (speed = " + car.getSpeed() + "): " + distance + " m to the finish!");
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public boolean isWinnerExist() {
        return isWinner.get();
    }

}
