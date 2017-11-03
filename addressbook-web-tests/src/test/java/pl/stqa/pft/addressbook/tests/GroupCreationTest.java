package pl.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.GroupData;
import pl.stqa.pft.addressbook.model.Groups;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTest extends TestBase {

   @Test
  public void testGroupCreation() {

     app.goTo().groupPage();
    //zbiory
     Groups before = app.group().all();
     GroupData group = new GroupData().withName("test2");
     app.group().create(group);
     Groups after = app.group().all();
     assertThat(after.size(), equalTo(before.size() + 1));

     //group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());  //jako parametr przyjmuje grupę, jako wynik id grupy, przesuwamy parametr do assertThat
     //before.add(group); - usuwamy
     assertThat(after, equalTo(before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt())))); //metoda hamcrest - sprawdza czy dwa obiekty są sobie równe. najpierw piszemy MatcherAssert.assertThat(after, CoreMatchers.equalTo(before));
      // aby łatwiej się to czytało - żarówka/ add static import na MatcherAssert i CoreMatchers- Kod się skraca// potem przesuwamy paramter z group.withId...
   }


    /*app.goTo().groupPage();
    //listy
    List<GroupData> before = app.group().list();
    GroupData group = new GroupData().withName("test2");
    app.group().create(group);
    List<GroupData> after = app.group().list();
    Assert.assertEquals(after.size(), before.size() + 1);
   /*porównanie za pomocą cyklu
    int max = 0;
    for (GroupData g : after) {
      if (g.getId() > max) {
        max = g.getId();
      }
    }*/

    /*porównanie za pomocą comparatora anonimowa funkcja

    before.add(group);
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(),g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

  */

}
