package com.rastros.Game_Components.Game;

import com.rastros.Game_Components.Algorithms.EasyAlgorithm;
import com.rastros.Game_Components.Algorithms.MediumAlgorithm;
import com.rastros.Game_Components.Board.Deck_7x7;
import com.rastros.Game_Components.Menu.Menus;
import com.rastros.Game_Components.Players.Players;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Game_Mode
{
    //colors

    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m"; //red color
    public static final String ANSI_WHITE = "\u001B[37m";

    //variables

    /**
     * @param arePlayersRandomized: on each game start, checks if the players were randomized to find the match first player.
     * @param playerOneHome: gets the player one's home.
     * @param playersTurn: stores which player turns it is, pair numbers for the player one and odd numbers for the player two .
     * @param gameState: gets the game state, draw, win or loss.
     * @param playersMove: get the players move by input
     * @param isNumber: checks if the input is a number
     * @param IntPlayerMove: converts the move into an int number type to make validations
     * @param possibleMovements: number of possible movements on a  specific position
     */
    private static boolean arePlayersRandomized=false;
    private static int playerOneHome=0;
    private static int playersTurn = 0;
    private static int gameState;
    private String playersMove;
    private boolean isNumber = false;
    private int IntPlayerMove = 0;
    private int possibleMovements=0;
    private int easyAlgorithmWins;
    private int mediumAlgorithmWins;
    private int draws;
    private int games = 0;
    private int mediumAlgorithmHome = 0;
    private static int cpusTurn = 0;


    //instances
    Menus backToMenu=new Menus();

    /**
     * Player vs Player mode
     *
     * @param player1: the first player's name
     * @param player2 the secound player's name
     * @throws IOException
     * @throws InterruptedException
     */
    public void PlayerVsPlayer(String player1, String player2) throws IOException, InterruptedException
    {
        String[] players=new String[2];
        players[0]=player1;
        players[1]=player2;

        Scanner readPlayersMove = new Scanner(System.in);
        Deck_7x7 playOnDeck = new Deck_7x7();
        Players accessPlayerMethods=new Players();

        possibleMovements = playOnDeck.getMoves();

        if(!arePlayersRandomized)
        {
            playersTurn= randomizeFirstPlayer();

            if(playersTurn==0)
                playerOneHome=1;
            else
                playerOneHome=2;

            arePlayersRandomized= true;
        }

        if(possibleMovements==0)
        {
            System.out.println("Empate!!!");

            if(doYouWantToPlayAgain()==1)
            {
                playOnDeck.gameReset();
                playOnDeck.gameInitializer();
                arePlayersRandomized=false;
                PlayerVsPlayer(players[0],players[1]);

            }else
            {
                playOnDeck.gameReset();
                playOnDeck.gameInitializer();
                arePlayersRandomized=false;
                backToMenu.Main_Menu();
            }
        }

        gameState= playOnDeck.getPlayerVsPlayerGameState(playerOneHome);

        if (gameState == 10)
        {
            accessPlayerMethods.addWinsToPlayer(players[0]);
            System.out.println("O vencedor(a) é " +players[0]);

            if(doYouWantToPlayAgain()==1)
            {
                playOnDeck.gameReset();
                playOnDeck.gameInitializer();
                arePlayersRandomized=false;
                PlayerVsPlayer(players[0],players[1]);

            }else
            {
                playOnDeck.gameReset();
                playOnDeck.gameInitializer();
                arePlayersRandomized=false;
                backToMenu.Main_Menu();
            }
        }
        else if (gameState == -10)
        {
            accessPlayerMethods.addWinsToPlayer(players[1]);

            System.out.println("O vencedor(a) é "+players[1]);


            if(doYouWantToPlayAgain()==1)
            {
                playOnDeck.gameReset();
                playOnDeck.gameInitializer();
                arePlayersRandomized=false;
                PlayerVsPlayer(players[0],players[1]);

            }else
            {
                playOnDeck.gameReset();
                playOnDeck.gameInitializer();
                arePlayersRandomized=false;
                backToMenu.Main_Menu();
            }
        }

        else
        {
            do
            {
                try
                {
                    if(playersTurn%2==0)
                    {
                        System.out.println("Vez do jogador(a) "+players[0]);

                    }else
                    {
                        System.out.println("Vez do jogador(a) "+players[1]);
                    }

                    System.out.println("Introduza o numero do movimento que deseja!");
                    playersMove = readPlayersMove.nextLine();
                    this.playersTurn++;
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
            } while (IntPlayerMove < 1 || IntPlayerMove > possibleMovements || !isNumber );

            playOnDeck.writePlayOnBoard(IntPlayerMove); //send the move to the deck

            PlayerVsPlayer(player1,player2);
        }
    }

    public int easyCpuVsMediumCpu() throws IOException, InterruptedException
    {
        var playOnDeck = new Deck_7x7();
        int possibleMovements = playOnDeck.getMoves();  //gets the current move's possible movements
        int move=0;

        //Instances
        MediumAlgorithm accessMediumAlgorithms = new MediumAlgorithm();
        EasyAlgorithm acceEasyAlgorithm=new EasyAlgorithm();

        if (!arePlayersRandomized)
        {
            cpusTurn = randomizeFirstPlayer();

            if (cpusTurn == 0)
                mediumAlgorithmHome = 1;
            else
                mediumAlgorithmHome = 2;

            arePlayersRandomized = true;
        }


        int state = playOnDeck.getPlayervsCpuGameState(2);    //checks the game state

        if (state == 10)      //if the CPU wins
        {
            mediumAlgorithmWins++;
            games++;

            if (games<=100)
            {
                try
                {

                    playOnDeck.gameReset();
                    playOnDeck.gameInitializer();
                    System.out.println("\nGame "+games + " iniciando...");
                    System.out.println("--------------------------------------------------------------------------------------\n");
                    easyCpuVsMediumCpu();

                }catch (Exception e)
                {
                    System.out.println("oops, a memoria RAM não aguentou!");
                    e.getMessage();
                }

            } else
            {
                System.out.println("Algoritmo medio: "+ mediumAlgorithmWins);
                System.out.println("Algoritmo facíl: "+easyAlgorithmWins);
                System.out.println("Empates: "+draws);

                backToMenu.Main_Menu();

            }
        } else if (state == -10)   //if the challenger wins
        {
            easyAlgorithmWins++;
            games++;
            System.out.println("Clique a tecla ENTER para continuar" );
            try { System.in.read(); } catch (Exception e)
            { e.getMessage(); }


            if (games <= 100)
            {
                try
                {

                    playOnDeck.gameReset();
                    playOnDeck.gameInitializer();
                    System.out.println("\nGame "+games + " iniciando...");
                    System.out.println("--------------------------------------------------------------------------------------\n");
                    easyCpuVsMediumCpu();

                }catch (Exception e)
                {
                    System.out.println("oops, a memoria RAM não aguentou!");
                    e.getMessage();
                }


            } else
            {
                System.out.println("Algoritmo medio: "+ mediumAlgorithmWins);
                System.out.println("Algoritmo facíl: "+easyAlgorithmWins);
                System.out.println("Empates: "+draws);
                backToMenu.Main_Menu();
            }
        }

        if (possibleMovements == 0)     //if possible movements are 0 its a tie
        {
            games++;
            draws++;

            if (games <= 100)
            {
                try
                {

                    playOnDeck.gameReset();
                    playOnDeck.gameInitializer();
                    System.out.println("\nGame "+games + " iniciando...");
                    System.out.println("--------------------------------------------------------------------------------------\n");
                    easyCpuVsMediumCpu();

                }catch (Exception e)
                {
                    System.out.println("oops, a memoria RAM não aguentou!");
                    e.getMessage();
                }

            } else
            {
                System.out.println("Algoritmo medio: "+ mediumAlgorithmWins);
                System.out.println("Algoritmo facíl: "+easyAlgorithmWins);
                System.out.println("Empates: "+draws);
                System.out.println("Clique a tecla ENTER para continuar" );
                try { System.in.read(); } catch (Exception e)
                { e.getMessage(); }

                backToMenu.Main_Menu();
            }
        }

        if (cpusTurn % 2 == 0)      // challenger's turn
        {
            int getMoves=playOnDeck.getMoves();


            if(getMoves==1)
            {
                move=1;
                playOnDeck.writePlayOnBoard(move);
            }else
            {
                move=accessMediumAlgorithms.homeTwoAlgorithm();
                System.out.println(move);
                playOnDeck.writePlayOnBoard(move); //send the move to the deck
            }

            cpusTurn++;
            easyCpuVsMediumCpu();
        } else                         //CPU's turn
        {
            int getMoves=playOnDeck.getMoves();


            if(getMoves==1)
            {
                move=1;
                playOnDeck.writePlayOnBoard(move);
            }else
            {
                move=acceEasyAlgorithm.move(getMoves);
                playOnDeck.writePlayOnBoard(move); //send the move to the deck
            }
            cpusTurn++;
            easyCpuVsMediumCpu();
        }
        return -1;
    }

    /**
     * Method used to check if the player wants to play again or not, if yes, the game restarts, else, goes back to the main menu
     * @return answer
     */
    public int doYouWantToPlayAgain()
    {
        Scanner playAgain = new Scanner(System.in);
        System.out.println("Deseja jogar novamente?\n");
        System.out.println(ANSI_GREEN+"1-> SIM!"+ANSI_GREEN);
        System.out.println(ANSI_RED+"2-> NÃO!"+ANSI_RED);

        int yesOrNo=playAgain.nextInt();

        System.out.println(ANSI_WHITE);
        return yesOrNo;
    }

    /**
     * Method that randomizes the first player
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
