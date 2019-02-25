package frontendapi;

import frontend.UIMain;

abstract public class FrontendAPICall {

    protected UIMain ui;

    public FrontendAPICall() {
        this.ui = UIMain.getInstance();
    }

    public abstract double call();
}
