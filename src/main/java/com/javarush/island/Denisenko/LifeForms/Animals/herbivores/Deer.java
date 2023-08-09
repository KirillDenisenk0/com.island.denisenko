package com.javarush.island.Denisenko.LifeForms.Animals.herbivores;

import com.javarush.island.Denisenko.LifeForms.Animals.AnimalParent;
import com.javarush.island.Denisenko.PlayField.IslandField;
import com.javarush.island.Denisenko.PlayField.Location;

public class Deer extends  HerbivoreParent {
    public Deer() {
        super(300, 4, 50, 20, "Deer");
    }

    @Override
    public void multiply(AnimalParent maybePartner) {
        if (maybePartner instanceof Deer){
            Location location = IslandField.getInstance().getLocation(maybePartner.getRow(), maybePartner.getColumn());
            IslandField.getInstance().addAnimal(new Deer(), location.getRow(), location.getColumn());
        }
    }
}
