package ui_public.api.move_distance_calls;

import ui_public.api.FrontendAPICall;

public abstract class MoveDistanceCall extends FrontendAPICall  {
    private double myDistance;

    protected MoveDistanceCall(double distance) {
        myDistance = distance;
    }

    @Override
    public double call() {
        move();
        return Math.abs(myDistance);
    }

    private void move() {
        double heading = Math.toRadians(ui.getHeading());
        double newX = ui.getX() + Math.sin(heading) * myDistance;
        double newY = ui.getY() - Math.cos(heading) * myDistance;
        ui.setPosition(newX, newY);
    }
}
