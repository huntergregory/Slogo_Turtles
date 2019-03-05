package parser_private.commands.multiple_turtle_commands;

import parser_private.Command;
import parser_private.commands.control_commands.ListCommand;
import state_public.CommandInter;

import java.util.ArrayList;
import java.util.List;

public class TellCommand extends Command {

    private ListCommand turtlesToTell;

    public TellCommand(List<CommandInter> params) {
        super(params);
        turtlesToTell = (ListCommand)params.get(0);
    }

    @Override
    public double execute() {
        double turtleID = 0;
        List<Integer> ids = new ArrayList<>();
        for (int i = 0; i < turtlesToTell.size(); i++) {
            turtleID = turtlesToTell.getParam(i).execute();
            ids.add((int)turtleID);
        }
        myStateManager.getTurtleManager().setTurtlesActive(ids);
        return turtleID;
    }


}
