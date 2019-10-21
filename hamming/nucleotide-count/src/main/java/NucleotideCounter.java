import java.util.HashMap;
import java.util.Map;

class NucleotideCounter {

  private Map<Character, Integer> counts;
  private String input;

  public NucleotideCounter(String input) {

    if (input == null) {
      throw new IllegalArgumentException("Empty input not allowed");
    }
    if (input.length() > 0 && !input.matches("[ACGT]+")) {
      throw new IllegalArgumentException("Unknown nucleotyde");
    }

    this.input = input;

    counts = new HashMap<>();
    counts.put('A', 0);
    counts.put('C', 0);
    counts.put('G', 0);
    counts.put('T', 0);
  }

  Map<Character, Integer> nucleotideCounts() {
    input.chars().forEach(x -> counts.put((char) x, counts.get((char) x) + 1));
    return counts;
  }
  
}
