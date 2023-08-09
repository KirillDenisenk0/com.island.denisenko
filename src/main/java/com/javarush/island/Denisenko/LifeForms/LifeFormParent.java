package com.javarush.island.Denisenko.LifeForms;

public class LifeFormParent {
    private  final double weight;
    private  final int maxAmountOnCell;
    private final String nameOfLifeForm;
    private int row;
    private  int column;

    public LifeFormParent(double weight, int maxAmountOnCell, String nameOfLifeForm) {
        this.weight = weight;
        this.maxAmountOnCell = maxAmountOnCell;
        this.nameOfLifeForm = nameOfLifeForm;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public double getWeight() {
        return weight;
    }

    public int getMaxAmountOnCell() {
        return maxAmountOnCell;
    }

    public String getNameOfLifeForm() {
        return nameOfLifeForm;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
