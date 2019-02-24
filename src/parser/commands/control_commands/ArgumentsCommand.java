package parser.commands.control_commands;

import parser.Command;

import java.util.List;

public class ArgumentsCommand extends ListCommand {

    public ArgumentsCommand(List<Command> params) {
        super(params);
    }

    public Command getParam(int index) {
        return commands.get(index);
    }

}
