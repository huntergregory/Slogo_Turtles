package ui_private;

import state_public.StateManager;
import ui_private.displays.CommandTerminal;
import ui_private.features.Feature;
import ui_private.features.exceptions.NoFeatureException;
import ui_private.features.selectors.Selector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class FeatureFactory {
    private static final String FEATURE_PROPERTIES = "features";
    private static final String FEATURE_PATH = "ui_private.features.";

    private StateManager myStateManager;
    private CommandTerminal myCommandTerminal;

    public FeatureFactory(StateManager manager, CommandTerminal terminal) {
        myStateManager = manager;
        myCommandTerminal = terminal;
    }

    public Feature getFeature(String label) throws NoFeatureException {
        try {
            var resource = getResource();
            String key = convertLabelToKey(label);
            var clazz = Class.forName(FEATURE_PATH + resource.getString(key));
            var feature = (Feature) clazz.getDeclaredConstructor(StateManager.class).newInstance(myStateManager);
            System.out.println("got feature");

            if (feature instanceof Selector) {
                ((Selector) feature).setCommandTerminal(myCommandTerminal); //TODO
            }
            return feature;
        }
        catch (Exception e) {
            throw new NoFeatureException();
        }
    }


    public String[] getFeatureNames() {
        var resource = getResource();
        ArrayList<String> features = new ArrayList<>();
        for(var key : Collections.list(resource.getKeys())) {
            features.add(convertKeyToLabel(key));
        }
        return features.toArray(new String[0]);
    }


    private ResourceBundle getResource() {
        return ResourceBundle.getBundle(FEATURE_PROPERTIES);
    }

    private String convertKeyToLabel(String key) {
        return key.replace('_', ' ');
    }

    private String convertLabelToKey(String label) {
        return label.replace(' ', '_');
    }
}
