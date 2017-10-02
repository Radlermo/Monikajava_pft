package pl.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTest extends TestBase {

    @Test
    public void testContactDeletion() {
      int before = app.getContactHelper().getContactCount();
      if (! app.getContactHelper().isThereAContact()){
        app.getContactHelper().createContact(new ContactData("Monika1", "Radler1", "654589698", "twst@test.pl", "test1"), true);
      }
        app.getContactHelper().selectContact(before -1);
        app.getContactHelper().deleteContact();
        app.getContactHelper().alertAccept();
      int after = app.getContactHelper().getContactCount();
      Assert.assertEquals(after, before - 1);

      }
}
