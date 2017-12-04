package pl.stqa.pft.addressbook.tests;


import com.thoughtworks.xstream.XStream;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.annotations.TestInstance;
import pl.stqa.pft.addressbook.model.ContactData;
import pl.stqa.pft.addressbook.model.Contacts;
import pl.stqa.pft.addressbook.model.GroupData;
import pl.stqa.pft.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

public class ContactCreationTest extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContacts() throws IOException {
        //odczyt z pliku
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")));
        String xml = "";
        String line = reader.readLine();
        while (line != null) {
            xml += line;
            line = reader.readLine();
        }
        XStream xstream = new XStream();
        xstream.processAnnotations(ContactData.class);
        List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
        return contacts.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();
    }

    @Test(dataProvider = "validContacts")
    public void testContactCreation(ContactData contact) {
      //Groups groups = app.db().groups();
      app.goTo().goToHomePage();
      File photo = new File("src/test/resources/scrum.jpg");
      Contacts before = app.db().contacts();
     /*ContactData newContact = new ContactData().withFirstname("Monika6").withLastname("Radler8").withAddress("testowa6").withTelephonehome("754589697").withEmail("7wst@test.pl")
              .withPhoto(photo).inGroup(groups.iterator().next());//.withGroup("test2")*/

       GroupData groups = app.db().groups().iterator().next();
      app.contact().create(contact.withPhoto(photo).inGroup(groups)), true);
      //app.contact().create(contact, true);
      app.goTo().goToHomePage();
      //
      Contacts after = app.db().contacts();
      assertThat(after.size(), equalTo(before.size() + 1));
      assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt(g -> g.getId()).max().getAsInt()))));
      VerifyContactListInUi();
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
