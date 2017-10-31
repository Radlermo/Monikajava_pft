package pl.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactCreationTest extends TestBase {

    @Test
    public void testContactCreation() {
      List<ContactData> before = app.getContactHelper().getContactList();
      app.getContactHelper().goToContactCreation();
      ContactData contact = new ContactData("Monika6", "Radler8", "754589697", "7wst@test.pl","test2");
      app.getContactHelper().fillContactForm(contact, true);
      /*app.getContactHelper().fillContactForm(new ContactData("Monika1", "Radler1", "654589698", "twst@test.pl", "test1"), true);*/
      app.getContactHelper().goToHomePage();

      List<ContactData> after = app.getContactHelper().getContactList();
      Assert.assertEquals(after.size(), before.size() + 1);


      /*identyfikator który jest większy niż wszystkie wcześniej utworzone na liście
      int max = 0;
      for(ContactData c : after) { /*zmienna która przebiega listę after
          if(c.getId() > max) { /*jeśli identyfikator tego contactu jest większy niż znaleziony maksymalny, to powiększamy maximum
              max =c.getId();  /* robimy go równym identyfikatorowi tego contactu

      }}*/

      contact.setId(after.stream().max((t1, t2) -> Integer.compare(t1.getId(), t2.getId())).get().getId());
      before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
      /*Assert.assertEquals(new HashSet<Object>(before) , new HashSet<Object>(after));*/
    }

    }
