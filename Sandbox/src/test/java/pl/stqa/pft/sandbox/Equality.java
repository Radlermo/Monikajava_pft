package pl.stqa.pft.sandbox;

public class Equality {

  public static void main(String[] args) {
    String s1 = "firefox";
      /*  String s2 = s1;*/
   String s2 = new String(s1);
   String s3 = "firefox 2.0";
   String s4 = "firefox " + Math.sqrt(4.0); /*pierwiastek*/

    System.out.println(s1 == s2);
    System.out.println(s1.equals(s2));

    System.out.println(s3 == s4);
    System.out.println(s3.equals(s4));

  }
}
