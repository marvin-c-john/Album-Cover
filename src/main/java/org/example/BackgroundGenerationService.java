package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class BackgroundGenerationService {

    public BufferedImage generateBackground(ColorPalette palette, int width, int height) {

        BufferedImage miniImage = new BufferedImage(8, 8, BufferedImage.TYPE_INT_RGB);

        Graphics2D g2d = miniImage.createGraphics();

        // Nur das Nötigste geändert: Schleife weg, erste dominante Farbe geholt
        if (palette.getDominantColors() != null && !palette.getDominantColors().isEmpty()) {
            Pixel pixel = palette.getDominantColors().get(0); // Index 0 ist die stärkste Farbe
            g2d.setColor(new Color(pixel.red(), pixel.green(), pixel.blue()));
            g2d.fillRect(0, 0, 8, 8); // Das komplette Mini-Bild damit füllen
        }

        g2d.dispose();

        BufferedImage backgroundImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D gLarge = backgroundImage.createGraphics();
        gLarge.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        gLarge.drawImage(miniImage, 0, 0, width, height, null);
        gLarge.dispose();

        return backgroundImage;
    }
}