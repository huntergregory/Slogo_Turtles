package control.backendapi;

import parser.ParserException;

public abstract class BackendAPICall {

    public abstract Object call() throws ParserException;
}
