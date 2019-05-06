package parser_private.commands.turtle_commands;

import state.ICommand;
import state.Turtle;

import java.awt.geom.Point2D;
import java.util.List;

public class Stamp extends TurtleCommand {

    public Stamp(List<ICommand> params) {
        super(params);
    }

    @Override
    public double execute() {
        System.out.println("Stamp");

        return runTurtleCommand(turtle -> {
            for (int i = 0; i < 4; i++) {
                moveAndTurn(turtle);
            }
            return (double)turtle.getID();
        });


    }
    private void moveAndTurn(Turtle turtle) {
        Point2D pos = turtle.getPosition();
        turtle.setPosition(pos.getX() + Math.sin(turtle.getHeading()) * 20,
                pos.getY() - Math.cos(turtle.getHeading()) * 20);
        turtle.setHeading(turtle.getHeading() + 1.57);
    }
}
