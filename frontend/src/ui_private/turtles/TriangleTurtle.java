package ui_private.turtles;

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
        return new Double[] {WIDTH /2.0, 0.0, WIDTH, HEIGHT, 0.0, HEIGHT};
    }

    private Double[] getEquilateralPoints() {
        var topX = WIDTH /2.0;
        var rootThree = Math.sqrt(3);
        var triangleHeight = rootThree * WIDTH /2.0;
        var topY = (HEIGHT - triangleHeight) / 2.0;
        var bottomY = topY + triangleHeight;
        var bottomRightX = 0.0;
        var bottomLeftX = WIDTH;
        return new Double[] {topX, topY, bottomLeftX, bottomY, bottomRightX, bottomY};
    }
}
