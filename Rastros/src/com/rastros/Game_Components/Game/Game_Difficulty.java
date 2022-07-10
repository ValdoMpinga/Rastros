package com.rastros.Game_Components.Game;

import com.rastros.Game_Components.Algorithms.EasyAlgorithm;
import com.rastros.Game_Components.Algorithms.MediumAlgorithm;
import com.rastros.Game_Components.Algorithms.MiniMax;
import com.rastros.Game_Components.Board.Deck_7x7;
import com.rastros.Game_Components.Menu.Menus;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Game_Difficulty
{
    //colors

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    //Instances

    Game_Mode accessGameModeMethods = new Game_Mode();
    Menus backToMenu = new Menus();
    MiniMax miniMax = new MiniMax();

    //Variables

    /**
     * @param arePlayersRandomized: on each game start, checks if the players were randomized to find the match first player.
     * @param gameRound: counts the game moves.
     * @param cpusHomeNumber: gets the cpu's home.
     * @param cpusTurn: stores which player turns it is, pair numbers for the challenger and odd numbers for CPU .
     * @param state: gets the game state, draw, win or loss.
     */
    private static boolean arePlayersRandomized = false;
    private static int gameRound = 1;
    private static int cpusHomeNumber = 0;
    private static int cpusTurn = 0;
    public static int state = 0;

    //PLAYER VS CPU METHODS BELOW

    /**
     * Easy mode algorithm, plays randomly
     *
     * @return game state
     * @throws InterruptedException
     * @throws IOException
     */
    public int Easy_Mode() throws InterruptedException, IOException
    {
        var playOnDeck = new Deck_7x7();    //deck class instance

        Scanner readPlayersMove = new Scanner(System.in);
        int possibleMovements = playOnDeck.getMoves();  //gets the current move's possible movements

        if (!arePlayersRandomized)
        {
            cpusTurn = randomizeFirstPlayer();

            if (cpusTurn == 0)
                cpusHomeNumber = 1;
            else
                cpusHomeNumber = 2;

            arePlayersRandomized = true;
        }

        state = playOnDeck.getPlayervsCpuGameState(cpusHomeNumber);    //checks the game state

        if (state == 10)      //if the CPU wins
        {
            playOnDeck.deckRunner();
            System.out.println("A Katarina venceu o jogo");

            if (accessGameModeMethods.doYouWantToPlayAgain() == 1)
            {
                playOnDeck.gameReset();
                playOnDeck.gameInitializer();
                arePlayersRandomized = false;
                Easy_Mode();

            } else
            {
                playOnDeck.gameReset();
                playOnDeck.gameInitializer();
                arePlayersRandomized = false;
                backToMenu.Main_Menu();
            }

        } else if (state == -10)   //if the challenger wins
        {
            playOnDeck.deckRunner();

            System.out.println("O desafinate é o vencedor");

            if (accessGameModeMethods.doYouWantToPlayAgain() == 1)
            {
                playOnDeck.gameReset();
                playOnDeck.gameInitializer();
                arePlayersRandomized = false;
                Easy_Mode();

            } else
            {
                playOnDeck.gameReset();
                playOnDeck.gameInitializer();
                arePlayersRandomized = false;
                backToMenu.Main_Menu();
            }
        }

        if (possibleMovements == 0)     //if possible movements are 0 its a tie
        {
            System.out.println("É um empate!!!!!!!!!!");

            if (accessGameModeMethods.doYouWantToPlayAgain() == 1)
            {
                playOnDeck.gameReset();
                playOnDeck.gameInitializer();
                arePlayersRandomized = false;
                Easy_Mode();

            } else
            {
                playOnDeck.gameReset();
                playOnDeck.gameInitializer();
                arePlayersRandomized = false;
                backToMenu.Main_Menu();
            }
        }

        var getRandomCPU_move = new EasyAlgorithm();

        if (cpusTurn % 2 == 0)      // challenger's turn 
        {
            String playersMove;
            boolean isNumber = false;
            int IntPlayerMove = 0;

            do
            {
                try
                {
                    System.out.println("Introduza o numero do movimento que deseja!");
                    playersMove = readPlayersMove.nextLine();
                    isNumber = playersMove.matches("\\d");   //regular expression that validates that one digit is introduced
                    IntPlayerMove = Integer.parseInt(playersMove);

                    if (IntPlayerMove < 1 || IntPlayerMove > possibleMovements && !isNumber)
                    {
                        System.out.println("Por favor introduza um digito maior que zero e menor ou igual a " + possibleMovements);
                        System.out.println();
                    }

                } catch (Exception e)
                {
                    e.getMessage();
                }

            } while (IntPlayerMove < 1 || IntPlayerMove > possibleMovements || !isNumber);


            playOnDeck.writePlayOnBoard(IntPlayerMove); //send the move to the deck

            cpusTurn++;
            Easy_Mode();
        } else          //CPU's turn
        {
            if(playOnDeck.getMoves()==1)
                playOnDeck.writePlayOnBoard(1);
            else
            playOnDeck.writePlayOnBoard(getRandomCPU_move.move(playOnDeck.getMoves()));

            cpusTurn++;
            Easy_Mode();
        }
        return 0;
    }

    /*******************************************************************************************************************/

    /**
     * Medium mode algorithm, always moves to the home direction
     *
     * @return game state
     * @throws InterruptedException
     * @throws IOException
     */
    public int Medium_Mode() throws InterruptedException, IOException      //Medium
    {
        Scanner readPlayersMove = new Scanner(System.in);
        var playOnDeck = new Deck_7x7();
        int possibleMovements = playOnDeck.getMoves();  //gets the current move's possible movements

        //Instancs
        MediumAlgorithm accessMediumAlgorithms = new MediumAlgorithm();

        if (!arePlayersRandomized)
        {
            cpusTurn = randomizeFirstPlayer();

            if (cpusTurn == 0)
                cpusHomeNumber = 1;
            else
                cpusHomeNumber = 2;

            arePlayersRandomized = true;
        }

        int state = playOnDeck.getPlayervsCpuGameState(cpusHomeNumber);    //checks the game state

        if (state == 10)      //if the CPU wins
        {
            playOnDeck.deckRunner();
            System.out.println("A Katarina venceu o jogo");

            if (accessGameModeMethods.doYouWantToPlayAgain() == 1)
            {
                playOnDeck.gameReset();
                playOnDeck.gameInitializer();
                arePlayersRandomized = false;
                gameRound = 1;
                Medium_Mode();

            } else
            {
                playOnDeck.gameReset();
                playOnDeck.gameInitializer();
                arePlayersRandomized = false;
                gameRound = 1;
                backToMenu.Main_Menu();
            }

        } else if (state == -10)   //if the challenger wins
        {
            playOnDeck.deckRunner();

            System.out.println("O desafinate é o vencedor");

            if (accessGameModeMethods.doYouWantToPlayAgain() == 1)
            {
                playOnDeck.gameReset();
                playOnDeck.gameInitializer();
                arePlayersRandomized = false;
                gameRound = 1;
                Medium_Mode();

            } else
            {
                playOnDeck.gameReset();
                playOnDeck.gameInitializer();
                arePlayersRandomized = false;
                gameRound = 1;
                backToMenu.Main_Menu();
            }
        }

        if (possibleMovements == 0)     //if possible movements are 0 its a tie
        {
            System.out.println("É um empate!!!!!!!!!!");

            if (accessGameModeMethods.doYouWantToPlayAgain() == 1)
            {
                playOnDeck.gameReset();
                playOnDeck.gameInitializer();
                arePlayersRandomized = false;
                gameRound = 1;
                Medium_Mode();

            } else
            {
                playOnDeck.gameReset();
                playOnDeck.gameInitializer();
                arePlayersRandomized = false;
                gameRound = 1;
                backToMenu.Main_Menu();
            }
        }

        if (cpusTurn % 2 == 0)      // challenger's turn
        {
            System.out.println(ANSI_YELLOW + "JOGADA NUMERO " + gameRound + ANSI_YELLOW);

            String playersMove;
            boolean isNumber = false;
            int IntPlayerMove = 0;

            do
            {
                try
                {
                    System.out.println("Introduza o numero do movimento que deseja!");
                    playersMove = readPlayersMove.nextLine();
                    isNumber = playersMove.matches("\\d");   //regular expression that validates that one digit is introduced
                    IntPlayerMove = Integer.parseInt(playersMove);

                    if (IntPlayerMove < 1 || IntPlayerMove > possibleMovements && !isNumber)
                    {
                        System.out.println("Por favor introduza um digito maior que zero e menor ou igual a " + possibleMovements);
                        System.out.println();
                    }

                } catch (Exception e)
                {
                    e.getMessage();
                }
            } while (IntPlayerMove < 1 || IntPlayerMove > possibleMovements || !isNumber);


            playOnDeck.writePlayOnBoard(IntPlayerMove); //send the move to the deck

            gameRound++;
            cpusTurn++;
            Medium_Mode();
        } else                         //CPU's turn
        {
            System.out.println(ANSI_YELLOW + "JOGADA NUMERO " + gameRound + ANSI_YELLOW);
            Random randomizeOptimalMove = new Random();
            int move;

            int getMoves = playOnDeck.getMoves();

            if (cpusHomeNumber == 1)
            {
                if (getMoves == 1)
                {
                    move = 1;
                    System.out.println("Katarina jogou: " + move);
                    playOnDeck.writePlayOnBoard(move);
                } else
                {
                    //  move = randomizeOptimalMove.nextInt(2 - 1) + 1;
                    move = accessMediumAlgorithms.homeOneAlgorithm();
                    System.out.println("Katarina jogou: " + move);
                    playOnDeck.writePlayOnBoard(move);
                }


            } else if (cpusHomeNumber == 2)
            {
                if (getMoves == 1)
                {
                    move = 1;
                    System.out.println("Katarina jogou: " + move);
                    playOnDeck.writePlayOnBoard(1);
                } else
                {
                    move = accessMediumAlgorithms.homeTwoAlgorithm();
                    System.out.println("Katarina jogou: " + move);
                    playOnDeck.writePlayOnBoard(move);
                }

            }


            gameRound++;
            cpusTurn++;
            Medium_Mode();
        }
        return -1;
    }

    /*******************************************************************************************************************/

    /**
     * Hard mode algorithm, uses minimax
     *
     * @return game state
     * @throws InterruptedException
     * @throws IOException
     */
    public int Hard_Mode() throws InterruptedException, IOException      //Hard
    {

        Scanner readPlayersMove = new Scanner(System.in);
        var playOnDeck = new Deck_7x7();
       // playOnDeck.gameInitializer();
        int possibleMovements = playOnDeck.getMoves();  //gets the current move's possible movements

        //Instances
        MiniMax accessMiniMax = new MiniMax();

        if (!arePlayersRandomized)
        {
            cpusTurn = randomizeFirstPlayer();

            if (cpusTurn == 0)
                cpusHomeNumber = 1;
            else
                cpusHomeNumber = 2;

            arePlayersRandomized = true;
        }

        int state = playOnDeck.getPlayervsCpuGameState(cpusHomeNumber);    //checks the game state

        if (state == 10)      //if the CPU wins
        {
            playOnDeck.deckRunner();
            System.out.println("A Katarina venceu o jogo");

            if (accessGameModeMethods.doYouWantToPlayAgain() == 1)
            {
                playOnDeck.gameReset();
                playOnDeck.gameInitializer();
                arePlayersRandomized = false;
                gameRound = 1;
                Hard_Mode();

            } else
            {
                playOnDeck.gameReset();
                playOnDeck.gameInitializer();
                arePlayersRandomized = false;
                gameRound = 1;
                backToMenu.Main_Menu();
            }

        } else if (state == -10)   //if the challenger wins
        {
            playOnDeck.deckRunner();

            System.out.println("O desafinate é o vencedor");

            if (accessGameModeMethods.doYouWantToPlayAgain() == 1)
            {
                playOnDeck.gameReset();
                playOnDeck.gameInitializer();
                arePlayersRandomized = false;
                gameRound = 1;
                Hard_Mode();

            } else
            {
                playOnDeck.gameReset();
                playOnDeck.gameInitializer();
                arePlayersRandomized = false;
                gameRound = 1;
                backToMenu.Main_Menu();
            }
        }

        if (possibleMovements == 0)     //if possible movements are 0 its a tie
        {
            System.out.println("É um empate!!!!!!!!!!");

            if (accessGameModeMethods.doYouWantToPlayAgain() == 1)
            {
                playOnDeck.gameReset();
                playOnDeck.gameInitializer();
                arePlayersRandomized = false;
                gameRound = 1;
                Hard_Mode();

            } else
            {
                playOnDeck.gameReset();
                playOnDeck.gameInitializer();
                arePlayersRandomized = false;
                gameRound = 1;
                backToMenu.Main_Menu();
            }
        }

        if (cpusTurn % 2 == 0)      // challenger's turn
        {
            System.out.println(ANSI_YELLOW + "JOGADA NUMERO " + gameRound + ANSI_YELLOW);

            String playersMove;
            boolean isNumber = false;
            int IntPlayerMove = 0;

            do
            {
                try
                {
                    System.out.println("Introduza o numero do movimento que deseja!");
                    playersMove = readPlayersMove.nextLine();
                    isNumber = playersMove.matches("\\d");   //regular expression that validates that one digit is introduced
                    IntPlayerMove = Integer.parseInt(playersMove);

                    if (IntPlayerMove < 1 || IntPlayerMove > possibleMovements && !isNumber)
                    {
                        System.out.println("Por favor introduza um digito maior que zero e menor ou igual a " + possibleMovements);
                        System.out.println();
                    }

                } catch (Exception e)
                {
                    e.getMessage();
                }
            } while (IntPlayerMove < 1 || IntPlayerMove > possibleMovements || !isNumber);

            playOnDeck.writePlayOnBoard(IntPlayerMove); //send the move to the deck

            gameRound++;
            cpusTurn++;
            Hard_Mode();
        } else                         //CPU's turn
        {
            System.out.println(ANSI_YELLOW + "JOGADA NUMERO " + gameRound + ANSI_YELLOW);
            int move;

            int getMoves = playOnDeck.getMoves();

            if (cpusHomeNumber == 1)
            {

                if (getMoves == 1)
                {
                    move = 1;
                    System.out.println("Katarina jogou: " + move);
                    playOnDeck.writePlayOnBoard(move);
                }else
                {
                    move = accessMiniMax.getMoveThroughtEvaluation(cpusHomeNumber,true);     //gets the move from the minimax algorithm
                    System.out.println("Katarina jogou: " + move);
                    playOnDeck.writePlayOnBoard(move);
                }
            } else if (cpusHomeNumber == 2)
            {
                if (getMoves == 1)
                {
                    move = 1;
                    System.out.println("Katarina jogou: " + move);
                    playOnDeck.writePlayOnBoard(1);
                } else
                {
                    move = accessMiniMax.getMoveThroughtEvaluation(cpusHomeNumber,false);     //gets the move from the minimax algorithm
                    System.out.println("Katarina jogou: " + move);
                    playOnDeck.writePlayOnBoard(move);
                }

            }

            gameRound++;
            cpusTurn++;
            Hard_Mode();
        }
        return -1;
    }


    /**
     * Randomizes the first player on the beggining of the game
     *
     * @return player
     */
    public static int randomizeFirstPlayer()
    {
        Random randomMove = new Random();
        int player = randomMove.nextInt(2);

        return player;
    }
}
