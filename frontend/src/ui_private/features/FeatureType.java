package ui_private.features;

import ui_private.features.exceptions.NoFeatureException;

public enum FeatureType {
    PEN_COLOR_CHOOSER("color_choosers.PenColorChooser"),
    BACKGROUND_COLOR_CHOOSER("color_choosers.BackgroundColorChooser"),
    LANGUAGE_SELECTOR("selectors.LanguageSelector"),
    TURTLE_IMAGE_SELECTOR("selectors.TurtleImageSelector"),
    PAST_COMMANDS_WINDOW("scrollable_windows.PastCommandsWindow"),
    USER_COMMANDS_WINDOW("scrollable_windows.UserCommandsWindow"),
    VARIABLES_WINDOW("scrollable_windows.VariablesWindow");

    private String myClassName;
    FeatureType(String className) {
        myClassName = "ui_private.features." + className;
    }

    public Feature getFeature() throws NoFeatureException {
        try {
            var featureClass = Class.forName(this.myClassName);
            Object feature = featureClass.getDeclaredConstructor().newInstance();
            return (Feature) feature;
        }
        catch (Exception e) {
            throw new NoFeatureException();
        }
    }
}
