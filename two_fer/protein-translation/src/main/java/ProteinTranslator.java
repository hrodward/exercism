import java.util.ArrayList;
import java.util.List;

class ProteinTranslator {

    List<String> translate(String rnaSequence) {
        List<String> protein = new ArrayList<>();
        
        // Elegant solution:
        // Matcher match = Pattern.compile("\\w\\w\\w").matcher(rnaSequence);
        // while(match.find()) {
        //     match.group();
        // }

        outer:
        for (int i = 0; i < rnaSequence.length()-2; i += 3) {
            String codon = rnaSequence.substring(i, i + 3);
            switch (codon) {
            case "UAA":
            case "UAG":
            case "UGA":
                break outer; // STOP
            case "AUG":
                protein.add("Methionine");
                break;
            case "UUU":
            case "UUC":
                protein.add("Phenylalanine");
                break;
            case "UUA":
            case "UUG":
                protein.add("Leucine");
                break;
            case "UCU":
            case "UCC":
            case "UCA":
            case "UCG":
                protein.add("Serine");
                break;
            case "UAU":
            case "UAC":
                protein.add("Tyrosine");
                break;
            case "UGU":
            case "UGC":
                protein.add("Cysteine");
                break;
            case "UGG":
                protein.add("Tryptophan");
                break;
            default:
                break;
            }
        }
        return protein;
    }
}