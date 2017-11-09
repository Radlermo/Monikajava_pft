package pl.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactModification extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().allContacts().size() == 0){
      app.contact().create(new ContactData().withFirstname("Monika6").withLastname("Radler8").withAddress("testowa4").withTelephonehome("754589697").withEmail("7wst@test.pl").withGroup("test2"),true);
    }
  }

  @Test
  public void testContactModification() {
    Set<ContactData> before = app.contact().allContacts();
    ContactData modifiedContact = before.iterator().next();
    //int index = before.size()-1;
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("Monika12").withLastname("Radler12").withAddress("testowa4")
            .withTelephonehome("754589697").withEmail("7wst@test.pl").withGroup("test2");

    app.contact().modify(contact);
    Set<ContactData> after = app.contact().allContacts();
    Assert.assertEquals(after.size(), before.size());

    before.remove(modifiedContact);
    before.add(contact);
    Assert.assertEquals(before, after);
   /* Assert.assertEquals(new HashSet<Object>(before) , new HashSet<Object>(after)); /* zbiory*/
  }



}
