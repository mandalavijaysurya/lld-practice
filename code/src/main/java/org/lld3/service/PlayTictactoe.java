package org.lld3.service;


import org.lld3.exception.WrongMoveException;
import org.lld3.model.Board;
import org.lld3.model.Cell;
import org.lld3.model.Player;
import org.lld3.model.Symbol;

import java.util.*;

public class PlayTictactoe {

    private final Scanner scanner;
    private final List<HashMap<Symbol, Integer>> rowHashMaps;
    private final List<HashMap<Symbol, Integer>> columnHashMaps;


    public PlayTictactoe(Scanner sc){
        this.scanner = sc;
        rowHashMaps = new ArrayList<>();
        columnHashMaps = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            rowHashMaps.add(new HashMap<>());
            columnHashMaps.add(new HashMap<>());
        }
    }


    public Optional<Player> playTictactoe(Board board, List<Player> players) {
        int i = 0;
        while (isBoardNotFull(board.getBoard())) {
            Player player = players.get(i % players.size());
            System.out.println("Enter row for move");
            int row = scanner.nextInt();
            System.out.println("Enter column for move");
            int column = scanner.nextInt();
            Cell cell = board.getBoard().get(row).get(column);
            if(cell != null){
                try{
                    throw new WrongMoveException("Position at row "+ row +" and column "+column+" were already filled");
                }
                catch(WrongMoveException ex){
                    System.out.println(ex.getMessage());
                }
                continue;
            }
            cell = new Cell(player.getPlayerSymbol());
            board.getBoard().get(row).set(column,cell);
            rowHashMaps.get(row)
                    .put(player.getPlayerSymbol(), rowHashMaps.get(row)
                            .getOrDefault(player.getPlayerSymbol(),0)+1);
            columnHashMaps.get(column)
                            .put(player.getPlayerSymbol(), columnHashMaps.get(column)
                                    .getOrDefault(player.getPlayerSymbol(),0)+ 1);
            board.printBoard();
            if(checkBoard(board, player)){
                return Optional.of(player);
            }
            i++;
        }
        return Optional.empty();
    }

    private boolean checkBoard(Board board, Player player){
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
        if(count == gameBoard.size())
            return true;

        return false;
    }

    private boolean isBoardNotFull(List<List<Cell>> board){
        for(List<Cell> rowCell: board){
            for(Cell cell: rowCell){
                if(cell == null)
                    return true;
            }
        }
        return false;
    }
}
