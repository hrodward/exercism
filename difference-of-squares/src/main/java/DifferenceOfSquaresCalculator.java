class DifferenceOfSquaresCalculator {

    int computeSquareOfSumTo(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Only natural integers accepted");
        }
        int sumN = n * (n + 1) / 2;
        return (int) Math.pow(sumN, 2);
    }

    int computeSumOfSquaresTo(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Only natural integers accepted");
        }
        return n * (n + 1) * (2 * n + 1) / 6;
    }

    int computeDifferenceOfSquares(int n) {
        return computeSquareOfSumTo(n) - computeSumOfSquaresTo(n);
    }

}
