import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private LineSegment [] segments;
    public BruteCollinearPoints(Point[] points) {
        if (points == null) throw new java.lang.NullPointerException("null argument to constructor");
        checkNullEntries(points);
        ArrayList<LineSegment> segmentsList = new ArrayList<LineSegment>();
        Point[] pointsCopy = Arrays.copyOf(points, points.length);
        Arrays.sort(pointsCopy);
        checkDuplicatedEntries(pointsCopy);
        for (int i = 0; i < (pointsCopy.length - 3); ++i)
            for (int j = i + 1; j < (pointsCopy.length - 2); ++j)
                for (int k = j + 1; k < (pointsCopy.length - 1); ++k)
                    for (int l = k + 1; l < (pointsCopy.length); ++l) {
                        if (pointsCopy[i].slopeTo(pointsCopy[j]) == pointsCopy[i].slopeTo(pointsCopy[l]) &&
                                pointsCopy[i].slopeTo(pointsCopy[j]) == pointsCopy[i].slopeTo(pointsCopy[k])) {
                            LineSegment tempLineSegment = new LineSegment(pointsCopy[i], pointsCopy[l]);
                            if (!segmentsList.contains(tempLineSegment))
                                segmentsList.add(tempLineSegment);
                        }
                    }
        segments = segmentsList.toArray(new LineSegment[segmentsList.size()]);
    }
    public int numberOfSegments()   {
        return segments.length;
    }
    public LineSegment[] segments()   {
        return segments;
    }
    
    private void checkDuplicatedEntries(Point[] points) {
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i].compareTo(points[i + 1]) == 0) {
                throw new IllegalArgumentException("Duplicated entries in given points");
            }
        }
    }

    private void checkNullEntries(Point[] points) {
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i] == null) {
                throw new java.lang.NullPointerException("One of the point in points array is null");
            }
        }
    }
}
