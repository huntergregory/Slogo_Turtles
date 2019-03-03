package ui_private.displays;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import state_public.TurtleManager;
import ui_private.turtles.ImageTurtleView;
import ui_private.turtles.LineStroke;
import ui_private.turtles.TurtleView;

import java.util.ArrayList;

public class TurtleDisplay {
    private static final String PANE_CSS_CLASS = "pane";
    public static String DEFAULT_IMAGE_NAME = "tan_turtle.png";
    public static final LineStroke DEFAULT_LINE_STROKE = LineStroke.NORMAL;
    public static final Color DEFAULT_PEN_COLOR = Color.BLACK;

    private Color myPenColor;
    private LineStroke myPenStroke;
    private Image myImage;

    private TurtleManager myTurtleManager;
    private Pane myTurtlePane;
    private ArrayList<TurtleView> myTurtleViews; //TODO: call that class TurtleView
    private double myWidth;
    private double myHeight;

    public TurtleDisplay(TurtleManager turtleManager, double width, double height) {
        myTurtleManager = turtleManager;
        myWidth = width;
        myHeight = height;
        initializePane();
        initializeTurtles();
    }

    private void initializePane() {
        myTurtlePane = new Pane();
        myTurtlePane.setMaxSize(myWidth, myHeight);
        myTurtlePane.setMinSize(myWidth, myHeight);
        myTurtlePane.getStyleClass().add(PANE_CSS_CLASS);
    }


    // Must be called after initializing myPane
    private void initializeTurtles() {
        myTurtleViews = new ArrayList<>();
        myTurtleManager.setDimensions(myWidth, myHeight);
        myTurtleViews.add(new ImageTurtleView(0, getTurtleXOrigin(), getTurtleYOrigin(), myTurtlePane.getChildren()));
    }

    public void setBackgroundColor(Color color) {
        myTurtlePane.setBackground(new Background(new BackgroundFill(color, null, null)));
    }

    public void setPenColor(Color color) {
        for (TurtleView turtleView : myTurtleViews)
            turtleView.setPenColor(color);
    }

    public void setTurtleImage(Image image) {
        if (myImage == null)
            myImage = new Image(getClass().getClassLoader().getResourceAsStream(DEFAULT_IMAGE_NAME));

        for (TurtleView turtleView : myTurtleViews) {
            if (!(turtleView instanceof ImageTurtleView))
                continue;
            ((ImageTurtleView) turtleView).setImage(myImage);
        }
    }

    public void addTurtle() {
        //FIXME
    }

    public void removeTurtle() {
        //FIXME
        //TODO: catch NoTurtleException from TurtleManager
    }

    public Pane getPane() {
        return myTurtlePane;
    }

    private double getTurtleXOrigin() {
        return myWidth / 2.0;
    }

    private double getTurtleYOrigin() {
        return myHeight / 2.0;
    }
}
