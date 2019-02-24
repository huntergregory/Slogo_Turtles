package frontend;

/**
 * Add more enumerations to allow addition of different turtle strokes.
 */
public enum LineStroke {
    NORMAL("normal", new double[]{});

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
