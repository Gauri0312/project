import java.util.*;

enum Suit {
    SPADES, CLUBS, HEARTS, DIAMONDS
}

enum Rank {
    TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE
}

class Card {
    private final Suit suit;
    private final Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}

class Deck {
    private final List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("The deck is empty! Cannot draw more cards.");
        }
        return cards.remove(cards.size() - 1);
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public List<Card> getRemainingCards() {
        return new ArrayList<>(cards);
    }
}

class CardComparator implements Comparator<Card> {
    @Override
    public int compare(Card card1, Card card2) {
        // Determine color (Red: HEARTS, DIAMONDS; Black: SPADES, CLUBS)
        int color1 = (card1.getSuit() == Suit.HEARTS || card1.getSuit() == Suit.DIAMONDS) ? 1 : 0;
        int color2 = (card2.getSuit() == Suit.HEARTS || card2.getSuit() == Suit.DIAMONDS) ? 1 : 0;

        if (color1 != color2) {
            return Integer.compare(color1, color2);
        }

        // Compare suits within the same color
        int suitComparison = card1.getSuit().compareTo(card2.getSuit());
        if (suitComparison != 0) {
            return suitComparison;
        }

        // Compare ranks
        return card1.getRank().compareTo(card2.getRank());
    }
}

public class DeckOfCardsSimulation {
    public static void main(String[] args) {
        Deck deck = new Deck();

        System.out.println("Shuffling the Deck...");
        deck.shuffle();

        System.out.println("Drawing 20 Random Cards...");
        List<Card> drawnCards = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            drawnCards.add(deck.drawCard());
        }

        System.out.println("Drawn Cards:");
        for (Card card : drawnCards) {
            System.out.println(card);
        }

        System.out.println("\nSorting the Drawn Cards...");
        Collections.sort(drawnCards, new CardComparator());

        System.out.println("Sorted Cards:");
        for (Card card : drawnCards) {
            System.out.println(card);
        }
    }
}
