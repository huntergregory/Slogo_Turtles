package parser_private.commands.turtle_commands;

import parser_private.Command;

import java.util.List;

public class LeftCommand extends Command {

    private Command degreesCommand;

    public LeftCommand(List<Command> params) {
        this.degreesCommand = params.get(0);
    }

    public double runCommand() {
        System.out.println("Turning left");
        return 0.0; //new LeftCall(degreesCommand.execute()).call();
    }
}
