package com.rastros.Game_Components.Players;

import com.rastros.Main;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Players extends Player
{
    public Players() throws FileNotFoundException { }

    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_RED = "\u001B[31m"; //red color

    Scanner readPlayersName= new Scanner(System.in);
    boolean playerExistsOnPlayersFile=false;
    @Override
    public String getName()
    {
        return super.getName();
    }

    @Override
    public void setName(String name)
    {
        super.setName(name);
    }

    @Override
    public int getScore()
    {
        return super.getScore();
    }

    @Override
    public void setScore(int score)
    {
        super.setScore(score);
    }

    @Override
    public int getGamesPlayed()
    {
        return super.getGamesPlayed();
    }

    @Override
    public void setGamesPlayed(int gamesPlayed)
    {
        super.setGamesPlayed(gamesPlayed);
    }

    /**
     * Method that adds a player to the players file
     */
    public void addPlayer()
    {
        System.out.println("introduza o nome do jogador");
        setName(readPlayersName.nextLine());

        try
        {
            FileOutputStream createPlayer=new FileOutputStream("players.txt",true); //opens the file
            PrintWriter writePlayer=new PrintWriter(createPlayer);                              // opens the file writer
            String line;


            line=String.format("%s;%s;%s",getName(),getScore(),getGamesPlayed());
            writePlayer.println(line);  //write to the file
            writePlayer.close();        //closes the file

        }catch (Exception e)
        {
            e.getMessage();
        }
    }

    /**
     * Method that read the players
     */
    public void readPlayeres()
    {
        String line;            //a string that stores the parameters that are inserted to the file
        String[] lines =null;   //an array that stores all the parameters read by the file, they are divided into the array on each
                                // ';' found on the file

        try
        {
            FileInputStream readPlayers=new FileInputStream("players.txt");
            Scanner writePlayers= new Scanner(readPlayers);

            while(writePlayers.hasNext())   //while there are non empty line os the array
            {
                line= writePlayers.nextLine();  //the line is read
                lines=line.split(";");

                System.out.println(String.format("Nome do jogador:\t\t\t\t\t\t %s \nJogos jogados:\t\t\t\t\t\t\t %s \n" +
                        "Pontuação total do jogador:\t\t\t\t %s ",lines[0],lines[1],lines[2]));
                System.out.println();
                System.out.println("/**********************************************************************/");
            }

        }catch(Exception e)
        {
            e.getMessage();
        }
    }

    /**
     *Method that adds wins to a player and also the score, one win is equal to +10 score points
     * On this code, the players file in opened on read mode and we iterate to it while there is a new line looking for
     * the winner, and when its found, the line is updated, copying the all the elements to the backup file, and the when
     * the winner is found , that line is updated with the new score and wins number and passed to the backup file. The
     * old file is deleted and the backup file is renamed to the previous deleted file name, "players.txt" which contains
     * the players name, wins and score
     */
    public void addWinsToPlayer(String winner) throws IOException
    {
        String line;
        String[] lines =null;
        int wins;
        int score;

        File backupFile=new File("playersBackup.txt");                     //backup file

        try
        {
            FileInputStream readPlayers=new FileInputStream("players.txt");   //players file
            Scanner writePlayers= new Scanner(readPlayers);

            while(writePlayers.hasNext())
            {
                line= writePlayers.nextLine();
                lines=line.split(";");

                try
                {
                    FileOutputStream createPlayer=new FileOutputStream(backupFile,true);//opening the backup file to
                    PrintWriter writePlayerToFile=new PrintWriter(createPlayer);                // write the updates on it

                    if(lines[0].compareTo(winner)==0)   //if the winner is on that file line, the score and wins are updated
                    {                                   //and the updated line is written to the backup file

                        playerExistsOnPlayersFile=true;

                        wins=Integer.parseInt(lines[1]);
                        wins++;

                        score=Integer.parseInt(lines[2]);
                        score+=10;

                        line=String.format("%s;%s;%s",lines[0],wins,score);
                        System.out.println(line);
                        writePlayerToFile.println(line);
                        writePlayerToFile.close();
                    }
                    else                               //if the winner is not in the line, the line is written to the backup
                    {                                   //anyway becausa the winner can be ahead and we dont lose data
                        line=String.format("%s;%s;%s",lines[0],lines[1],lines[2]);
                        writePlayerToFile.println(line);
                        writePlayerToFile.close();
                    }

                }catch (Exception e)
                {
                    e.getMessage();
                }
            }
             writePlayers.close();  //closes the backup files

            Main callDelete=new Main();
            callDelete.deletePlayersFile();     //calls the delete players method, to delete the out dated file

            callDelete.renameBackupFile();      //renames then updated file back to players.txt

        }catch(Exception e)
        {
            e.getMessage();
        }

        if(!playerExistsOnPlayersFile)          //case the player doesnt exists
        {
            FileOutputStream createPlayer=new FileOutputStream("players.txt",true);//opening the backup file to
            PrintWriter writePlayerToFile=new PrintWriter(createPlayer);                // write the updates on it

            writePlayerToFile.println(String.format("%s;%s;%s",winner,"1","10"));
            writePlayerToFile.close();
        }
    }

    /**
     * Method that searches for a players
     */
    public void searchPlayer(String player)
    {
        boolean playerFound=false;
        String line;
        String[] lines =null;

        try
        {
            FileInputStream readPlayers=new FileInputStream("players.txt");
            Scanner writePlayers= new Scanner(readPlayers);

            while(writePlayers.hasNext())   //while there are non empty line os the array
            {
                line= writePlayers.nextLine();  //the line is read
                lines=line.split(";");


                if(player.compareTo(lines[0])==0)
                {
                    System.out.println(String.format("Nome do jogador:\t\t\t\t\t\t %s \nJogos jogados:\t\t\t\t\t\t\t %s \n" +
                            "Pontuação total do jogador:\t\t\t\t %s ",lines[0],lines[1],lines[2]));
                    System.out.println();
                    System.out.println("/**********************************************************************/");

                    playerFound=true;
                }

            }

        }catch(Exception e)
        {
            e.getMessage();
        }

        if(playerFound==false)
        {
            System.out.println("O jogador "+player +" não existe no sistema, por favor, o registe o no sistema através do menu principal.");
        }
    }

}
