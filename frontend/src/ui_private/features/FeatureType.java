package ui_private.features;

public enum FeatureType {
    PEN_COLOR_CHOOSER("color_choosers.PenColorChooser");


    private String myClassName;
    FeatureType(String className) {
        myClassName = "ui_private.features." + className;
    }

    public String getClassName() {
        return myClassName;
    }
}
