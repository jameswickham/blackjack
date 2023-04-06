package com.james;

import java.util.List;
import java.util.ArrayList;

public class Hand {
  public List<Card> cards;

  public Hand() {
    cards = new ArrayList<>();
  }

  public void addCard(Card card) {
    cards.add(card);
  }
  
  public void newHand() {
    cards = new ArrayList<>();
  }

  public int checkRank() {
    int value = 0;
    int aces = 0;
    for (Card card : cards) {
      int cardValue = card.rankToValue();
      value += cardValue;
      if (cardValue == 11) {
        aces++;
      }
      while (value > 21 && aces > 0) {
        value -= 10;
        aces--;
      }
    }
    return value;
  }

  public boolean isBust() {
    return checkRank() > 21;
  }
  
  public boolean isBlackjack() {
    return checkRank() == 21;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (Card card : cards) {
      sb.append(card).append(", ");
    }
    
    return sb.toString();
  }
}
