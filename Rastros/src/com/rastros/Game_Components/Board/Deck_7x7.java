package com.rastros.Game_Components.Board;

import java.util.ArrayList;
import java.util.Arrays;

public final class Deck_7x7
{

    //colors
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    /**
     * @param getInstance: this is the parameter used to use the singleton Design Pattern using the board
     * @param boardLines: the default row numbers of the deck
     * @param boardColumns: the default columns numbers of the deck
     * @param deck: its the game deck it self
     * @param minimaxDeck: the  deck to apply minimax
     * @param heuristicDeck: the  deck with the heuristics of each slot
     * @param current_i: tracks the white peace row position during the game
     * @param current_j: tracks the white peace column position during the game
     * @param moveList: the arrayList that stores the actual possible game moves
     * @param heuristicValue: the heuristic value of a specific slot on the heuristic deck
     */
    private static Deck_7x7 getInstance;
    private static int boardColumns = 7;
    private static int boardLines = 7;
    public static char[][] deck = new char[boardColumns][boardColumns];
    public static char[][] minimaxDeck = new char[boardColumns][boardColumns];
    private static int[][] heuristicDeck = new int[boardColumns][boardColumns];
    private static char[][] backupDeck = new char[boardColumns][boardColumns];
    private static int current_i = 2;
    private static int current_j = 4;
    private static int minimax_i = 2;
    private static int minimax_j = 4;
    private int heuristicValue = 0;
    private static int current_i_backup;
    private static int current_j_backup;
    ArrayList<String> moveList = new ArrayList<>(); //moves arrayList
    ArrayList<String> mininaxMoveList = new ArrayList<>(); //moves arrayList

    /**
     * Initializes the game
     */
    public void gameInitializer()
    {
        for (int i = 0; i < boardLines; i++)
        {
            for (int j = 0; j < boardColumns; j++)
            {
                if (deck[i][j] == '\0')
                    deck[i][j] = ' ';       //initializes all empty spaces
            }
            System.out.println();
        }

        deck[6][0] = '1'; //player one's home
        deck[0][6] = '2'; //player two's home
        deck[2][4] = 'W'; //where the game starts
        current_i = 2;
        current_j = 4;
    }

    /**
     * Initializes the heuristic deck
     */
    public void heuristicDeckInitializer()
    {
        //top deck initializer

        for (int i = 0; i <= 2; i++)
            heuristicDeck[i][0] = -1;

        for (int i = 0; i <= 2; i++)
            heuristicDeck[i][1] = -2;

        for (int i = 0; i <= 2; i++)
            heuristicDeck[i][2] = -3;

        for (int i = 0; i <= 2; i++)
            heuristicDeck[i][3] = -4;

        for (int i = 0; i <= 2; i++)
            heuristicDeck[i][4] = -5;

        heuristicValue = -9;
        for (int i = 0; i <= 2; i++)
        {
            heuristicDeck[i][5] = heuristicValue;
            heuristicValue++;
        }

        int heuristicValue = -10;
        for (int i = 0; i <= 2; i++)
        {
            heuristicDeck[i][6] = heuristicValue;
            heuristicValue++;
        }

        /*********************************************************/

        //middle to bottom deck initializer

        heuristicValue = 6;
        for (int i = 3; i <= 6; i++)
        {
            heuristicDeck[i][0] = heuristicValue;

            if (i == 5)
                heuristicValue += 2;
            else
                heuristicValue++;
        }

        heuristicValue = 6;
        for (int i = 3; i <= 6; i++)
        {
            heuristicDeck[i][1] = heuristicValue;
            heuristicValue++;

        }

        for (int i = 3; i <= 6; i++)
            heuristicDeck[i][2] = 5;

        for (int i = 3; i < 6; i++)
            heuristicDeck[i][3] = 4;

        for (int i = 3; i <= 6; i++)
            heuristicDeck[i][4] = 3;

        for (int i = 3; i <= 6; i++)
            heuristicDeck[i][5] = 2;

        for (int i = 3; i <= 6; i++)
            heuristicDeck[i][6] = 1;
    }

