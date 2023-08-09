package com.javarush.island.Denisenko.LifeForms.Animals.herbivores;

import com.javarush.island.Denisenko.LifeForms.Animals.AnimalParent;
import com.javarush.island.Denisenko.PlayField.IslandField;
import com.javarush.island.Denisenko.PlayField.Location;

public class Caterpillar extends  HerbivoreParent {
    public Caterpillar() {
        super(0.01, 0, 0, 1000, "Caterpillar");
    }

    @Override
    public void multiply(AnimalParent maybePartner) {
        if (maybePartner instanceof Caterpillar){
            Location location = IslandField.getInstance().getLocation(maybePartner.getRow(), maybePartner.getColumn());
            IslandField.getInstance().addAnimal(new Caterpillar(), location.getRow(), location.getColumn());
        }
    }
}
