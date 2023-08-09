package com.javarush.island.Denisenko.LifeForms.Animals.predators;

import com.javarush.island.Denisenko.LifeForms.Animals.AnimalParent;
import com.javarush.island.Denisenko.PlayField.IslandField;
import com.javarush.island.Denisenko.PlayField.Location;

public class Snake extends  PredatorParent {
    public Snake() {
        super(15, 1, 3, 30, "Snake");
    }

    @Override
    public double getChanceToEat(String foodName) {
        return switch (foodName) {
            case "Duck" -> 0.1;
            case "Fox" -> 0.15;
            case "Rabbit" -> 0.2;
            case "Mouse" -> 0.4;
            default -> 0;
        };
    }


    @Override
    public void multiply(AnimalParent maybePartner) {
        if (maybePartner instanceof Snake) {
            Location location = IslandField.getInstance().getLocation(maybePartner.getRow(), maybePartner.getColumn());
            IslandField.getInstance().addAnimal(new Snake(), location.getRow(), location.getColumn());
        }
    }
}
