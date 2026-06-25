package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class BackgroundGenerationService {

    public BufferedImage generateBackground(ColorPalette palette, int width, int height) {

        BufferedImage miniImage = new BufferedImage(8, 8, BufferedImage.TYPE_INT_RGB);


        Graphics2D g2d = miniImage.createGraphics();


        int numberOfColors = palette.getDominantColors().size();
        double stripeWidth = 8.0 / numberOfColors;

        for (int i = 0; i < numberOfColors; i++) {
            Pixel pixel = palette.getDominantColors().get(i);
            g2d.setColor(new Color(pixel.red(), pixel.green(), pixel.blue()));
            int x = (int) (i * stripeWidth);
            int w = (int) Math.ceil(stripeWidth);
            g2d.fillRect(x, 0, w, 8);
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