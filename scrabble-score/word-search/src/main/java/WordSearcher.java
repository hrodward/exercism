import java.util.AbstractMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class WordSearcher {

	private enum Direction {

		HORIZONTAL_RIGHT(0, 1),
		HORIZONTAL_LEFT(0, -1),
		VERTICAL_UP(-1, 0),
		VERTICAL_DOWN(1, 0),
		DIAGONAL_UP_RIGHT(-1, 1),
		DIAGONAL_UP_LEFT(-1, -1),
		DIAGONAL_DOWN_RIGHT(1, 1),
		DIAGONAL_DOWN_LEFT(1, -1);

		private final int incrementX;
		private final int incrementY;

		private Direction(final int y, final int x) {
			this.incrementY = y;
			this.incrementX = x;
		}

		public int getIncrementX() {
			return incrementX;
		}

		public int getIncrementY() {
			return incrementY;
		}

		public static Direction getDirectionByIncrements(final int x, final int y) {
			for (Direction d : EnumSet.allOf(Direction.class)) {
				if (d.getIncrementX() == x && d.getIncrementY() == y) {
					return d;
				}
			}
			return null;
		}

	}

	public Map<String, Optional<WordLocation>> search(final Set<String> searchWords, final char[][] vv) {
		return searchWords
				.stream()
				.map(word -> search(word, vv))
				.collect(Collectors.toMap(Entry::getKey, Entry::getValue));
	}

	private Entry<String, Optional<WordLocation>> search(final String searchWord, final char[][] vv) {
		char firstLetter = searchWord.charAt(0);
		for (int y = 1; y <= vv.length; y++) {
			char[] row = vv[y - 1];
			for (int x = 1; x <= row.length; x++) {
				if (firstLetter != row[x - 1]) {
					continue;
				}
				Optional<WordLocation> res = searchFrom(searchWord, vv, y, x);
				if (res.isPresent()) {
					return new AbstractMap.SimpleEntry<>(searchWord, res);
				}
			}
		}
		return new AbstractMap.SimpleEntry<>(searchWord, Optional.empty());
	}

	public static void main(final String[] args) {
		WordSearcher wordSearcher= new WordSearcher();
		Map<String, Optional<WordLocation>> expectedLocations = new HashMap<>();
		expectedLocations.put("clojure", Optional.of(new WordLocation(new Pair(1, 10), new Pair(7, 10))));

		Set<String> searchWords = expectedLocations.keySet();

		Map<String, Optional<WordLocation>> actualLocations = wordSearcher.search(
				searchWords,
				new char[][]{
					{'j', 'e', 'f', 'b', 'l', 'p', 'e', 'p', 'r', 'e'},
					{'c', 'a', 'm', 'd', 'c', 'i', 'm', 'g', 't', 'c'},
					{'o', 'i', 'v', 'o', 'k', 'p', 'r', 'j', 's', 'm'},
					{'p', 'b', 'w', 'a', 's', 'q', 'r', 'o', 'u', 'a'},
					{'r', 'i', 'x', 'i', 'l', 'e', 'l', 'h', 'r', 's'},
					{'w', 'o', 'l', 'c', 'q', 'l', 'i', 'r', 'p', 'c'},
					{'s', 'c', 'r', 'e', 'e', 'a', 'u', 'm', 'g', 'r'},
					{'a', 'l', 'x', 'h', 'p', 'b', 'u', 'r', 'y', 'i'},
					{'j', 'a', 'l', 'a', 'y', 'c', 'a', 'l', 'm', 'p'},
					{'c', 'l', 'o', 'j', 'u', 'r', 'e', 'r', 'm', 't'}
				}
				);

		System.out.println(actualLocations);
	}

	private Optional<WordLocation> searchFrom(final String searchWord, final char[][] vv, final int startY, final int startX) {
		final int limitY = vv.length;
		final int limitX = vv[0].length;
		final char secondChar = searchWord.charAt(1);

		Optional<Pair> findFirst =
				IntStream.rangeClosed(startY - 1, startY + 1)
				.mapToObj(rowIndex ->
      		IntStream.rangeClosed(startX - 1, startX + 1)
    		    .filter(colIndex -> rowIndex > 0 && rowIndex <= limitY && colIndex > 0 && colIndex <= limitX && secondChar == vv[rowIndex - 1][colIndex - 1])
        		.mapToObj(colIndex -> {
      				int diffY = rowIndex - startY;
      				int diffX = colIndex - startX;
      				Direction dir = Direction.getDirectionByIncrements(diffX, diffY);
      				return verifyDirection(searchWord, vv, startY, startX, dir);
        		})
        		.filter(o -> o != null)
    		)
    		.flatMap(in -> in)
    		.findFirst();

		if (findFirst.isPresent()) {
			return Optional.of(new WordLocation(new Pair(startX, startY), findFirst.get()));
		}
		return Optional.empty();
	}

	private Pair verifyDirection(final String searchWord, final char[][] vv, final int startY, final int startX, final Direction dir) {
		final int limitY = vv.length;
		final int limitX = vv[0].length;
		int y = startY;
		int x = startX;


		for (int idx = 1; idx < searchWord.length(); idx++) {
			y += dir.getIncrementY();
			x += dir.getIncrementX();
			if (y <= 0 || y > limitY || x <= 0 || x > limitX) {
				return null; // not found
			}
			final char currentChar = searchWord.charAt(idx);
			char charAtBoard = vv[y - 1][x - 1];
			if (currentChar != charAtBoard) {
				return null;
			}
		}

		return new Pair(x, y);
	}

}