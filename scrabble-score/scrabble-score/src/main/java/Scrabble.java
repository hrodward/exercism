import java.util.stream.Collectors;
import java.util.stream.Stream;

class Scrabble {

    private final String word;

    Scrabble(String word) {
        this.word = word;
    }

    int getScore() {
        Stream<String> stream = Stream.of(word.toUpperCase().split(""));
        return stream.collect(Collectors.summingInt(this::getLetterScore));

        // Alternative solution:
        // IntStream is = word.toUpperCase().codePoints().map(c -> (char) c);
        // return is.reduce(0, (a, b) -> a + getLetterScore(String.valueOf((char) b)));
    }

    private int getLetterScore(String letter) {

        int score = 0;

        switch (letter) {
        case "":
            break;
        case "A":
        case "E":
        case "I":
        case "O":
        case "U":
        case "L":
        case "N":
        case "R":
        case "S":
        case "T":
            score = 1;
            break;
        case "D":
        case "G":
            score = 2;
            break;
        case "B":
        case "C":
        case "M":
        case "P":
            score = 3;
            break;
        case "F":
        case "H":
        case "V":
        case "W":
        case "Y":
            score = 4;
            break;
        case "K":
            score = 5;
            break;
        case "J":
        case "X":
            score = 8;
            break;
        case "Q":
        case "Z":
            score = 10;
            break;
        default:
            throw new IllegalArgumentException("Letter not in the abecedary");
        }

        return score;
    }

}
