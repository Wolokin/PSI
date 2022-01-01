package agariofuzzylogic;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Variable;

import java.util.ArrayList;
import java.util.Random;

import static javafx.application.Application.launch;

public class AnimatedCircleOnCanvas extends Application {
    public static final double W = 500; // canvas dimensions.
    public static final double H = 500;
    public static final int T = 100;

    public static final double D = 20;  // diameter.

    int counter = 0;
    boolean isRunning = true;

    @Override
    public void start(Stage stage) {
        Random random = new Random();

        final Canvas canvas = new Canvas(W, H);
        ArrayList<Particle> particles = new ArrayList<>();
        Label stats = new Label("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        VacuumCleaner vacuumCleaner = new VacuumCleaner(particles, stats);
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                GraphicsContext gc = canvas.getGraphicsContext2D();
                gc.setFill(Color.CORNSILK);
                gc.fillRect(0, 0, W, H);
                gc.setFill(Color.FORESTGREEN);
                for (Particle particle : particles) {
                    gc.fillOval(particle.getDrawingX(), particle.getDrawingY(), particle.getRadius() * 2, particle.getRadius() * 2);
                }
                particles.removeIf(particle -> new Vector2d(vacuumCleaner.getX(), vacuumCleaner.getY(), particle.getX(), particle.getY()).length() < vacuumCleaner.getRadius());
                if(counter % 100 == 0) {
                    counter = 1;
                    particles.add(new Particle(random.nextDouble() * W, random.nextDouble() * H));
                }
                else {
                    counter++;
                }
                vacuumCleaner.move();
                gc.fillOval(vacuumCleaner.getDrawingX(), vacuumCleaner.getDrawingY(), vacuumCleaner.getRadius()*2, vacuumCleaner.getRadius()*2);
            }
        };

        Button button = new Button("pause/play");
        button.setOnAction(actionEvent -> {
            if(isRunning) {
                timer.stop();
                System.out.println("mhm");
                isRunning = false;
            }
            else {
                timer.start();
                isRunning = true;
            }
            System.out.println("mhm");
        });
        VBox vbox = new VBox(
                canvas,
                new Button("hehe"),
                stats);
        vbox.setAlignment(Pos.CENTER);
        stage.setScene(
                new Scene(
                        new Group(vbox),
                        1500,
                        1000
                )
        );
        stage.show();

        timer.start();
    }

    public static void main(String[] args) {

        try {
            String fileName = "fcl/fuzzy_agario.fcl";
            System.out.println(fileName);
            int poziomNatezenia = 1;//Integer.parseInt(args[1]);
            int poraDnia = 2;//Integer.parseInt(args[2]);
            FIS fis = FIS.load(fileName, false);

            // zadaj wartosci wejsciowe
//            fis.setVariable("poziom_natezenia", poziomNatezenia);
//            fis.setVariable("pora_dnia", poraDnia);

            // wyswietl wykresy funkcji fuzyfikacji i defuzyfikacji
//            JFuzzyChart.get().chart(fis);

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

        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out
                    .println("Niepoprawna liczba parametrow. Przyklad: java FuzzyExample string<plik_fcl> int<poziom natezenia> int<pora dnia>");
        } catch (NumberFormatException ex) {
            System.out
                    .println("Niepoprawny parametr. Przyklad: java FuzzyExample string<plik_fcl> int<poziom natezenia> int<pora dnia>");
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

        launch(args);
    }
}