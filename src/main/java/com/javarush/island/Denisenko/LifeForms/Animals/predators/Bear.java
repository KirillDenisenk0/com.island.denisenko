package com.javarush.island.Denisenko.LifeForms.Animals.predators;

import com.javarush.island.Denisenko.LifeForms.Animals.AnimalParent;
import com.javarush.island.Denisenko.PlayField.IslandField;
import com.javarush.island.Denisenko.PlayField.Location;

public class Bear  extends  PredatorParent{
    public Bear() {
        super(500, 2, 80, 5, "Bear");
    }


    @Override
    public double getChanceToEat(String foodName) {
        return switch (foodName) {
            case "Duck" -> 0.1;
            case "Buffalo" -> 0.2;
            case "Horse" -> 0.4;
            case "WildBoar" -> 0.5;
            case "Goat", "Sheep" -> 0.7;
            case "Deer", "Rabbit", "Snake" -> 0.8;
            case "Mouse" -> 0.9;
            default -> 0;
        };
    }


    @Override
    public void multiply(AnimalParent maybePartner) {
        if (maybePartner instanceof Bear){
            Location location = IslandField.getInstance().getLocation(maybePartner.getRow(), maybePartner.getColumn());
            IslandField.getInstance().addAnimal(new Bear(), location.getRow(), location.getColumn());
        }
    }
}
