package parser_private.commands.turtle_commands.move_distance_commands;

import parser_private.Command;

import java.util.List;

public class MoveDistanceCommand extends Command {
    private Command myDistance;

    public MoveDistanceCommand(List<Command> params) {
        super(params);
        this.myDistance = params.get(0);
    }

    @Override
    public double runCommand() {
        double distance = myDistance.execute();

        return 0;
    }

    private void move() {
        double heading = Math.toRadians(ui.getHeading());
        double newX = ui.getX() + Math.sin(heading) * myDistance;
        double newY = ui.getY() - Math.cos(heading) * myDistance;
        ui.setPosition(newX, newY);
    }
}
