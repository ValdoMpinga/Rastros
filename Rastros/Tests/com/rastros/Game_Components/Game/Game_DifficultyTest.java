package com.rastros.Game_Components.Game;

import com.rastros.Game_Components.Algorithms.MediumAlgorithm;
import com.rastros.Game_Components.Board.Deck_7x7;
import org.junit.Test;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;


class Game_DifficultyTest
{
    /*
    @Test
    public int hard_Mode() throws InterruptedException, IOException      //Medium
    {
        Scanner readPlayersMove = new Scanner(System.in);
        var playOnDeck = new Deck_7x7();
        int possibleMovements = playOnDeck.getMoves();  //gets the current move's possible movements

        //Instancs
        MediumAlgorithm accessMediumAlgorithms=new MediumAlgorithm();

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
                    move=accessMediumAlgorithms.homeOneAlgorithm();
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
    */
}