    /**
     * Deck method with a 7x7 length
     */
    private char[][] Deck_7x7()
    {

        System.out.print(ANSI_BLUE + "   0" + "   1" + "   2" + "   3" + "   4" + "   5" + "   6" + ANSI_BLUE); // a kind of the game borderlands and indicator

        for (int i = 0; i < boardLines; i++)
        {
            System.out.print(ANSI_BLUE + "\n" + i + "|" + "\t");   // loop that generates  the first division on every deck[0] position
            for (int j = 0; j < boardColumns; j++)             // using the '|'
            {
                if (deck[i][j] == '\0')
                {
                    deck[i][j] = ' ';   //initializes all empty spaces
                }

                System.out.print(deck[i][j]);     //initializes all empty spaces

                System.out.print(ANSI_PURPLE + "| \t" + ANSI_PURPLE);// loop that generates the division between the slots using the '|'
            }
            System.out.println();
        }

        return deck;
    }

    /**
     * Gets every possible moves on a specific [i][j] position
     *
     * @return movementNumber: the number of moves possible on the current position
     */
    public short getMoves()
    {
//        getInstance();

        char[][] gameDeck = Deck_7x7();
        short movementNumber = 0;    //the  movement id
        short topLineMoves = 0, middleLineMoves = 0, bottomLineMoves = 0;


        /*************************************************************************************************************/

        //Gets all the moves of the line above the 'W' piece

        if (current_i > 0)
        {
            current_i -= 1;   //goes one lines backward, that is, to the first line

            if (current_j == 6)
            {
                for (int spot = current_j; spot >= (current_j - 1); spot--)
                {
                    if (gameDeck[current_i][spot] != 'P' && current_i <= 6)
                    {
                        moveList.add(String.format("%s%s", current_i, spot));//add the moves the move list
                        topLineMoves++;
                        movementNumber++;
                    }
                }
            } else
            {
                for (int spot = (current_j + 1); spot >= (current_j - 1); spot--)
                {
                    if (spot > 6 || spot < 0)
                    {
                        break;
                    } else if (gameDeck[current_i][spot] != 'P' && current_i <= 6)//ensures that the spot is empty and its not outside the deck
                    {
                        moveList.add(String.format("%s%s", current_i, spot));//add the moves the move list
                        topLineMoves++;
                        movementNumber++;
                    }
                }
            }
            current_i += 1;   //goes one lines forward, that is, to the first line
        }

        System.out.println("------- MOVIMENTOS DA LINHA ACIMA DA PEÇA BRANCA -------");
        for (int i = 0; i < topLineMoves; i++)
            System.out.println((i + 1) + "-> " + moveList.get(i));

        /*************************************************************************************************************/

        //Gets all the moves of the line where the 'W' piece is

        middleLineMoves = topLineMoves;

        if (current_j == 6)
        {
            if (gameDeck[current_i][current_j - 1] != 'P' && current_i <= 6)
            {
                moveList.add(String.format("%s%s", current_i, current_j - 1)); //add the moves the move list
                middleLineMoves++;
                movementNumber++;
            }
        } else
        {
            for (int spot = (current_j + 1); spot >= (current_j - 1); spot -= 2)//get current lines moves
            {
                if (spot > 6 || spot < 0)//ensures that we always stay on the deck
                {
                    break;
                } else if (gameDeck[current_i][spot] != 'P' && current_i <= 6)  //ensures that the spot is empty and its not outside the deck
                {
                    moveList.add(String.format("%s%s", current_i, spot)); //add the moves the move list
                    middleLineMoves++;
                    movementNumber++;
                }
            }
        }

        System.out.println("------- MOVIMENTOS DA LINHA ATUAL DA PEÇA BRANCA -------");
        for (int i = topLineMoves; i < middleLineMoves; i++)
            System.out.println((i + 1) + "-> " + moveList.get(i));

        /*************************************************************************************************************/

        //Gets all the moves of the line bellow the 'W' piece

        bottomLineMoves = middleLineMoves;

        if (current_i < 6) //ensures that we never look new movement outside the deck
        {
            current_i += 1;   //goes one line forward

            if (current_j == 6)
            {
                for (int spot = current_j; spot >= (current_j - 1); spot--)
                {
                    if (gameDeck[current_i][spot] != 'P' && current_i <= 6)
                    {
                        moveList.add(String.format("%s%s", current_i, spot));//add the moves the move list
                        bottomLineMoves++;
                        movementNumber++;
                    }
                }
            } else
            {
                for (int spot = (current_j + 1); spot >= (current_j - 1); spot--)
                {
                    if (spot > 6 || spot < 0)
                    {
                        break;
                    } else if (gameDeck[current_i][spot] != 'P' && current_i <= 6)//ensures that the spot is empty and its not outside the deck
                    {
                        moveList.add(String.format("%s%s", current_i, spot));//add the moves the move list
                        bottomLineMoves++;
                        movementNumber++;

                    }
                }

            }
            current_i -= 1;   //goes one line backward
        }

        System.out.println("------- MOVIMENTOS DA LINHA BAIXO DA PEÇA BRANCA -------");
        for (int i = middleLineMoves; i < bottomLineMoves; i++)
            System.out.println((i + 1) + "-> " + moveList.get(i));

        /**********************************************************************************************************************************************/

        if (movementNumber == 0)
        {
            return 0;
        }

        System.out.println("\n");

        return movementNumber;
    }

