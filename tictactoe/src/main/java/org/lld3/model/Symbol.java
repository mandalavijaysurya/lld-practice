package org.lld3.model;

public class Symbol {

    private Character character;

    private Symbol(Character playerCharacter){
        this.character = playerCharacter;
    }

    public Character getCharacter(){
        return character;
    }

    public Symbol setCharacter(Character character){
        this.character = character;
        return this;
    }
    
    public static Builder builder(){
        return new Builder();
    }
    
    public static class Builder{
        private Character character;
        
        public Builder character(Character playerCharacter){
            this.character = playerCharacter;
            return this;
        }
        
        public Symbol build(){
            return new Symbol(this.character);
        }
    }



}
