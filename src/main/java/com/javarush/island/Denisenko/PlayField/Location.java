package com.javarush.island.Denisenko.PlayField;

import com.javarush.island.Denisenko.LifeForms.Animals.AnimalParent;
import com.javarush.island.Denisenko.LifeForms.LifeFormParent;
import com.javarush.island.Denisenko.LifeForms.Plant.Plant;

import java.util.ArrayList;
import java.util.List;

public class Location {
    private final int row;
    private final int column;
    private final List<AnimalParent> animals;
    private final List<Plant> plants;

    public Location(int row, int column) {
        this.row = row;
        this.column = column;

        animals = new ArrayList<>();
        plants = new ArrayList<>();
    }

    public void addAnimal(AnimalParent animal) {
        animal.setRow(row);
        animal.setColumn(column);

        animals.add(animal);
    }


    public void removeAnimal(AnimalParent animal) {
        animals.remove(animal);
    }

    public void addPlant(Plant plant) {
        plant.setRow(row);
        plant.setColumn(column);
        plants.add(plant);
    }


    public void removePlant(Plant plant) {
        plants.remove(plant);
    }


    public List<Plant> getPlants() {
        return plants;
    }


    public List<AnimalParent> getAnimals() {
        return animals;
    }


    public List<LifeFormParent> getLifeForms() {
        List<LifeFormParent> lifeForms = new ArrayList<>();
        lifeForms.addAll(animals);
        lifeForms.addAll(plants);
        return lifeForms;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}

