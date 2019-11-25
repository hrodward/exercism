import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Hand implements Comparable<Hand> {

	private final String textCards;
	private List<Card> cards = new ArrayList<>(5);
	private final int classification;

	private final Map<Integer, Integer> numbers = new HashMap<>();
	private final Map<Suit, Integer> suits = new HashMap<>();

	Hand(final String textCards) {
		this.textCards = textCards;
		cards = parseHand(textCards);
		classification = calculateClassification();
	}

	private List<Card> parseHand(final String textCards) {
		return
				Stream.of(textCards.split(" "))
//    				.peek(card -> System.out.println("parseHand.textCard=" + card))
  				.map(Card::new)
//    				.peek(card -> System.out.println("parseHand.cardCard=" + card))
  				.sorted()
//    				.peek(card -> System.out.println("parseHand.sortedCardCard=" + card))
  				.map(card -> {
  					int numberCounter = numbers.containsKey(card.getNumber()) ? numbers.get(card.getNumber()) + 1 : 1;
  					numbers.put(card.getNumber(), numberCounter);

  					int suitCounter = suits.containsKey(card.getSuit()) ? suits.get(card.getSuit()) + 1 : 1;
  					suits.put(card.getSuit(), suitCounter);
						return card;
  				})
  				.collect(Collectors.toList());
	}

	private int calculateClassification() {
		int classification = 0;

		int differentNumbers = numbers.size();
		int differentSuits = suits.size();
		boolean isSequentialHand = true;
		int number = -1;

		for (Card card : cards) {
			int currentNumber = card.getNumber();
			if (number == -1) {
				number = currentNumber;
			} else {
				if(isSequentialHand && currentNumber == number - 1) {
					number = currentNumber;
				} else {
					isSequentialHand = false;
				}
			}
		}

		int maxCounter = 0;
		for (Entry<Integer, Integer> numEntry : numbers.entrySet()) {
			maxCounter = numEntry.getValue() > maxCounter ? numEntry.getValue() : maxCounter;
		}

		if(differentSuits == 1 && isSequentialHand) {
			classification = 1; // straight flush
		} else if(differentNumbers == 2) {
			if (maxCounter == 4) {
				classification = 2; // 4 of a kind
			} else {
				classification = 3; // full house
			}
		} else if(differentSuits == 1) {
			classification = 4; // flush
		} else if(isSequentialHand) {
			classification = 5; // straight
		} else if(differentNumbers == 3) {
			if(maxCounter == 3) {
				classification = 6; // 3 of a kind
			} else {
				classification = 7; // two pair
			}
		} else if(differentNumbers == 4) {
			classification = 8; // one pair
		} else if(differentNumbers == 5 && !isSequentialHand) {
			classification = 9; // high card
		}

		return classification;
	}

	@Override
	public int compareTo(final Hand otherHand) {
		int classification1 = this.getClassification();
		int classification2 = otherHand.getClassification();
		if (classification1 != classification2) {
//			System.out.println("Sorted by classification: " + classification1 + " vs " + classification2);
			return classification1 - classification2;
		} else {
			for (int i = 0; i < this.getHand().size(); i++) {
				Card c1 = this.getHand().get(i);
				Card c2 = otherHand.getHand().get(i);
				int cardComparison = c1.compareTo(c2);
				if (cardComparison != 0) {
//					System.out.println("Sorted by card: " + c1 + " vs " + c2);
					return cardComparison;
				}
			}
		}
//		System.out.println("Hands are equal: " + this + " vs " + otherHand);
		return 0;
	}

	public List<Card> getHand() {
		return cards;
	}

	@Override
	public String toString() {
		return cards.stream().map(Card::toString).collect(Collectors.joining(" "));
	}

	public int getClassification() {
		return classification;
	}

	public String getTextCards() {
		return textCards;
	}

}