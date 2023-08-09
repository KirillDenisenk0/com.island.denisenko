package com.javarush.island.Denisenko.Thread;

import com.javarush.island.Denisenko.Thread.tasks.AnimalEatTask;
import com.javarush.island.Denisenko.Thread.tasks.AnimalHpDecreaseTask;
import com.javarush.island.Denisenko.Thread.tasks.AnimalMoveTask;
import com.javarush.island.Denisenko.Thread.tasks.AnimalMultiplyTask;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AnimalLifecycleTask implements  Runnable{

    private final AnimalEatTask animalEatTask;
    private final AnimalMultiplyTask animalMultiplyTask;
    private final AnimalHpDecreaseTask animalHpDecreaseTask;
    private final CountDownLatch latch;

    public AnimalLifecycleTask() {
        latch = new CountDownLatch(3);
        animalEatTask = new AnimalEatTask(latch);
        animalMultiplyTask = new AnimalMultiplyTask(latch);
        animalHpDecreaseTask = new AnimalHpDecreaseTask(latch);
    }

    @Override
    public void run() {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        // животное ест пищу
        executorService.submit(animalEatTask);
        // животное размножается
        executorService.submit(animalMultiplyTask);
        // уменьшение здоровья животного
        executorService.submit(animalHpDecreaseTask);
        try {
            latch.await(); // ожидаем, пока CountDownLatch не достигнет нуля
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // животные двигаются на другие локации
        executorService.submit(new AnimalMoveTask());
    }

    public AnimalMultiplyTask getObjectMultiplyTask() {
        return animalMultiplyTask;
    }

    public AnimalEatTask getAnimalEatTask() {
        return animalEatTask;
    }

    public AnimalHpDecreaseTask getAnimalHpDecreaseTask() {
        return animalHpDecreaseTask;
    }
}
