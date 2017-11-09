package pl.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactCreationTest extends TestBase {

    @Test
    public void testContactCreation() {
      Set<ContactData> before = app.contact().allContacts();
      ContactData contact = new ContactData().withFirstname("Monika6").withLastname("Radler8").withAddress("testowa4").withTelephonehome("754589697").withEmail("7wst@test.pl").withGroup("test2");
      app.contact().create(contact,true);
      Set<ContactData> after = app.contact().allContacts();
      Assert.assertEquals(after.size(), before.size() + 1);

      contact.withId(after.stream().mapToInt(g -> g.getId()).max().getAsInt());
      before.add(contact);
      Assert.assertEquals(before, after);
      /*Assert.assertEquals(new HashSet<Object>(before) , new HashSet<Object>(after));*/
    }


}
