package frontend.turtles;

import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.paint.Paint;

public class TriangleTurtle extends Turtle {
    private static final Paint COLOR = Color.RED;

    public TriangleTurtle(double displayWidth, double displayHeight, ObservableList list) {
        super(displayWidth, displayHeight, list);
    }

    @Override
    protected void initializeNode() {
        var triangle = new Polygon();
        triangle.getPoints().addAll(getIsoscelesPoints());
        triangle.setFill(COLOR);
        myNode = triangle;
    }

    private Double[] getIsoscelesPoints() {
        return new Double[] {Turtle.WIDTH/2.0, 0.0, Turtle.WIDTH, Turtle.HEIGHT, 0.0, Turtle.HEIGHT};
    }

    private Double[] getEquilateralPoints() {
        var topX = Turtle.WIDTH/2.0;
        var rootThree = Math.sqrt(3);
        var triangleHeight = rootThree * Turtle.WIDTH/2.0;
        var topY = (Turtle.HEIGHT - triangleHeight) / 2.0;
        var bottomY = topY + triangleHeight;
        var bottomRightX = 0.0;
        var bottomLeftX = Turtle.WIDTH;
        return new Double[] {topX, topY, bottomLeftX, bottomY, bottomRightX, bottomY};
    }
}
