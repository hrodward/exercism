class PhoneNumber {

  private static String wrongLengthExceptionMessage = "incorrect number of digits";
  private static String moreThan11DigitsExceptionMessage = "more than 11 digits";
  private static String numberIs11DigitsButDoesNotStartWith1ExceptionMessage = "11 digits must start with 1";
  private static String illegalCharacterExceptionMessage = "letters not permitted";
  private static String illegalPunctuationExceptionMessage = "punctuations not permitted";
  private static String areaCodeStartsWithZeroExceptionMessage = "area code cannot start with zero";
  private static String areaCodeStartsWithOneExceptionMessage = "area code cannot start with one";
  private static String exchangeCodeStartsWithZeroExceptionMessage = "exchange code cannot start with zero";
  private static String exchangeCodeStartsWithOneExceptionMessage = "exchange code cannot start with one";

  private String phoneNumber;

  public PhoneNumber(String input) {

    if (input == null) {
      throw new IllegalArgumentException("Empty input not allowed");
    }

    this.phoneNumber = 
      input.replaceAll(" ", "")
        .replaceAll("\\+", "")
        .replaceAll("\\(", "")
        .replaceAll("\\)", "")
        .replaceAll("-", "")
        .replaceAll("\\.", "");

    checkInput();
  }

  String getNumber() {
    return phoneNumber.length() == 11 ? phoneNumber.substring(1) : phoneNumber;
  }

  private void checkInput() {
    if (phoneNumber.length() < 10) {
      throw new IllegalArgumentException(wrongLengthExceptionMessage);
    }
    if (phoneNumber.length() == 11 && Character.getNumericValue(phoneNumber.charAt(0)) != 1) {
      throw new IllegalArgumentException(numberIs11DigitsButDoesNotStartWith1ExceptionMessage);
    }
    if (phoneNumber.length() > 11) {
      throw new IllegalArgumentException(moreThan11DigitsExceptionMessage);
    }
    if (phoneNumber.matches(".*[A-Za-z]+.*")) {
      throw new IllegalArgumentException(illegalCharacterExceptionMessage);
    }
    if (!phoneNumber.matches("[0-9]+")) {
      throw new IllegalArgumentException(illegalPunctuationExceptionMessage);
    }
    if (getAreaCode(phoneNumber) == 0) {
      throw new IllegalArgumentException(areaCodeStartsWithZeroExceptionMessage);
    }
    if (getAreaCode(phoneNumber) == 1) {
      throw new IllegalArgumentException(areaCodeStartsWithOneExceptionMessage);
    }
    if (getExchangeCode(phoneNumber) == 0) {
      throw new IllegalArgumentException(exchangeCodeStartsWithZeroExceptionMessage);
    }
    if (getExchangeCode(phoneNumber) == 1) {
      throw new IllegalArgumentException(exchangeCodeStartsWithOneExceptionMessage);
    }
  }

  private int getAreaCode(String phoneNumber) {
    return Character.getNumericValue(phoneNumber.charAt(phoneNumber.length() == 10 ? 0 : 1));
  }

  private int getExchangeCode(String phoneNumber) {
    return Character.getNumericValue(phoneNumber.charAt(phoneNumber.length() == 10 ? 3 : 4));
  }

}
