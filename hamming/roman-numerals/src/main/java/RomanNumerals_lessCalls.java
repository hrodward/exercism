class RomanNumeral_lessCalls {

  private int inputNumber;
  private int recursiveCounter;

  RomanNumeral_lessCalls(int input) {
    inputNumber = input;
  }

  public static void main(String[] args) {
    RomanNumeral_lessCalls romanNumeral = new RomanNumeral_lessCalls(1888);
    System.out.println(romanNumeral.getRomanNumeral());
    System.out.println("Total calls = " + romanNumeral.recursiveCounter);
  }

  String getRomanNumeral() {
    return getRomanNumeral_M(inputNumber, new StringBuilder());
  }

  private String getRomanNumeral_M(int currentNumber, StringBuilder romanNumeral) {
    // end state
    if (currentNumber == 0) {
      return romanNumeral.toString();
    }
    // recursive states
    if (currentNumber >= 1000) {
      recursiveCounter++;
      return getRomanNumeral_M(currentNumber - 1000, romanNumeral.append("M"));
    } else if (currentNumber >= 900) {
      return getRomanNumeral_C(currentNumber - 900, romanNumeral.append("CM"));
    }
    return getRomanNumeral_D(currentNumber, romanNumeral);
  }

  private String getRomanNumeral_D(int currentNumber, StringBuilder romanNumeral) {
    // end state
    if (currentNumber == 0) {
      return romanNumeral.toString();
    }
    // recursive states
    if (currentNumber >= 500) {
      return getRomanNumeral_C(currentNumber - 500, romanNumeral.append("D"));
    } else if (currentNumber >= 400) {
      return getRomanNumeral_C(currentNumber - 400, romanNumeral.append("CD"));
    }
    return getRomanNumeral_C(currentNumber, romanNumeral);
  }

  private String getRomanNumeral_C(int currentNumber, StringBuilder romanNumeral) {
    // end state
    if (currentNumber == 0) {
      return romanNumeral.toString();
    }
    // recursive states
    if (currentNumber >= 100) {
      recursiveCounter++;
      return getRomanNumeral_C(currentNumber - 100, romanNumeral.append("C"));
    } else if (currentNumber >= 90) {
      return getRomanNumeral_X(currentNumber - 90, romanNumeral.append("XC"));
    }
    return getRomanNumeral_L(currentNumber, romanNumeral);
  }

  private String getRomanNumeral_L(int currentNumber, StringBuilder romanNumeral) {
    // end state
    if (currentNumber == 0) {
      return romanNumeral.toString();
    }
    // recursive states
    if (currentNumber >= 50) {
      return getRomanNumeral_X(currentNumber - 50, romanNumeral.append("L"));
    } else if (currentNumber >= 40) {
      return getRomanNumeral_X(currentNumber - 40, romanNumeral.append("XL"));
    }
    return getRomanNumeral_X(currentNumber, romanNumeral);
  }

  private String getRomanNumeral_X(int currentNumber, StringBuilder romanNumeral) {
    // end state
    if (currentNumber == 0) {
      return romanNumeral.toString();
    }
    // recursive states
    if (currentNumber >= 10) {
      recursiveCounter++;
      return getRomanNumeral_X(currentNumber - 10, romanNumeral.append("X"));
    } else if (currentNumber == 9) {
      romanNumeral.append("IX");
      return romanNumeral.toString();
    }
    return getRomanNumeral_V(currentNumber, romanNumeral);
  }

  private String getRomanNumeral_V(int currentNumber, StringBuilder romanNumeral) {
    // end state
    if (currentNumber == 0) {
      return romanNumeral.toString();
    }
    // recursive states
    if (currentNumber >= 5) {
      return getRomanNumeral_I(currentNumber - 5, romanNumeral.append("V"));
    } else if (currentNumber == 4) {
      romanNumeral.append("IV");
      return romanNumeral.toString();
    }
    return getRomanNumeral_I(currentNumber, romanNumeral);
  }

  private String getRomanNumeral_I(int currentNumber, StringBuilder romanNumeral) {
    if (currentNumber >= 1) {
      recursiveCounter++;
      return getRomanNumeral_I(currentNumber - 1, romanNumeral.append("I"));
    } else {
      return romanNumeral.toString();
    }
  }

}