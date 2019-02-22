package control.frontendapi;

public class PenDownCall extends FrontendAPICall {
    @Override
    public double call() {
        ui.setPenIsDown(true);
        return 1;
    }
}
