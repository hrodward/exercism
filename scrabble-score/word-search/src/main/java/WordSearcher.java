import java.util.AbstractMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
		expectedLocations.put("coffee", Optional.of(new WordLocation(new Pair(2, 1), new Pair(7, 1))));

		Set<String> searchWords = expectedLocations.keySet();

		Map<String, Optional<WordLocation>> actualLocations = wordSearcher.search(
				searchWords,
				new char[][]{
					{'x', 'c', 'o', 'f', 'f', 'e', 'e', 'z', 'l', 'p'}
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
      				System.out.println("Dir=" + dir);
      				return verifyDirection(searchWord, vv, rowIndex, colIndex, dir);
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
		final int limitX = vv[0].length;
		final int limitY = vv.length;
		AtomicInteger y = new AtomicInteger(startY);
		AtomicInteger x = new AtomicInteger(startX);
		AtomicInteger idx = new AtomicInteger(2);

		System.out.println("Start coords: [" + startX + "," + startY + "]");
		boolean allMatch = Stream.generate(() -> new Pair(x.addAndGet(dir.getIncrementX()), y.addAndGet(dir.getIncrementY())))
			.limit(searchWord.length() - 2)
			.peek(pair -> System.out.println(pair))
			.allMatch(pair -> {
				boolean first = pair.getY() > 0 && pair.getY() <= limitY && pair.getX() > 0 && pair.getX() <= limitX;
				if (first ) {
					System.out.println("  first=" + first);
					char wordChar = searchWord.charAt(idx.getAndIncrement());
					char boardChar = vv[pair.getY() - 1][pair.getX() - 1];
					System.out.println("    pairY=" + pair.getY() + ", pariX=" + pair.getX());
			    boolean second = wordChar == boardChar;
			    System.out.println("  wordChar=" + wordChar + " vs " + boardChar);
					return first && second;
				}
				return false;
			});

		if(allMatch) {
			Pair pair = new Pair(x.get(), y.get());
			System.out.println("Returning Pair" + pair);
			return pair;
		}
		return null;
	}

}