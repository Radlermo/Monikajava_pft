package pl.stqa.pft.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;
import pl.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

public class ContactModification extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0){
      app.goTo().goToHomePage();
      app.contact().create(new ContactData().withFirstname("Monika6").withLastname("Radler8").withAddress("testowa4").withTelephonehome("754589697").withEmail("7wst@test.pl").withGroup("test2"),true);
    }
  }

  @Test
  public void testContactModification() {
    File photo = new File("src/test/resources/scrum.jpg");
    app.goTo().goToHomePage();
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    //int index = before.size()-1;
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("Monika12").withLastname("Radler12").withAddress("testowa4")
            .withTelephonehome("754589697").withEmail("7wst@test.pl").withGroup("test2").withPhoto(photo);;
    app.goTo().goToHomePage();
    app.contact().modify(contact);
    //app.goTo().goToHomePage();
    Contacts after = app.db().contacts();
    assertEquals(after.size(), before.size());
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
   /* Assert.assertEquals(new HashSet<Object>(before) , new HashSet<Object>(after)); /* zbiory*/
   VerifyContactListInUi();
  }

  }
