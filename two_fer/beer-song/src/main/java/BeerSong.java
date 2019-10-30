import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class BeerSong {

  private static final String ZERO = "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n\n";
  private static final String ONE = "1 bottle of beer on the wall, 1 bottle of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n\n";
  private static final String OTHER = "%d bottles of beer on the wall, %d bottles of beer.\nTake one down and pass it around, %d bottle%s of beer on the wall.\n\n";

  private AtomicInteger counter;

  public static void main(String[] args) {
    System.out.println(new BeerSong().sing(2, 3));
  }

  String singSong() {
    return sing(99, 100);
  }

  String sing(int verseStart, int verses) {
    counter = new AtomicInteger(verseStart);
    return Stream.generate(this::extracted).limit(verses).collect(Collectors.joining());
  }

  private String extracted() {
    int index = counter.getAndDecrement();
    switch (index) {
      case 0: return ZERO;
      case 1: return ONE;
      default: return String.format(OTHER, index, index, index - 1, index == 2 ? "" : "s");
    }
  }

  String singAlternative(int verseStart, int verses) {
    StringBuilder sb = new StringBuilder();
    for (int index = verseStart, counter = 0; counter < verses; index--, counter++) {
      switch (index) {
      case 1:
        sb.append(ONE);
        break;
      case 0:
        sb.append(ZERO);
        break;
      case 2:
        sb.append(String.format(OTHER, index, index, index - 1, ""));
        break;
      default:
        sb.append(String.format(OTHER, index, index, index - 1, "s"));
      }
    }
    return sb.toString();
  }

}