package control.frontendapi.calculated_distance_calls;

public class HomeCall extends CalculatedDistanceCall {
    @Override
    public double call() {
        return moveToAndReturnDistance(0, 0);
    }
}
