package pl.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if(app.group().list().size() == 0){
            app.group().create(new GroupData("test", null, null));
        } /*jeśli nie ma grupy do usunięcia to ją stwórz*/
    }

    @Test
    public void testGroupDeletion() {

      List<GroupData> before = app.group().list();
      int index = before.size() -1;
      app.group().delete(index);
      List<GroupData> after = app.group().list();
      Assert.assertEquals(after.size(), before.size() - 1);

      before.remove(index);
      Assert.assertEquals(before, after);
      }

    private void delete(int index) {
        app.group().selectGroup(index);
        app.group().deleteSelectedGroup();
        app.group().returnToGroupPage();
    }


}
