package ui_public.api.set_pen_calls;

import ui_public.api.FrontendAPICall;

public class SetPenCall extends FrontendAPICall {
    private boolean myWillBeDown;

    protected SetPenCall(boolean down) {
        myWillBeDown = down;
    }

    @Override
    public double call() {
        ui.setPenIsDown(myWillBeDown);
        return myWillBeDown ? 1 : 0;
    }
}
