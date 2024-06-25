import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

public class KMeans {
    private static final int M = 9;

    static class Point {
        double x;
        double y;
        int cluster;
        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        List<Point> data = readCSV("data/points.csv");
        
        List<Point> centroids = initializeCentroids(data);

        int t = 0; 

        while (true) {
            assignToClusters(data, centroids);
 
            List<Point> newCentroids = calculateCentroids(data, M);  

            if (centroidsEqual(centroids, newCentroids)) {
                break; 
            }
            centroids = newCentroids;

            t++;
        }
        System.out.println("The algorithm made : " + t + " iterations");
        saveClusters(data, "results/output_points.csv");
        saveCentroids(centroids, "results/output_centroids.csv");
        System.out.println("Clustering error is : " + clusteringError(data,centroids));

        //printCl(data);
        //printCe(centroids);

    }

     private static List<Point> readCSV(String fileName) {
        List<Point> data = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                String[] values = scanner.nextLine().split(",");
                double x = Double.parseDouble(values[0]);
                double y = Double.parseDouble(values[1]);
                data.add(new Point(x, y));
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }


      private static void saveClusters(List<Point> data, String fileName) {
        try (PrintWriter writer = new PrintWriter(new File(fileName))) {
            for (Point point : data) {
                writer.println(point.x + "," + point.y);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void saveCentroids(List<Point> centroids, String fileName) {
        try (PrintWriter writer = new PrintWriter(new File(fileName))) {
            for (int i = 0; i < centroids.size(); i++) {
                Point centroid = centroids.get(i);
                writer.println(centroid.x + "," + centroid.y);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    private static List<Point> initializeCentroids(List<Point> data) {
        List<Point> centroids = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < M; i++) {
            int randomNum = random.nextInt(data.size());
            centroids.add(new Point(data.get(randomNum).x, data.get(randomNum).y));
        }

        return centroids;
    }

    private static void assignToClusters(List<Point> data, List<Point> centroids) {
        for (Point point : data) {
            double minDistance = 10.0;
            int cluster = -1;

            for (int i = 0; i < M; i++) {
                double distance = euclideanDistance(point, centroids.get(i));
                if (distance < minDistance) {
                    minDistance = distance;
                    cluster = i;
                }
            }

            point.cluster = cluster;
        }
    }

    private static List<Point> calculateCentroids(List<Point> data, int M) {
        List<Point> centroids = new ArrayList<>();

        for (int j = 0; j < M; j++) {
            double sumX = 0;
            double sumY = 0;
            int size = 0;

            for (Point point : data) {
                if (point.cluster == j) {
                    sumX += point.x;
                    sumY += point.y;
                    size++;
                }
            }
            double cx = sumX / size;
            double cy = sumY / size;
            centroids.add(new Point(cx, cy));
        }

        return centroids;
    }

    private static double euclideanDistance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }

    private static boolean centroidsEqual(List<Point> c1, List<Point> c2) {

        for (int i = 0; i < c1.size(); i++) {
            if (euclideanDistance(c1.get(i), c2.get(i)) > 0.001) {
                return false;
            }
        }
        return true;
    }

    private static double clusteringError(List<Point> data, List<Point> centroids){
        double error = 0;
    
        for (int i = 0; i < data.size(); i++) {

            double distance = euclideanDistance(data.get(i), centroids.get(data.get(i).cluster));
            error += distance;
        }

        return error;
    }

    private static void printCl(List<Point> data) {
        for (Point point : data) {
            System.out.println("Point (" + point.x + ", " + point.y + ") belongs to Cluster " + (point.cluster+1));
        }
    }

    private static void printCe(List<Point> centroids) {
        for (int i = 0; i < centroids.size(); i++) {
            Point centroid = centroids.get(i);
            System.out.println("Centroid " + (i+1) + ": (" + centroid.x + ", " + centroid.y + ")");
        }
    }
}
