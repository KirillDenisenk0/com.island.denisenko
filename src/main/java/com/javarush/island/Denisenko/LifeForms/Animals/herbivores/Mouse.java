package com.javarush.island.Denisenko.LifeForms.Animals.herbivores;
import com.javarush.island.Denisenko.LifeForms.Animals.AnimalParent;
import com.javarush.island.Denisenko.PlayField.IslandField;
import com.javarush.island.Denisenko.PlayField.Location;
public class Mouse extends  HerbivoreParent{
    public Mouse() {
        super(0.05, 1, 0.01, 500, "Mouse");
    }


    @Override
    public double getChanceToEat(String foodName) {
        return switch (foodName) {
            case "Caterpillar" -> 0.9;
            case "Plant" -> 1;
            default -> 0;
        };
    }


    @Override
    public void multiply(AnimalParent maybePartner) {
        if (maybePartner instanceof Mouse){
            Location location = IslandField.getInstance().getLocation(maybePartner.getRow(), maybePartner.getColumn());
            IslandField.getInstance().addAnimal(new Mouse(), location.getRow(), location.getColumn());
        }
    }
}
