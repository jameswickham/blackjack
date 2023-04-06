package com.james;

public class Player {

  private int playerWallet = 100;
  private int playerBet;
  private String playerName;
  private Hand hand = new Hand();

  public Player(String playerName, Hand playerHand) {
    setPlayerName(playerName);
  }

  public int getPlayerWallet() {
    return playerWallet;
  }

  public void setPlayerWallet(int playerWallet) {
    this.playerWallet = playerWallet;
  }

  public int getPlayerBet() {
    return playerBet;
  }

  public void setPlayerBet(int playerBet) {
    this.playerBet = playerBet;
  }

  public void setPlayerName(String playerName) {
    this.playerName = playerName;
  }

  public String getPlayerName() {
    return playerName;
  }

  public Hand getHand() {
    return hand;
  }

  public void setHand(Hand hand) {
    this.hand = hand;
  }
  
  public void newHand() {
    hand = new Hand();
  }
}
