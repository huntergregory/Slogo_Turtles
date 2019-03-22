package state;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class that manages both default and user defined palettes
 * @author David Miron
 * @author Harry Ross
 */
public class PaletteManager {

    private static final List<Palette> defaultPalettes = Collections.unmodifiableList(
        new ArrayList<>() {{
            add(new Palette(1, Color.LIGHTPINK));
            add(new Palette(2, Color.LIGHTBLUE));
            add(new Palette(3, Color.LIGHTGREEN));
            add(new Palette(4, Color.WHITE));
            add(new Palette(5, Color.BLACK));
        }}
    );

    private List<Palette> myPalettes;
    private ObservableList<String> myPaletteStrings = FXCollections.observableList(new ArrayList<>());

    /**
     * Creates new instance of PaletteManager, initializes palette list and ObservableList
     */
    public PaletteManager() {
        myPalettes = new ArrayList<>();
        myPalettes.addAll(defaultPalettes);
        for (Palette p : defaultPalettes) {
            myPaletteStrings.add(p.toString());
        }
    }

    /**
     * Sets given index to new palette with given RGB values
     * @param index Index of new palette
     * @param red Red value
     * @param green Green value
     * @param blue Blue value
     */
    public void setPalette(int index, int red, int green, int blue) {
        Palette newPalette = new Palette(index, red, green, blue);
        myPalettes.add(newPalette);
        myPaletteStrings.add(newPalette.toString());
    }

    Palette getDefaultBackgroundColor() {
        return myPalettes.get(0);
    }

    Palette getDefaultPenColor() {
        return myPalettes.get(1);
    }

    /**
     * Gets Palette of specified index, if not found return first default palette
     * @param index Index of palette requested
     * @return Palette that corresponds with given index
     */
    public Palette getPalette(int index) {
        for (Palette palette: myPalettes) {
            if (palette.getId() == index) {
                return palette;
            }
        }
        return myPalettes.get(0);
    }

    /**
     * Returns ObservableList of defined Palettes
     * @return Defined Palettes
     */
    public ObservableList<String> getPaletteList() {
        return myPaletteStrings;
    }
}
