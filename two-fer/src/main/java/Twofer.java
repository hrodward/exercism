class Twofer {
    String twofer(String name) {
        String prefix = "One for ";
        String postfix = ", one for me.";
        StringBuilder phrase = new StringBuilder(prefix);
        String usedName = name == null ? "you" : name;
        return phrase.append(usedName).append(postfix).toString();
    }
}
