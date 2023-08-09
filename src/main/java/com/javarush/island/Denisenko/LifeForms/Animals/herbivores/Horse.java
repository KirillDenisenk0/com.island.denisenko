package com.javarush.island.Denisenko.LifeForms.Animals.herbivores;

import com.javarush.island.Denisenko.LifeForms.Animals.AnimalParent;
import com.javarush.island.Denisenko.PlayField.IslandField;
import com.javarush.island.Denisenko.PlayField.Location;
public class Horse extends  HerbivoreParent {

    public Horse() {
        super(400, 4, 60, 20, "Horse");
    }


    @Override
    public void multiply(AnimalParent maybePartner) {
        if (maybePartner instanceof Horse){
            Location location = IslandField.getInstance().getLocation(maybePartner.getRow(), maybePartner.getColumn());
            IslandField.getInstance().addAnimal(new Horse(), location.getRow(), location.getColumn());
        }
    }
}
