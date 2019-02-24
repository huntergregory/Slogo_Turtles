package parser;

import java.util.List;

public abstract class ActionCommand extends Command {

    public ActionCommand() {

    }

    public ActionCommand(List<Command> params) {
        super(params);
    }

}
