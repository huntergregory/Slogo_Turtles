package parser.commands.control_commands;

import parser.Command;

public class VariableCommand extends Command {

    String name;

    public VariableCommand(String name) {
        this.name = name;
    }


    String getVariableName() {
        return name;
    }


    @Override
    public double runCommand() {
        return getVariable(name);
    }
}
