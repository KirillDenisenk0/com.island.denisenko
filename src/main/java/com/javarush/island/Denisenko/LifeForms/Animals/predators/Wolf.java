package com.javarush.island.Denisenko.LifeForms.Animals.predators;

import com.javarush.island.Denisenko.LifeForms.Animals.AnimalParent;
import com.javarush.island.Denisenko.LifeForms.LifeFormParent;
import com.javarush.island.Denisenko.PlayField.IslandField;
import com.javarush.island.Denisenko.PlayField.Location;

public class Wolf extends PredatorParent {
    public Wolf() {
        super(50,3,8,30,"Wolf");
    }

   // шанс на поедание других форм жизни
    @Override
    public double getChanceToEat(String foodName) {
        return switch (foodName) {
            case "Horse", "Buffalo" -> 0.1;
            case "Deer", "WildBoar" -> 0.15;
            case "Duck" -> 0.4;
            case "Goat", "Rabbit" -> 0.6;
            case "Sheep" -> 0.7;
            case "Mouse" -> 0.8;
            default -> 0;
        };
    }

    // размножение
    @Override
    public void multiply(AnimalParent maybePartner) {
        if (maybePartner instanceof Wolf) {
            Location location = IslandField.getInstance().getLocation(maybePartner.getRow(), maybePartner.getColumn());
            IslandField.getInstance().addAnimal(new Wolf(), location.getRow(), location.getColumn());
        }
    }
}
