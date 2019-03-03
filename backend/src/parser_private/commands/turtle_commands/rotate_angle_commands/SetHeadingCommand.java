package parser_private.commands.turtle_commands.rotate_angle_commands;

import parser_private.Command;

import java.util.List;

public class SetHeadingCommand extends TurtleCommand {

    private Command myNewHeading;

    public SetHeadingCommand (List<Command> params) {
        super(params);
        myNewHeading = params.get(0);
    }

    @Override
    public double runCommand() {
        double oldHeading = myManager.getHeading();
        myManager.setHeading(myNewHeading.execute());
        return Math.abs(myNewHeading.execute() - oldHeading);
    }
}
