package ui_private.turtles;

import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import state.Turtle;

/**
 *
 * @author Hunter Gregory
 */
public class TriangleTurtleView extends TurtleView {
    private static final Paint COLOR = Color.RED;

    public TriangleTurtleView(ObservableList list, Turtle turtle, double dispOffsetX, double dispOffsetY) {
        super(list, turtle, dispOffsetX, dispOffsetY);
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
