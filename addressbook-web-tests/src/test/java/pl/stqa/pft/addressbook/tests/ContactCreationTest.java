package pl.stqa.pft.addressbook.tests;


import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;
import pl.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

public class ContactCreationTest extends TestBase {

    @Test
    public void testContactCreation() {
      File photo = new File("src/test/resources/scrum.jpg");
      Contacts before = app.contact().allContacts();
      ContactData contact = new ContactData().withFirstname("Monika6").withLastname("Radler8").withAddress("testowa6").withTelephonehome("754589697").withEmail("7wst@test.pl")
              .withGroup("test2").withPhoto(photo);
      app.contact().create(contact,true);
      Contacts after = app.contact().allContacts();
      assertThat(after.size(), equalTo(before.size() + 1));
      assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt(g -> g.getId()).max().getAsInt()))));
      /*Assert.assertEquals(new HashSet<Object>(before) , new HashSet<Object>(after));*/
    }
// pomocniczy test, który wskazuje bieżący katalog w trakcie wykonywania testu
    @Test
    public void testCurrentDir() {
        File curreantDir = new File(".");
        System.out.println(curreantDir.getAbsolutePath());
        File photo = new File("src/test/resources/scrum.jpg");
        System.out.println(photo.getAbsolutePath()); // czy śceżka jest ok
        System.out.println(photo.exists()); //czy plik istnieje

    }


}
