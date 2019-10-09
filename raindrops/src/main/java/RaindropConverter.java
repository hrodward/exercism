import java.util.ArrayList;
import java.util.List;

class RaindropConverter {

    String convert(int number) {

        String raindropSpeak = "";

        List<Integer> factors = new ArrayList<>();

        factors = getFactors(number, 7);

        System.out.println(factors);

        return "toto";

    }

    public static void main(String[] args) {
        RaindropConverter rc = new RaindropConverter();
        rc.convert(34);
    }

    private List<Integer> getFactors(int number, int maxFactor) {
        List<Integer> factors = new ArrayList<>();
        factors.add(1);
        for (int i = 2; i <= Math.min(number, maxFactor + 1); i++) {
            if (number % i == 0) {
                factors.add(i);
            }
        }
        return factors;
    }

}
