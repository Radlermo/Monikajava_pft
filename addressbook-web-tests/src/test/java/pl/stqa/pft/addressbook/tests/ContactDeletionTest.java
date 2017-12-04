package pl.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;
import pl.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;


public class ContactDeletionTest extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    File photo = new File("src/test/resources/scrum.jpg");
    if (app.db().contacts().size() == 0){
      app.contact().create(new ContactData().withFirstname("Monika1").withLastname("Radler1").withAddress("testowa4").withTelephonehome("654589698")
              .withEmail("twst@test.pl").withPhoto(photo));
       //.withGroup("test1")
    }
  }

    @Test
    public void testContactDeletion() {

      app.goTo().goToHomePage();
      Contacts before = app.db().contacts();
      ContactData deletedContact = before.iterator().next();
     // int index = before.size()- 1;
      app.goTo().goToHomePage();
      app.contact().delete(deletedContact);
      app.goTo().goToHomePage();
      Contacts after = app.db().contacts();
      Assert.assertEquals(after.size() , before.size() - 1);

      assertThat(after, equalTo(before.without(deletedContact)));
      VerifyContactListInUi();
      }


}

