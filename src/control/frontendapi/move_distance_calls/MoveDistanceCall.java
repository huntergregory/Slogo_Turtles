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
        double heading = Math.toRadians(ui.getHeading());
        double newX = Math.sin(heading) * myDistance;
        double newY = -Math.cos(heading) * myDistance;
        ui.setPosition(newX, newY);
        System.out.println("new x is " + newX);
        System.out.println("new y is " + newY);
    }
}
