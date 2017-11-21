package pl.stqa.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.GroupData;
import pl.stqa.pft.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTest extends TestBase {
    //provider testowych danych
    @DataProvider
    public Iterator<Object[]> validGroups() {
        //budujemy listę tablic objektów
        List<Object[]> list = new ArrayList<Object[]>();
        //wypełniamy listę danymi testowymi
        list.add(new Object[] {new GroupData().withName("test1").withHeader("header1").withFooter("footer1")}); //name, header, footer
        list.add(new Object[] {new GroupData().withName("test2").withHeader("header2").withFooter("footer2")});
        list.add(new Object[] {new GroupData().withName("test3").withHeader("header3").withFooter("footer3")});
        return list.iterator();

    }

    @Test(dataProvider = "validGroups")
    public void testGroupCreation(GroupData group) {
            app.goTo().groupPage();
            //zbiory
            Groups before = app.group().all();
            app.group().create(group);
            assertThat(app.group().count(), equalTo(before.size() + 1));
            Groups after = app.group().all();
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