    /**
     * This method always return the same object, on this context, the same board using Singleton patterb
     *
     * @return getInstance: returns the deck
     */
    public static Deck_7x7 getInstance()
    {


        if (getInstance == null)
        {
            getInstance = new Deck_7x7();
        }
        return getInstance;
    }

    /**
     * Writes the player's move on the board
     */
    public void writePlayOnBoard(int movement)
    {
        int i_move_position, j_move_position;
        String move = moveList.get(movement - 1);

        i_move_position = Character.getNumericValue(move.charAt(0));
        j_move_position = Character.getNumericValue(move.charAt(1));

        deck[current_i][current_j] = 'P';
        deck[i_move_position][j_move_position] = 'W';

        current_i = i_move_position;
        current_j = j_move_position;

        moveList.clear();   //clears the move list array
    }

    /**
     * Class that runs the deck
     */
    public void deckRunner()
    {

        System.out.print("   \t\t0" + "   \t1" + "   \t2" + "   \t3" + "   \t4" + "   \t5" + "   \t6");
        for (int i = 0; i < boardLines; i++)
        {
            System.out.print("\n" + i + "\t|" + "\t");   // loop that generates  the first division on every deck[0] position
            for (int j = 0; j < boardColumns; j++)             // using the '|'
            {

                if (deck[i][j] == '\0')
                    deck[i][j] = ' ';
                System.out.print(deck[i][j]);     //initializes all empty spaces


                System.out.print("\t| \t");// loop that generates the division between the slots using the '|'
            }
            System.out.println();
        }
    }

    /**
     * Class that runs the deck
     */
    public void HeuristicdeckRunner()
    {
        System.out.print(ANSI_GREEN + "   \t\t0" + "   \t1" + "   \t2" + "   \t3" + "   \t4" + "   \t5" + "   \t6" + ANSI_GREEN);

        for (int i = 0; i < boardLines; i++)
        {
            System.out.print(ANSI_GREEN + "\n" + i + "\t|" + "\t" + ANSI_GREEN);   // loop that generates  the first division on every deck[0] position
            for (int j = 0; j < boardColumns; j++)             // using the '|'
            {
                System.out.print(heuristicDeck[i][j]);     //initializes all empty spaces


                System.out.print(ANSI_GREEN + "\t| \t" + ANSI_GREEN);// loop that generates the division between the slots using the '|'
            }
            System.out.println();
        }
    }

    /**
     * gets the actual game state of the player vs player game
     *
     * @param playerOneHome: its the reference used to identify each players home after randomizing
     *                       the first player on the game, the return is always based on the player one's home
     * @return home heuristic, 10 if is the player one's home, else -10
     */
    public int getPlayerVsPlayerGameState(int playerOneHome)
    {
        if (playerOneHome == 1)
        {
            if (deck[6][0] == 'W')
            {
                return 10;
            } else if (deck[0][6] == 'W')
            {
                return -10;
            }

        } else if (playerOneHome == 2)
        {
            if (deck[0][6] == 'W')
            {
                return 10;
            } else if (deck[6][0] == 'W')
            {
                return -10;
            }
        }
        return 0;
    }

