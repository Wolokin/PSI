package agariofuzzylogic;

import javafx.scene.control.Label;
import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Rule;
import net.sourceforge.jFuzzyLogic.rule.Variable;

import java.util.List;

import static agariofuzzylogic.AnimatedCircleOnCanvas.H;
import static agariofuzzylogic.AnimatedCircleOnCanvas.W;
import static java.lang.Math.*;


public class VacuumCleaner extends Circle {
    FIS fis;
    List<Particle> particles;
    double speed = 1.0;
    double angle = 0.0;
    int counter = 0;
    Label stats;
    final double detectionAngle = Math.PI/4;

    VacuumCleaner(List<Particle> particles, Label stats) {
        super(W/2.0, H/2.0);
        this.stats = stats;
        this.radius = 20;
        this.particles = particles;

        String fileName = "fcl/fuzzy_agario.fcl";
        fis = FIS.load(fileName, false);

        JFuzzyChart.get().chart(fis);

        // logika sterownika
//            fis.evaluate();

        // graficzna prezentacja wyjscia
//            Variable v = fis.getVariable("zmiana_natezenia");
//            JFuzzyChart.get().chart(v, v.getDefuzzifier(), true);
//
//            for (int i = 0; i < 10; i++) {
//                fis.setVariable("poziom_natezenia", poziomNatezenia + i);
//                fis.setVariable("pora_dnia", poraDnia + i);
//                fis.evaluate();
//                v = fis.getVariable("zmiana_natezenia");
//                JFuzzyChart.get().chart(v, v.getDefuzzifier(), true);
//            }
//            System.out.println("end");
    }

    public void move() {
        if(counter % 10 == 0) {
            final int scaling = 2;
            counter = 1;
            double closest_particle_in_front = 100*scaling;
            int particles_left = 0;
            int particles_right = 0;
            Vector2d hardLeft = new Vector2d(angle - Math.PI / 2);
            Vector2d left = new Vector2d(angle - detectionAngle/2);
            Vector2d right = new Vector2d(angle + detectionAngle/2);
            Vector2d hardRight = new Vector2d(angle + Math.PI / 2);
            for (Particle p : particles) {
                Vector2d v = new Vector2d(getX(), getY(), p.getX(), p.getY());
                if (hardLeft.cross(v) * left.cross(v) < 0 && left.cross(v) < 0) particles_left++;
                else if (left.cross(v) * right.cross(v) < 0 && right.cross(v) < 0) {
                    closest_particle_in_front = min(closest_particle_in_front, v.length());
                } else if (right.cross(v) * hardRight.cross(v) < 0 && hardRight.cross(v) < 0) particles_right++;
            }
            StringBuilder s = new StringBuilder("#".repeat(130)+"\n");
            closest_particle_in_front /= scaling;
            particles_left = min(particles_left, 40);
            particles_right = min(particles_right, 40);
            s.append("closest_particle_in_front: ").append(closest_particle_in_front).append("\n");
            s.append("particles_left: ").append(particles_left).append("\n");
            s.append("particles_right: ").append(particles_right).append("\n");
            fis.setVariable("closest_particle_in_front", closest_particle_in_front);
            fis.setVariable("particles_left", particles_left);
            fis.setVariable("particles_right", particles_right);

            fis.evaluate();

            Variable v = fis.getVariable("direction_change");
            double direction_change = v.getValue();
            s.append("Fuzzy result: ").append(direction_change).append("\n");
            System.out.println(direction_change);
            direction_change /= 13;
            direction_change -= 0.5;
            direction_change *= PI;
            s.append("Angle change: ").append(direction_change).append("\n\n");
            angle += direction_change;
            x += speed * cos(angle);
            y += speed * sin(angle);
//            JFuzzyChart.get().chart(v, v.getDefuzzifier(), true);
            for(Rule rule : fis.getFunctionBlock("f").getFuzzyRuleBlock("first").getRules()) {
                s.append(rule.toString()).append("\n");
            }
            stats.setText(s.toString());
        }
        else {
            counter++;
        }
    }
}
