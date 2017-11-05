package pl.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTest extends TestBase {

    @Test
    public void testContactCreation() {
      List<ContactData> before = app.contact().list();
      ContactData contact = new ContactData().withFirstname("Monika6").withLastname("Radler8").withAddress("testowa4").withTelephonehome("754589697").withEmail("7wst@test.pl").withGroup("test2");
      app.contact().create(contact,true);
      List<ContactData> after = app.contact().list();
      Assert.assertEquals(after.size(), before.size() + 1);


      contact.withId(after.stream().max((t1, t2) -> Integer.compare(t1.getId(), t2.getId())).get().getId());
      before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
      /*Assert.assertEquals(new HashSet<Object>(before) , new HashSet<Object>(after));*/
    }


}
