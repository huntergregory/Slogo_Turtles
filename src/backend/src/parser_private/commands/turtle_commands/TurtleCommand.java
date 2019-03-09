package parser_private.commands.turtle_commands;

import parser_private.Command;
import state.ICommand;
import state.Turtle;

import java.util.List;
import java.util.function.Function;

/**
 * @author David Miron
 * @author Harry Ross
 */
public abstract class TurtleCommand extends Command {

    public TurtleCommand(List<ICommand> params) {
        super(params);
    }

    protected double runTurtleCommand(Function<Turtle, Double> func) {
        return myStateManager.getTurtleManager().runTurtleCommand(func);
    }

}
