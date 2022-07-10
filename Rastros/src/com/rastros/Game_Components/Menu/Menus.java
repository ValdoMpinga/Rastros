package com.rastros.Game_Components.Menu;

import com.rastros.Game_Components.Board.Deck_7x7;
import com.rastros.Game_Components.Game.Game_Difficulty;
import com.rastros.Game_Components.Game.Game_Mode;
import com.rastros.Game_Components.Players.Players;
import com.rastros.Game_Components.Tutorial.Tutorial;

import java.io.IOException;
import java.util.Scanner;

public class Menus
{
    //colors

    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_RED = "\u001B[31m"; //red color

    //Instanes

    Deck_7x7 accessDeckMethods=new Deck_7x7();

    /**
     * The main menu
     */
    public void  Main_Menu() throws IOException, InterruptedException
    {

        int option=0;
        var accessPlayersMethods=new Players();
        Scanner readOption= new Scanner(System.in);

        do
        {
            System.out.println(ANSI_RED+"\n" +
                    "  _____           _                 \n" +
                    " |  __ \\         | |                \n" +
                    " | |__) |__ _ ___| |_ _ __ ___  ___ \n" +
                    " |  _  // _` / __| __| '__/ _ \\/ __|\n" +
                    " | | \\ \\ (_| \\__ \\ |_| | | (_) \\__ \\\n" +
                    " |_|  \\_\\__,_|___/\\__|_|  \\___/|___/\n" +
                    "                                    \n" +
                    "                                    \n"+ANSI_RED);
            System.out.println(ANSI_WHITE+ "********************************************************************************************************************");
            System.out.println("*************************************       1-Tutorial             *************************************************");
            System.out.println("*************************************       2-Jogar                *************************************************");
            System.out.println("*************************************       3-Registar Jogador     *************************************************");
            System.out.println("*************************************       4-Listar jogadores        **********************************************");
            System.out.println("*************************************       5-Pesquisar jogador        *********************************************");
            System.out.println("*************************************       0-Sair                 *************************************************");
            System.out.println("********************************************************************************************************************");

            System.out.println();

            System.out.println("Por favor introduza a sua opção");
            option=readOption.nextInt();

            if(option>5 || option<0)
            {
                System.out.println("--------------------------------------------------------------------------------");
                System.out.println("Por favor introduza uma opção valida!");
                System.out.println("--------------------------------------------------------------------------------");
            }

        }while(option>5 || option<0);

        switch (option)
        {
            case 1:                                  //tutorial menu
            {
                var tutorial = new Tutorial();
                tutorial.tutorialMenu();

                break;
            }

            case 2:                                  //game modes menu
            {
                Game_mode_menu();

                break;
            }

            case 3 :                                //add a player
            {
                accessPlayersMethods.addPlayer();
                Main_Menu();
                break;
            }

            case 4:                                  //read players
            {
                accessPlayersMethods.readPlayeres();
                System.out.println(ANSI_RED + "Clique a tecla ENTER para voltar ao menu principal" + ANSI_BLUE);
                try { System.in.read(); } catch (Exception e)
                { e.getMessage(); }

                Main_Menu();                         //main menu
                break;
            }

            case 5:
            {
                Scanner searchPlayer=new Scanner(System.in);

                System.out.println("Introduza o nome do jogador a pesquisar: ");
                String player=searchPlayer.nextLine();

                accessPlayersMethods.searchPlayer(player);

                System.out.println(ANSI_RED + "Clique a tecla ENTER para voltar ao menu principal" + ANSI_BLUE);
                try { System.in.read(); } catch (Exception e)
                { e.getMessage(); }

                Main_Menu();
            }

            case 0:                         //exit
            {
                System.out.println("Até a proxima ;-)");
                return;

             //   closeProgram();
               // break;
            }
        }
    }

    /**
     * Game mode menu
     *
     */
    public void Game_mode_menu() throws IOException, InterruptedException
    {

        Scanner readOption= new Scanner(System.in);
        int option=0;

        System.out.println("Por favor selecione o seu modo de jogo!");

        do
        {
            System.out.println("*********************************************************************************************************************");
            System.out.println("*************************************       1- 1 vs CPU              ************************************************");
            System.out.println("*************************************       2- 1 vs 1                ************************************************");
            System.out.println("*************************************       3- CPU vs CPU            ************************************************");
            System.out.println("*************************************       0-voltar                 ************************************************");
            System.out.println("*********************************************************************************************************************");

            System.out.println();

            System.out.println("Por favor introduza a sua opção");
            option=readOption.nextInt();

            if(option>3 || option<0)
            {
                System.out.println("--------------------------------------------------------------------------------");
                System.out.println("Por favor introduza uma opção valida!");
                System.out.println("--------------------------------------------------------------------------------");
            }

        }while(option<0 || option>3);

        switch (option)
        {
            case 1:                 //difficulty menu
            {
                accessDeckMethods.gameReset();
                accessDeckMethods.gameInitializer();
                Difficulty();
                break;
            }

            case 2:                 //player vs player mode
            {
                Scanner readPlayers=new Scanner(System.in);
                String player1,player2;

                System.out.println("Introduza nome do primeiro jogador: ");
                player1=readPlayers.nextLine();

                System.out.println("Introduza nome do segundo jogador: ");
                player2=readPlayers.nextLine();

                Game_Mode callPlayerVsPlayerMode=new Game_Mode();
                accessDeckMethods.gameReset();
                accessDeckMethods.gameInitializer();
                callPlayerVsPlayerMode.PlayerVsPlayer(player1,player2);

                break;
            }

            case 3:
            {
                Game_Mode callCpuVsCpuMode=new Game_Mode();
                callCpuVsCpuMode.easyCpuVsMediumCpu();
            }

            case 0:                 //exit
            {
                Main_Menu();
            }
        }

    }

    /**
     * Difficulty menu
     *
     */
    public void Difficulty() throws IOException, InterruptedException
    {
       //instances

        Game_Difficulty accessGameDifficulties=new Game_Difficulty();

        Scanner readOption= new Scanner(System.in);
        int option=0;

        System.out.println("Por favor selecione o grau de dificuldade");

        do
        {
            System.out.println("**********************************************************************************************************************");
            System.out.println("*************************************         1- Facil                  **********************************************");
            System.out.println("*************************************         2- Médio                  **********************************************");
            System.out.println("*************************************         3- Difícil                **********************************************");
            System.out.println("*************************************         0- Voltar                 **********************************************");
            System.out.println("**********************************************************************************************************************");

            System.out.println();

            System.out.println("Por favor introduza a sua opção");
            option=readOption.nextInt();

            if(option>3|| option<0)
            {
                System.out.println("--------------------------------------------------------------------------------");
                System.out.println("Por favor introduza uma opção valida!");
                System.out.println("--------------------------------------------------------------------------------");
            }

        }while(option<0 || option>3);

        switch (option)
        {
            case 1:                 //easy mode
            {
                accessGameDifficulties.Easy_Mode();

                break;
            }

            case 2:                 //medium mode
            {
                accessGameDifficulties.Medium_Mode();

                break;
            }
            case 3:                 //hard mode
            {
                accessGameDifficulties.Hard_Mode();

                break;
            }

            case 0:                 //exit
            {
                Game_mode_menu();
                break;
            }
        }
    }

    /**
     * Close program
     * @return 0
     */
    public int closeProgram()
    {
        return 0;
    }
}
