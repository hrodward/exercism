class IsbnVerifier {

    boolean isValid(String input) {
        String isbn = input.replaceAll("-", "");
        if (isbn.length() != 10) {
            return false;
        }
        int sum = 0;
        for (int idx = 0, multiplier = 10; idx < isbn.length(); idx++) {
            String letter = String.valueOf(isbn.charAt(idx));
            if (letter.equals("-")) {
                continue;
            } else if (letter.equalsIgnoreCase("X") && idx != isbn.length() - 1) {
                return false;
            } else if (letter.matches("[0-9Xx]")) {
                int number = letter.equalsIgnoreCase("X") ? 10 : Integer.parseInt(letter);
                sum += number * multiplier--;
            }
        }
        return sum % 11 == 0;
    }

}
