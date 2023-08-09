package com.javarush.island.Denisenko.Thread;

import com.javarush.island.Denisenko.LifeForms.Animals.herbivores.*;
import com.javarush.island.Denisenko.LifeForms.Animals.predators.*;
import com.javarush.island.Denisenko.LifeForms.Plant.Plant;
import com.javarush.island.Denisenko.PlayField.IslandField;
import com.javarush.island.Denisenko.PlayField.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class IslandSimulation {
    private final long startTime;
    private final int countHerbivores = 35;
    private final int countPlants = 40;
    private final int countPredators = 20;
    private static volatile IslandSimulation instance;
    private volatile ScheduledExecutorService executorService;

    private IslandSimulation() {
        startTime = System.currentTimeMillis();
    }

    public static IslandSimulation getInstance() {
        if (instance == null) {
            synchronized (IslandSimulation.class) {
                if (instance == null) {
                    instance = new IslandSimulation();
                }
            }
        }
        return instance;
    }

    // остров с хищниками травоядными и растениями
    public void createIslandModel(int countHerbivores, int countPredators, int countPlants) {
        placeHerbivores(countHerbivores);
        placePredators(countPredators);
        placePlants(countPlants);

        runIslandModel();
    }

   //кол-во всего по умолчании
    public void createIslandModel() {
        placeHerbivores(countHerbivores);
        placePredators(countPredators);
        placePlants(countPlants);

        runIslandModel();
    }

    // islandModel start
    private void runIslandModel() {
        executorService = Executors.newScheduledThreadPool(3);

        AnimalLifecycleTask animalLifecycleTask = new AnimalLifecycleTask();
        PlantGrowthTask plantGrowthTask = new PlantGrowthTask();
        StatisticsTask statisticsTask = new StatisticsTask(animalLifecycleTask.getAnimalEatTask(), animalLifecycleTask.getAnimalHpDecreaseTask(), animalLifecycleTask.getObjectMultiplyTask());

        executorService.scheduleAtFixedRate(animalLifecycleTask, 1, 8, TimeUnit.SECONDS);
        executorService.scheduleAtFixedRate(plantGrowthTask, 40, 30, TimeUnit.SECONDS);
        executorService.scheduleAtFixedRate(statisticsTask, 0, 8, TimeUnit.SECONDS);
    }

   // лист травоядных с заданным значением
    private List<HerbivoreParent> createHerbivores(int countHerbivores) {
        List<HerbivoreParent> herbivores = new ArrayList<>();
        Random random = new Random();

        // Создаем по одному животному каждого вида
        herbivores.add(new Buffalo());
        herbivores.add(new Caterpillar());
        herbivores.add(new Deer());
        herbivores.add(new Duck());
        herbivores.add(new Goat());
        herbivores.add(new Horse());
        herbivores.add(new Mouse());
        herbivores.add(new Rabbit());
        herbivores.add(new Sheep());
        herbivores.add(new WildBoar());

        // Генерируем случайное количество животных каждого вида, не менее 1
        int remainingCount = countHerbivores - herbivores.size();
        for (int i = 0; i < remainingCount; i++) {
            // Генерируем случайный индекс для выбора вида животного
            int randomIndex = random.nextInt(herbivores.size());
            HerbivoreParent randomHerbivore = herbivores.get(randomIndex);
            try {
                // Создаем экземпляр животного через рефлексию
                HerbivoreParent newHerbivore = randomHerbivore.getClass().newInstance();
                herbivores.add(newHerbivore);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return herbivores;
    }

    // список хищников с заданным количеством
    private List<PredatorParent> createPredators(int countPredators) {
        List<PredatorParent> predators = new ArrayList<>();
        Random random = new Random();

        // Создаем по одному животному каждого вида
        predators.add(new Bear());
        predators.add(new Eagle());
        predators.add(new Fox());
        predators.add(new Snake());
        predators.add(new Wolf());

        // Генерируем случайное количество животных каждого вида, не менее 1
        int remainingCount = countPredators - predators.size();
        for (int i = 0; i < remainingCount; i++) {
            // Генерируем случайный индекс для выбора вида животного
            int randomIndex = random.nextInt(predators.size());
            PredatorParent randomPredator = predators.get(randomIndex);
            try {
                // Создаем экземпляр животного через рефлексию
                PredatorParent newPredator = randomPredator.getClass().newInstance();
                predators.add(newPredator);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return predators;
    }

    // создать список растений с заданным количеством
    private List<Plant> createPlants(int countPlants) {
        List<Plant> plants = new ArrayList<>();
        for (int i = 0; i < countPlants; i++) {
            plants.add(new Plant(1,200,"Grass"));
        }
        return plants;
    }

   //размещаем травоядных на острове
    public void placeHerbivores(int countHerbivores) {
        List<HerbivoreParent> herbivores = createHerbivores(countHerbivores);
        Random random;
        random = ThreadLocalRandom.current();
        for (HerbivoreParent herbivore : herbivores) {
            boolean placed = false;
            while (!placed) {
                int row = random.nextInt(IslandField.getInstance().getNumRows());
                int column = random.nextInt(IslandField.getInstance().getNumColumns());
                Location location = IslandField.getInstance().getLocation(row, column);
                if (location.getAnimals().stream().filter(c -> c.getNameOfLifeForm().equals(herbivore.getNameOfLifeForm())).toList().size() <= herbivore.getMaxAmountOnCell()) {
                    IslandField.getInstance().addAnimal(herbivore, row, column);
                    placed = true;
                }
            }
        }
    }

    // размещаем хищников на острове
    public void placePredators(int countPredators) {
        List<PredatorParent> predators = createPredators(countPredators);

        Random random = ThreadLocalRandom.current();
        for (PredatorParent predator : predators) {
            boolean placed = false;
            while (!placed) {
                int row = random.nextInt(IslandField.getInstance().getNumRows());
                int column = random.nextInt(IslandField.getInstance().getNumColumns());
                Location location = IslandField.getInstance().getLocation(row, column);
                if (location.getAnimals().stream().filter(c -> c.getNameOfLifeForm().equals(predator.getNameOfLifeForm())).toList().size() <= predator.getMaxAmountOnCell()) {
                    IslandField.getInstance().addAnimal(predator, row, column);
                    placed = true;
                }
            }
        }
    }
// размещаем растения
    public void placePlants(int countPlants) {
        List<Plant> plants = createPlants(countPlants);

        Random random = ThreadLocalRandom.current();
        for (Plant plant : plants) {
            boolean placed = false;
            while (!placed) {
                int row = random.nextInt(IslandField.getInstance().getNumRows());
                int column = random.nextInt(IslandField.getInstance().getNumColumns());
                Location location = IslandField.getInstance().getLocation(row, column);
                if (location.getPlants().size() <= plant.getMaxAmountOnCell()) {
                    IslandField.getInstance().addPlant(plant, row, column);
                    placed = true;
                }
            }
        }
    }

   //получаем текущее время симуляции
    public long getTimeNow() {
        return (System.currentTimeMillis() - startTime) / 1000;
    }

    public ScheduledExecutorService getExecutorService() {
        return executorService;
    }

    public int getCountHerbivores() {
        return countHerbivores;
    }

    public int getCountPlants() {
        return countPlants;
    }

    public int getCountPredators() {
        return countPredators;
    }
}
