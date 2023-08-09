package com.javarush.island.Denisenko.LifeForms.Animals.herbivores;
import com.javarush.island.Denisenko.LifeForms.Animals.AnimalParent;
import com.javarush.island.Denisenko.PlayField.IslandField;
import com.javarush.island.Denisenko.PlayField.Location;
public class WildBoar extends  HerbivoreParent {
    public WildBoar() {
        super(400, 2, 50, 50, "WildBoar");
    }


    @Override
    public double getChanceToEat(String foodName) {
        return switch (foodName) {
            case "Mouse" -> 0.5;
            case "Caterpillar" -> 0.9;
            case "Plant" -> 1;
            default -> 0;
        };
    }

    @Override
    public void multiply(AnimalParent maybePartner) {
        if (maybePartner instanceof WildBoar){
            Location location = IslandField.getInstance().getLocation(maybePartner.getRow(), maybePartner.getColumn());
            IslandField.getInstance().addAnimal(new WildBoar(), location.getRow(), location.getColumn());
        }
    }
}