    /**
     * @param cpu_homeNumber:ts the reference used to identify each players home after randomizing
     *                          the first player on the game, the return is always based on the CPU's  home
     * @return home heuristic, 10 if is the player one's home, else -10
     */
    public int getPlayervsCpuGameState(int cpu_homeNumber)
    {
        if (cpu_homeNumber == 1)
        {
            if (deck[0][6] == 'W')
            {
                return 10;
            } else if (deck[6][0] == 'W')
            {
                return -10;
            }
        } else if (cpu_homeNumber == 2)
        {
            if (deck[6][0] == 'W')
            {
                return 10;
            } else if (deck[0][6] == 'W')
            {
                return -10;
            }
        }
        return 0;
    }

    /**
     * Backups the actual game state
     */

    /**
     * Resets the game
     */
    public void gameReset()
    {

        for (int i = 0; i < boardLines; i++)
        {
            for (int j = 0; j < boardColumns; j++)
            {

                deck[i][j] = ' ';   //initializes every null slot to ' ' empty spaces

                // System.out.print(ANSI_PURPLE + "| \t" + ANSI_PURPLE);// loop that generates the division between the slots using the '|'
            }
            System.out.println();
        }

    }

    /**
     * Resets the board after watching a tutorial
     */
    public void tutorialGameReset()
    {
        current_i = 2;
        current_j = 4;
    }

    /**
     * Gets the move of a specific index
     *
     * @param index: its an index
     * @return move index
     */
    public String getMoveIndex(int index)
    {
        getMovesWithoutDisplay();

        return moveList.get(index);
    }

    /**
     * //gets the heuristic according to the i and j index
     *
     * @return heuristic evaluation
     */
    public int getIndexHeuristic()
    {
        heuristicDeckInitializer();

        return heuristicDeck[minimax_i][minimax_j];
    }

    /**
     * @return deck
     */
    public char[][] deckRetornable()
    {
        return deck;
    }

    /**
     * specific move to display on the turorial
     */
    public void firstPlayForTutorialDemo()
    {
        deck[2][4] = 'W';
        deck[2][3] = 'P';
    }

    /**
     * specific move to display on the turorial
     */
    public void secoundPlayForTutorialDemo()
    {
        deck[2][4] = ' ';
        deck[6][5] = 'P';
        deck[6][5] = 'P';
        deck[5][6] = 'P';
        deck[5][5] = 'P';
        deck[6][6] = 'W';
    }


    public void clearMoveList()
    {
        moveList.clear();
    }

    /******************************************     MINIMAX     ***********************************************************/

