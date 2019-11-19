import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class BowlingGame {

	private static final int MAX_FRAMES_COUNT = 10;
	private static final int MAX_PINS_COUNT = 10;

	private boolean isGameOver = false;
	private boolean isStrike = false;
	private boolean isSpare = false;
	private boolean isFillBall = false;
	private int framesCount = 1;
	private int pinsSum = 0;
	private int score = 0;
	private int throwsThisFrame = 1;
	private List<Integer> strikes = new ArrayList<>();
	private RuntimeException ex;

	public void roll(final int pins) {
		if (pins < 0) {
			ex = new IllegalStateException("Negative roll is invalid");
		}
		if (isGameOver) {
			ex = new IllegalStateException("Cannot roll after game is over");
		}
		pinsSum += pins;
		if (pins > MAX_PINS_COUNT || pinsSum > MAX_PINS_COUNT) {
			ex = new IllegalStateException("Pin count exceeds pins on the lane");
		}
		if (ex != null) {
			return;
		}
		updateScore(pins);
		if (isFillBall) {
			processFillBall(pins);
			return;
		}
		isStrike = throwsThisFrame == 1 && pinsSum == MAX_PINS_COUNT;
		if (isStrike) {
			processStrike();
			return;
		}
		processSpareThrow(pins);
		prepareNextRoll();
	}

	private void processFillBall(final int pins) {
		if (isStrike) {
			isStrike = false;
			if (pinsSum == MAX_PINS_COUNT) {
				pinsSum = 0;
			}
		} else {
			isFillBall = false;
			isGameOver = true;
		}
	}

	private void updateScore(final int pins) {
		score += pins + pins * strikes.size();
		strikes = strikes.stream().map(i -> i - 1).filter(i -> i > 0).collect(Collectors.toList());
	}

	private void processStrike() {
		if (framesCount < MAX_FRAMES_COUNT) {
			strikes.add(2);
		}
		isFillBall = framesCount == MAX_FRAMES_COUNT;
		pinsSum = 0;
		throwsThisFrame = 1;
		framesCount++;
	}

	private void processSpareThrow(final int pins) {
		if (isSpare) {
			score += pins;
			isSpare = false;
		}
		isSpare = throwsThisFrame == 2 && pinsSum == MAX_PINS_COUNT;
	}

	private void prepareNextRoll() {
		isFillBall = framesCount == MAX_FRAMES_COUNT && (isSpare || isStrike);
		isGameOver = framesCount == MAX_FRAMES_COUNT && throwsThisFrame == 2 && !isFillBall;
		if (throwsThisFrame == 2) {
			pinsSum = 0;
		}
		framesCount += throwsThisFrame == 2 ? 1 : 0;
		throwsThisFrame = throwsThisFrame == 1 ? 2 : 1;
	}

	public int score() {
		if (ex != null) {
			throw ex;
		}
		if (framesCount < MAX_FRAMES_COUNT || isFillBall) {
			throw new IllegalStateException("Score cannot be taken until the end of the game");
		}
		return score;
	}

}