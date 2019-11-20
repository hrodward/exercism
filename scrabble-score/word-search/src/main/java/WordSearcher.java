import java.util.AbstractMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class WordSearcher {

	private char[][] vv;
	private String searchWord;

	public Map<String, Optional<WordLocation>> search(final Set<String> searchWords, final char[][] vv) {
		this.vv = vv;

		return searchWords
				.stream()
				.map(this::search)
				.collect(Collectors.toMap(Entry::getKey, Entry::getValue));
	}

	private Entry<String, Optional<WordLocation>> search(final String searchWord) {
		this.searchWord = searchWord;
		final char firstLetter = searchWord.charAt(0);

		Optional<WordLocation> wordLocation =
				IntStream.rangeClosed(1, vv.length)
      	.mapToObj(x ->
      		IntStream.rangeClosed(1, vv[x - 1].length)
      			.filter(y -> firstLetter == vv[x - 1][y - 1])
      			.mapToObj(y -> searchFrom(x, y))
        		.filter(Optional::isPresent)
      	)
    		.flatMap(in -> in)
    		.findFirst()
    		.orElse(Optional.empty());

		return new AbstractMap.SimpleEntry<>(searchWord, wordLocation);
	}

	private Optional<WordLocation> searchFrom(final int startX, final int startY) {
		final int limitX = vv.length;
		final int limitY = vv[0].length;
		final char secondChar = searchWord.charAt(1); // first char has already been found

		Optional<Pair> end =
				IntStream.rangeClosed(startX - 1, startX + 1)
				.mapToObj(x ->
      		IntStream.rangeClosed(startY - 1, startY + 1)
    		    .filter(y -> x > 0 && x <= limitX && y > 0 && y <= limitY && secondChar == vv[x - 1][y - 1])
        		.mapToObj(y -> findEndPair(x, y, Direction.getDirectionByIncrements(x - startX, y - startY)))
        		.filter(Objects::nonNull)
    		)
    		.flatMap(in -> in)
    		.findFirst();

		if (end.isPresent()) {
			return Optional.of(new WordLocation(new Pair(startY, startX), end.get()));
		}
		return Optional.empty();
	}

	private Pair findEndPair(final int startY, final int startX, final Direction dir) {
		final int limitY = vv.length;
		final int limitX = vv[0].length;
		final AtomicInteger x = new AtomicInteger(startX);
		final AtomicInteger y = new AtomicInteger(startY);
		final AtomicInteger idx = new AtomicInteger(2); // first two chars have already been checked

		boolean allLettersMatch = Stream
			.generate(() -> new Pair(x.addAndGet(dir.getIncrementY()), y.addAndGet(dir.getIncrementX())))
			.limit(searchWord.length() - 2)
			.allMatch(p -> {
		    boolean isNotOutOfBoard = p.getX() > 0 && p.getX() <= limitX && p.getY() > 0 && p.getY() <= limitY;
		    return isNotOutOfBoard && searchWord.charAt(idx.getAndIncrement()) == vv[p.getY() - 1][p.getX() - 1];
			});

		return allLettersMatch ? new Pair(x.get(), y.get()) : null;
	}

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

		private Direction(final int x, final int y) {
			this.incrementX = x;
			this.incrementY = y;
		}

		public int getIncrementX() {
			return incrementX;
		}

		public int getIncrementY() {
			return incrementY;
		}

		public static Direction getDirectionByIncrements(final int x, final int y) {
			for (Direction d : EnumSet.allOf(Direction.class)) {
				if (d.getIncrementY() == y && d.getIncrementX() == x) {
					return d;
				}
			}
			return null;
		}

	}

}