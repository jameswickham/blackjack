package com.james;

public class Dealer {

  private Hand hand = new Hand();

  public Hand getHand() {
    return hand;
  }

  public void setHand(Hand hand) {
    this.hand = hand;
  }
  
  public void newHand() {
    hand = new Hand();
  }

  public void hitOrStick(Deck deck, Turn turn) {

    do {
      if (hand.isBlackjack()) {
        System.out.println("The Dealer has Blackjack! Their hand is: " + getHand());
        break;
      }

      if (hand.checkRank() <= 15) {
        System.out.println("The dealer's hand is " + getHand() + ". The dealer hits..");
        turn.dealCard(deck, getHand());
      } else {
        System.out.println("The Dealer has stuck at: " + getHand());
        break;
      }

    } while (!hand.isBust());

    if (hand.isBust()) {
      System.out.println("Dealer has bust! Their hand is " + getHand());
    }
  }
}