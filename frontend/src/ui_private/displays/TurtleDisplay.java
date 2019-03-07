package ui_private.displays;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import state_public.Palette;
import state_public.Turtle;
import state_public.TurtleManager;
import ui_private.turtles.LineStroke;
import ui_private.turtles.TriangleTurtleView;
import ui_private.turtles.TurtleView;

import java.awt.*;
import java.util.ArrayList;

//TODO make which turtle type we're using dynamic
public class TurtleDisplay {
    private static final String PANE_CSS_CLASS = "pane";
    public static String DEFAULT_IMAGE_NAME = "tan_turtle.png"; //TODO remove
    public static final LineStroke DEFAULT_LINE_STROKE = LineStroke.NORMAL; //TODO remove
    public static final Color DEFAULT_PEN_COLOR = Color.BLACK; //TODO remove
    private Image myImage; //TODO remove

    private TurtleManager myTurtleManager;
    private Pane myTurtlePane;
    private ArrayList<TurtleView> myTurtleViews;
    private double myWidth;
    private double myHeight;

    public TurtleDisplay(TurtleManager turtleManager, double width, double height) { // FIXME
        myTurtleManager = turtleManager;
        myWidth = width;
        myHeight = height;
        myTurtleManager.setPanelWidthHeight(myWidth, myHeight);
        initializePane();
        initializeTurtles();
        bindBackground();
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
        int id = 1;
        myTurtleManager.addTurtle(id);
        Turtle turtleStates = myTurtleManager.getTurtle(id);
        myTurtleViews.add(new TriangleTurtleView(0, myTurtlePane.getChildren(), turtleStates, getTurtleXOrigin(), getTurtleYOrigin()));
    }

    private void bindBackground() {
        myTurtleManager.getBackgroundColor().getColorProperty().addListener((o, oldVal, newVal) -> setBackground(newVal));
    }

    private void setBackground(Color color) {
        BackgroundFill oldFill = myTurtlePane.getBackground().getFills().get(0);
        BackgroundFill newFill = new BackgroundFill(color, oldFill.getRadii(), oldFill.getInsets());
        myTurtlePane.setBackground(new Background(newFill));
    }

/* TODO: remove


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
*/

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
