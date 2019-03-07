package state_public;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PaletteManager {

    public static final List<Palette> defaultPalettes = Collections.unmodifiableList(
        new ArrayList<>() {{
            add(new Palette(0, Color.WHITE));
            add(new Palette(1, Color.DARKGREEN));
        }}
    );

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
            if (palette.getId() == index)
                return palette;
        }
        return myPalettes.get(0);
    }

    public void setPalette(int index, int red, int green, int blue) {
        myPalettes.add(new Palette(index, red, green, blue));
    }
}
