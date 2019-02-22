package control.frontendapi;

public class PenUpCall extends FrontendAPICall {
    @Override
    public double call() {
        ui.setPenIsDown(false);
        return 0;
    }
}
