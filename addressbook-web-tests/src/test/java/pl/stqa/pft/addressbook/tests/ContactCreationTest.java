package pl.stqa.pft.addressbook.tests;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.hamcrest.CoreMatchers;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;
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
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

public class ContactCreationTest extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContactsFromXml() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))) {
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
    }

    @DataProvider
    public Iterator<Object[]> validContactsFromJson() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
            String json = "";
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
            }.getType()); //List<ContactData>.class
            return contacts.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();
        }
    }

    @BeforeMethod
   public void ensurePrecondition() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            Groups beforeTest = app.db().groups();
            GroupData groupTest = new GroupData().withName("test1");
            app.group().create(groupTest);
            assertThat(app.db().groups().size(), equalTo(beforeTest.size() + 1));
            Groups afterTest = app.db().groups();
            assertThat(afterTest, equalTo(beforeTest.withAdded(groupTest.withId(afterTest.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
        }
    }

    @Test(dataProvider = "validContactsFromXml")
    public void testContactCreation(ContactData contact) {
      app.goTo().goToHomePage();
      Contacts beforeContact = app.db().contacts();
      app.goTo().addNew(); //sprawdza czy na stronie kontaktów istnieje grupa, jeśli tak tworzy kontakt
      File photo = new File("src/test/resources/scrum.jpg");
      GroupData groups = app.db().groups().iterator().next();

      /*ContactData newContact = new ContactData().withFirstname("Monika6").withLastname("Radler8").withAddress("testowa6").withTelephonehome("754589697").withEmail("7wst@test.pl")
              .withPhoto(photo).inGroup(groups);//.withGroup("test2")*/
      //app.contact().create(contact.inGroup(groups).withPhoto(photo));
      //app.contact().create(contact);//, true);
      app.goTo().goToHomePage();
      assertThat(app.db().contacts().size(), equalTo(beforeContact.size() + 1));

      Contacts afterContact = app.db().contacts();
        assertThat(afterContact, equalTo(beforeContact.withAdded(contact.withId(afterContact.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
      //VerifyContactListInUi();
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
