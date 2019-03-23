package ui_private;

import state.StateManager;
import ui_private.displays.CommandTerminal;
import ui_private.features.Feature;
import ui_private.features.exceptions.NoFeatureException;

/**
 * Creates Features based on their names.
 * @author Hunter Gregory
 */
public class FeatureFactory {
    private static final String FEATURE_PROPERTIES = "features";
    private static final String FEATURE_PATH = "ui_private.features.";

    private StateManager myStateManager;
    private CommandTerminal myCommandTerminal;
    private ResourceBundleHelper myResourceHelper;

    /**
     * Create a FeatureFactory
     * @param manager
     * @param terminal
     */
    public FeatureFactory(StateManager manager, CommandTerminal terminal) {
        myStateManager = manager;
        myCommandTerminal = terminal;
        myResourceHelper = new ResourceBundleHelper(FEATURE_PROPERTIES);
    }

    /**
     * @param label
     * @return a Feature with the given name
     * @throws NoFeatureException
     */
    public Feature getFeature(String label) throws NoFeatureException {
        try {
            var clazz = Class.forName(FEATURE_PATH + myResourceHelper.getInfo(label));
            var feature = (Feature) clazz.getDeclaredConstructor(StateManager.class).newInstance(myStateManager);

            feature.setCommandTerminal(myCommandTerminal);

            feature.setLabelText(label);
            return feature;
        }
        catch (Exception e) {
            throw new NoFeatureException();
        }
    }

    /**
     * @return an array of all the possible Feature names
     */
    public String[] getFeatureNames() {
        return myResourceHelper.getAllLabels();
    }
}
