package org.example;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
    static void main() {
        try {
            String imagePath = "src/main/resources/nba-youngboy.jpeg";

            ImageProcessor processor = new ImageProcessor();

            List<Pixel> allPixels = processor.extractPixels(imagePath);

            if (allPixels.isEmpty()) {
                System.out.println(" Keine Pixel extrahiert Pfad oder Format falsch");
                return;
            }


            ColorClusteringService clusteringService = new ColorClusteringService();
            ColorPalette palette = clusteringService.calculatePalette(allPixels, 4);

            BackgroundGenerationService backgroundService = new BackgroundGenerationService();
            BufferedImage background = backgroundService.generateBackground(palette, 1920, 1080);


            File outputFile = new File(imagePath + "background.png");
            ImageIO.write(background, "png", outputFile);

            System.out.println("Hintergrund wurde gespeichert als: " + outputFile.getAbsolutePath());

        } catch (IOException e) {
            System.err.println("Fehler beim Speichern des Hintergrundbildes: " + e.getMessage());

        }
    }
}
