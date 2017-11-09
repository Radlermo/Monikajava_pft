package pl.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;

import java.util.List;
import java.util.Set;

public class ContactDeletionTest extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().allContacts().size() == 0){
      app.contact().create(new ContactData().withFirstname("Monika1").withLastname("Radler1").withAddress("testowa4").withTelephonehome("654589698")
              .withEmail("twst@test.pl").withGroup("test1"), true);

    }
  }

    @Test
    public void testContactDeletion() {

      Set<ContactData> before = app.contact().allContacts();
      ContactData deletedContact = before.iterator().next();
     // int index = before.size()- 1;
      app.contact().delete(deletedContact);
      Set<ContactData> after = app.contact().allContacts();
      Assert.assertEquals(after.size() , before.size() - 1);

      before.remove(deletedContact);
      Assert.assertEquals(before, after);
      }


}

