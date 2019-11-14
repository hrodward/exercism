import javafx.geometry.Point2D;

class Queen {
  private final Point2D position;

  Queen(double x, double y) {
    checkInputCoords(x, y);
    position = new Point2D(x, y);
  }

  public Point2D getPosition() {
    return position;
  }

  private void checkInputCoords(double x, double y) {
    if (x < 0) {
      throw new IllegalArgumentException("Queen position must have positive row.");
    }
    if (x > 7) {
      throw new IllegalArgumentException("Queen position must have row <= 7.");
    }
    if (y < 0) {
      throw new IllegalArgumentException("Queen position must have positive column.");
    }
    if (y > 7) {
      throw new IllegalArgumentException("Queen position must have column <= 7.");
    }
  }
}

class QueenAttackCalculator {

  private final double q1x;
  private final double q2x;
  private final double q1y;
  private final double q2y;

  QueenAttackCalculator(Queen q1, Queen q2) {
    if (q1 == null || q2 == null) {
      throw new IllegalArgumentException("You must supply valid positions for both Queens.");
    }
    
    q1x = q1.getPosition().getX();
    q2x = q2.getPosition().getX();
    q1y = q1.getPosition().getY();
    q2y = q2.getPosition().getY();

    if (q1x == q2x && q1y == q2y) {
      throw new IllegalArgumentException("Queens cannot occupy the same position.");
    }
  }

  boolean canQueensAttackOneAnother() {
    return q1x == q2x || q1y == q2y || Math.abs(q1x - q2x) == Math.abs(q1y - q2y);
  }
}
