package parser_public;

import java.util.ArrayList;

public class TurtleManager {
    private ArrayList<Turtle> myTurtles;

    protected TurtleManager() {
        this(1);
    }

    protected TurtleManager(int numTurtles) {
        myTurtles = new ArrayList<>();
        while (numTurtles > 0) {
            myTurtles.add(new Turtle());
            numTurtles -= 1;
        }
    }

    public void addTurtle() {
        myTurtles.add(new Turtle());
    }

    public void removeTurtle() {
        int last = myTurtles.size() - 1;
        myTurtles.remove(last);
    }
}
