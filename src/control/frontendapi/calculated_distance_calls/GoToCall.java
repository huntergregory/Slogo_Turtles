package control.frontendapi.calculated_distance_calls;

public class GoToCall extends CalculatedDistanceCall {
    private double myX;
    private double myY;

    public GoToCall(double x, double y) {
        myX = x; myY = y;
    }

    @Override
    public double call() {
        return moveToAndReturnDistance(myX, myY);
    }
}
