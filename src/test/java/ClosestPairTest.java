import org.junit.jupiter.api.Test;
import java.awt.Point;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClosestPairTest {

    @Test
    void testClosestPair() {
        Metrics metrics = new Metrics();
        ClosestPair cp = new ClosestPair(metrics);

        Point[] points = {
                new Point(2, 3),
                new Point(12, 30),
                new Point(40, 50),
                new Point(5, 1),
                new Point(12, 10),
                new Point(3, 4)
        };

        double expected = Math.hypot(2 - 3, 3 - 4);
        double actual = cp.closestPair(points);
        assertEquals(expected, actual, 0.0001);

        Random rand = new Random();
        for (int t = 0; t < 100; t++) {
            int n = 100 + rand.nextInt(1900);
            Point[] pts = new Point[n];
            for (int i = 0; i < n; i++) {
                pts[i] = new Point(rand.nextInt(10000), rand.nextInt(10000));
            }
            double result = cp.closestPair(pts);
            assert(result >= 0);
        }
    }
}