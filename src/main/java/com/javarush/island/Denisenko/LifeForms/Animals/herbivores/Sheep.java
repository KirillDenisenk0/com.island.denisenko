package com.javarush.island.Denisenko.LifeForms.Animals.herbivores;
import com.javarush.island.Denisenko.LifeForms.Animals.AnimalParent;
import com.javarush.island.Denisenko.PlayField.IslandField;
import com.javarush.island.Denisenko.PlayField.Location;
public class Sheep extends  HerbivoreParent{
    public Sheep() {
        super(70, 3, 15, 140, "Sheep");
    }

    @Override
    public void multiply(AnimalParent maybePartner) {
        if (maybePartner instanceof Sheep){
            Location location = IslandField.getInstance().getLocation(maybePartner.getRow(), maybePartner.getColumn());
            IslandField.getInstance().addAnimal(new Sheep(), location.getRow(), location.getColumn());
        }
    }
}
