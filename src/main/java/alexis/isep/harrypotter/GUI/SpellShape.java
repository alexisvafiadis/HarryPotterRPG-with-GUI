package alexis.isep.harrypotter.GUI;

import javafx.scene.Group;
import javafx.scene.paint.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class SpellShape extends Group {

    private static final int NUM_LINES = 10;
    private static final double ANGLE_OFFSET = Math.PI / 2;
    private static final double WIDTH = 40;
    private static final double HEIGHT = 80;

    public SpellShape(int radius, Color color, boolean offensive) {
        if (offensive) {
            Circle circle = new Circle(radius);
            circle.setStroke(Color.SILVER);
            circle.setStrokeWidth(3);
            RadialGradient gradient = new RadialGradient(0, 0, 0.5, 0.5, 0.5, true, CycleMethod.NO_CYCLE,
                    new Stop(0, color), new Stop(1, Color.WHITESMOKE));
            circle.setFill(gradient);

            getChildren().add(circle);

            for (int i = 0; i < NUM_LINES; i++) {
                double angle = i * 2 * Math.PI / NUM_LINES + ANGLE_OFFSET;
                double x1 = radius * Math.cos(angle);
                double y1 = radius * Math.sin(angle);
                double x2 = (radius + 3) * Math.cos(angle);
                double y2 = (radius + 3) * Math.sin(angle);
                Line line = new Line(x1, y1, x2, y2);
                line.setStrokeWidth(3);
                line.setStroke(Color.SILVER);
                getChildren().add(line);
            }
        }
        else {
            Rectangle rectangle = new Rectangle(WIDTH, HEIGHT);
            rectangle.setStroke(Color.SILVER);
            rectangle.setStrokeWidth(3);
            LinearGradient gradient = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
                    new Stop(0, color), new Stop(1, Color.WHITESMOKE));
            rectangle.setFill(gradient);

            getChildren().add(rectangle);
        }
    }

}

