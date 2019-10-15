import java.util.HashMap;
import java.util.Map;

class House {

  private static final Map<Integer, String> poemMap;
  static {
    poemMap = new HashMap<>();
    poemMap.put(1, "This is the house that Jack built.");
    poemMap.put(2, "the malt that lay in ");
    poemMap.put(3, "the rat that ate ");
    poemMap.put(4, "the cat that killed ");
    poemMap.put(5, "the dog that worried ");
    poemMap.put(6, "the cow with the crumpled horn that tossed ");
    poemMap.put(7, "the maiden all forlorn that milked ");
    poemMap.put(8, "the man all tattered and torn that kissed ");
    poemMap.put(9, "the priest all shaven and shorn that married ");
    poemMap.put(10, "the rooster that crowed in the morn that woke ");
    poemMap.put(11, "the farmer sowing his corn that kept ");
    poemMap.put(12, "the horse and the hound and the horn that belonged to ");
  }

  String verse(int verseNumber) {
    return verse(1, verseNumber, poemMap.get(1));
  }

  private String verse(int verseIndex, int verseNumber, String accumulator) {
    if (verseNumber == 1) {
      return accumulator;
    }
    accumulator = accumulator.replace("This is ", "This is " + poemMap.get(++verseIndex));
    return verse(verseIndex, --verseNumber, accumulator);
  }

  String sing() {
    return verses(1, 12);
  }

  String verses(int startVerse, int endVerse) {
    StringBuilder sb = new StringBuilder();
    for (int i = startVerse; i < endVerse; i++) {
      sb.append(verse(i) + "\n");
    }
    return sb.append(verse(endVerse)).toString();
  }

}