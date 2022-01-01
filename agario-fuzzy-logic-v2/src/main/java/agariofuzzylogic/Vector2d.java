package agariofuzzylogic;

public class Vector2d {
    double x;
    double y;

    Vector2d(double angle) {
        x = Math.cos(angle);
        y = Math.sin(angle);
    }

    Vector2d(double x1, double y1, double x2, double y2) {
        x = x2-x1;
        y = y2-y1;
    }

    double cross(Vector2d o) {
        return x*o.y - y*o.x;
    }

    double length() {
        return Math.sqrt(x*x + y*y);
    }
}
