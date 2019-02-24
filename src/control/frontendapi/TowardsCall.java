package control.frontendapi;

public class TowardsCall extends FrontendAPICall {
    private final double NUM_DEGREES = 360;
    private double myX;
    private double myY;

    protected TowardsCall(double x, double y) {
        myX = x;
        myY = y;
    }

    @Override
    public double call() {
        double deltaX = myX - ui.getX();
        double deltaY = -(myY - ui.getY());
        if (deltaX == 0 && deltaY == 0)
            return 0;

        double newHeading = getNewHeading(deltaX, deltaY);

        double oldHeading = ui.getHeading();
        ui.setHeading(newHeading);

        //TODO: Remove if modulo is undesired
        double difference = newHeading - oldHeading;
        double modDifference = difference % NUM_DEGREES;
        return (difference >= 0) ? modDifference : -modDifference;
    }

    private double getNewHeading(double deltaX, double deltaY) {
        double newHeading;
        if (deltaY == 0)
            newHeading = (deltaX > 0) ? 90 : -90; // OR 270
        else
            newHeading = Math.toDegrees(Math.atan(deltaX / deltaY));
        return newHeading;
    }
}