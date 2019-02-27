package parser_private.commands.turtle_commands.move_distance_commands;

public abstract class MoveDistanceCall extends FrontendAPICall  {
    private double myDistance;

    protected MoveDistanceCall(double distance) {
        myDistance = distance;
    }

    @Override
    public double call() {
        //move();
        return Math.abs(myDistance);
    }

    /*private void move() {
        double heading = Math.toRadians(ui.getHeading());
        double newX = ui.getX() + Math.sin(heading) * myDistance;
        double newY = ui.getY() - Math.cos(heading) * myDistance;
        ui.setPosition(newX, newY);
    }*/
}
