package ui_private.features;

import ui_private.features.color_choosers.BackgroundColorChooser;
import ui_private.features.color_choosers.PenColorChooser;
import ui_private.features.exceptions.NoFeatureException;
import ui_private.features.scrollable_windows.PastCommandsWindow;
import ui_private.features.selectors.LanguageSelector;
import ui_private.features.selectors.TurtleImageSelector;
import ui_public.UserCommandsWindow;
import ui_public.VariablesWindow;

public enum FeatureType {
    PEN_COLOR_CHOOSER(PenColorChooser.class),
    BACKGROUND_COLOR_CHOOSER(BackgroundColorChooser.class),
    LANGUAGE_SELECTOR(LanguageSelector.class),
    TURTLE_IMAGE_SELECTOR(TurtleImageSelector.class),
    PAST_COMMANDS_WINDOW(PastCommandsWindow.class),
    USER_COMMANDS_WINDOW(UserCommandsWindow.class),
    VARIABLES_WINDOW(VariablesWindow.class);


    private Class<? extends Feature> myClass;
    FeatureType(Class<? extends Feature> clazz) {
        myClass = clazz;
    }

    public Feature getFeature() throws NoFeatureException {
        try {
            Object feature = this.myClass.getDeclaredConstructor().newInstance();
            return (Feature) feature;
        }
        catch (Exception e) {
            throw new NoFeatureException();
        }
    }
}
