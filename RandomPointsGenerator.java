import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;

public class RandomPointsGenerator {

    public static void main(String[] args) {
        List<double[]> points = generateRandomPoints();

        /*
        for (int i = 0; i < points.size(); i++) {
            double[] point = points.get(i);
            System.out.println("Point " + (i + 1) + ": (" + point[0] + ", " + point[1] + ")");
        }
        */

       try (FileWriter writer = new FileWriter("data/points.csv")) {
            for (double[] point : points) {
                writer.write(point[0] + "," + point[1] + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<double[]> generateRandomPoints() {
        List<double[]> points = new ArrayList<>();

        points.addAll(generatePointsInSquare(150, 0.8, 1.2, 0.8, 1.2));
        points.addAll(generatePointsInSquare(150, 0, 0.5, 0, 0.5));
        points.addAll(generatePointsInSquare(150, 1.5, 2, 0, 0.5));
        points.addAll(generatePointsInSquare(150, 0, 0.5, 1.5, 2));
        points.addAll(generatePointsInSquare(150, 1.5, 2, 1.5, 2));
        points.addAll(generatePointsInSquare(75, 0, 0.4, 0.8, 1.2));
        points.addAll(generatePointsInSquare(75, 1.6, 2, 0.8, 1.2));
        points.addAll(generatePointsInSquare(75, 0.8, 1.2, 0.3, 0.7));
        points.addAll(generatePointsInSquare(75, 0.8, 1.2, 1.3, 1.7));
        points.addAll(generatePointsInSquare(150, 0, 2, 0, 2));

        return points;
    }

    private static List<double[]> generatePointsInSquare(int count, double minX, double maxX, double minY, double maxY) {
        List<double[]> points = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < count; i++) {
            double x = minX + (maxX - minX) * random.nextDouble();
            double y = minY + (maxY - minY) * random.nextDouble();
            double[] point = new double[2];
            point[0] = x;
            point[1] = y;
            points.add(point);
        }

        return points;
    }
}
