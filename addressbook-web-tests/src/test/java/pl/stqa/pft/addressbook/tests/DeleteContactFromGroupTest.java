package pl.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;
import pl.stqa.pft.addressbook.model.Contacts;
import pl.stqa.pft.addressbook.model.GroupData;
import pl.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DeleteContactFromGroupTest extends TestBase {
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
    public void deleteContactFromGroupTest() {
        GroupData group = app.db().groups().iterator().next();
        boolean isGroupWithContacts = true;
        for (GroupData groupData : app.db().groups()) { //find group with at least one assosiate contact;
            if (groupData.getContacts().size() > 0) {
                group = groupData;
                isGroupWithContacts = false;
                break;
            }
        }
        if (isGroupWithContacts) { //add any contact to group;
            ContactData contact = app.db().contacts().iterator().next();
            app.goTo().goToHomePage();
            app.contact().addContactToGroup(contact, group);
            app.goTo().goToHomePage();
            assertThat(app.db().groups(group.getId()).getContacts(), equalTo(group.getContacts().withAdded(contact)));
        }
        Contacts beforeDeleting = app.db().groups(group.getId()).getContacts();
        ContactData assosiateContact = app.db().groups(group.getId()).getContacts().iterator().next();
        app.goTo().goToHomePage();
        app.contact().deleteContactfromGroup(group, assosiateContact);
        app.goTo().goToHomePage();
        Contacts afterDeleting = app.db().groups(group.getId()).getContacts();
        assertThat(afterDeleting, equalTo(beforeDeleting.without(assosiateContact)));
    }
}
