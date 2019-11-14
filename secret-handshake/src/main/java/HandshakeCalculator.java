import java.util.ArrayList;
import java.util.List;

class HandshakeCalculator {

    private static final int REVERSE = 0b10000;
    private static final int MAX_CODE = 0b11111;

    private static final Signal[] SIGNALS = Signal.values();

    List<Signal> calculateHandshake(int number) {
	if (number < 0) {
	    throw new IllegalArgumentException("Only positive integers are accepted");
	}
	if (number > MAX_CODE) {
	    number &= MAX_CODE;
	}
	final boolean isReverseOrder = number >= REVERSE;
	if (isReverseOrder) {
	    number -= REVERSE;
	}
	final List<Signal> handshake = new ArrayList<>();
	for (int idx = SIGNALS.length - 1; idx >= 0; idx--) {
	    int code = (int) Math.pow(2, idx);
	    if ((number & code) == code) {
		Signal signal = SIGNALS[idx];
		if (isReverseOrder) {
		    handshake.add(signal);
		} else {
		    handshake.add(0, signal);
		}
	    }
	}
	return handshake;
    }

}
