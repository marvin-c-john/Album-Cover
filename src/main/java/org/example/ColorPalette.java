package org.example;

import java.util.List;

public class ColorPalette {

    private List<Pixel> dominantColors;

    public ColorPalette(List<Pixel> dominantColors) {
        this.dominantColors = dominantColors;
    }


    public List<Pixel> getDominantColors() {
        return dominantColors;
    }
}
