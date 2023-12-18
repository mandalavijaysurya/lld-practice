package org.lld3.model;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<List<Cell>> board;
    private String boardName;

    public Board(String boardName) {
        board = new ArrayList<>();
        for(int i = 0;  i < 3; i++){
            board.add(new ArrayList<>());
            for(int j = 0; j < 3; j++){
                board.get(i).add(null);
            }
        }
        this.boardName = boardName;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public List<List<Cell>> getBoard() {
        return board;
    }

    public void printBoard(){
        for(List<Cell> rowCells : board){
            for(Cell cell: rowCells){
                if(cell == null)
                    System.out.print("| |");
                else
                    System.out.print("|"+cell.getPlayerSymbol().getCharacter()+"|");
            }
            System.out.println();
        }
    }
}
