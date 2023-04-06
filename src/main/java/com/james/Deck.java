package com.james;

import java.util.ArrayList;
import java.util.Collections;

import java.util.*;

public class Deck {
  private final List<Card> cards;

  public Deck() {
    cards = new ArrayList<>();
    for (Suits suit : Suits.values()) {
      for (Rank rank : Rank.values()) {
        cards.add(new Card(rank, suit));
      }
    }
    Collections.shuffle(cards);
  }

  public Card draw() {
    return cards.remove(0);
  }
}