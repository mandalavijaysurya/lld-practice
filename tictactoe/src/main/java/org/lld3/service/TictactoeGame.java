package org.lld3.service;


import org.lld3.exception.WrongMoveException;
import org.lld3.model.Board;
import org.lld3.model.Cell;
import org.lld3.model.Player;
import org.lld3.model.Symbol;

import java.util.*;

public class TictactoeGame {

    private final Scanner scanner;
    private final List<HashMap<Symbol, Integer>> rowHashMaps;
    private final List<HashMap<Symbol, Integer>> columnHashMaps;


    public TictactoeGame(Scanner sc){
        scanner = sc;
        rowHashMaps = new ArrayList<>();
        columnHashMaps = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            rowHashMaps.add(new HashMap<>());
            columnHashMaps.add(new HashMap<>());
        }
    }


    public Optional<Player> play(Board board, List<Player> players) {
        int i = 0;
        while (Board.isBoardNotFull(board.getBoard())) {
            Player player = players.get(i % players.size());
            System.out.println("Enter row for move:");
            int row = scanner.nextInt();
            System.out.println("Enter column for move:");
            int column = scanner.nextInt();
            Cell cell = board.getBoard().get(row).get(column);

            // Checking whether the cell is not null. If not null, then cell was filled with symbol
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
            updateHashMaps(row, column,player);
            board.printBoard();
            if(Board.checkBoard(board, player, rowHashMaps, columnHashMaps)){
                return Optional.of(player);
            }
            i++;
        }
        return Optional.empty();
    }

    private void updateHashMaps(int row, int column, Player player){
        rowHashMaps.get(row)
                .put(player.getPlayerSymbol(), rowHashMaps.get(row)
                        .getOrDefault(player.getPlayerSymbol(),0)+1);
        columnHashMaps.get(column)
                .put(player.getPlayerSymbol(), columnHashMaps.get(column)
                        .getOrDefault(player.getPlayerSymbol(),0)+ 1);
    }




}
