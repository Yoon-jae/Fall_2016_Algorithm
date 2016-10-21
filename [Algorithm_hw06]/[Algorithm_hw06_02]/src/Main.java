import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by yoonjae on 10/21/16.
 */
public class Main {

    public static ArrayList<Point> points = new ArrayList<>(1000001);

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("data07_closest.txt"));
        String line;

        while ((line = br.readLine()) != null) {
            StringTokenizer stk = new StringTokenizer(line, ", ");
            points.add(new Point(Double.parseDouble(stk.nextToken()), Double.parseDouble(stk.nextToken())));
        }

        points.sort((p1, p2) -> Double.compare(p1.x, p2.x));

        double euclideanDistance = closestPair(0, points.size() - 1);

        System.out.printf("Euclidean distance : %.3f\n", euclideanDistance);
    }

    /**
     * Find closest pair using Divide and Conquer
     *
     * @param left
     * @param right
     * @return
     */
    private static double closestPair(int left, int right) {
        double midValueOfX, min1, min2, minValue = Double.MAX_VALUE;
        ArrayList<Point> candidate = new ArrayList<>();

        // Brute force part when size is less than 3
        if (right - left + 1 <= 3) {
            for (int i = left; i <= right; i++)
                for (int j = i + 1; j <= right; j++)
                    minValue = Math.min(minValue, points.get(i).getDistance(points.get(j)));
            return minValue;
        }

        if (left < right) {
            int mid = (left + right) / 2;
            midValueOfX = points.get(mid).x;
            min1 = closestPair(left, mid);
            min2 = closestPair(mid + 1, right);
            minValue = Math.min(min1, min2);

            // Get candidate from between mid value - minValue and mid value + minValue
            for (int i = left; i <= right; i++) {
                if (midValueOfX - minValue <= points.get(i).x && points.get(i).x <= midValueOfX + minValue)
                    candidate.add(points.get(i));
            }

            candidate.sort((p1, p2) -> Double.compare(p1.y, p2.y));

            // Check merge part to find minValue
            for (int i = 0; i < candidate.size(); i++) {
                for (int j = i + 1; j < candidate.size(); j++) {
                    if (candidate.get(i).y + minValue >= candidate.get(j).y)
                        minValue = Math.min(minValue, candidate.get(i).getDistance(candidate.get(j)));
                    else break;
                }
            }
        }

        return minValue;
    }

}

class Point {
    double x;
    double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getDistance(Point p) {
        return Math.sqrt(Math.pow(this.x - p.x, 2) + Math.pow(this.y - p.y, 2));
    }
}