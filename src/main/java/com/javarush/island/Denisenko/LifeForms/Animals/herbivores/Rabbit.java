package com.javarush.island.Denisenko.LifeForms.Animals.herbivores;
import com.javarush.island.Denisenko.LifeForms.Animals.AnimalParent;
import com.javarush.island.Denisenko.PlayField.IslandField;
import com.javarush.island.Denisenko.PlayField.Location;

public class Rabbit extends  HerbivoreParent{
    public Rabbit() {
        super(2, 2, 0.45, 150, "Rabbit");
    }


    @Override
    public void multiply(AnimalParent maybePartner) {
        if (maybePartner instanceof Rabbit){
            Location location = IslandField.getInstance().getLocation(maybePartner.getRow(), maybePartner.getColumn());
            IslandField.getInstance().addAnimal(new Rabbit(), location.getRow(), location.getColumn());
        }
    }
}
