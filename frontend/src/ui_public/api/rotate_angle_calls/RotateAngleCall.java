package ui_public.api.rotate_angle_calls;

import ui_public.api.FrontendAPICall;

public abstract class RotateAngleCall extends FrontendAPICall {
    private double myAngle;

    protected RotateAngleCall(double angle) {
        myAngle = angle;
    }

    @Override
    public double call() {
        ui.setHeading(myAngle + ui.getHeading());
        return Math.abs(myAngle);
    }
}
