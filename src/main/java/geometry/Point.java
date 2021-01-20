package geometry;
public class Point {

    private int x;
    private int y;

    public int getX(){
        return this.x;
    }
    public void setX(int x){
        this.x = x;
    }
    public int getY(){
        return this.y;
    }
    public void setY(int y){
        this.y = y;
    }

    public static Point createPoint(int x, int y) {
        Point point = new Point();
        point.setX(x);
        point.setY(y);
        return point;
    }
}
