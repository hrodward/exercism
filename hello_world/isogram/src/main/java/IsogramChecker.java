import java.util.Arrays;

class IsogramChecker {

    boolean isIsogram(String phrase) {
        String formattedPhrase = phrase.toLowerCase().replaceAll(" ", "").replaceAll("-", "");
        String filteredPhrase = 
            Arrays.stream(formattedPhrase.split(""))
            .distinct()
            .reduce(String::concat)
            .orElse("");
        return formattedPhrase.equals(filteredPhrase);
    }

}
