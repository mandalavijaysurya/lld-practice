package org.lld3.model;

public class Player {
    private String playerName;
    private Symbol playerSymbol;

    public String getPlayerName(){
        return this.playerName;
    }

    public Symbol getPlayerSymbol(){
        return this.playerSymbol;
    }
    public static Builder builder(){
        return new Builder();
    }
   public static class Builder{
       private String playerName;
       private Symbol playerSymbol;

       public Builder playerName(String playerName) {
           this.playerName = playerName;
           return this;
       }

       public Builder playerSymbol(Symbol playerSymbol) {
           this.playerSymbol = playerSymbol;
           return this;
       }

       public Player build(){
           Player player = new Player();
           player.playerName = this.playerName;
           player.playerSymbol = this.playerSymbol;
           return player;
       }
   }
}
