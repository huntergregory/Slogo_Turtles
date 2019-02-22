package control.frontendapi.calculated_distance_calls;

public class ClearScreenCall extends  CalculatedDistanceCall {
    @Override
    public double call() {
        ui.resetTurtles();
        return moveToAndReturnDistance(0, 0);
    }
}