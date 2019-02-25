package frontendapi.set_pen_calls;

import frontendapi.FrontendAPICall;

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
