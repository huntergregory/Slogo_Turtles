package ui_private.features.selectors;

import javafx.collections.ObservableList;
import state_public.StateManager;
import ui_private.displays.CommandTerminal;

public class TurtleImageSelector extends Selector {
    private static final String TITLE = "Turtle Image";
    private static final String[] IMAGE_NAMES = {"tan_turtle.png"}; //TODO: include names
    //private ObservableList IMAGES = constructList(); //can't be static because getClass can't be in a static method
//FIXME
    /*private ObservableList constructList() {
        ArrayList<Image> images = new ArrayList<>();
        for (String imageName : IMAGE_NAMES) {
            InputStream stream = getClass().getClassLoader().getResourceAsStream(imageName);
            if (stream != null)
                images.add(new Image(stream));
        }
        return FXCollections.observableArrayList(images);
    }*/

    public TurtleImageSelector(StateManager manager) {
        super(manager);
    }

    @Override
    protected ObservableList getItemList() {
        return null;
    }

    @Override
    protected void handleItemSelected(String item) {
        System.out.println("here");
        //TurtleManager.getInstance().setTurtleImage(item);   FIXME
    }

    @Override
    public void setCommandTerminal(CommandTerminal terminal) {
        myCommandTerminal = terminal;
    }

    @Override
    protected String getLabelText() {
        return TITLE;
    }
}
