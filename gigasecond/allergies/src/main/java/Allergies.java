import java.util.ArrayList;
import java.util.List;

class Allergies {

  private static final Allergen[] ALLERGEN_VALUES = Allergen.values();
  private static final int MAX_ALLERGEN_SCORE = (int) Math.pow(2, ALLERGEN_VALUES.length);

  private List<Allergen> allergens = new ArrayList<>();
  private int score;
  private int maxExp;

  Allergies(int input) {
    score = input;
    maxExp = Math.getExponent(score);
    if (score > MAX_ALLERGEN_SCORE) {
      // Input score too high for Allergen enum values
      score -= Math.pow(2, maxExp);
      maxExp = Math.getExponent(score);
    }
  }

  boolean isAllergicTo(Allergen allergen) {
    return getList().contains(allergen);
  }

  List<Allergen> getList() {
    return gatherAllergies(score, maxExp);
  }

  private List<Allergen> gatherAllergies(int scoreLeft, int exp) {
    if (scoreLeft <= 0) {
      return allergens;
    }
    Allergen allergen = ALLERGEN_VALUES[exp];
    int allergenScore = allergen.getScore();
    if (scoreLeft >= allergenScore) {
      allergens.add(0, allergen);
      scoreLeft -= allergenScore;
    }
    return gatherAllergies(scoreLeft, --exp);
  }

}