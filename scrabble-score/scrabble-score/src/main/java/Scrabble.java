import java.util.HashMap;
import java.util.Map;

class Scrabble {

    private static final Map<Character, Integer> SCORES;
    static {
        SCORES = new HashMap<>();
        fill(SCORES, "AEIOULNRST", 1);
        fill(SCORES, "DG", 2);
        fill(SCORES, "BCMP", 3);
        fill(SCORES, "FHVWY", 4);
        fill(SCORES, "K", 5);
        fill(SCORES, "JX", 8);
        fill(SCORES, "QZ", 10);
    }

    private static void fill(Map<Character, Integer> theMap, String letters, int score) {
        letters.chars().forEach(letter -> theMap.put((char) letter, score));
    }

    private final String word;

    Scrabble(String word) {
        this.word = word;
    }

    int getScore() {
        return word.toUpperCase().chars().map(n -> SCORES.get((char) n)).sum();
    }
}
