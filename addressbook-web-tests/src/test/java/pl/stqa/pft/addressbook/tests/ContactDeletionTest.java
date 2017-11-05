package pl.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTest extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().list().size() == 0){
      app.contact().create(new ContactData("Monika1", "Radler1", "testowa4","654589698", "twst@test.pl", "test1"),true);
    }
  }

    @Test
    public void testContactDeletion() {

      List<ContactData> before = app.contact().list();
      int index = before.size()- 1;
      app.contact().delete(index);
      List<ContactData> after = app.contact().list();
      Assert.assertEquals(after.size() , before.size() - 1);

      before.remove(index);
      Assert.assertEquals(before, after);
      }


}

