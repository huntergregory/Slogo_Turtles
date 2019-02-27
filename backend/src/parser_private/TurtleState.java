package parser_private;

public class TurtleState {

    private double myX;
    private double myY;
    private double myHeading;
    private boolean myIsShowing;
    private boolean myPenDown;
    private boolean myErasePreviousLines;

    private TurtleState(double x, double y, double heading, boolean show, boolean pendown, boolean eraseLines) {
        myX = x;
        myY = y;
        myHeading = heading;
        myIsShowing = show;
        myPenDown = pendown;
        myErasePreviousLines = eraseLines;
    }

    public TurtleState() {
        this(0, 0, 0, true, true, false);
    }

    public void setX(double newX) {
        myX = newX;
    }

    public void setY(double newY) {
        myY = newY;
    }

    void setHeading(double newHeading) {
        myHeading = newHeading;
    }

    public void setShowing(boolean newShow) {
        myIsShowing = newShow;
    }

    public void setPenDown(boolean penDown) {
        myPenDown = penDown;
    }

    /*
    public double getX() {
        return myX;
    }

    public double getY() {
        return myY;
    }

    public double getHeading() {
        return myHeading;
    }

    public boolean getShowing() {
        return myIsShowing;
    }

    public boolean getPenDown() {
        return myPenDown;
    }
    */
}
