package com.rastros.Game_Components.Algorithms;

import com.rastros.Game_Components.Board.Deck_7x7;

import java.util.Random;

public class EasyAlgorithm
{

    /**
     * Method that generates moves to the player VS CPU easy mode
     * @param possibleMoves: varuable that will store all the possible moves
     * @return move: the number of possible moves
     */
    public int move(int possibleMoves)
    {
        Random randomMove=new Random();
        int move=randomMove.nextInt(possibleMoves-1)+1;

        System.out.println("A Katarina jogou: "+move);
        return move;
    }
}
