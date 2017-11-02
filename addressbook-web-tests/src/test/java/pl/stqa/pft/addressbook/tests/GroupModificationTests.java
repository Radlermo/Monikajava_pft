package pl.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class GroupModificationTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
  app.goTo().groupPage();
    if(app.group().all().size() == 0){
    app.group().create(new GroupData().withName("test1"));
  } /*jeśli nie ma grupy do usunięcia to ją stwórz*/
}

  @Test
  public void testGroupModification() {
    Set<GroupData> before = app.group().all();
    GroupData modifiedGroup = before.iterator().next();
    GroupData group = new GroupData().withId(modifiedGroup.getId()).withName("test1").withHeader("test2").withFooter("test3");
    app.group().modify(group);
    Set<GroupData> after = app.group().all();/*rozmiar list po*/
    Assert.assertEquals(after.size(), before.size()); /*porównanie rozmiaru list*/

    before.remove(modifiedGroup);
    before.add(group);
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(),g2.getId());
    Assert.assertEquals (before, after);

     }
/* stara wersja z listami
List<GroupData> before = app.group().list();/*rozmiar list przed
int index = before.size() - 1;
  GroupData group = new GroupData().withId(before.get(index).getId()).withName("test1").withHeader("test2").withFooter("test3");
    app.group().modify(index, group);
  List<GroupData> after = app.group().list();/*rozmiar list po
    Assert.assertEquals(after.size(), before.size()); /*porównanie rozmiaru list

    before.remove(index);
    before.add(group);
  Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(),g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals (before, after);*/


}
