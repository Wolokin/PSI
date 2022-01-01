package agariofuzzylogic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;

import static java.lang.Math.abs;

public record SensorParams(double x, double y, double w, double h, double sa, double ea) {

    public double toDegrees(double radians) {
        return (((180*radians/Math.PI) % (360)) + (360)) % (360);
    }

    public void applyFillArc(GraphicsContext gc) {
//        System.out.println(toDegrees(sa));
//        System.out.println(toDegrees(abs(ea)));
        gc.fillArc(x-w/2,y-h/2,w,h,2*Math.PI - toDegrees(sa),toDegrees(abs(ea)), ArcType.ROUND);
//        gc.fillArc(x-w/2,y-h/2,w,h,45,-90, ArcType.ROUND);
    }
}
