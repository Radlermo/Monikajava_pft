package pl.stqa.pft.sandbox;

public class Point {
  public static void main(String[] args) {
    PointP1 p1 = new PointP1();
    p1.x1 = 2;
    p1.x2 = 5;

    PointP2 p2 = new PointP2();
    p2.y1 = 5;
    p2.y2 = 9;

    System.out.println("Odległość między punktami A=(2,5) i B=(5,9) wynosi " + Math.sqrt(((p1.x2 - p1.x1) * (p1.x2 - p1.x1)) + (p2.y2 - p2.y1) * (p2.y2 - p2.y1)));

  }

  public static double distance(PointP1 p1) {
    return ((p1.x2 - p1.x1) * (p1.x2 - p1.x1));
  }

  public static double distance(PointP2 p2) {
    return ((p2.y2 - p2.y1) * (p2.y2 - p2.y1));

  }
}


