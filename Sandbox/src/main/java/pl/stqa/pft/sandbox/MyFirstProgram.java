package pl.stqa.pft.sandbox;

public class MyFirstProgram {
  public static void main(String[] args) {
    hello("world");
    hello("user1");
    hello("test");
    hello("Monika");

    Square s = new Square(5);
       System.out.println("Pole kwadratu o boku " + s.l + " = " + s.area());

    Rectangle r = new Rectangle(4,6);
       System.out.println("Pole prostokąta o boku " + r.a + " i " + r.b + " = " + r.area());
  }

  public static void hello(String somebody) {
    System.out.println("Hello, " + somebody + "!");

  }

  }
