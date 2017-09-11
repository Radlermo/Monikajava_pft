package pl.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTest extends TestBase {

    @Test
    public void testContactCreation() {

        app.getContactHelper().goToContactCreation();
        app.getContactHelper().fillContactForm(new ContactData("Monika1", "Radler1", "654589698", "twst@test.pl"));
        app.getContactHelper().goToHomePage();
    }

    }
