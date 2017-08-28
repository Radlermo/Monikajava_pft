package pl.stqa.pft.sandbox;

public class PointExcerciseTwo {
  public static void main(String[] args) {
    Point p1 = new Point(2,5);

    Point p2 = new Point(5,9);


    System.out.println("Odległość między punktami A (" + p1.x + ", " + p1.y + ") i B (" +p2.x +", "+p2.y+") wynosi " +p1.distance(p2));
  }
}


