package control.frontendapi.calculated_distance_calls;

import control.frontendapi.FrontendAPICall;

public abstract class CalculatedDistanceCall extends FrontendAPICall {
    protected double moveToAndReturnDistance(double newX, double newY) {
        double oldX = ui.getX();
        double oldY = ui.getY();
        ui.setXY(newX, newY);
        var deltaXSquared = Math.pow(newX - oldX, 2);
        var deltaYSquared = Math.pow(newY - oldY, 2);
        return Math.sqrt(deltaXSquared + deltaYSquared);
    }
}
