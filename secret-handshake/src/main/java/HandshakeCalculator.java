import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

class HandshakeCalculator {

    private static final int REVERSE  = 0b10000;
    private static final int MAX_CODE = 0b11111;
    private static final Signal[] SIGNALS = Signal.values();

    List<Signal> calculateHandshake(int number) {
        if (number < 0 || number > MAX_CODE) {
            throw new IllegalArgumentException("Only integers from 1 to 31 are accepted");
        }
        boolean isReverseOrder = number >= REVERSE;
        if (isReverseOrder) {
            number -= REVERSE;
        }
        String binaryRepresentation = Integer.toBinaryString(number);
        AtomicInteger index = new AtomicInteger(0);
        List<Signal> handshake = new ArrayList<>();
        new StringBuilder(binaryRepresentation)
            .reverse()
            .chars()
            .forEach(codePoint -> {
                int currentIndex = index.getAndIncrement();
                if((char) codePoint == '1') {
                    Signal signal = SIGNALS[currentIndex];
                    if (isReverseOrder) {
                        handshake.add(0, signal);
                    } else {
                        handshake.add(signal);
                    }
                }
            });
        return handshake;
    }

}
