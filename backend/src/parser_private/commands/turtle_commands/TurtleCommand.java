package parser_private.commands.turtle_commands;

import parser_private.Command;
import state_public.CommandInter;
import state_public.Turtle;

import java.util.List;
import java.util.function.Function;

public abstract class TurtleCommand extends Command {

    public TurtleCommand(List<CommandInter> params) {
        super(params);
    }

    protected double runTurtleCommand(Function<Turtle, Double> func) {
        return myStateManager.getTurtleManager().runTurtleCommand(func);
    }

}
