package parser_public;

public class TurtleState {

    private int myTurtleID;
    private double myX;
    private double myY;
    private double myHeading;
    private boolean myIsShowing;
    private boolean myPenDown;

    private TurtleState(int turtID, double x, double y, double heading, boolean show, boolean pendown) {
        myTurtleID = turtID;
        myX = x;
        myY = y;
        myHeading = heading;
        myIsShowing = show;
        myPenDown = pendown;
    }

    public TurtleState() {
        this(0, 0, 0, 0, true, false);
    }

    public TurtleState(double x, double y, double heading, boolean show, boolean pendown) {
        this(0, x, y, heading, show, pendown);
    }

    public TurtleState(TurtleState prev) {
        this(prev.myTurtleID, prev.myX, prev.myY, prev.myHeading, prev.myIsShowing, prev.myPenDown);
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

    public int getTurtleID() {
        return myTurtleID;
    }

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
}
