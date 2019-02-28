package ui_private.features;

public enum FeatureType {
    PEN_COLOR("selectors.PenColorSelector");


    private String myClassName;
    FeatureType(String className) {
        myClassName = "java.lang.frontend.src.ui_private.features." + className;
    }

    public String getClassName() {
        return myClassName;
    }
}
