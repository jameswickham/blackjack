package com.james;

import java.util.Scanner;

public class Turn {

  public void dealCard(Deck deck, Hand hand) {
    hand.addCard(deck.draw());
  }

  public void hitOrStick(Deck deck, String playerName, Scanner sc, Hand hand) {

    while (!hand.isBust()) {

      if (hand.isBlackjack()) {
        System.out.println(playerName + " You have Blackjack! Your hand is: " + hand);
        break;
      }

      sc.nextLine();

      System.out.println(playerName + " Your hand is: " + hand + " do you want to stick or hit [S/H]");

      if (sc.hasNext("H")) {
        dealCard(deck, hand);
      } else if (sc.hasNext("S")) {
        System.out.println(playerName + " You've stuck, your final hand is: " + hand);
        break;
      } else {
        System.out.println(playerName + " Please Enter either S/H");
      }

    }

    if (hand.isBust()) {
      System.out.println(playerName + " You've bust! Your final hand is: " + hand);
    }

  }
}
