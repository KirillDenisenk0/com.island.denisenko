package com.javarush.island.Denisenko.LifeForms.Animals;

import com.javarush.island.Denisenko.Exception.ObjectNotLifeFormException;
import com.javarush.island.Denisenko.LifeForms.LifeFormParent;
import com.javarush.island.Denisenko.LifeForms.Plant.Plant;
import com.javarush.island.Denisenko.PlayField.IslandField;
import com.javarush.island.Denisenko.PlayField.Location;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public abstract class  AnimalParent extends LifeFormParent {
    private  final int cellSpeed;
    private final double maxHealthPoints;
    private double healthPoints;

    public AnimalParent(double weight, int cellSpeed , double maxHealthPoints,int maxAmountOnCell,String nameOfLifeForm) {
        super(weight, maxAmountOnCell, nameOfLifeForm);
        this.cellSpeed = cellSpeed;
        this.maxHealthPoints = maxHealthPoints;
        this.healthPoints = maxHealthPoints;
    }

    public boolean eat (Object food){
        double chanceToEat;
        LifeFormParent lifeFormParent = null;
        boolean isAnimalEatFoodOrNot;

        if(food instanceof  LifeFormParent){
            lifeFormParent =(LifeFormParent) food;

        } else {
            try {
                throw  new ObjectNotLifeFormException("This is not lifeForm itself!");
            }catch (ObjectNotLifeFormException e){
                e.getMessage();
            }
        }

        String nameOfFood = lifeFormParent.getNameOfLifeForm();
        chanceToEat = getChanceToEat(nameOfFood);

        isAnimalEatFoodOrNot = ThreadLocalRandom.current().nextDouble() < chanceToEat;

        if(isAnimalEatFoodOrNot){
            setHealthPoints(Math.min((getHealthPoints() + lifeFormParent.getWeight()), getMaxHealthPoints()));
            Location location = IslandField.getInstance().getLocation(lifeFormParent.getRow(), lifeFormParent.getColumn());
            if (lifeFormParent instanceof AnimalParent animal) {
                if (location.getAnimals().contains(animal)) {
                    IslandField.getInstance().removeAnimal(animal, location.getRow(), location.getColumn());
                } else {
                    return false;
                }
            } else {
                Plant plant = (Plant) lifeFormParent;
                if (location.getPlants().size() > 0) {
                    IslandField.getInstance().removePlant(plant, location.getRow(), location.getColumn());
                } else {
                    return false;
                }
            }
        }
        return  isAnimalEatFoodOrNot;
    }



    public abstract double getChanceToEat(String foodName);


    public abstract void multiply(AnimalParent partner);


    public void move() {
        Random random = new Random();
        int randomCells = random.nextInt(getCellSpeed()) + 1;
        //  случайное направление вверх, вниз, влево или вправо
        int direction = random.nextInt(4);
        // Вычисляем новые координаты в зависимости от направления
        int newRow = getRow();
        int newColumn = getColumn();
        switch (direction) {
            case 0 -> // Вверх
                    newRow -= randomCells;
            case 1 -> // Вниз
                    newRow += randomCells;
            case 2 -> // Влево
                    newColumn -= randomCells;
            case 3 -> // Вправо
                    newColumn += randomCells;
        }
        // Проверка на то , чтобы новые полученные  координаты не выходили за установленные границы
        while (newRow < 0 || newRow >= IslandField.getInstance().getNumRows() || newColumn < 0 || newColumn >= IslandField.getInstance().getNumColumns()) {
            randomCells = random.nextInt(getCellSpeed()) + 1;
            direction = random.nextInt(4);

            newRow = getRow();
            newColumn = getColumn();
            switch (direction) {
                case 0 -> // Вверх
                        newRow -= randomCells;
                case 1 -> // Вниз
                        newRow += randomCells;
                case 2 -> // Влево
                        newColumn -= randomCells;
                case 3 -> // Вправо
                        newColumn += randomCells;
            }
        }
        IslandField.getInstance().removeAnimal(this, getRow(), getColumn()); // Обновление новых координат
        setRow(newRow);
        setColumn(newColumn);
        IslandField.getInstance().addAnimal(this, newRow, newColumn);
    }


    public int getCellSpeed() {
        return cellSpeed;
    }

    public double getMaxHealthPoints() {
        return maxHealthPoints;
    }

    public double getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(double healthPoints) {
        this.healthPoints = healthPoints;
    }
}
