package control;

import frontend.UIMain;

abstract public class FrontendAPICall extends APICall {

    protected UIMain ui;

    public FrontendAPICall() {
        this.ui = UIMain.getInstance();
    }
}
