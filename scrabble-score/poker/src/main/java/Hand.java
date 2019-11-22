import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Hand implements Comparable<Hand> {

		private List<Card> cards = new ArrayList<>(5);

		Hand(final String textCards) {
			cards = parseHand(textCards);
		}

		private List<Card> parseHand(final String textCards) {
			return
					Stream.of(textCards.split(" "))
//    				.peek(card -> System.out.println("parseHand.textCard=" + card))
    				.map(Card::new)
//    				.peek(card -> System.out.println("parseHand.cardCard=" + card))
    				.sorted()
//    				.peek(card -> System.out.println("parseHand.sortedCardCard=" + card))
    				.collect(Collectors.toList());
		}

		@Override
		public int compareTo(final Hand otherHand) {
			for (int i = 0; i < cards.size(); i++) {
				Card c1 = this.cards.get(i);
				Card c2 = otherHand.cards.get(i);
				int cardComparison = c1.compareTo(c2);
				if(cardComparison > 0) {
					return 1;
				} else if(cardComparison < 0) {
					return -1;
				}
			}
			return 0;
		}

		public List<Card> getHand() {
			return cards;
		}

		@Override
		public String toString() {
			return cards.stream().map(Card::toString).collect(Collectors.joining(" "));
		}

	}