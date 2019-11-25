import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Poker {

	private final List<Hand> hands;

	public Poker(final List<String> textHands) {
		hands = textHands.stream()
//							.peek(textHand -> System.out.println("Poker.textHand=" + textHand))
							.map(Hand::new)
							.peek(hand -> System.out.println("Poker.sortedCardHand=" + hand))
							.collect(Collectors.toList());
	}

	public List<String> getBestHands() {
		System.out.println(hands);
		List<Hand> bestHands = hands.stream()
//			.peek(hand -> System.out.println("getBestHands.hand=" + hand))
			.collect(Collectors.groupingBy(hand -> hand.getClassification()))
			.entrySet()
			.stream()
			.peek(hands -> System.out.println("getBestHands.For classification " + hands.getKey() + ": " + hands.getValue()))
			.sorted((entry1, entry2) -> entry1.getKey() - entry2.getKey())
			.peek(hands -> System.out.println("getBestHands.SORTED - For classification " + hands.getKey() + ": " + hands.getValue()))
			.findFirst().get().getValue().stream().sorted().collect(Collectors.toList())
			;


		int maxCardNumber = -1;
		List<String> bestOfTheBest = new ArrayList<>();
		for (Hand hand : bestHands) {
			int currentMaxCardNumber = hand.getHand().get(0).getNumber();
			if(maxCardNumber == -1) {
				maxCardNumber = currentMaxCardNumber;
				bestOfTheBest.add(hand.getTextCards());
			} else if (maxCardNumber == currentMaxCardNumber){
				bestOfTheBest.add(hand.getTextCards());
			}

//				bestHands.get()
//					.getValue().stream()
//					.sorted()
//					.peek(hand -> System.out.println("getBestHands.bestHands hand: " + hand))
//					.map(Hand::getTextCards)
//					.collect(Collectors.toList());
		}
		return bestOfTheBest;
	}

	public static void main(final String[] args) {
		String highest8 = "4D 5S 6S 8D 3C";
    String highest10 = "2S 4C 7S 9H 10H";
    String highestJh = "3S 4S 5D 6H JH";
    String highestJd = "3H 4H 5C 6C JD";
    String pairOf2 = "4S 2H 6S 2D JH";
    String doublePair2AndQ = "2S QS 2C QD JH";
    String threeOf4 = "4S 5H 4C 8S 4H";
    String straight = "3S 4D 2S 6D 5C";
    String flushTo7 = "2S 4S 5S 6S 7S";
    String full = "4S 5H 4C 5D 4H";
    String squareOf3 = "3S 3H 2S 3D 3C";
    String straightFlushTo9 = "7S 8S 9S 6S 10S";

//		List<String> textHands = Arrays.asList(highest8, highest10, highestJh, highestJd);
		List<String> textHands = Arrays.asList(highest10, squareOf3, straightFlushTo9, highestJh, full, straight, highest8);
//		List<String> textHands = Arrays.asList(highest10);
		Poker pk = new Poker(textHands);
		System.out.println(pk.getBestHands());
	}

}
