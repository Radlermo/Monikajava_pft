package pl.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupModificationTests extends TestBase {

  @Test
  public void testGroupModification() {

    app.getNavigationHelper().gotoGroupPage();

    if(! app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup (new GroupData("test", null, null));
    } /*jeśli nie ma grupy do usunięcia to ją stwórz*/
    List<GroupData> before = app.getGroupHelper().getGroupList();/*rozmiar list przed*/
    app.getGroupHelper().selectGroup(before.size() - 1);
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("test", "test2", "test3"));
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();/*rozmiar list po*/
    Assert.assertEquals(after.size(), before.size()); /*porównanie rozmiaru list*/

  }
}
