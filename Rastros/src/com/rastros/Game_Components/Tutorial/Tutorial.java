package com.rastros.Game_Components.Tutorial;

import com.rastros.Game_Components.Board.Deck_7x7;
import com.rastros.Game_Components.Menu.Menus;

import java.io.IOException;
import java.util.Scanner;

public class Tutorial
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

    /**
     * The game tutorial
     * @throws IOException
     * @throws InterruptedException
     */
    public void tutorialMenu() throws IOException, InterruptedException
    {
        System.out.println(ANSI_RED+"\n" +
                "  _______ _    _ _______ ____  _____  _____          _       \n" +
                " |__   __| |  | |__   __/ __ \\|  __ \\|_   _|   /\\   | |      \n" +
                "    | |  | |  | |  | | | |  | | |__) | | |    /  \\  | |      \n" +
                "    | |  | |  | |  | | | |  | |  _  /  | |   / /\\ \\ | |      \n" +
                "    | |  | |__| |  | | | |__| | | \\ \\ _| |_ / ____ \\| |____  \n" +
                "    |_|   \\____/   |_|  \\____/|_|  \\_\\_____/_/    \\_\\______| \n" +
                "                                                             \n" +
                "                                                             \n"+ANSI_RED);
        System.out.println("\n");

        Scanner cleanBuffer=new Scanner(System.in);
        System.out.println(ANSI_RED + "O QUE É O JOGO  RASTROS?" + ANSI_RED);
        System.out.println(ANSI_RED + "Clique a tecla ENTER para continuar" + ANSI_BLUE);
        try { System.in.read(); } catch (Exception e)
        { e.getMessage(); }


        System.out.println();

        var showDeck = new Deck_7x7();
        showDeck.gameInitializer();
        System.out.println(ANSI_WHITE + "Rastros é um jogo de tabuleiro jogado entre duas pessoas e ganha o jogador que conseguir marcar um autogolo na sua baliza." + ANSI_WHITE);
        System.out.println(ANSI_WHITE + "A baliza do jogador 1 encontra se no canto inferior esquerdo (baliza numero 1) e a do jogador 2 encontra se no canto superior direito (baliza numero 2)." + ANSI_WHITE);
        System.out.println();
        System.out.println(ANSI_RED);
        showDeck.deckRunner();
        System.out.println();


        showDeck.firstPlayForTutorialDemo();
        System.out.println(ANSI_RED + "E COMO É QUE JOGAMOS???" + ANSI_RED);
        System.out.println(ANSI_RED + "Clique a tecla ENTER para continuar" + ANSI_BLUE);
        try { System.in.read(); } catch (Exception e)
        { e.getMessage(); }
        System.out.println(ANSI_RED + "Clique a tecla ENTER para continuar" + ANSI_BLUE);

        System.out.println();

        System.out.println(ANSI_WHITE+ "Calma!!!"+ANSI_WHITE);
        System.out.println(ANSI_WHITE+ "Antes de explicarmos como a jogabilidade funciona, temos que deixar alguns pontos bem claro:\n"+ANSI_WHITE);
        System.out.println(ANSI_WHITE+"->  Tanto o jogador 1 ou 2 usam a mesma peça, neste caso a peça"+ANSI_RED+" W"+ ", ou seja,é tipo o jogo de puxar a corda, ganha quem puxar a corda para o seu lado, neste caso a sua casa!" +ANSI_WHITE+ANSI_RED+ANSI_WHITE+";");
        System.out.println(ANSI_WHITE+"->  O primeiro jogador é "+ANSI_WHITE + ANSI_RED+"SEMPRE "+ANSI_RED +ANSI_WHITE+"aquele que possui a baliza 1 como casa, esta é a regra que mantem o jogo equilibrado e justo a partir do inicio do mesmo!"+ANSI_WHITE);
        System.out.println(ANSI_WHITE+"->  Sempre que uma jogada feita, a casa anterior, onde a peça "+ANSI_WHITE +ANSI_WHITE+ANSI_RED+"W " +ANSI_WHITE+"estava, recebe uma nova peça, neste caso a peça "+ANSI_WHITE+ANSI_RED+"P "+ANSI_WHITE+"e com isso, torna-se impossivel voltar para aquela casa ate o final do jogo!");
        System.out.println(ANSI_RED);
        showDeck.deckRunner();

        System.out.println();
        System.out.println(ANSI_RED + "PERCEBIDO. E A COMO É QUE SE JOGA?" + ANSI_RED);
        System.out.println(ANSI_RED + "Clique a tecla ENTER para continuar" + ANSI_BLUE);
        try { System.in.read(); } catch (Exception e)
        { e.getMessage(); }
        System.out.println();

        System.out.println(ANSI_WHITE+ "Muito simples!!!"+ANSI_WHITE);
        System.out.println(ANSI_WHITE+ "O jogo esta organizado em linhas e colunas como pode ver, entao cada posição tem um índice!"+ANSI_WHITE);


        System.out.println();
        System.out.println(ANSI_RED + "E COMO EU SEI O ÍNDICE DE CADA POSIÇÃO? EU NÃO ENTENDO NADA DISSO, SOCORRO!!!!!!!" + ANSI_RED);
        System.out.println(ANSI_RED + "Clique a tecla ENTER para continuar" + ANSI_BLUE);
        try { System.in.read(); } catch (Exception e)
        { e.getMessage(); }
        System.out.println();

        System.out.println(ANSI_WHITE+ "Calma, hahaha, é de notar que as linhas e colunas tem um numero no inicio delas, e a partir delas da para ver o indice!"+ANSI_WHITE);
        System.out.println(ANSI_WHITE+ "Por exemplo, a peça"+ANSI_WHITE + ANSI_WHITE+ANSI_RED+" W " +ANSI_WHITE+"esta localizada na "+ANSI_WHITE+ANSI_RED+"linha 2, que por sua vez esta na coluna 4 " +  ANSI_WHITE+"ou seja, esta localizada no indice"+ ANSI_GREEN+" p24, p significa posição");
        showDeck.gameReset();
        showDeck.gameInitializer();
        System.out.println(ANSI_RED);
        showDeck.deckRunner();

        System.out.println();
        System.out.println(ANSI_RED + "OK, AGORA SIM ESTOU PRONTO PARA APRENDER A MOVIMENTAR AS PEÇAS!" + ANSI_RED);
        System.out.println(ANSI_RED + "Clique a tecla ENTER para continuar" + ANSI_BLUE);
        try { System.in.read(); } catch (Exception e)
        { e.getMessage(); }
        System.out.println();

        System.out.println(ANSI_WHITE+ "Verdade, a movimentação de peças esta divida em tres ramos:\n"+ANSI_WHITE);
        System.out.println(ANSI_WHITE+"1. MOVIMENTOS DA LINHA ACIMA DA PEÇA BRANCA: Sao os movimentos disponiveis para jogar na linha acima da peça branca \n");
        System.out.println(ANSI_WHITE+"2. MOVIMENTOS DA LINHA ATUAL DA PEÇA BRANCA: Sao os movimentos disponiveis para jogar na linha atual da peça branca \n");
        System.out.println(ANSI_WHITE+"3. MOVIMENTOS DA LINHA ABAIXO DA PEÇA BRANCA:Sao os movimentos disponiveis para jogar na linha abaixo da peça branca \n");
        System.out.println(ANSI_WHITE+"esta organização foi feita desta forma pelo programador do jogo para que minimizar o tempo de escolha da posição, ou seja, para jogar numa linha é só olhar para os movimentos disponiveis do ramo que pretendemos. No jogo, as opçóes de jogada serão mostradas assim!");
        System.out.println(ANSI_RED);
        showDeck.tutorialGameReset();
        showDeck.gameInitializer();
        showDeck.getMoves();

        System.out.println();
        System.out.println(ANSI_RED + "PRONTO, POSSO COMEÇAR A JOGAR?" + ANSI_RED);
        System.out.println(ANSI_RED + "Clique a tecla ENTER para continuar" + ANSI_BLUE);
        try { System.in.read(); } catch (Exception e)
        { e.getMessage(); }
        System.out.println();

        System.out.println(ANSI_WHITE+ "Claro, mas tenha em mente uma coisa, se o jogo alguma vez ficar numa situação semelhante a esta abaixo, em que a peça "+ANSI_WHITE +ANSI_WHITE+ANSI_RED+" W "+ANSI_WHITE+"encontra-se bloqueada pelas peças "+ANSI_RED+"P, significa empate!");
        showDeck.secoundPlayForTutorialDemo();
        showDeck.deckRunner();
        System.out.println(ANSI_GREEN + "ADEUS E BOA SORTE!" + ANSI_GREEN);
        System.out.println(ANSI_RED + "Clique a tecla ENTER para continuar" + ANSI_BLUE);
        try { System.in.read(); } catch (Exception e)
        { e.getMessage(); }


        System.out.println(ANSI_WHITE);
        Menus backToMenu=new Menus();
        backToMenu.Main_Menu();


    }
}
