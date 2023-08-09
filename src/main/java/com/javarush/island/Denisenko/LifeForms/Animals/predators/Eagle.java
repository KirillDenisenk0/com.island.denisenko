package com.javarush.island.Denisenko.LifeForms.Animals.predators;

import com.javarush.island.Denisenko.LifeForms.Animals.AnimalParent;
import com.javarush.island.Denisenko.PlayField.IslandField;
import com.javarush.island.Denisenko.PlayField.Location;

public class Eagle extends  PredatorParent {

    public Eagle() {
        super(6, 3, 1, 20, "Eagle");
    }

    @Override
    public double getChanceToEat(String foodName) {
        return switch (foodName) {
            case "Fox" -> 0.1;
            case "Duck" -> 0.8;
            case "Rabbit", "Mouse" -> 0.9;
            default -> 0;
        };
    }


    @Override
    public void multiply(AnimalParent maybePartner) {
        if (maybePartner instanceof Eagle){
            Location location = IslandField.getInstance().getLocation(maybePartner.getRow(), maybePartner.getColumn());
            IslandField.getInstance().addAnimal(new Eagle(), location.getRow(), location.getColumn());
        }
    }
}
