package parser_private.commands.turtle_commands;

import parser_private.Command;
import parser_public.TurtleManager;

import java.util.List;

/**
 * Class created solely to prevent repeatedly calling getInstance on TurtleManager.
 * @author Hunter Gregory
 */
public abstract class TurtleCommand extends Command {
    protected TurtleManager myManager = TurtleManager.getInstance();

    public TurtleCommand(List<Command> params) {
        super(params);
    }
}
