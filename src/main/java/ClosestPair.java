import java.awt.Point;
import java.util.Arrays;
import java.util.Comparator;

public class ClosestPair {
    private final Metrics metrics;

    public ClosestPair(Metrics metrics) {
        this.metrics = metrics;
    }

    public double closestPair(Point[] points) {
        Point[] pointsSortedByX = points.clone();
        Arrays.sort(pointsSortedByX, Comparator.comparingInt(p -> p.x));
        Point[] pointsSortedByY = points.clone();
        Arrays.sort(pointsSortedByY, Comparator.comparingInt(p -> p.y));
        return closestPairRecursive(pointsSortedByX, pointsSortedByY, 0, points.length - 1);
    }

    private double closestPairRecursive(Point[] px, Point[] py, int left, int right) {
        metrics.enterRecursion();
        if (right - left <= 3) {
            double minDist = Double.MAX_VALUE;
            for (int i = left; i <= right; i++) {
                for (int j = i + 1; j <= right; j++) {
                    double dist = distance(px[i], px[j]);
                    if (dist < minDist) {
                        minDist = dist;
                    }
                    metrics.incrementComparisons();
                }
            }
            metrics.exitRecursion();
            return minDist;
        }
        int mid = (left + right) / 2;
        Point midPoint = px[mid];

        Point[] pyl = new Point[mid - left + 1];
        Point[] pyr = new Point[right - mid];
        int li = 0, ri = 0;
        for (Point p : py) {
            if (p.x <= midPoint.x) {
                pyl[li++] = p;
            } else {
                pyr[ri++] = p;
            }
        }

        double dl = closestPairRecursive(px, pyl, left, mid);
        double dr = closestPairRecursive(px, pyr, mid + 1, right);
        double d = Math.min(dl, dr);

        Point[] strip = new Point[right - left + 1];
        int stripCount = 0;
        for (Point p : py) {
            if (Math.abs(p.x - midPoint.x) < d) {
                strip[stripCount++] = p;
            }
            metrics.incrementComparisons();
        }

        double minStripDistance = closestStripDistance(strip, stripCount, d);
        metrics.exitRecursion();

        return Math.min(d, minStripDistance);
    }

    private double closestStripDistance(Point[] strip, int size, double d) {
        double min = d;
        for (int i = 0; i < size; ++i) {
            for (int j = i + 1; j < size && (strip[j].y - strip[i].y) < min; ++j) {
                double dist = distance(strip[i], strip[j]);
                if (dist < min) {
                    min = dist;
                }
                metrics.incrementComparisons();
            }
        }
        return min;
    }

    private double distance(Point p1, Point p2) {
        return Math.hypot(p1.x - p2.x, p1.y - p2.y);
    }
}