import java.util.EnumSet;

public enum Suit {
		DIAMONDS('D'), CLUBS('C'), SPADES('S'), HEARTS('H');

		private char representation;

		private Suit(final char representation) {
			this.representation = representation;
		}

		public char getRepresentation() {
			return representation;
		}

		public static Suit getSuitFromRep(final char rep) {
			for (Suit suit : EnumSet.allOf(Suit.class)) {
				if (rep == suit.getRepresentation()) {
					return suit;
				}
			}
			return null;
		}
	}