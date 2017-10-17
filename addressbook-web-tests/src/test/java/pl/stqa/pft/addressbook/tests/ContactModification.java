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
    app.getContactHelper().selectContact(before.size()-1);
    app.getContactHelper().initContactModification(before.size()-1);
    /*ContactData contact = new ContactData("Monika2", "Radler5", "654589697", "twst@test.pl","test3");*/
    ContactData contact = new ContactData(before.get(before.size()-1).getId(),"Monika2", "Radler5", "654589697", "twst@test.pl","test3");
    app.getContactHelper().fillContactForm(contact, false);
    /*app.getContactHelper().fillContactForm(new ContactData("Monika2", "Radler5", "654589697", "twst@test.pl","test3"), false); */
    app.getContactHelper().submitContactModification();
   List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size()- 1);
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before.size()) , new HashSet<Object>(after.size())); /* zbiory*/
  }
}
