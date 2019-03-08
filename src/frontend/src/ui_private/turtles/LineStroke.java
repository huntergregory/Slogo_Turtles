package ui_private.turtles;

/**
 * Add more enumerations to allow addition of different turtle strokes.
 */
public enum LineStroke {
    NORMAL("normal", new double[]{}),

    line1.getStrokeDashArray().addAll(25d, 20d, 5d, 20d);

    Line line2 = new Line(20, 60, 270, 60);
line2.getStrokeDashArray().addAll(50d, 40d);

    Line line3 = new Line(20, 80, 270, 80);
line3.getStrokeDashArray().addAll(25d, 10d);

    Line line4 = new Line(20, 100, 270, 100);
line4.getStrokeDashArray().addAll(2d);

    Line line5 = new Line(20, 120, 270, 120);
line5.getStrokeDashArray().addAll(2d, 21d);

    String myName;
    double[] myStrokeArray;
    LineStroke(String name, double[] array) {
        myName = name;
        myStrokeArray = array;
    }

    public String getText() {
        return myName;
    }

    public double[] getStrokeArray() {
        return myStrokeArray;
    }
}
