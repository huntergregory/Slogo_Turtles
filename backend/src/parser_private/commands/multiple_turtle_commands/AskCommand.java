package parser_private.commands.multiple_turtle_commands;

import parser_private.Command;
import parser_private.commands.control_commands.ListCommand;
import state_public.CommandInter;

import java.util.ArrayList;
import java.util.List;

public class AskCommand extends Command {

    private ListCommand myTurtles;
    private ListCommand myCommands;

    public AskCommand(List<CommandInter> params) {
        super(params);
        myTurtles = (ListCommand)params.get(0);
        myCommands = (ListCommand)params.get(1);
    }

    @Override
    public double execute() {
        new TellCommand(new ArrayList<>() {{
            add(myTurtles);
        }}).execute();

        double output = myCommands.execute();
        myStateManager.getTurtleManager().revertActiveTurtles();
        return output;
    }


}
