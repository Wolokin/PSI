package agariofuzzylogic;

import java.util.Random;

public class RandomPlayer extends Circle {
    double x;
    double y;
    double vx;
    double vy;
    double speed = 0.1;
    int counter = 0;

    Random random;

    RandomPlayer() {
        super(0,0);
        random = new Random();
    }

    public void move() {
        double nx = x + vx;
        double ny = y + vy;
        while(!checkBounds(nx, ny) || (counter % 1000 == 0)) {
            counter = 1;
            double alpha = random.nextDouble(0, 2*Math.PI);
            vx = speed * Math.cos(alpha);
            vy = speed * Math.sin(alpha);
            nx = x + vx;
            ny = y + vy;
        }
        counter++;
        x = nx;
        y = ny;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
