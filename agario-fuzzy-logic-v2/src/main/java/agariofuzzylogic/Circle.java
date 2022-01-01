package agariofuzzylogic;

import static agariofuzzylogic.AnimatedCircleOnCanvas.H;
import static agariofuzzylogic.AnimatedCircleOnCanvas.W;

public abstract class Circle {
    double x;
    double y;
    double radius;

    Circle(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getRadius() {
        return radius;
    }

    protected boolean checkBounds(double x, double y) {
        return !(x < 0 || x > W - getRadius()) && !(y < 0 || y > H - getRadius());
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getDrawingX() {
        return x - radius;
    }

    public double getDrawingY() {
        return y - radius;
    }
}
