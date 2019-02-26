package ui_public.api.turtle_visibility_calls;

import ui_public.api.FrontendAPICall;

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
