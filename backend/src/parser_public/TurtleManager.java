package parser_public;

import parser_private.Turtle;

import java.util.ArrayList;

public class TurtleManager {
    private static TurtleManager instance;

    private ArrayList<Turtle> myTurtles;

    private TurtleManager() {
        myTurtles = new ArrayList<>();
    }

    public static TurtleManager getInstance() {
        if (instance == null) {
            instance = new TurtleManager();
        }
        return instance;
    }

    public void addTurtle(double displayWidth, double displayHeight, double turtleWidth, double turtleHeight) {
        int id = myTurtles.size();
        myTurtles.add(new Turtle(id, displayWidth, displayHeight, turtleWidth, turtleHeight));
    }

    public void removeTurtle() {
        removeTurtle(myTurtles.size() - 1);
    }

    public void removeTurtle(int id) {
        if (idOutOfBounds(id))
            return;
        myTurtles.remove(id);
    }

    private boolean idOutOfBounds(int id) {
        return id >= myTurtles.size() || id < 0;
    }
}
