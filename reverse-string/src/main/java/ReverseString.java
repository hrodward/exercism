import java.util.stream.Stream;

class ReverseString {
    String reverse(String inputString) {
        int inLength = inputString.length();
        char[] reversed = new char[inLength];
        for (int i = 0; i < inLength; i++) {
            reversed[inLength - 1 - i] = inputString.charAt(i);
        }
        return String.valueOf(reversed);
    }
}
