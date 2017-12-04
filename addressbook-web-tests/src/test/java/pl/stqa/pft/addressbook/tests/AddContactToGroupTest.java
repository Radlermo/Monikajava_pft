package pl.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;
import pl.stqa.pft.addressbook.model.Contacts;
import pl.stqa.pft.addressbook.model.GroupData;
import pl.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactToGroupTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            if (app.db().groups().size() == 0) {
                app.goTo().groupPage();
                Groups beforeTest = app.db().groups();
                GroupData groupTest = new GroupData().withName("test1");
                app.group().create(groupTest);
                assertThat(app.db().groups().size(), equalTo(beforeTest.size() + 1));
                Groups afterTest = app.db().groups();
                assertThat(afterTest, equalTo(beforeTest.withAdded(groupTest.withId(afterTest.stream().mapToInt(g -> g.getId()).max().getAsInt()))));

            }
            app.goTo().goToHomePage();
            Contacts beforeContact = app.db().contacts();
            app.contact().initAddContact();
            ContactData contact = new ContactData().withFirstname("Monika6").withLastname("Radler8").withAddress("testowa6")
                    .withTelephonehome("754589697").withEmail("7wst@test.pl");
            app.goTo().goToHomePage();
            assertThat(app.db().contacts().size(), equalTo(beforeContact.size() + 1));
            Contacts afterContact = app.db().contacts();
            assertThat(afterContact, equalTo(beforeContact.withAdded(contact.withId(afterContact.stream().mapToInt(c -> c.getId()).max().getAsInt()))));
        }
    }

    @Test
    public void addContactToGroupTest() {
        Contacts contactsAtAll = app.db().contacts();
        ContactData contact = contactsAtAll.iterator().next();
        Groups groupsInContact = contact.getGroups();
        Groups groupsAtAll = app.db().groups();
        GroupData assosiateGroup;

        if (groupsInContact.size() == groupsAtAll.size()) { //verify if there is at least one group for adding
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("AssosiateGroupName"));
            Groups groupsAddNew = app.db().groups();
            groupsAddNew.removeAll(groupsAtAll);
            assosiateGroup = groupsAddNew.iterator().next();
        } else {                                              //choose one NOT from contact.inGroups() list;
            groupsAtAll.removeAll(groupsInContact);
            assosiateGroup = groupsAtAll.iterator().next();
        }
        app.goTo().goToHomePage();
        app.contact().addContactToGroup(contact, assosiateGroup);
        app.goTo().goToHomePage();
        assertThat(app.db().contacts(contact.getId()).getGroups(), equalTo(groupsInContact.withAdded(assosiateGroup)));
    }
 }

