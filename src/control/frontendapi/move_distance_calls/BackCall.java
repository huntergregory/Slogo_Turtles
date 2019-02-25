package control.frontendapi.move_distance_calls;

public class BackCall extends MoveDistanceCall {
    public BackCall(double distance) {
        super(-distance);
    }
}
