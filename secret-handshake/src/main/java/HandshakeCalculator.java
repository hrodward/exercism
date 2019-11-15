import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class HandshakeCalculator {

    private static final int REVERSE = 0b10000;
    private static final int MAX_CODE = 0b11111;

    List<Signal> calculateHandshake(int number) {
	if (number < 0) {
	    throw new IllegalArgumentException("Only positive integers are accepted");
	}
	if (number > MAX_CODE) {
	    number &= MAX_CODE;
	}
	final List<Signal> handshake = new ArrayList<>();
	for (Signal signal : Signal.values()) {
	    int code = (int) Math.pow(2, signal.ordinal());
	    if ((number & code) == code) {
		handshake.add(signal);
	    }
	}
	if (number >= REVERSE) {
	    Collections.reverse(handshake);
	}
	return handshake;
    }

}
