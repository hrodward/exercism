class LuhnValidator {


    public static void main(String[] args) {
        System.out.println(new LuhnValidator().isValid("4539 1488 0343 6467"));
    }

    boolean isValid(String input) {
        String candidate = input.replaceAll(" ", "");

        if (candidate.length() <= 1 || !candidate.matches("[0-9]+")) {
            return false;
        }

        int sum = 0;
        for (int i = candidate.length() - 1; i >= 0; i -= 2) {
            sum += getIntAt(candidate, i);
            if (i > 0) {
                sum += doubleSecondNumber(candidate, i - 1);
            }
        }
        
        return sum % 10 == 0;
    }

    private int doubleSecondNumber(String candidate, int index) {
        int num = getIntAt(candidate, index) * 2;
        if (num > 9) {
            num -= 9;
        }
        return num;
    }

    private int getIntAt(String candidate, int index) {
        return Integer.parseInt(Character.toString(candidate.charAt(index)));
    }

}
