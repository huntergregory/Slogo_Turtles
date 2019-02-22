package control.frontendapi.rotate_angle_calls;

import control.frontendapi.FrontendAPICall;

public abstract class RotateAngleCall extends FrontendAPICall {
    private double myAngle;

    public RotateAngleCall(double angle) {
        myAngle = angle;
    }

    @Override
    public double call() {
        ui.setHeading(myAngle + ui.getHeading());
        return Math.abs(myAngle);
    }
}
