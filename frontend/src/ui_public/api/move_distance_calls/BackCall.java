package ui_public.api.move_distance_calls;

public class BackCall extends MoveDistanceCall {
    public BackCall(double distance) {
        super(-distance);
    }
}
