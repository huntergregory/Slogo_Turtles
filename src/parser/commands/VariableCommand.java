package parser.commands;

import jdk.jshell.spi.ExecutionControl;
import parser.Command;

import java.util.List;

public class VariableCommand extends Command {

    String name;

    public VariableCommand(String name) {
        this.name = name;
    }


    public String getVariableName() {
        return name;
    }


    @Override
    public double runCommand() {
        return 0;
    }
}
