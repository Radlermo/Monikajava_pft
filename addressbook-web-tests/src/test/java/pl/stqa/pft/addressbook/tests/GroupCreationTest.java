package pl.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class GroupCreationTest extends TestBase {

   @Test
  public void testGroupCreation() {

     app.goTo().groupPage();
    //zbiory
     Set<GroupData> before = app.group().all();
     GroupData group = new GroupData().withName("test2");
     app.group().create(group);
     Set<GroupData> after = app.group().all();
     Assert.assertEquals(after.size(), before.size() + 1);

     group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());  //jako parametr przyjmuje grupę, jako wynik id grupy

     before.add(group);
     Assert.assertEquals(before, after);
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
