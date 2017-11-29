package pl.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.GroupData;
import pl.stqa.pft.addressbook.model.Groups;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTest extends TestBase {

 Logger logger = LoggerFactory.getLogger(GroupCreationTest.class);

    //provider testowych danych
    @DataProvider //XML
    public Iterator<Object[]> validGroupsFromXml() throws IOException {
        //odczyt z pliku
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.xml")))) {
            String xml = "";
            String line = reader.readLine();
            while (line != null) {
                xml += line;
            /*String[] split = line.split(";");
            list.add(new Object[] {new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2])});*/
                line = reader.readLine();
            }

            XStream xstream = new XStream();
            xstream.processAnnotations(GroupData.class);
            List<GroupData> groups = (List<GroupData>) xstream.fromXML(xml);
            return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }

    @DataProvider //Json
    public Iterator<Object[]> validGroupsFromJson() throws IOException {
        //odczyt z pliku
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.json")))) {
            String json = "";
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }

            Gson gson = new Gson();
            List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>() {
            }.getType()); //to samo co List<GroupData>.class
            return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }

    @Test(dataProvider = "validGroupsFromXml")
    public void testGroupCreation(GroupData group) {
        app.goTo().groupPage();
      //zbiory
      Groups before = app.db().groups();
      app.group().create(group);
      assertThat(app.group().count(), equalTo(before.size() + 1));
      Groups after = app.db().groups();
      //group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());  //jako parametr przyjmuje grupę, jako wynik id grupy, przesuwamy parametr do assertThat
      //before.add(group); - usuwamy
      assertThat(after, equalTo(before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt())))); //metoda hamcrest - sprawdza czy dwa obiekty są sobie równe. najpierw piszemy MatcherAssert.assertThat(after, CoreMatchers.equalTo(before));
            // aby łatwiej się to czytało - żarówka/ add static import na MatcherAssert i CoreMatchers- Kod się skraca// potem przesuwamy paramter z group.withId...

    }

    @Test(dataProvider = "validGroupsFromJson")
    public void testGroupCreationJson(GroupData group) {
        app.goTo().groupPage();
        //zbiory
        Groups before = app.db().groups();
        app.group().create(group);
        assertThat(app.group().count(), equalTo(before.size() + 1));
        Groups after = app.db().groups();
        //group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());  //jako parametr przyjmuje grupę, jako wynik id grupy, przesuwamy parametr do assertThat
        //before.add(group); - usuwamy
        assertThat(after, equalTo(before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt())))); //metoda hamcrest - sprawdza czy dwa obiekty są sobie równe. najpierw piszemy MatcherAssert.assertThat(after, CoreMatchers.equalTo(before));
        // aby łatwiej się to czytało - żarówka/ add static import na MatcherAssert i CoreMatchers- Kod się skraca// potem przesuwamy paramter z group.withId...
        }


    @Test //test sprawdzający czy niemożliwe jest stworzenie grupy z nazwą zawierającą apostrof, znak jest niedozwolony więc grupa się nie utworzy
    public void testBadGroupCreation() {

        app.goTo().groupPage();
        //zbiory
        Groups before = app.group().all();
        GroupData group = new GroupData().withName("test'22");
        app.group().create(group);
        assertThat(app.group().count(), equalTo(before.size())); // liczenie grup
        Groups after = app.group().all();
        assertThat(after, equalTo(before));
    }

}
