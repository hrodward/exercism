import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Poker {

	private final List<Hand> hands;

	public Poker(final List<String> textHands) {
		hands = textHands.stream()
							.peek(textHand -> System.out.println("Poker.textHand=" + textHand))
							.map(Hand::new)
							.peek(hand -> System.out.println("Poker.sortedCardHand=" + hand))
							.collect(Collectors.toList());
	}

	public List<String> getBestHands() {
		System.out.println(hands);
		return
				hands.stream()
					.peek(hand -> System.out.println("getBestHands.hand=" + hand))
					.sorted()
					.peek(hand -> System.out.println("getBestHands.sortedHand: " + hand))
					.map(Hand::toString)
					.peek(hand -> System.out.println("getBestHands.textHand: " + hand))
					.collect(Collectors.toList());
	}

	public static void main(final String[] args) {
		String highest8 = "4D 5S 6S 8D 3C";
    String highest10 = "2S 4C 7S 9H 10H";
    String highestJh = "3S 4S 5D 6H JH";
    String highestJd = "3H 4H 5C 6C JD";

		List<String> textHands = Arrays.asList(highest8, highest10, highestJh, highestJd);
//		List<String> textHands = Arrays.asList(highest10);
		Poker pk = new Poker(textHands);
		System.out.println(pk.getBestHands());
	}

}
