package com.javarush.island.Denisenko.Thread.tasks;

import com.javarush.island.Denisenko.LifeForms.Animals.AnimalParent;
import com.javarush.island.Denisenko.PlayField.IslandField;
import com.javarush.island.Denisenko.PlayField.Location;
import com.javarush.island.Denisenko.Thread.IslandSimulation;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class AnimalHpDecreaseTask implements  Runnable{

    private double percentOfHpToDecrease = 15;
    private final CountDownLatch latch;
    private int animalsDiedByHungry;


    public AnimalHpDecreaseTask(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        animalsDiedByHungry = 0;
        List<AnimalParent> animals = IslandField.getInstance().getAllAnimals().stream().filter(c -> c.getMaxHealthPoints() > 0).toList();
        if (IslandSimulation.getInstance().getTimeNow() / 60 >= 3) {
            percentOfHpToDecrease = percentOfHpToDecrease * 2;
        }
        for (AnimalParent animal : animals) {
            double hpToDecrease = animal.getMaxHealthPoints() * percentOfHpToDecrease / 100.0;
            if (animal.getHealthPoints() - hpToDecrease > 0) {
                animal.setHealthPoints(animal.getHealthPoints() - hpToDecrease);
            } else {
                Location location = IslandField.getInstance().getLocation(animal.getRow(), animal.getColumn());
                IslandField.getInstance().removeAnimal(animal, location.getRow(), location.getColumn());
                animalsDiedByHungry++;
            }
        }
        latch.countDown();
    }
    public int getAnimalsDiedByHungry() {
        return animalsDiedByHungry;
    }
}
