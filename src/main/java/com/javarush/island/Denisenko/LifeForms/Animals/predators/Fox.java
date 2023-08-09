package com.javarush.island.Denisenko.LifeForms.Animals.predators;

import com.javarush.island.Denisenko.LifeForms.Animals.AnimalParent;
import com.javarush.island.Denisenko.PlayField.IslandField;
import com.javarush.island.Denisenko.PlayField.Location;

public class Fox  extends  PredatorParent {

    public Fox() {
        super(8, 2, 2, 30, "Fox");
    }


    @Override
    public double getChanceToEat(String foodName) {
        return switch (foodName) {
            case "Caterpillar" -> 0.4;
            case "Duck" -> 0.6;
            case "Rabbit" -> 0.7;
            case "Mouse" -> 0.9;
            default -> 0;
        };
    }


    @Override
    public void multiply(AnimalParent maybePartner) {
        if (maybePartner instanceof Fox){
            Location location = IslandField.getInstance().getLocation(maybePartner.getRow(), maybePartner.getColumn());
            IslandField.getInstance().addAnimal(new Fox(), location.getRow(), location.getColumn());
        }
    }
}
