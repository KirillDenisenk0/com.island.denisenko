package com.javarush.island.Denisenko.Thread.tasks;

import com.javarush.island.Denisenko.LifeForms.Animals.AnimalParent;
import com.javarush.island.Denisenko.LifeForms.LifeFormParent;
import com.javarush.island.Denisenko.LifeForms.Plant.Plant;
import com.javarush.island.Denisenko.PlayField.IslandField;
import com.javarush.island.Denisenko.PlayField.Location;
import com.javarush.island.Denisenko.Thread.IslandSimulation;
import com.javarush.island.Denisenko.Thread.StatisticsTask;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class AnimalEatTask implements  Runnable{
    private final CountDownLatch latch;
    private int animalsEaten;

    public AnimalEatTask(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        animalsEaten = 0;
        List<AnimalParent> animals = IslandField.getInstance().getAllAnimals();
        List<LifeFormParent> lifeFormsEaten = new ArrayList<>();
        int countAnimalsStart = animals.size();

        if (countAnimalsStart > 0 && animals.stream().filter(c -> !(c.getNameOfLifeForm().equals("Caterpillar"))).toList().size() > 0) {
            Iterator<AnimalParent> iterator = animals.iterator();

            while (iterator.hasNext()) {
                AnimalParent currentAnimal = iterator.next();
                if (currentAnimal.getMaxHealthPoints() > 0) {
                    Location location = IslandField.getInstance().getLocation(currentAnimal.getRow(), currentAnimal.getColumn());
                    List<LifeFormParent> locationLifeForms = location.getLifeForms();

                    if (!locationLifeForms.isEmpty()) {
                        for (LifeFormParent lifeForm : locationLifeForms) {
                            if (currentAnimal.getChanceToEat(lifeForm.getNameOfLifeForm()) > 0 && !(lifeFormsEaten.contains(lifeForm))) {
                                boolean isEaten = currentAnimal.eat(lifeForm);

                                if (isEaten) {
                                    if (lifeForm instanceof AnimalParent animalEaten) {
                                        if (location.getAnimals().contains(animalEaten)) {
                                            IslandField.getInstance().removeAnimal(animalEaten, location.getRow(), location.getColumn());
                                        }
                                        lifeFormsEaten.add(animalEaten);
                                        animalsEaten++;
                                    } else {
                                        Plant plant = (Plant) lifeForm;
                                        if (location.getPlants().size() > 0) {
                                            IslandField.getInstance().removePlant(plant, location.getRow(), location.getColumn());
                                        }
                                    }
                                }
                                break;
                            }
                        }
                    }
                }
                iterator.remove();
            }
        } else if (countAnimalsStart == 0) {
            System.out.printf("ВЫ ПРОИГРАЛИ! ВСЕ ЖИВОТНЫЕ УМЕРЛИ НА %d ДЕНЬ!", StatisticsTask.getCurrentDay());
            IslandSimulation.getInstance().getExecutorService().shutdown();
            System.exit(0);
        } else {
            System.out.printf("ПОБЕДИЛИ ГУСЕНИЦЫ! В ЖИВЫХ ОСТАЛИСЬ ТОЛЬКО ОНИ НА %d ДЕНЬ!", StatisticsTask.getCurrentDay()); // так как они бесссмертные и не требуют пищи
            IslandSimulation.getInstance().getExecutorService().shutdown();
            System.exit(0);
        }
        latch.countDown();
    }
    public int getAnimalsEaten() {
        return animalsEaten;
    }
}
