package org.example;
import org.junit.jupiter.api.Test;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ImageProcessorTest {

    @Test
    public void testGetImage() {
        ImageProcessor imageProcessor = new ImageProcessor();
        URL resource = getClass().getClassLoader().getResource("bryson-tiller.png");
        assertNotNull(resource, "Die Datei wurde nicht gefunden");
        String absolutePath = new File(resource.getFile()).getAbsolutePath();
        BufferedImage image = imageProcessor.getImage(absolutePath);
        assertNotNull(image, "Das Bild konnte nicht geladen werden (ist null).");

        System.out.println("Bild wurde im Resources Ordner gefunden und erfolgreich geladen.");
    }


    @Test
    void extractPixels() {
        ImageProcessor imageProcessor = new ImageProcessor();

        URL resource = getClass().getClassLoader().getResource("bryson-tiller.png");
        assertNotNull(resource, "Bild fehlt ");
        String absolutePath = new File(resource.getFile()).getAbsolutePath();
        java.util.List<Pixel> pixelList = imageProcessor.extractPixels(absolutePath);
        assertNotNull(pixelList, "Pixel list darf nicht null sein");
        org.junit.jupiter.api.Assertions.assertFalse(pixelList.isEmpty(), "List muss pixel enthalten");
        Pixel firstPixel = pixelList.getFirst();
        System.out.println("Erster Pixel geladen R: " + firstPixel.red() + " G: " + firstPixel.green() + " B: " + firstPixel.blue());
    }
}

