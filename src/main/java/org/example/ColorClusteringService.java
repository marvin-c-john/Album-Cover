package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ColorClusteringService {


    public ColorPalette calculatePalette(List<Pixel> allPixels, int numberOfColors) {
        List<Cluster> clusterList = new ArrayList<>();
        ColorClusteringService colorClusteringService = new ColorClusteringService();

        Random random = new Random();


            // random pixel aus der liste kriegen und cluster erstellen
            for (int j = 0; j < numberOfColors; j++) {
                int randomIndex = random.nextInt(allPixels.size());
                Pixel pixel = allPixels.get(randomIndex);
                Cluster clusterCentroid = new Cluster(pixel);
                clusterList.add(clusterCentroid);
            }

        for (int i = 0; i < 10; i++) {

            if (i > 0){
                for (int j = 0; j < clusterList.size(); j++) {
                    clusterList.get(j).clear();
                }

            }
            // misst den abstand zwischen pixel und centroid und der am nächsten ist, wird zum cluster hinzugefügt
            for(Pixel p: allPixels){
                double minDistance = Double.MAX_VALUE;
                int clusterIndex = 0;

                for (int j = 0; j < clusterList.size(); j++) {
                   Pixel centroid = clusterList.get(j).getCentroid();
                   double newDistance = colorClusteringService.calculateDistance(p, centroid);


                       if(newDistance < minDistance ){
                           minDistance = newDistance;
                           clusterIndex = j;
                       }
                }
                clusterList.get(clusterIndex).addPixel(p);
            }


            //
            for (int j = 0; j < clusterList.size(); j++) {
                clusterList.get(j).updateCentroid();
            }

        }

        List<Pixel>  resultColors = new ArrayList<>();

        for (int i = 0; i < clusterList.size(); i++) {
           Pixel color = clusterList.get(i).getCentroid();
           resultColors.add(color);
        }
        ColorPalette colorPalette = new ColorPalette(resultColors);
        return colorPalette;

    }

    // Berechnet den Abstand zwischen zwei Pixeln
    public double calculateDistance (Pixel p1, Pixel p2){
        double red = Math.pow((p1.red() - p2.red()), 2);
        double green = Math.pow((p1.green() - p2.green()), 2);
        double blue = Math.pow((p1.blue() - p2.blue()), 2);

        return Math.sqrt(red + green + blue);
    }
}
