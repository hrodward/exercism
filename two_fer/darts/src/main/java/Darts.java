import javafx.geometry.Point2D;

class Darts {

    private Point2D point;

    Darts(double x, double y) {
        point = new Point2D(x, y);
    }

    int score() {
        int score = 0;
        double distance = point.distance(0, 0);

        if (distance <= Target.INNER.radius) {
            score = Target.INNER.score;
        } else if (distance <= Target.MIDDLE.radius) {
            score = Target.MIDDLE.score;
        } else if (distance <= Target.OUTER.radius) {
            score = Target.OUTER.score;
        }

        return score;
    }

    private enum Target {
        
        OUTER(10, 1), MIDDLE(5, 5), INNER(1, 10);

        double radius;
        int score;

        Target(double radius, int score) {
            this.radius = radius;
            this.score = score;
        }

    }

}
