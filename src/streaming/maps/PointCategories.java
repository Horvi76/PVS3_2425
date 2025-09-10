package streaming.maps;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PointCategories {

    static class Point{
        double x,y;
        String pointID;

        public Point(double x, double y, String pointID) {
            this.x = x;
            this.y = y;
            this.pointID = pointID;
        }

        @Override
        public String toString() {
            return pointID + "[" +
                    "x=" + x +
                    ", y=" + y +']';
        }

        public static List<Point> generatePoints(int count){
            Random r = new Random();

            return IntStream.range(0, count)
                    .mapToObj( number -> new Point(
                            r.nextDouble(-1000,1000),
                            r.nextDouble(-1000,1000),
                            "pt" + (10000+number)
                    )).toList();
        }
    }


    public static void main(String[] args) {
        List<Point> points = Point.generatePoints(20000);
        System.out.println(points);


        //namapuj do kvadrantu
        Map<String, List<Point>> pointMap = points.stream()
                .collect(Collectors.groupingBy(point -> {
                    if (point.x == 0 || point.y == 0) return "Outlier";
                    if (point.x > 0 && point.y > 0) return "UpperRight";
                    if (point.x < 0 && point.y > 0) return "UpperLeft";
                    if (point.x < 0 && point.y < 0) return "DownLeft";
                    else return "DownRight";
                }));
        pointMap.forEach((category, pointList) ->
                System.out.println(category + ": " + pointList.size()));
    }

}
