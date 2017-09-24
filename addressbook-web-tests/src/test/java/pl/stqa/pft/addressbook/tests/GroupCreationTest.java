package pl.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.GroupData;

public class GroupCreationTest extends TestBase {

  @Test
  public void testGroupCreation() {

    app.getNavigationHelper().gotoGroupPage();
    int before = app.getGroupHelper().getGroupCount();/*ilość grup przed dodaniem*/
    app.getGroupHelper().createGroup(new GroupData("test", null, null));
    int after = app.getGroupHelper().getGroupCount();/*ilość grup po dodaniu*/
    Assert.assertEquals(after, before + 1);/*sprawdzenie czy ilość gruo jest prawidłowa*/


  }

}
