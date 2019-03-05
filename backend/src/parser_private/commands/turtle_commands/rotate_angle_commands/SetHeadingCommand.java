package parser_private.commands.turtle_commands.rotate_angle_commands;

import parser_private.commands.turtle_commands.TurtleCommand;
import state_public.CommandInter;

import java.util.List;

public class SetHeadingCommand extends TurtleCommand {

    private CommandInter myNewHeading;

    public SetHeadingCommand (List<CommandInter> params) {
        super(params);
        myNewHeading = params.get(0);
    }

    @Override
    public double execute() {
        double newHeading = myNewHeading.execute();
        return runTurtleCommand((turtle) -> {
            double oldHeading = turtle.getHeading();
            turtle.setHeading(newHeading);
            return Math.abs(newHeading - oldHeading);
        });
    }
}
