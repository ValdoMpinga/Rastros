package com.rastros.Game_Components.Algorithms;

import com.rastros.Game_Components.Board.Deck_7x7;

import java.util.Random;

public class MediumAlgorithm
{
    /**
     * Method thar returns the cpu's move while playing to slot 1
     * @return move
     */
    public int homeOneAlgorithm()
    {
        Random randomizeOptimalMove = new Random();

        return  randomizeOptimalMove.nextInt(2 - 1)+1;

    }

    /**
     * Method thar returns the cpu's move while playing to slot 2
     * @return move
     */
    public int  homeTwoAlgorithm()
    {
        Deck_7x7 accessDeckMethods=new Deck_7x7();

        return accessDeckMethods.getMoves();
    }
}
