package frontend;

/**
 * Add more enumerations to allow addition of different turtle strokes.
 */
public enum LineStroke {
    NORMAL("normal", new double[]{0});

    String myName;
    double[] myStrokeArray;
    LineStroke(String name, double[] array) {
        myName = name;
        myStrokeArray = array;
    }

    protected String getText() {
        return myName;
    }

    protected double[] getStrokeArray() {
        return myStrokeArray;
    }
}
