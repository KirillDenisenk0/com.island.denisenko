package com.javarush.island.Denisenko.LifeForms.Animals.predators;

import com.javarush.island.Denisenko.LifeForms.Animals.AnimalParent;

public abstract class PredatorParent extends AnimalParent {
    public PredatorParent(double weight, int cellSpeed, double maxHealthPoints, int maxAmountOnCell, String name) {
        super(weight, cellSpeed, maxHealthPoints, maxAmountOnCell, name);
    }

    @Override
    public double getChanceToEat(String foodName) {
        return 0;
    }

    @Override
    public void multiply(AnimalParent partner) {

    }
}
