package control.frontendapi.turtle_visibility_calls;

import control.frontendapi.FrontendAPICall;

public abstract class VisibilityCall extends FrontendAPICall {
    private boolean myWillShow;

    public VisibilityCall(boolean show) {
        myWillShow = show;
    }

    @Override
    public double call() {
        ui.setTurtleIsShowing(myWillShow);
        return myWillShow ? 1 : 0;
    }
}
