package ui_private.features;

import ui_private.features.exceptions.NoFeatureException;
import ui_private.features.scrollable_windows.TurtleStateWindow;
import ui_private.features.scrollable_windows.VariablesWindow;
import ui_private.features.selectors.BackgroundColorSelector;
import ui_private.features.selectors.LanguageSelector;
import ui_private.features.selectors.MoveTurtleSelector;
import ui_private.features.selectors.PastCommandsSelector;
import ui_private.features.selectors.PenColorSelector;
import ui_private.features.selectors.PenUpDownSelector;
import ui_private.features.selectors.TurtleImageSelector;
import ui_private.features.selectors.UserCommandsSelector;
import ui_private.features.sliders.PenThicknessSlider;

//import ui_private.features.scrollable_windows.PastCommandsWindow;
//import ui_public.UserCommandsWindow;

public enum FeatureType {
    PEN_COLOR_CHOOSER(PenColorSelector.class),
    BACKGROUND_COLOR_CHOOSER(BackgroundColorSelector.class),
    LANGUAGE_SELECTOR(LanguageSelector.class),
    TURTLE_IMAGE_SELECTOR(TurtleImageSelector.class),
    //PAST_COMMANDS_WINDOW(PastCommandsWindow.class),
    PAST_COMMANDS_SELECTOR(PastCommandsSelector.class),
    //USER_COMMANDS_WINDOW(UserCommandsWindow.class),
    USER_COMMANDS_SELECTOR(UserCommandsSelector.class),
    MOVE_TURTLE_SELECTOR(MoveTurtleSelector.class),
    PEN_UPDOWN_SELECTOR(PenUpDownSelector.class),
    VARIABLES_WINDOW(VariablesWindow.class),
    TURTLESTATE_WINDOW(TurtleStateWindow.class),
    PEN_THICKNESS_SLIDER(PenThicknessSlider.class);




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
