package frontendapi.move_to_position_calls;

import frontendapi.FrontendAPICall;
import frontendapi.TowardsCall;

public abstract class MoveToPositionCall extends FrontendAPICall {
    private double myNewX;
    private double myNewY;
    private boolean myEraseLines;

    protected MoveToPositionCall(double newX, double newY, boolean eraseLines) {
        myNewX = newX;
        myNewY = newY;
        myEraseLines = eraseLines;
    }

    @Override
    public double call() {
        new TowardsCall(myNewX, myNewY).call();
        System.out.println("pointing towards " + myNewX + " " + myNewY);
        double distanceTravelled = getCartesianDistance();
        ui.setPosition(myNewX, myNewY);
        if (myEraseLines)
            ui.eraseLines();
        return distanceTravelled;
    }

    private double getCartesianDistance() {
        double oldX = ui.getX();
        double oldY = ui.getY();
        var deltaXSquared = Math.pow(myNewX - oldX, 2);
        var deltaYSquared = Math.pow(myNewY - oldY, 2);
        return Math.sqrt(deltaXSquared + deltaYSquared);
    }
}