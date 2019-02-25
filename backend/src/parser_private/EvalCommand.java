package parser_private;

import java.util.List;

public abstract class EvalCommand extends Command {

    public EvalCommand(List<Command> params) {
        super(params);
    }
}
