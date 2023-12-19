package org.lld3;

import org.lld3.exception.NoWinnerException;
import org.lld3.model.Board;
import org.lld3.model.Player;
import org.lld3.model.Symbol;
import org.lld3.service.TictactoeGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Initializing .....");
        System.out.println("Creating players.....");
        List<Player> players = new ArrayList<>();
        Symbol player1Symbol = Symbol.builder()
                        .character('X')
                                .build();
        Player player1 = Player.builder()
                .playerName("Player - 1")
                .playerSymbol(player1Symbol)
                        .build();

        Symbol player2Symbol = Symbol.builder()
                .character('O')
                .build();

        Player player2 = Player.builder()
                .playerName("Player - 2")
                .playerSymbol(player2Symbol)
                .build();

        players.add(player1);
        players.add(player2);
        System.out.println("Players got created");
        System.out.println("Creating board");

        Board board = new Board(player1.getPlayerName()+"_"+player2.getPlayerName());
        TictactoeGame game = new TictactoeGame(sc);
        System.out.println("Playing...");
        Player winner = game.play(board, players).orElseThrow(() -> new NoWinnerException("All places got filled, its a draw"));

        System.out.println("Winner is: "+ winner.getPlayerName());
        sc.close();
    }

}