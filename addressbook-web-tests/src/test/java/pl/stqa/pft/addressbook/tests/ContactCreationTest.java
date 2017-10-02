package pl.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactCreationTest extends TestBase {

    @Test
    public void testContactCreation() {
      List<ContactData> before = app.getContactHelper().getContactList();
      app.getContactHelper().goToContactCreation();
      app.getContactHelper().fillContactForm(new ContactData("Monika1", "Radler1", "654589698", "twst@test.pl", "test1"), true);
      app.getContactHelper().goToHomePage();

      List<ContactData> after = app.getContactHelper().getContactList();
      Assert.assertEquals(after.size(), before.size() + 1);
    }

    }
