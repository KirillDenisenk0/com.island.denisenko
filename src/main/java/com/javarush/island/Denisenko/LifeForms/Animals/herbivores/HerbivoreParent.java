package com.javarush.island.Denisenko.LifeForms.Animals.herbivores;

import com.javarush.island.Denisenko.LifeForms.Animals.AnimalParent;

public abstract class HerbivoreParent extends AnimalParent {

    public HerbivoreParent(double weight, int sellSpeed, double maxHealthPoints, int maxAmountOnCell, String name) {
        super(weight, sellSpeed, maxHealthPoints, maxAmountOnCell, name);
    }
    @Override
    public double getChanceToEat(String foodName) {
        return switch (foodName) {
            case "Plant" -> 1;
            default -> 0;
        };
    }
}