    /**
     * Gets every possible moves on a specific [i][j] position without displaying the moves
     *
     * @return movementNumber: the number of moves possible on the current position
     */
    public short getMovesWithoutDisplay()
    {

        initializeMiniMaxDeck();
        short movementNumber = 0;    //the  movement id
        short topLineMoves = 0, middleLineMoves = 0, bottomLineMoves = 0;

        /*************************************************************************************************************/

        //Gets all the moves of the line above the 'W' piece

        if (minimax_i > 0)
        {
            minimax_i -= 1;   //goes one lines backward, that is, to the first line

            if (minimax_j == 6)
            {
                for (int spot = minimax_j; spot >= (minimax_j - 1); spot--)
                {
                    if (minimaxDeck[minimax_i][spot] != 'P' && minimax_i <= 6)
                    {
                        mininaxMoveList.add(String.format("%s%s", minimax_i, spot));//add the moves the move list
                        topLineMoves++;
                        movementNumber++;
                    }
                }
            } else
            {
                for (int spot = (minimax_j + 1); spot >= (minimax_j - 1); spot--)
                {
                    if (spot > 6 || spot < 0)
                    {
                        break;
                    } else if (minimaxDeck[minimax_i][spot] != 'P' && minimax_i <= 6)//ensures that the spot is empty and its not outside the deck
                    {
                        mininaxMoveList.add(String.format("%s%s", minimax_i, spot));//add the moves the move list
                        topLineMoves++;
                        movementNumber++;
                    }
                }
            }
            minimax_i += 1;   //goes one lines forward, that is, to the first line
        }

        /*************************************************************************************************************/

        //Gets all the moves of the line where the 'W' piece is

        middleLineMoves = topLineMoves;

        if (minimax_j == 6)
        {
            if (minimaxDeck[minimax_i][minimax_j - 1] != 'P' && minimax_i <= 6)
            {
                mininaxMoveList.add(String.format("%s%s", minimax_i, minimax_j - 1)); //add the moves the move list
                middleLineMoves++;
                movementNumber++;
            }
        } else
        {
            for (int spot = (minimax_j + 1); spot >= (minimax_j - 1); spot -= 2)//get current lines moves
            {
                if (spot > 6 || spot < 0)//ensures that we always stay on the deck
                {
                    break;
                } else if (minimaxDeck[minimax_i][spot] != 'P' && minimax_i <= 6)  //ensures that the spot is empty and its not outside the deck
                {
                    mininaxMoveList.add(String.format("%s%s", minimax_i, spot)); //add the moves the move list
                    middleLineMoves++;
                    movementNumber++;
                }
            }

            /*************************************************************************************************************/

            //Gets all the moves of the line bellow the 'W' piece

            bottomLineMoves = middleLineMoves;

            if (minimax_i < 6) //ensures that we never look new movement outside the deck
            {
                minimax_i += 1;   //goes one line forward

                if (minimax_j == 6)
                {
                    for (int spot = minimax_j; spot >= (minimax_j - 1); spot--)
                    {
                        if (minimaxDeck[minimax_i][spot] != 'P' && minimax_i <= 6)
                        {
                            mininaxMoveList.add(String.format("%s%s", minimax_i, spot));//add the moves the move list
                            bottomLineMoves++;
                            movementNumber++;
                        }
                    }
                } else
                {
                    for (int spot = (minimax_j + 1); spot >= (minimax_j - 1); spot--)
                    {
                        if (spot > 6 || spot < 0)
                        {
                            break;
                        } else if (minimaxDeck[minimax_i][spot] != 'P' && minimax_i <= 6)//ensures that the spot is empty and its not outside the deck
                        {
                            mininaxMoveList.add(String.format("%s%s", minimax_i, spot));//add the moves the move list
                            bottomLineMoves++;
                            movementNumber++;

                        }
                    }
                }
                minimax_i -= 1;   //goes one line backward
            }

            /**********************************************************************************************************************************************/
        }
        return movementNumber;
    }

    /**
     * Initializes the minimax deck, the deck to apply the minimax algorithm
     */
    public void initializeMiniMaxDeck()
    {
        gameInitializer();

        for (int i = 0; i < deck.length; i++)
            for (int j = 0; j < minimaxDeck[i].length; j++)
                minimaxDeck[i][j] = deck[i][j];


        minimax_i = current_i;
        minimax_j = current_j;
    }


    /**
     * Writes the move on the minimax deck
     *
     * @param movement: the movement that will be written to the deck
     */
    public void writePlayOnMinimaxBoard(int movement)
    {
      //  gameInitializer();
       // initializeMiniMaxDeck();

        int i_minimax_move_position, j_minimax_move_position;
        String move = mininaxMoveList.get(movement - 1);

        i_minimax_move_position = Character.getNumericValue(move.charAt(0));
        j_minimax_move_position = Character.getNumericValue(move.charAt(1));

        deck[minimax_i][minimax_j] = 'P';
        minimaxDeck[i_minimax_move_position][j_minimax_move_position] = 'W';

        minimax_i = i_minimax_move_position;
        minimax_j = j_minimax_move_position;

        mininaxMoveList.clear();   //clears the move list array
    }


    public void clearMinimaxMoveList()
    {
        mininaxMoveList.clear();
    }

}
