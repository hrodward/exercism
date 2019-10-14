import java.util.HashMap;
import java.util.Map;

class TwelveDays {

    private static final Map<Integer, String> lyrics;
    static {
        lyrics = new HashMap<>();
        lyrics.put(1, "On the first day of Christmas my true love gave to me: a Partridge in a Pear Tree.");
        lyrics.put(2, "On the second day of Christmas my true love gave to me: two Turtle Doves, and a Partridge in a Pear Tree.");
        lyrics.put(3, "On the third day of Christmas my true love gave to me: three French Hens, two Turtle Doves, and a Partridge in a Pear Tree.");
        lyrics.put(4, "On the fourth day of Christmas my true love gave to me: four Calling Birds, three French Hens, two Turtle Doves, and a Partridge in a Pear Tree.");
        lyrics.put(5, "On the fifth day of Christmas my true love gave to me: five Gold Rings, four Calling Birds, three French Hens, two Turtle Doves, and a Partridge in a Pear Tree.");
        lyrics.put(6, "On the sixth day of Christmas my true love gave to me: six Geese-a-Laying, five Gold Rings, four Calling Birds, three French Hens, two Turtle Doves, and a Partridge in a Pear Tree.");
        lyrics.put(7, "On the seventh day of Christmas my true love gave to me: seven Swans-a-Swimming, six Geese-a-Laying, five Gold Rings, four Calling Birds, three French Hens, two Turtle Doves, and a Partridge in a Pear Tree.");
        lyrics.put(8, "On the eighth day of Christmas my true love gave to me: eight Maids-a-Milking, seven Swans-a-Swimming, six Geese-a-Laying, five Gold Rings, four Calling Birds, three French Hens, two Turtle Doves, and a Partridge in a Pear Tree.");
        lyrics.put(9, "On the ninth day of Christmas my true love gave to me: nine Ladies Dancing, eight Maids-a-Milking, seven Swans-a-Swimming, six Geese-a-Laying, five Gold Rings, four Calling Birds, three French Hens, two Turtle Doves, and a Partridge in a Pear Tree.");
        lyrics.put(10, "On the tenth day of Christmas my true love gave to me: ten Lords-a-Leaping, nine Ladies Dancing, eight Maids-a-Milking, seven Swans-a-Swimming, six Geese-a-Laying, five Gold Rings, four Calling Birds, three French Hens, two Turtle Doves, and a Partridge in a Pear Tree.");
        lyrics.put(11, "On the eleventh day of Christmas my true love gave to me: eleven Pipers Piping, ten Lords-a-Leaping, nine Ladies Dancing, eight Maids-a-Milking, seven Swans-a-Swimming, six Geese-a-Laying, five Gold Rings, four Calling Birds, three French Hens, two Turtle Doves, and a Partridge in a Pear Tree.");
        lyrics.put(12, "On the twelfth day of Christmas my true love gave to me: twelve Drummers Drumming, eleven Pipers Piping, ten Lords-a-Leaping, nine Ladies Dancing, eight Maids-a-Milking, seven Swans-a-Swimming, six Geese-a-Laying, five Gold Rings, four Calling Birds, three French Hens, two Turtle Doves, and a Partridge in a Pear Tree.");
    }

    private static final int MIN = 1;
    private static final int MAX = 12;
    
    String verse(int verseNumber) {
        return verses(verseNumber, verseNumber);
    }
    
    String sing() {
        return verses(MIN, MAX);
    }

    String verses(int startVerse, int endVerse) throws IllegalArgumentException {
        if (startVerse < MIN || endVerse > MAX || startVerse > endVerse) {
            throw new IllegalArgumentException(String.format(
                    "startVerse must be at least %d and endVerse must be at most %d and startVerse must be less than or equal to endVerse",
                    MIN, MAX));
        }
        StringBuilder sb = new StringBuilder();
        for (int i = startVerse; i <= endVerse; i++) {
            sb.append(lyrics.get(i) + "\n" + (i != endVerse ? "\n" : ""));
        }
        return sb.toString();
    }

}
