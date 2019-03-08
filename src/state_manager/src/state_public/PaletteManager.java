package state_public;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PaletteManager {

    public static final List<Palette> defaultPalettes = Collections.unmodifiableList(
        new ArrayList<>() {{
            add(new Palette(1, Color.LIGHTPINK));
            add(new Palette(2, Color.LIGHTBLUE));
            add(new Palette(3, Color.LIGHTGREEN));
            add(new Palette(4, Color.WHITE));
            add(new Palette(5, Color.BLACK));
        }}
    );

    public List<String> getColorNames() {
        ArrayList<String> names = new ArrayList<>();
        for (Palette p : defaultPalettes) {
            names.add(p.getId() + " " + p.getColor());
        }
        return names;
    }

    private List<Palette> myPalettes;

    public PaletteManager() {
        myPalettes = new ArrayList<>();
        myPalettes.addAll(defaultPalettes);
    }

    public Palette getDefaultBackgroundColor() {
        return myPalettes.get(0);
    }

    public Palette getDefaultPenColor() {
        return myPalettes.get(1);
    }

    public Palette getPalette(int index) {
        for (Palette palette: myPalettes) {
            if (palette.getId() == index) {
                return palette;
            }
        }
        return myPalettes.get(0);
    }

    public void setPalette(int index, int red, int green, int blue) {
        myPalettes.add(new Palette(index, red, green, blue));
    }
}
