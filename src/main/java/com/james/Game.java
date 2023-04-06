package com.james;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Game {

  private static final String NUMBER_OF_PLAYERS_INPUT_MESSAGE = "Please select the number of players, 1-4";
  private static final String PLAYER_NAME_INPUT_MESSAGE = "Please enter Player[%s]'s name:";
  private static final String DEALERS_TURN_MESSAGE = "Now the Dealers turn...";
  private static final String PLACE_BETS_MESSAGE = "Place your bet %s. Your current wallet amount is: %s";
  private static final String WALLET_BALANCE_MESSAGE = "%s your wallet balance is: %s";
  private static final String WIN_MESSAGE = "%s you won your bet!";
  private static final String LOOSE_MESSAGE = "%s you lost your bet!";
  private static final int MIN_PLAYERS = 1;
  private static final int MAX_PLAYERS = 4;

  public List<Player> playerList = new ArrayList<Player>();
  private Deck deck = new Deck();
  private Dealer dealer = new Dealer();

  public void start() {
    Scanner sc = new Scanner(System.in);

    displayBlackJackRules();
    int numOfPlayers = inputNumOfPlayers(sc);
    initialisePlayers(numOfPlayers, sc);
    
    boolean keepPlaying = true;
    while (keepPlaying) {
      placeBets(playerList, sc);
      playersTurn(playerList, sc);
      int dealersResult = dealersTurn();
      settleBets(playerList, dealersResult);
      displayPlayersWallet(playerList);
      clearHands(playerList);
      sc.nextLine();
    }
    sc.close();
  }

  public Deck createDeck() {
    return new Deck();
  }

  public void displayBlackJackRules() {
    System.out.println("The game of Blackjack");
    System.out.println("");
    System.out.println("  BLACKJACK RULES: ");
    System.out
        .println("    -Each player is dealt 2 cards. The dealer is dealt 2 cards with one face-up and one face-down.");
    System.out.println("    -Cards are equal to their value with face cards being 10 and an Ace being 1 or 11.");
    System.out.println("    -The players cards are added up for their total.");
    System.out.println(
        "    -Players “Hit” to get another card from the deck. Players “Stick” if they wish to keep their current card total.");
    System.out.println("    -Dealer “Hits” until they equal or exceed 17.");
    System.out
        .println("    -The goal is to have a higher card total than the dealer without exceeding 21(going bust!).");
    System.out.println("    -Players win if they beat the dealer");
    System.out.println("");
  }

  public static int inputNumOfPlayers(Scanner sc) {
    int numOfPlayers = 0;
    while (numOfPlayers < MIN_PLAYERS || numOfPlayers > MAX_PLAYERS) {
      System.out.println(NUMBER_OF_PLAYERS_INPUT_MESSAGE);
      numOfPlayers = isUserInputAnInt(sc);
    }

    return numOfPlayers;
  }

  public void initialisePlayers(int numOfPlayers, Scanner sc) {
    int i = 1;
    while (i <= numOfPlayers) {
      playerList.add(new Player(inputPlayerName(sc, i), new Hand()));
      i++;
    }
  }

  public void placeBets(List<Player> playerList, Scanner sc) {
    int betAmount;
    for (Player player : playerList) {
      if (player.getPlayerWallet() > 0) {
        do {
          System.out.println(String.format(PLACE_BETS_MESSAGE, player.getPlayerName(), player.getPlayerWallet()));
          betAmount = sc.nextInt();
          player.setPlayerBet(betAmount);
        } while (!(betAmount > 0 && betAmount <= player.getPlayerWallet()));
      }
    }
  }

  public void playersTurn(List<Player> playerList, Scanner sc) {
    for (Player player : playerList) {
      Turn turn = new Turn();
      turn.dealCard(deck, player.getHand());
      turn.dealCard(deck, player.getHand());
      turn.hitOrStick(deck, player.getPlayerName(), sc, player.getHand());
    }
  }

  public int dealersTurn() {
    System.out.println(DEALERS_TURN_MESSAGE);
    Turn turn = new Turn();
    turn.dealCard(deck, dealer.getHand());
    turn.dealCard(deck, dealer.getHand());
    dealer.hitOrStick(deck, turn);
    return dealer.getHand().checkRank();
  }

  public void settleBets(List<Player> playerList, int dealersResult) {
    for (Player player : playerList) {
      if ((player.getHand().checkRank() > 21) || (player.getHand().checkRank() < dealersResult && dealersResult <= 21)) {
        int reduceWallet = player.getPlayerWallet() - player.getPlayerBet();
        player.setPlayerWallet(reduceWallet);
        System.out.println(String.format(LOOSE_MESSAGE, player.getPlayerName()));
      } else {
        int increaseWallet = player.getPlayerWallet() + player.getPlayerBet();
        player.setPlayerWallet(increaseWallet);
        System.out.println(String.format(WIN_MESSAGE, player.getPlayerName()));
      }
    }
  }

  public void displayPlayersWallet(List<Player> playerList) {
    for (Player player : playerList) {
      System.out.println(String.format(WALLET_BALANCE_MESSAGE, player.getPlayerName(), player.getPlayerWallet()));
    }
  }
  
  public void clearHands(List<Player> playerList) {
    for (Player player : playerList) {
      player.newHand();
    }
    dealer.newHand();
  }

  public static String inputPlayerName(Scanner sc, int i) {
    System.out.println(String.format(PLAYER_NAME_INPUT_MESSAGE, i));
    String playerName = sc.next();

    return playerName;
  }

  public static int isUserInputAnInt(Scanner sc) {
    while (!sc.hasNextInt()) {
      System.out.println(NUMBER_OF_PLAYERS_INPUT_MESSAGE);
      sc.nextLine();
    }
    return sc.nextInt();
  }
}