package parser.ActionCommands;

import parser.ActionCommand;

public class ForwardCommand extends ActionCommand {


    @Override
    public double execute() {
        System.out.println("Executing Forward");
        return 0;
    }

}
