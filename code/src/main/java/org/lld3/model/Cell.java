package org.lld3.model;

public class Cell {
    private Symbol playerSymbol;

    public Cell(Symbol symbol){
        this.playerSymbol = symbol;
    }

    public Symbol getPlayerSymbol() {
        return playerSymbol;
    }

    public void setSymbol(Symbol symbol) {
        this.playerSymbol = symbol;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Cell cell){
            return this.getPlayerSymbol().equals(cell.getPlayerSymbol());
        }
        return false;
    }
}
