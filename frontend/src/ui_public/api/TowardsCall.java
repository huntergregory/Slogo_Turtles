package ui_public.api;

public class TowardsCall extends FrontendAPICall {
    private final double NUM_DEGREES = 360;
    private double myX;
    private double myY;

    public TowardsCall(double x, double y) {
        myX = x;
        myY = y;
    }

    @Override
    public double call() {
        double newHeading = getNewHeading();
        double oldHeading = ui.getHeading();
        ui.setHeading(newHeading);

        //TODO: Remove if modulo is undesired
        double difference = newHeading - oldHeading;
        double modDifference = difference % NUM_DEGREES;
        return modDifference;
    }

    private double getNewHeading() {
        double deltaX = myX - ui.getX();
        double deltaY = -(myY - ui.getY());
        if (deltaX == 0 && deltaY == 0)
            return ui.getHeading();

        double newHeading;
        if (deltaY == 0)
            newHeading = (deltaX > 0) ? 90 : -90; // OR 270
        else {
            double upLeftOrUpRightHeading = Math.toDegrees(Math.atan(deltaX / deltaY));
            System.out.println("proposed heading: " + upLeftOrUpRightHeading);
            newHeading = (deltaX >= 0 && deltaY > 0 || deltaX <= 0 && deltaY > 0) ? upLeftOrUpRightHeading : upLeftOrUpRightHeading + 180;
            System.out.println("set heading: " + newHeading);
        }
        return newHeading;
    }
}