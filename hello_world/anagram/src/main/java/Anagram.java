import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Anagram {

  private String wordSortedAndLowerCase;
  private String word;

  public Anagram(String word) {
    this.word = word;
    this.wordSortedAndLowerCase = sortIgnoreCase(word);
  }

  public List<String> match(List<String> candidates) {
    return candidates.stream()
        .filter(x -> !this.word.equalsIgnoreCase(x) && this.wordSortedAndLowerCase.equalsIgnoreCase(sortIgnoreCase(x)))
        .collect(Collectors.toList());
  }

  private String sortIgnoreCase(String anyWord) {
    if (anyWord == null || anyWord.isEmpty()) {
      return anyWord;
    }
    List<String> letters = Arrays.asList(anyWord.toLowerCase().split(""));
    letters.sort((a, b) -> a.compareTo(b));
    return letters.toString();
  }

}