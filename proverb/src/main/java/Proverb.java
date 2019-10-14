class Proverb {

    private static final String GENERAL = "For want of a %s the %s was lost.\n";
    private static final String END = "And all for the want of a %s.";

    private final String[] words;

    Proverb(String[] words) {
        this.words = words;
    }

    String recite() {
        if (words == null || words.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(String.format(END, words[0]));
        for (int i = words.length - 1; i > 0; i--) {
            sb.insert(0, String.format(GENERAL, words[i - 1], words[i]));
        }
        return sb.toString();
    }

}
