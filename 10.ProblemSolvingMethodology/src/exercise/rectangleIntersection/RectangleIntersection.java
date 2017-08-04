package exercise.rectangleIntersection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class RectangleIntersection {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        List<Rectangle> rectangles = new ArrayList<>();
        List<Rectangle> intersectingAreas = new ArrayList<>();
        Set<Rectangle> polyintersectingAreas = new TreeSet<>();

        for (int i = 0; i < n; i++) {
            String[] inputTokens = reader.readLine().split("\\s+");
            Rectangle rectangle = new Rectangle(Integer.parseInt(inputTokens[0]), Integer.parseInt(inputTokens[1]),
                    Integer.parseInt(inputTokens[2]), Integer.parseInt(inputTokens[3]));
            rectangles.add(rectangle);
        }

        long totalIntersectingArea = 0;
        for (int i = 0; i < rectangles.size(); i++) {
            for (int j = i + 1; j < rectangles.size(); j++) {
                Rectangle rect = rectangles.get(i).intersectionArea(rectangles.get(j));
                if (rect != null) {
                    intersectingAreas.add(rect);
                    totalIntersectingArea += rect.getArea();
                }
            }
        }

        for (int i = 0; i < intersectingAreas.size(); i++) {
            for (int j = i + 1; j < intersectingAreas.size(); j++) {
                Rectangle rect = intersectingAreas.get(i).intersectionArea(intersectingAreas.get(j));
                if (rect != null) {
                    if (!polyintersectingAreas.add(rect)) {
                        totalIntersectingArea -= rect.getArea();
                    }
                }
            }
        }

        System.out.println(totalIntersectingArea);
    }
}
