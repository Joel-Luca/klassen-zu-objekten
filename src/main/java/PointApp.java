import geometry.Point;

public class PointApp {

    public static void main(String[] args) {

        Point p1 = new Point();
        Point p2 = new Point();

        p1.setX(1);
        p1.setY(2);
        String x = Integer.toString(p1.getX());
        String y = Integer.toString(p1.getY());
        System.out.println("X: " + x + "Y: " + y);

        p2.setX(3);
        p2.setY(4);
        x = Integer.toString(p2.getX());
        y = Integer.toString(p2.getY());
        System.out.println("X: " + x + "Y: " + y);
    }
}
