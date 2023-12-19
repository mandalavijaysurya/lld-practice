package org.lld3.model;

import java.util.ArrayList;
import java.util.HashMap;
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

    public static boolean checkBoard(Board board, Player player, List<HashMap<Symbol, Integer>> rowHashMaps, List<HashMap<Symbol, Integer>> columnHashMaps){
        for(HashMap<Symbol, Integer> hm: rowHashMaps){
            for(Symbol key: hm.keySet()){
                if(hm.get(key) == 3)
                    return true;
            }
        }

        for(HashMap<Symbol, Integer> hm: columnHashMaps){
            for(Symbol key: hm.keySet()){
                if (hm.get(key) == 3)
                    return true;
            }
        }
        List<List<Cell>> gameBoard = board.getBoard();
        int count = 0;
        for(int i = 0; i < gameBoard.size(); i++){
            Cell cell = gameBoard.get(i).get(i);
            if(cell != null && cell.getPlayerSymbol().equals(player.getPlayerSymbol())){
                count++;
            }
        }
        if(count == gameBoard.size())
            return true;
        count = 0;

        for(int i = 0; i < gameBoard.size(); i++){
            Cell cell = board.getBoard().get(i).get(gameBoard.size()-i-1);
            if(cell != null && cell.getPlayerSymbol().equals(player.getPlayerSymbol())){
                count++;
            }
        }
        return count == gameBoard.size();
    }

    public static boolean isBoardNotFull(List<List<Cell>> board){
        for(List<Cell> rowCell: board){
            for(Cell cell: rowCell){
                if(cell == null)
                    return true;
            }
        }
        return false;
    }
}
