package org.example;

import java.util.ArrayList;
import java.util.List;

public class Cluster {

    public Pixel getCentroid() {
        return centroid;
    }

    private Pixel centroid;
    private List<Pixel> assignedPixels;

    public Cluster(Pixel centroid) {
        this.centroid = centroid;
        this.assignedPixels = new ArrayList<>();
    }

    public void addPixel(Pixel p){
        assignedPixels.add(p);
    }

    public void clear(){
        assignedPixels.clear();
    }

    public void updateCentroid(){
        if(assignedPixels.isEmpty()){
            return;
        } else {

            int sumR = 0;
            int sumG = 0;
            int sumB = 0;



            for (int i = 0; i < assignedPixels.size(); i++) {
                Pixel pixel = assignedPixels.get(i);
                int r = pixel.red();
                int g = pixel.green();
                int b = pixel.blue();



                sumR += r;
                sumG += g;
                sumB += b;

            }
                int totalPixels = assignedPixels.size();
                int avgR = sumR / totalPixels;
                int avgG = sumG / totalPixels;
                int avgB = sumB / totalPixels;

                this.centroid = new Pixel(avgR, avgG, avgB);

        }
    }
}
