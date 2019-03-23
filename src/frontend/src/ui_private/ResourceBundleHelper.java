package ui_private;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

/**
 * Assists in the process of getting information from properties files
 * @author Hunter Gregory
 */
public class ResourceBundleHelper {
    private ResourceBundle myResource;

    /**
     * Create a ResourceBundleHelper
     * @param propertyFile
     */
    public ResourceBundleHelper(String propertyFile) {
        myResource = ResourceBundle.getBundle(propertyFile);
    }

    /**
     * @param label
     * @return get the info associated with a String
     */
    public String getInfo(String label) {
        return myResource.getString(convertLabelToKey(label));
    }

    /**
     * @return an array Strings associated with each key
     */
    public String[] getAllLabels() {
        ArrayList<String> labels = new ArrayList<>();
        for(var key : Collections.list(myResource.getKeys())) {
            labels.add(convertKeyToLabel(key));
        }
        return labels.toArray(new String[0]);
    }

    /**
     * @param key
     * @return String associated with key
     */
    public String convertKeyToLabel(String key) {
        return key.replace('_', ' ');
    }

    private String convertLabelToKey(String label) {
        return label.replace(' ', '_');
    }
}
