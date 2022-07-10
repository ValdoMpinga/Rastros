package com.rastros;
import com.rastros.Game_Components.Algorithms.MiniMax;
import com.rastros.Game_Components.Board.Deck_7x7;
import com.rastros.Game_Components.Menu.Menus;
import java.io.File;
import java.io.IOException;

public class Main
{
    /**
     * Main
     * @param args: arguments
     */
    public static void main(String[] args)
    {
        try
        {
            var menu = new Menus();
            menu.Main_Menu();

        } catch (Exception e)
        {
            e.getMessage();
        }
    }

    /**
     * deletes the players file
     * @throws IOException
     */
    public void deletePlayersFile() throws IOException
    {
        File playersFile = new File("players.txt"); //opens the players file

        if (playersFile.delete())
            System.out.println("\n");

        System.out.flush();
    }

    /**
     * Renames the backup file to the players file
     */
    public void renameBackupFile()
    {
        System.out.flush();

        File backupFile = new File("playersBackup.txt");
        File playersFile = new File("players.txt");

        if (backupFile.renameTo(playersFile))
            System.out.println("\n");
        else
        {
            System.out.println("\n");
        }
    }
}
