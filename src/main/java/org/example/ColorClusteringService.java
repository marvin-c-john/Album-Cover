package org.example;

import java.util.List;

public class ColorClusteringService {


//    public ColorPalette calculatePalette(List<Pixel> allPixels, int numberOfColors){
//        // return ColorPalette objekt
//    }

    // Berechnet den Abstand zwischen zwei Pixeln
    public double calculateDistance(Pixel p1, Pixel p2) {
       double red = Math.pow((p1.red() - p2.red()), 2);
       double green = Math.pow((p1.green() - p2.green()), 2);
       double blue = Math.pow((p1.blue() - p2.blue()), 2);

      return Math.sqrt(red + green + blue);
    }
}
