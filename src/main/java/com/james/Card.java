package com.james;

public class Card {

  private final Rank rank;
  private final Suits suit;

  public Card(Rank rank, Suits suit) {
    this.rank = rank;
    this.suit = suit;
  }

  public int rankToValue() {
    switch (rank) {
    case ACE:
      return 11;
    case TWO:
      return 2;
    case THREE:
      return 3;
    case FOUR:
      return 4;
    case FIVE:
      return 5;
    case SIX:
      return 6;
    case SEVEN:
      return 7;
    case EIGHT:
      return 8;
    case NINE:
      return 9;
    default:
      return 10;
    }
  }

  public String toString() {
    return rank + " of " + suit;
  }

}
