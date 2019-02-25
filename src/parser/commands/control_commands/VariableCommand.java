package parser.commands.control_commands;

import parser.Command;

public class VariableCommand extends Command {

    String myName;

    public VariableCommand(String name) {
        this.myName = name;
    }


    String getVariableName() {
        return myName;
    }


    @Override
    public double runCommand() {
        return getVariable(myName);
    }
}
