class Hamming {

    private String leftStrand;
    private String rightStrand;

    Hamming(String leftStrand, String rightStrand) {
        if (leftStrand != null && leftStrand.length() > 0 || rightStrand != null && rightStrand.length() > 0) {
            if (leftStrand == null || leftStrand.length() == 0) {
                throw new IllegalArgumentException("left strand must not be empty.");
            }
            if (rightStrand == null || rightStrand.length() == 0) {
                throw new IllegalArgumentException("right strand must not be empty.");
            }
            if (leftStrand.length() != rightStrand.length()) {
                throw new IllegalArgumentException("leftStrand and rightStrand must be of equal length.");
            }
        }

        this.leftStrand = leftStrand;
        this.rightStrand = rightStrand;
    }

    int getHammingDistance() {
        if (leftStrand.length() == 0 && rightStrand.length() == 0) {
            return 0;
        }
        int distance = 0;
        for (int idx = 0; idx < leftStrand.length(); idx++) {
            char rc = rightStrand.charAt(idx);
            char lc = leftStrand.charAt(idx);
            if (rc != lc) {
                distance++;
            }
        }
        return distance;
    }

}
