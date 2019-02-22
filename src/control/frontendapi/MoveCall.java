package control.frontendapi;

public class MoveCall extends FrontendAPICall {

    private double distance;

    public MoveCall(double distance) {
        this.distance = distance;
    }

    @Override
    public double call() {
        ui.setXY(5, 5); // TODO: Complete this method
        return 0;
    }
}
