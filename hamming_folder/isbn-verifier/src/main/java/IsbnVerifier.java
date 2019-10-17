class IsbnVerifier {

    boolean isValid(String input) {
        String isbn = input.replaceAll("-", "");
        if (isbn.length() != 10) {
            return false;
        }
        int sum = 0;
        for (int idx = 0, multiplier = 10; idx < isbn.length(); idx++) {
            String letter = String.valueOf(isbn.charAt(idx));
            if (letter.equalsIgnoreCase("X") && idx != isbn.length() - 1) {
                return false;
            } else if (letter.matches("[0-9Xx]")) {
                int number = letter.equalsIgnoreCase("X") ? 10 : Integer.parseInt(letter);
                sum += number * multiplier--;
            }
        }
        return sum % 11 == 0;
    }

    public static void main(String[] args) {
        System.out.println(new IsbnVerifier().isValid("3-59-821507-X"));
    }

    private class StreamVars {
        int multiplier = 10;
        int length = 0;
    }

    // Case where X is in the middle of the input still fails
    boolean isValidStream(String input) {
        final StreamVars sv = new StreamVars();
        int sum = input.chars().mapToObj(c -> String.valueOf((char) c))
                .filter(x -> !(x.equals("-")) && x.matches("[0-9Xx]"))
                .mapToInt(s -> Integer.parseInt(s.equalsIgnoreCase("X") ? "10" : s)).reduce(0, (a, b) -> {
                    sv.length++;
                    return a + b * sv.multiplier--;
                });

        return sv.length == 10 && sum % 11 == 0;
    }

}
