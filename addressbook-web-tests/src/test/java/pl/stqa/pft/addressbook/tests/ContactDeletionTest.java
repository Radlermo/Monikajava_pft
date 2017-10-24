package pl.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTest extends TestBase {

    @Test(enabled = false)
    public void testContactDeletion() {

      if (! app.getContactHelper().isThereAContact()){
        app.getContactHelper().createContact(new ContactData("Monika1", "Radler1", "654589698", "twst@test.pl", "test1"), true);
      }

      List<ContactData> before = app.getContactHelper().getContactList();
      app.getContactHelper().selectContact(before.size()- 1);
      app.getContactHelper().deleteContact();
      app.getContactHelper().alertAccept();
      List<ContactData> after = app.getContactHelper().getContactList();
      Assert.assertEquals(after.size() , before.size() - 1);

      before.remove(before.size() - 1);
      Assert.assertEquals(before, after);
      }

      }

