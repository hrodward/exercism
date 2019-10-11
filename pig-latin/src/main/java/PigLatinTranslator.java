class PigLatinTranslator {

  private static final String AY = "ay";

  static String translate(String phrase) {
    String pigLatinTranslation = phrase;
    for (String word : phrase.split(" ")) {
      pigLatinTranslation = processWord(pigLatinTranslation, word);
    }
    return pigLatinTranslation;
  }

  private static String processWord(String pigLatinTranslation, String word) {
    boolean isFirst = true;
    String cluster = "";
    char[] charArray = word.toCharArray();
    for (int idx = 0; idx < charArray.length; idx++) {
      char letter = charArray[idx];
      
      // first letter
      if (isFirst) {
        if (isVowel(letter)) {
          pigLatinTranslation = pigLatinTranslation.replace(word, word.concat(AY));
          break;
        }
        isFirst = false;
        cluster += letter;
        continue;
      }

      // rest of letters
      if (isVowel(letter)) {

        // if cluster like "*q" and vowel = "u" --> square
        if (letter == 'u' && ("q".equalsIgnoreCase(cluster.substring(0))
            || cluster.length() == 2 && "q".equalsIgnoreCase(cluster.substring(1)))) {
          cluster += letter;
          idx++;
        }
        pigLatinTranslation = pigLatinTranslation.replace(word, word.substring(idx).concat(cluster).concat(AY));
        break;

      } else {

        if (letter == 'y') {
          pigLatinTranslation = pigLatinTranslation.replace(word, word.substring(idx).concat(cluster).concat(AY));
          break;
        }
        cluster += letter;
        if ("xr".equalsIgnoreCase(cluster) || "yt".equalsIgnoreCase(cluster)) {
          pigLatinTranslation = pigLatinTranslation.replace(word, word.concat(AY));
          break;
        }
        
      }

    }
    return pigLatinTranslation;
  }

  private static boolean isVowel(char c) {
    return "aeiou".contains(String.valueOf(c));
  }

  public static void main(String[] args) {
    System.out.println("");
    // System.out.println("apple=" + translate("apple"));
    // System.out.println("xray=" + translate("xray"));
    // System.out.println("yttria=" + translate("yttria"));
    // System.out.println("square=" + translate("square"));
    // System.out.println("chair=" + translate("chair"));
    // System.out.println("rhythm=" + translate("rhythm"));
    // System.out.println("my=" + translate("my"));
    System.out.println("quick fast run=" + translate("quick fast run")); // "ickquay astfay unray"
  }

}