package pl.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactModification extends TestBase {

  @Test
  public void testContactModification() {

    if (! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("Monika1", "Radler1", "654589698", "twst@test.pl", "test2"),true);
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() -1);
    app.getContactHelper().initContactModification();
    ContactData contact = new ContactData("Monika2", "Radler5", "654589697", "twst@test.pl", "test3"), false);
    app.getContactHelper().fillContactForm(contact, (boolean creation));
    app.getContactHelper().submitContactModification();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size()- 1);
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before) , new HashSet<Object>(after));
  }
}
