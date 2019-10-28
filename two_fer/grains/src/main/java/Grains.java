import java.math.BigInteger;

class Grains {

    private static final int CHESS_MAX_SQUARE = 64;

    private static String wrongSquareMessage = "square must be between 1 and 64";

    BigInteger computeNumberOfGrainsOnSquare(final int square) {
        if (square < 1 || square > CHESS_MAX_SQUARE) {
            throw new IllegalArgumentException(wrongSquareMessage);
        }
        return new BigInteger("2").pow(square - 1);
    }

    BigInteger computeTotalNumberOfGrainsOnBoard() {
        return sumGrainsUntilSquare(CHESS_MAX_SQUARE);
    }

    private BigInteger sumGrainsUntilSquare(int currentSquare) {
        if (currentSquare <= 1) {
            return BigInteger.ONE;
        }
        return computeNumberOfGrainsOnSquare(currentSquare).add(sumGrainsUntilSquare(currentSquare - 1));
    }

}
