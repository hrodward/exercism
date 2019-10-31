import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class RnaTranscription {

    private static final Map<String, String> dictionary = new HashMap<>();
    static {
        dictionary.put("G", "C");
        dictionary.put("C", "G");
        dictionary.put("T", "A");
        dictionary.put("A", "U");
    }

    String transcribe(String dnaStrand) {
        if (dnaStrand == null || dnaStrand.length() == 0) {
            return "";
        }
        return Stream
                .of(dnaStrand.split(""))
                .parallel()
                .map(c -> dictionary.get(c))
                .collect(Collectors.joining());
    }

}
