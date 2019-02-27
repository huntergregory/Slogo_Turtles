package parser_private;


/**
 * A backend turtle that employs bindings to relate properties to frontend turtle representation.
 * Ensures the turtle stays in a boundary width and height.
 * @author Hunter Gregory
 */
public class Turtle {
    private final double myBoundaryWidth;
    private final double myBoundaryHeight;
    private final double myTurtleWidth;
    private final double myTurtleHeight;

    private int myTurtleID;
    private double myX;
    private double myY;
    private double myHeading;
    private boolean myIsShowing;
    private boolean myPenIsDown;
    private boolean myErasePreviousLines;
    //FIXME: or do TurtleState

    /**
     * Assumes all double inputs are positive, and list input is nonnull.
     * @param displayWidth
     * @param displayHeight
     * @param turtleWidth
     * @param turtleHeight
     */
    public Turtle(int turtID, double displayWidth, double displayHeight, double turtleWidth, double turtleHeight) {
        myTurtleID = turtID;
        myBoundaryWidth = displayWidth;
        myBoundaryHeight = displayHeight;
        myTurtleWidth = turtleWidth;
        myTurtleHeight = turtleHeight;
        setDefaultState();
    }

    private void setDefaultState() {
        myX = 0;
        myY = 0;
        myHeading = 0;
        myIsShowing = true;
        myPenIsDown = true;
        myErasePreviousLines = false;
    }

    public void eraseLines() {
        myPen.erase();
    }

    public void setState(TurtleState newState) {
        myCurrentState = newState;

        setPosition(newState.getX(), newState.getY());
        setHeading(newState.getHeading());
        myPen.setIsDown(newState.getPenDown());
        myNode.setVisible(newState.getShowing());
    }

    private void setPosition(double newX, double newY) {
        double oldDisplayX = getOriginAdjustedTurtleX();
        double oldDisplayY = getOriginAdjustedTurtleY();
        setX(newX);
        setY(newY);
        double newDisplayX = getOriginAdjustedTurtleX();
        double newDisplayY = getOriginAdjustedTurtleY();
        myNode.relocate(newDisplayX, newDisplayY);
        myPen.draw(oldDisplayX + Turtle.WIDTH / 2.0,
                oldDisplayY + Turtle.HEIGHT / 2.0,
                newDisplayX + Turtle.WIDTH / 2.0,
                newDisplayY + Turtle.HEIGHT / 2.0);
        moveAboveLines();
    }

    /**
     * Call after setPosition if you want Turtles to be drawn above lines drawn.
     */
    private void moveAboveLines() {
        myModifiableList.remove(myNode);
        myModifiableList.add(myNode);
    }

    private void setHeading(double heading) {
        myNode.setRotate(heading);
    }

    public void setStroke(LineStroke stroke) {
        myPen.setStroke(stroke);
    }

    private double getOriginX() {
        return myDisplayWidth / 2.0;
    }

    private double getOriginY() {
        return myDisplayHeight / 2.0;
    }

    private double getOriginAdjustedTurtleX() {
        double centerX = getOriginX() - Turtle.WIDTH / 2.0;
        return myX + centerX;
    }

    private double getOriginAdjustedTurtleY() {
        double centerY = getOriginY()- Turtle.HEIGHT / 2.0;
        return myY + centerY;
    }

    private void setX(double x) {
        myX = getInBoundsNum(x, -getOriginX() + Turtle.WIDTH/2.0, getOriginX() - Turtle.WIDTH/2.0);
    }

    private void setY(double y) {
        myY = getInBoundsNum(y, -getOriginY() + Turtle.HEIGHT/2.0, getOriginY() - Turtle.HEIGHT/2.0);
    }

    private double getInBoundsNum(double num, double min, double max) {
        if (num < min) {
            System.out.println("position was out of bounds on the left or top");
            System.out.println("returning " + min);
            return min;
        }
        if (num > max) {
            System.out.println("position was out of bounds on the right or bottom");
            System.out.println("returning " + max);
            return max;
        }
        return num;
    }

    public int getTurtleID() {
        return myTurtleID;
    }
}
