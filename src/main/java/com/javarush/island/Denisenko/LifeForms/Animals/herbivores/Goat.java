package com.javarush.island.Denisenko.LifeForms.Animals.herbivores;

import com.javarush.island.Denisenko.LifeForms.Animals.AnimalParent;
import com.javarush.island.Denisenko.PlayField.IslandField;
import com.javarush.island.Denisenko.PlayField.Location;

public class Goat extends  HerbivoreParent{

    public Goat() {
        super(60, 3, 10, 140, "Goat");
    }


    @Override
    public void multiply(AnimalParent maybePartner) {
        if (maybePartner instanceof Goat){
            Location location = IslandField.getInstance().getLocation(maybePartner.getRow(), maybePartner.getColumn());
            IslandField.getInstance().addAnimal(new Goat(), location.getRow(), location.getColumn());
        }
    }
}
