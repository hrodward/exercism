class LargestSeriesProductCalculator {

    private String input = null;

    LargestSeriesProductCalculator(String inputNumber) {
        if (inputNumber != "" && !inputNumber.matches("[0-9]+")) {
            throw new IllegalArgumentException("String to search may only contain digits.");
        }
        input = inputNumber.replace(" ", "");
    }

    long calculateLargestProductForSeriesLength(int numberOfDigits) {
        checkInputs(numberOfDigits);
        if (input.equals("")) {
            return 1;
        }

        long maxProduct = Long.MIN_VALUE;

        for (int start = 0, end = numberOfDigits; end <= input.length(); start++, end++) {
            String substring = input.substring(start, end);
            long product = multiply(substring);
            if (product > maxProduct) {
                maxProduct = product;
            }
        }
        return maxProduct;
    }

    private long multiply(String substring) {
        
        long result = 1;
        for (char c : substring.toCharArray()) {
            int num = Integer.parseInt(Character.toString(c));
            result *= num;
        }
        return result;
    }

    private void checkInputs(int numberOfDigits) {
        if (input.equals("") && numberOfDigits != 0) {
            throw new IllegalArgumentException(
                    "Series length must be less than or equal to the length of the string to search.");
        }
        if (numberOfDigits > input.length()) {
            throw new IllegalArgumentException(
                    "Series length must be less than or equal to the length of the string to search.");
        }
        if (numberOfDigits < 0) {
            throw new IllegalArgumentException("Series length must be non-negative.");
        }
    }
}
