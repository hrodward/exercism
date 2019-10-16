class Bob {

  public String hey(String input) {

    String phrase = removeUnnecessaryCharacters(input);

    if (phrase.isEmpty()) {
      return "Fine. Be that way!";
    }

    int letterCounter = 0;
    int upperCaseCounter = 0;

    for (String letter : phrase.split("")) {
      if (letter.matches("[A-Za-z]")) {
        letterCounter++;
        if (letter.matches("[A-Z]")) {
          upperCaseCounter++;
        }
      }
    }

    boolean isQuestion = phrase.charAt(phrase.length() - 1) == '?';
    boolean isAggressive = upperCaseCounter > 1 && upperCaseCounter == letterCounter;

    if (isQuestion) {
      if (isAggressive) {
        return "Calm down, I know what I'm doing!";
      } else {
        return "Sure.";
      }
    } else if (isAggressive) {
      return "Whoa, chill out!";
    }

    return "Whatever.";
  }

  private String removeUnnecessaryCharacters(String input) {
    return input.replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').replace("  ", " ").trim();
  }

}
