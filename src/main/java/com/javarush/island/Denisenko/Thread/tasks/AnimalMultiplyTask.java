package com.javarush.island.Denisenko.Thread.tasks;

import com.javarush.island.Denisenko.LifeForms.Animals.AnimalParent;
import com.javarush.island.Denisenko.PlayField.IslandField;
import com.javarush.island.Denisenko.PlayField.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class AnimalMultiplyTask implements Runnable {

    private int babies;
    private final CountDownLatch latch;

    public AnimalMultiplyTask(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        babies = 0;
        List<AnimalParent> animals = IslandField.getInstance().getAllAnimals();
        List<AnimalParent> animalsMultiplied = new ArrayList<>();
        if (animals.size() > 0) {
            for (AnimalParent currentAnimal : animals) {
                if (!animalsMultiplied.contains(currentAnimal)) {
                    Location location = IslandField.getInstance().getLocation(currentAnimal.getRow(), currentAnimal.getColumn());
                    List<AnimalParent> locationAnimals = location.getAnimals();

                    if (locationAnimals.size() > 1) {
                        locationAnimals = locationAnimals.stream().filter(c -> c.getNameOfLifeForm().equals(currentAnimal.getNameOfLifeForm()) && c != currentAnimal).toList();

                        if (locationAnimals.size() > 0) {
                            AnimalParent partner = locationAnimals.get(0);

                            if (!animalsMultiplied.contains(partner)) {
                                currentAnimal.multiply(partner);

                                animalsMultiplied.add(currentAnimal);
                                animalsMultiplied.add(partner);

                                babies++;
                            }
                        }
                    }
                }
            }
        }
        latch.countDown();
    }

    public int getBabies() {
        return babies;
    }
}
