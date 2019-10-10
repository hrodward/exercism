
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class RaindropConverter {

    private static final int MAX_FACTOR = 7;

    String convert(int number) {
        StringBuilder raindropSpeak = new StringBuilder();
        List<Integer> factors = getFactors(number, MAX_FACTOR); // second param gives versatility
        for (Integer factor : factors) {
            String str = "";
            switch (factor) {
            case 3:
                str = "Pling";
                break;
            case 5:
                str = "Plang";
                break;
            case 7:
                str = "Plong";
                break;
            default:
                break;
            }
            raindropSpeak.append(str);
        }
        if (raindropSpeak.length() == 0) {
            raindropSpeak.append(number);
        }
        return raindropSpeak.toString();
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

    ////////////////////////////////
    // Most starred solution test //
    ////////////////////////////////
    public static void main(String[] args) {
        int number = 345;
        RainDrop[] testArray = RainDrop.values();
        System.out.println(
            Arrays.stream(testArray) // Stream<RainDrop>
            .filter(x -> x.isFactorOf(number)) // Stream<RainDrop>
            .map(x -> x.name()) // Stream<String>
            .reduce(String::concat) // Optional<String>
            .orElse(String.valueOf(number)) // String
        );
    }

    private enum RainDrop {
        Pling(3), Plang(5), Plong(7);        
        private Integer idx = null;
        RainDrop(int idx) {
            this.idx = idx;
        }
        private boolean isFactorOf(int number) {
            return number % idx == 0;
        }
    }

}
