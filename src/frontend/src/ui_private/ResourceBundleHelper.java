package ui_private;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

/**
 *
 * @author Hunter Gregory
 */
public class ResourceBundleHelper {
    private ResourceBundle myResource;

    public ResourceBundleHelper(String propertyFile) {
        myResource = ResourceBundle.getBundle(propertyFile);
    }

    public String getInfo(String label) {
        return myResource.getString(convertLabelToKey(label));
    }

    public String[] getAllLabels() {
        ArrayList<String> labels = new ArrayList<>();
        for(var key : Collections.list(myResource.getKeys())) {
            labels.add(convertKeyToLabel(key));
        }
        return labels.toArray(new String[0]);
    }

    public String convertKeyToLabel(String key) {
        return key.replace('_', ' ');
    }

    private String convertLabelToKey(String label) {
        return label.replace(' ', '_');
    }
}
