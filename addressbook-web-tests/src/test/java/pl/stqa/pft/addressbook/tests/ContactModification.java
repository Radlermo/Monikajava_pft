package pl.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;

public class ContactModification extends TestBase {

  @Test
  public void testContactModification() {
    int before = app.getContactHelper().getContactCount();
    if (! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("Monika1", "Radler1", "654589698", "twst@test.pl", "test2"),true);
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Monika2", "Radler5", "654589697", "twst@test.pl",null), false);
    app.getContactHelper().submitContactModification();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before);
  }
}
