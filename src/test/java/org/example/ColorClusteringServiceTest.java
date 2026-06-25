package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ColorClusteringServiceTest {

    @Test
    void testCalculateDistance_IdenticalPixels_ReturnsZero() {
        ColorClusteringService service = new ColorClusteringService();

        Pixel p1 = new Pixel(100, 150, 200);
        Pixel p2 = new Pixel(100, 150, 200);

        double distance = service.calculateDistance(p1, p2);

        // Distance between identical colors must be exactly 0.0
        assertEquals(0.0, distance, 0.001, "Distance between identical pixels should be 0");
    }

    @Test
    void testCalculateDistance_DifferentPixels_ReturnsCorrectDistance() {
        ColorClusteringService service = new ColorClusteringService();

        // Math check:
        // Red diff:   30 - 0 = 30  -> 30^2 = 900
        // Green diff: 40 - 0 = 40  -> 40^2 = 1600
        // Blue diff:  0 - 0  = 0   -> 0^2  = 0
        // Sum: 900 + 1600 = 2500 -> sqrt(2500) = 50.0
        Pixel p1 = new Pixel(30, 40, 0);
        Pixel p2 = new Pixel(0, 0, 0);

        double distance = service.calculateDistance(p1, p2);

        assertEquals(50.0, distance, 0.001, "Distance calculation is mathematically incorrect");
        System.out.println("[OK] Distance calculation verified: " + distance);
    }

    @Test
    void calculatePalette() {
        Pixel black1 = new Pixel(0,0,0);
        Pixel black2 = new Pixel(0,0,0);
        Pixel white1 = new Pixel(255,255,255);
        Pixel white2 = new Pixel(255,255,255);

        List<Pixel> allPixels = new ArrayList<>();
        allPixels.add(black1);
        allPixels.add(black2);
        allPixels.add(white1);
        allPixels.add(white2);

        ColorClusteringService colorClusteringService = new ColorClusteringService();
        ColorPalette palatte = colorClusteringService.calculatePalette(allPixels, 2);
        int palatteSize = palatte.getDominantColors().size();
        assertEquals(2, palatteSize);

        List<Pixel> resultColors = palatte.getDominantColors();


        Pixel expectedBlack = new Pixel(0, 0, 0);
        Pixel expectedWhite = new Pixel(255, 255, 255);

        assertTrue(resultColors.contains(expectedBlack), "Die Palette sollte Schwarz enthalten");
        assertTrue(resultColors.contains(expectedWhite), "Die Palette sollte Weiß enthalten");

    }
}