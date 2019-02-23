package control.frontendapi.move_distance_calls;

import control.frontendapi.FrontendAPICall;

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
        double heading = ui.getHeading();
        double newY = Math.sin(heading) * myDistance;
        double newX = Math.cos(heading) * myDistance;
        ui.setPosition(newX, newY);
    }
}
