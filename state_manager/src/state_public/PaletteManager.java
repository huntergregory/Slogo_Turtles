package state_public;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PaletteManager {

    public static final List<Palette> defaultPalettes = Collections.unmodifiableList(
        new ArrayList<>() {{
            add(new Palette(Color.WHITE));
            add(new Palette(Color.DARKGREEN));
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
}
