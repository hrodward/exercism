class RomanNumeral {

  private int inputNumber;

  RomanNumeral(int input) {
    inputNumber = input;
  }

  String getRomanNumeral() {
    return getRomanNumeral(inputNumber, new StringBuilder());
  }

  private String getRomanNumeral(int currentNumber, StringBuilder romanNumeral) {
    // end state
    if (currentNumber == 0) {
      return romanNumeral.toString();
    }

    // recursive states
    if (currentNumber >= 1000) {
      return getRomanNumeral(currentNumber - 1000, romanNumeral.append("M"));
    } else if (currentNumber >= 900) {
      return getRomanNumeral(currentNumber - 900, romanNumeral.append("CM"));
    } else if (currentNumber >= 500) {
      return getRomanNumeral(currentNumber - 500, romanNumeral.append("D"));
    } else if (currentNumber >= 400) {
      return getRomanNumeral(currentNumber - 400, romanNumeral.append("CD"));
    } else if (currentNumber >= 100) {
      return getRomanNumeral(currentNumber - 100, romanNumeral.append("C"));
    } else if (currentNumber >= 90) {
      return getRomanNumeral(currentNumber - 90, romanNumeral.append("XC"));
    } else if (currentNumber >= 50) {
      return getRomanNumeral(currentNumber - 50, romanNumeral.append("L"));
    } else if (currentNumber >= 40) {
      return getRomanNumeral(currentNumber - 40, romanNumeral.append("XL"));
    } else if (currentNumber >= 10) {
      return getRomanNumeral(currentNumber - 10, romanNumeral.append("X"));
    } else if (currentNumber >= 9) {
      return getRomanNumeral(currentNumber - 9, romanNumeral.append("IX"));
    } else if (currentNumber >= 5) {
      return getRomanNumeral(currentNumber - 5, romanNumeral.append("V"));
    } else if (currentNumber >= 4) {
      return getRomanNumeral(currentNumber - 4, romanNumeral.append("IV"));
    } else if (currentNumber >= 1) {
      return getRomanNumeral(currentNumber - 1, romanNumeral.append("I"));
    }

    throw new IllegalStateException("We should never get here");
  }

}