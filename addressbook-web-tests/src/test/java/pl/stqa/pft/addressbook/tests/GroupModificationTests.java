package pl.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.GroupData;

public class GroupModificationTests extends TestBase {

  @Test
  public void testGroupModification() {

    app.getNavigationHelper().gotoGroupPage();
    int before = app.getGroupHelper().getGroupCount();/*ilość grup przed dodaniem*/
    if(! app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup (new GroupData("test", null, null));
    } /*jeśli nie ma grupy do usunięcia to ją stwórz*/
    app.getGroupHelper().selectGroup(before - 1);
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("test", "test2", "test3"));
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupPage();
    int after = app.getGroupHelper().getGroupCount();/*ilość grup przed dodaniem*/
    Assert.assertEquals(after, before);/*sprawdzenie czy ilość gruo jest prawidłowa*/

  }
}
