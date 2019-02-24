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
        return Math.abs(myDistance);
    }

    private void move() {
        double heading = ui.getHeading();
        int newY = (int)(Math.sin(heading) * myDistance);
        int newX = (int)(Math.cos(heading) * myDistance);
        ui.setXY(newX, newY);
    }
}
