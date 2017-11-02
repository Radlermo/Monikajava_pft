package pl.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;
import pl.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactModification extends TestBase {

  @Test
  public void testContactModification() {

    if (! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("Monika1", "Radler1", "testowa4","654589698", "twst@test.pl", "test2"),true);
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size()-1);
    app.getContactHelper().initContactModification(before.size()-1);
    ContactData contact = new ContactData(before.get(before.size()-1).getId(), "Monika6", "Radler8", "testowa4", "754589697", "7wst@test.pl","test3");
        app.getContactHelper().fillContactForm(contact, false);
    /*app.getContactHelper().fillContactForm(new ContactData("Monika2", "Radler5", "654589697", "twst@test.pl","test3"), false); */
    app.getContactHelper().submitContactModification();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size()- 1);
    before.add(contact);
      Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
      before.sort(byId);
      after.sort(byId);
      Assert.assertEquals(before, after);
   /* Assert.assertEquals(new HashSet<Object>(before) , new HashSet<Object>(after)); /* zbiory*/
  }

}
