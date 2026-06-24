package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

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
}