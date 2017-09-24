package pl.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.GroupData;

public class GroupDeletionTests extends TestBase {

    @Test
    public void testGroupDeletion() {

        app.getNavigationHelper().gotoGroupPage();
        int before = app.getGroupHelper().getGroupCount();/*ilość grup przed dodaniem*/
        if(! app.getGroupHelper().isThereAGroup()){
          app.getGroupHelper().createGroup (new GroupData("test", null, null));
        } /*jeśli nie ma grupy do usunięcia to ją stwórz*/
        app.getGroupHelper().selectGroup(before - 1);
        app.getGroupHelper().deleteSelectedGroup();
        app.getGroupHelper().returnToGroupPage();
      int after = app.getGroupHelper().getGroupCount();/*ilość grup przed dodaniem*/
      Assert.assertEquals(after, before - 1);/*sprawdzenie czy ilość gruo jest prawidłowa*/
    }

}
