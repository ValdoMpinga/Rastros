package com.rastros.Game_Components.Algorithms;

import com.rastros.Game_Components.Board.Deck_7x7;
import com.rastros.Main;

import java.util.ArrayList;

public class MiniMax
{
    /**
     * @param evaluations: Arraylist with all of the evaluations of a specific move
     * @param move: represents the move that is being  evaluated by the minimax
     * @param index: the move index
     * @param moveIndex: the index of the playing move that will be returned to play on the deck
     */
    ArrayList<Integer> evaluations = new ArrayList<>();
    Deck_7x7 deckInstance = new Deck_7x7();
    private int move = 1;
    private int index = 0;
    int moveIndex;

    /**
     * The minimax is used according the heuristic deck, which is a deck that contains a specific evaluation for each playing spot
     * The minimax looks for the slot with the highest evaluation if maximing, else looks for the lowest.
     * The moves for the evaluatios are played on a different deck, the Minimax Board, of the Deck_7x7 class
     *
     * @param depth: the searching depth
     * @param maximazingPlayer:true for maximazing, else, false
     */
    public void minimax(int depth, boolean maximazingPlayer)
    {
        if (index == 3)         //stops on the 2nd iteration
        {
            index=0;
            deckInstance.clearMinimaxMoveList();
            return;
        }
        if (depth == 0)
        {
            deckInstance.initializeMiniMaxDeck();
            evaluations.add(deckInstance.getIndexHeuristic());      //adds the evaluation the evaluation arrayList
            deckInstance.clearMoveList();                           //clears the move list
            move++;

            if (move <= deckInstance.getMovesWithoutDisplay())      //deckInstance.getMovesWithoutDisplay() gets onlu the number
            {                                                       //of moves without displaying them
                index++;
                depth = 3;
            }
        }

        if (maximazingPlayer)
        {
            int play=deckInstance.getMovesWithoutDisplay()-index;
            deckInstance.writePlayOnMinimaxBoard(play);
            minimax(depth - 1, false);

        } else
        {
            int play=(deckInstance.getMovesWithoutDisplay() - (deckInstance.getMovesWithoutDisplay() - 1)) + index;
            deckInstance.writePlayOnMinimaxBoard(play);
            minimax(depth - 1, true);
        }
    }

    /**
     * Returns the moves after evaluating them
     * @param cpusHome: indicates the cpu's home after randomizing the first player
     * @return
     */
    public int getMoveThroughtEvaluation(int cpusHome,boolean maximazingPlayer)
    {
        minimax(3,maximazingPlayer);
        int highestEvaluation=evaluations.get(0);   //initializes with the first evaluation from the evaluations list
        int lowerEvaluation=evaluations.get(0);     //initializes with the first evaluation from the evaluations list
        moveIndex=1;

        if(cpusHome==1 )
        {
            for(int i=0;i<evaluations.size();i++)
            {
                if(evaluations.get(i)>highestEvaluation)
                {
                    moveIndex++;
                    if(evaluations.get(i)==10)      //its the maximizing player's goal
                    {
                        moveIndex++;
                        return moveIndex;
                    }
                    moveIndex++;

                }
            }
        }else if(cpusHome==2)
        {
            for(int i=0;i<evaluations.size();i++)
            {
                if(evaluations.get(i)<lowerEvaluation)
                {
                    moveIndex++;
                    if(evaluations.get(i)==-10)     //its the minimizing player's goal
                    {
                        moveIndex++;
                        return moveIndex;
                    }
                }
            }
        }
        evaluations.clear();
        return moveIndex;
    }
}

