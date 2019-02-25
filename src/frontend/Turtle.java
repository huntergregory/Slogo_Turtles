package frontend;

import javafx.collections.ObservableList;
import javafx.scene.image.ImageView;

/**
 * Represents a movable turtle with customizable image and drawing capabilities.
 * @author Hunter Gregory
 */
public class Turtle {
    private ImageView myImageView;
    private Pen myPen;
    private double myMinX;
    private double myMaxX;
    private double myMinY;
    private double myMaxY;
    private double myX;
    private double myY;
    private double myHeading;
    private boolean myIsShowing;

    /**
     * Assumes all double inputs are positive, and list input is nonnull.
     * @param screenWidth
     * @param screenHeight
     * @param topLeftX
     * @param topLeftY
     * @param list
     */
    protected Turtle(double screenWidth, double screenHeight, double topLeftX, double topLeftY, ObservableList list) {
        setBoundaries(screenWidth, screenHeight, topLeftX, topLeftY);
        myPen = new Pen(list);
        myImageView = new ImageView();
        //TODO: change image to a turtle
        myX = 0; myY = 0; myHeading = 0;
        myIsShowing = true;
    }

    private void setBoundaries(double screenWidth, double screenHeight, double topLeftX, double topLeftY) {
        myMinX = topLeftX;
        myMaxX = topLeftX + screenWidth;
        myMinY = topLeftY;
        myMaxY = topLeftY + screenHeight;
    }

    protected void setPosition(double x, double y) {
        double newX = getAdjustedValue(x, myMinX, myMaxX);
        double newY = getAdjustedValue(y, myMinY, myMaxY);
        myImageView.setX(newX);  //TODO: animate if desired
        myImageView.setY(newY);
        myPen.draw(myX, myY, newX, newY);
        myX = newX;
        myY = newY;
    }

    private double getAdjustedValue(double num, double min, double max) {
       if (num < min)
           return min;
       if (num > max)
           return max;
       return num;
    }

    protected double getX() {
        return myX;
    }

    protected double getY() {
        return myY;
    }

    protected void setHeading(double heading) {
        myHeading = heading;
        myImageView.setRotate(myHeading);
    }

    protected double getHeading() {
        return myHeading;
    }

    protected boolean getIsShowing() {
        return myIsShowing;
    }

    protected void setIsShowing(boolean show) {
        myIsShowing = show;
        myImageView.setVisible(show);
    }

    protected boolean getPenIsDown() {
        return myPen.getIsDown();
    }

    protected void setPenIsDown(boolean down) {
        myPen.setIsDown(down);
    }

    protected void setStroke(LineStroke stroke) {
        myPen.setStroke(stroke);
    }

    protected void eraseLines() {
        myPen.erase();
    }
}
