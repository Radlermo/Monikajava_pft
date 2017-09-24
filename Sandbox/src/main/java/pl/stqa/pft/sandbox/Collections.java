package pl.stqa.pft.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {

  public static void main(String[] args) {
    /*String[] langs = new String[4];
     [] - tablica , [4] rozmiar tablicy
   pierwsza wersja
    langs[0] = "Java";
    langs[1] = "C#";
    langs[2] = "Python";
    langs[3] = "PHP";  */

    String[] langs = {"Java","C#","Python", "PHP"};
    /*pierwsza wersja lista

    List<String> languages = new ArrayList<String>(); /*lista
    languages.add("Java");
    languages.add("C#");
    languages.add("Python");
    languages.add("PHP");*/

     /*druga wersja lista
    List<String> languages2 = Arrays.asList("Java","C#","Python", "PHP");

    for(int i=0; i < languages2.size(); i++)
      System.out.println("Chcę nauczyć się " + languages.get(i));*/

    List<String> languages = Arrays.asList("Java","C#","Python", "PHP");

    /*kolekcja pierwsza wersja
    for (int i=0; i < langs.length; i++) {
      System.out.println("Chcę nauczyć się " + langs[i]); /*langs[i] - łańcuch
    }*/
    /*inna forma kolekcji*/
    for(String l : langs) {
      System.out.println("Chcę nauczyć się " + l);
    }

  }
}
