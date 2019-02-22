package control.frontendapi.move_distance_calls;

import control.frontendapi.FrontendAPICall;

public class MoveDistanceCall extends FrontendAPICall  {
    private double myDistance;

    MoveDistanceCall(double distance) {
        myDistance = distance;
    }

    @Override
    public double call() {
        move();
        return myDistance;
    }

    private void move() {
        double heading = ui.getHeading();
        double newY = Math.sin(heading) * myDistance;
        double newX = Math.cos(heading) * myDistance;
        ui.setXY(newX, newY);
    }
}
