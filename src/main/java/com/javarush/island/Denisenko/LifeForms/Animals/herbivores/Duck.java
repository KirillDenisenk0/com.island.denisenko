package com.javarush.island.Denisenko.LifeForms.Animals.herbivores;

import com.javarush.island.Denisenko.LifeForms.Animals.AnimalParent;
import com.javarush.island.Denisenko.PlayField.IslandField;
import com.javarush.island.Denisenko.PlayField.Location;

public class Duck  extends  HerbivoreParent{
    public Duck() {
        super(1, 4, 0.15, 200, "Duck");
    }

    @Override
    public double getChanceToEat(String foodName) {
        return switch (foodName) {
            case "Caterpillar" -> 0.9;
            case "Plant" -> 1;
            default -> 0;
        };
    }

    @Override
    public void multiply(AnimalParent maybePartner) {
        if (maybePartner instanceof Duck){
            Location location = IslandField.getInstance().getLocation(maybePartner.getRow(), maybePartner.getColumn());
            IslandField.getInstance().addAnimal(new Duck(), location.getRow(), location.getColumn());
        }
    }
}
