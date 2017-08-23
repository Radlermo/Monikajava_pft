package pl.stqa.pft.sandbox;

public class Point {
  public static void main(String[] args) {
    double x1 = 2;
    double y1 = 5;
    double x2 = 5;
    double y2 = 9;

    double p1 = (5 - 2);
    double p2 = (9 - 5);

    System.out.println("Odległość między punktami A=(2,5) i B=(5,9) wynosi " + Math.sqrt(distance(x1, x2, y1, y2)));
    System.out.println("Odległość między punktami A=(2,5) i B=(5,9) wynosi " + Math.sqrt(distance(p1, p2)));
  }

  public static double distance(double x1, double x2, double y1, double y2) {
    return ((x2 - x1) * (x2 - x1)) + ((y2 - y1) * (y2 - y1));
  }

  public static double distance(double p1, double p2) {
    return (p1 * p1) + (p2 * p2);
  }
}

