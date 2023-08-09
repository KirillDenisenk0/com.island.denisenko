package com.javarush.island.Denisenko.LifeForms.Animals.herbivores;

import com.javarush.island.Denisenko.LifeForms.Animals.AnimalParent;
import com.javarush.island.Denisenko.PlayField.IslandField;
import com.javarush.island.Denisenko.PlayField.Location;

public class Buffalo extends  HerbivoreParent {
    public Buffalo() {
        super(700, 3, 100, 10, "Buffalo");
    }

    @Override
    public void multiply(AnimalParent maybePartner) {
        if (maybePartner instanceof Buffalo){
            Location location = IslandField.getInstance().getLocation(maybePartner.getRow(), maybePartner.getColumn());
            IslandField.getInstance().addAnimal(new Buffalo(), location.getRow(), location.getColumn());
        }
    }
}
