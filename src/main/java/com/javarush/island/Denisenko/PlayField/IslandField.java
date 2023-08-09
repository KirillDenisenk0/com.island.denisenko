package com.javarush.island.Denisenko.PlayField;

import com.javarush.island.Denisenko.LifeForms.Animals.AnimalParent;
import com.javarush.island.Denisenko.LifeForms.Plant.Plant;

import java.util.ArrayList;
import java.util.List;

public class IslandField {
    private Location[][] locations; // В виде острова будет двумерный массив, роль локаций в котором представляют ячейки
    private final int numRows = 100; //дефолтное значение из ТЗ
    private final int numColumns = 20; // дефолтное значение из ТЗ
    private static volatile IslandField instance;

    private IslandField() {
    }

    public static IslandField getInstance() {
        if (instance == null) {
            synchronized (IslandField.class) {
                if (instance == null) {
                    instance = new IslandField();
                }
            }
        }
        return instance;
    }

    public void initializeLocations(int numRows, int numColumns) {
        locations = new Location[numRows][numColumns];
        for (int i = 0; i < locations.length; i++) {
            for (int j = 0; j < locations[i].length; j++) {
                locations[i][j] = new Location(i, j);
            }
        }
    }


    public void initializeLocations() {
        locations = new Location[numRows][numColumns];
        for (int i = 0; i < locations.length; i++) {
            for (int j = 0; j < locations[i].length; j++) {
                locations[i][j] = new Location(i, j);
            }
        }
    }


    public synchronized Location getLocation(int row, int column) {
        return locations[row][column];
    }


    public void addAnimal(AnimalParent animal, int row, int column) {
        Location location = getLocation(row, column);
        location.addAnimal(animal);
    }


    public void removeAnimal(AnimalParent animal, int row, int column) {
        Location location = getLocation(row, column);
        location.removeAnimal(animal);
    }


    public void addPlant(Plant plant, int row, int column) {
        Location location = getLocation(row, column);
        location.addPlant(plant);
    }


    public void removePlant(Plant plant, int row, int column) {
        Location location = getLocation(row, column);
        location.removePlant(plant);
    }


    public synchronized List<AnimalParent> getAllAnimals() {
        List<AnimalParent> allAnimals = new ArrayList<>();
        for (Location[] row : locations) {
            for (Location location : row) {
                allAnimals.addAll(location.getAnimals());
            }
        }
        return allAnimals;
    }


    public List<Plant> getAllPlants() {
        List<Plant> allPlants = new ArrayList<>();
        for (Location[] row : locations) {
            for (Location location : row) {
                allPlants.addAll(location.getPlants());
            }
        }
        return allPlants;
    }
    public int getNumRows() {
        return numRows;
    }
    public int getNumColumns() {
        return numColumns;
    }
}
