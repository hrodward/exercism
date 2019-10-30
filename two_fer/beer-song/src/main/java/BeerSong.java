import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class BeerSong {

  private static final String GO = "Go to the store and buy some more";
  private static final String TAKE = "Take one down and pass it around";
  private static final String VERSE = "%s bottle%s of beer on the wall, %s bottle%s of beer.\n%s, %s bottle%s of beer on the wall.\n\n";

  private AtomicInteger counter;

  String singSong() {
    return sing(99, 100);
  }

  String sing(int verseStart, int verses) {
    counter = new AtomicInteger(verseStart);
    return Stream.generate(this::verse).limit(verses).collect(Collectors.joining());
  }

  private String verse() {
    int index = counter.getAndDecrement();
    switch (index) {
      case 0:  return String.format(VERSE, "No more", "s", "no more", "s", GO, "99", "s");
      case 1:  return String.format(VERSE, index, "", index, "", TAKE.replace("one", "it"), "no more", "s");
      case 2:  return String.format(VERSE, index, "s", index, "s", TAKE, index - 1, "");
      default: return String.format(VERSE, index, "s", index, "s", TAKE, index - 1, "s");
    }
  }

}