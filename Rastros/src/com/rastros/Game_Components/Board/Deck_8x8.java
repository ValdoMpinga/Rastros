package com.rastros.Game_Components.Board;

public final class Deck_8x8
{
    /**
     * @param getInstance: this is the parameter used to use the singleton Design Pattern using the board
     */
    private static Deck_8x8 getInstance;

    private Deck_8x8()
    {
        /**
         * Deck method with a 8x8 length
         * @param boardColumns: the number of board columns
         * @param boardRows: the number of board Rows
         * @param deck: its the deck itself
         */

        int boardColumns = 8;
        int boardRows = 8;

        char[][] deck = new char[boardColumns][boardColumns];
        deck[3][4]='W';
        deck[7][0]='1';
        deck[0][7]='2';


        System.out.print("   0" + "   1" + "   2" + "   3" + "   4" + "   5" + "   6" + "   7");    //those numbers are used
        //as columns indicators

        for (int i = 0; i < boardRows; i++)       //the Rows loop
        {
            System.out.print("\n" + /*(i + 1)*/i + "|" + "\t");   // loop that generates  the first division on every deck[0] position
            // using the '|'
            for (int j = 0; j < boardColumns; j++)
            {
                if(deck[i][j]== '\0')
                {
                    deck[i][j]=' ';   //initializes all empty spaces
                }

                System.out.print(deck[i][j]);   //prints the moves on the decks

                System.out.print("| \t");// loop that generates the division between the slots using the '|'
            }
            System.out.println();
        }
    }

    /**
     * This method always return the same object, on this context, the same board using Singleton patterb
     *
     * @return getInstance: returns the deck
     */
    public static Deck_8x8 getInstance()
    {
        if (getInstance == null)
        {
            getInstance = new Deck_8x8();
        }
        return getInstance;
    }
}
