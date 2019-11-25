public class Card implements Comparable<Card> {

	private final Suit suit;
	private final int number;

	Card(final String value, final Suit suit) {
		this.number = getNumericValue(value);
		this.suit = suit;
	}

	public Card(final String textCard) {
		this(textCard.substring(0, textCard.length() - 1), Suit.getSuitFromRep(textCard.charAt(textCard.length() - 1)));
	}

	private int getNumericValue(final String value) {
		int num = 0;
		switch (value) {
			case "J":
				num = 11;
				break;
			case "Q":
				num = 12;
				break;
			case "K":
				num = 13;
				break;
			case "A":
				num = 1;
				break;
			default:
				num = Integer.parseInt(value);
		}
		return num;
	}

	private String getFaceValue(final int number) {
		String val;
		switch (number) {
			case 11:
				val = "J";
				break;
			case 12:
				val = "Q";
				break;
			case 13:
				val = "K";
				break;
			case 1:
				val = "A";
				break;
			default:
				val = String.valueOf(number);
		}
		return val;
	}

	public Suit getSuit() {
		return suit;
	}

	public int getNumber() {
		return number;
	}

	@Override
	public String toString() {
		return getFaceValue(number) + suit.getRepresentation();
	}

	@Override
	public int compareTo(final Card otherCard) {
		return otherCard.number - number;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + number;
		result = prime * result + (suit == null ? 0 : suit.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Card other = (Card) obj;
		if (number != other.number) {
			return false;
		}
		if (suit != other.suit) {
			return false;
		}
		return true;
	}

}