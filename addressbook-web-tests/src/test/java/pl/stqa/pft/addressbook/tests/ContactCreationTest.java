package pl.stqa.pft.addressbook.tests;


import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;
import pl.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

public class ContactCreationTest extends TestBase {

    @Test
    public void testContactCreation() {
      Contacts before = app.contact().allContacts();
      ContactData contact = new ContactData().withFirstname("Monika6").withLastname("Radler8").withAddress("testowa4").withTelephonehome("754589697").withEmail("7wst@test.pl").withGroup("test2");
      app.contact().create(contact,true);
      Contacts after = app.contact().allContacts();
      assertThat(after.size(), equalTo(before.size() + 1));
      assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt(g -> g.getId()).max().getAsInt()))));
      /*Assert.assertEquals(new HashSet<Object>(before) , new HashSet<Object>(after));*/
    }

}
