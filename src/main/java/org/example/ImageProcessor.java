package org.example;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImageProcessor {



//Hier ist der Pfad zum Bild, gib mir die Pixel.
    public BufferedImage getImage(String imagePath){
        BufferedImage image = null;
        try {
            File inputFile = new File(imagePath);
             image = ImageIO.read(inputFile);

            if (image == null){
                System.out.println("Format wird nicht unterstützt");

            } else {
                System.out.println("Bild erfolgreich geladen");
            }
        } catch(IOException e){
            System.out.println("Fehler beim Lesen/Schreiben: " + e.getMessage());
        }
        return image;
    }


    //Alle pixel die aus dem bild extrahiert wurden
    public List<Pixel> extractPixels(String imagePath){
        BufferedImage image = getImage(imagePath);

        List<Pixel> pixelList = new ArrayList<>();

        if(image == null) return new  ArrayList<>();


        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight() ; y++) {

                int rgb = image.getRGB(x,y);
                Color color = new Color(rgb);
                int r = color.getRed();
                int g = color.getGreen();
                int b = color.getBlue();

                // 1. Helligkeit und Sättigung berechnen
                float[] hsb = Color.RGBtoHSB(r, g, b, null);
                float saturation = hsb[1];
                float brightness = hsb[2];

                //  Zu dunkle zu helle graue Pixel überspringen
                if (brightness < 0.15f || brightness > 0.85f) continue;
                if (saturation < 0.15f) continue;

                Pixel pixel = new Pixel(r,g,b);
                pixelList.add(pixel);

            }
        }
        return pixelList;
    }
}
