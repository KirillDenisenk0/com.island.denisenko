package com.javarush.island.Denisenko.Thread.tasks;

import com.javarush.island.Denisenko.LifeForms.Animals.AnimalParent;
import com.javarush.island.Denisenko.PlayField.IslandField;

import java.util.List;

public class AnimalMoveTask implements  Runnable{
    @Override
    public void run() {
        List<AnimalParent> animals = IslandField.getInstance().getAllAnimals().stream().filter(c -> c.getCellSpeed() > 0).toList();
        for (AnimalParent animal : animals) {
            animal.move();
        }
    }
}
