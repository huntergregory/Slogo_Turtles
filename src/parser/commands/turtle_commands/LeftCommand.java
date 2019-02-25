package parser.commands.turtle_commands;

import control.frontendapi.rotate_angle_calls.LeftCall;
import parser.Command;

import java.util.List;

public class LeftCommand extends Command {

    private Command degreesCommand;

    public LeftCommand(List<Command> params) {
        this.degreesCommand = params.get(0);
    }

    public double runCommand() {
        System.out.println("Turning left");
        return new LeftCall(degreesCommand.execute()).call();
    }
}
