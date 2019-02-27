package frontendapi;

public class SetHeadingCall extends FrontendAPICall {
    private double myNewHeading;

    public SetHeadingCall(double newHeading) {
        myNewHeading = newHeading;
    }

    @Override
    public double call() {
        //double oldHeading = ui.getHeading();
        //ui.setHeading(myNewHeading);
        return 0; //Math.abs(myNewHeading - oldHeading);
    }
}
