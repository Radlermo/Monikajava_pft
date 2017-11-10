package pl.stqa.pft.addressbook.tests;

import org.junit.Test;
import pl.stqa.pft.addressbook.model.ContactData;

public class ContactPhoneTests extends TestBase {

    @Test
    public void testContactPhones() {
        app.contact().goToHomePage(); //idziemy na stronę główną
        ContactData contact = app.contact().allContacts().iterator().next(); //ładujemy zbiór kontaktów
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact); // wybieramy losowo jakiś kontakt
    }
    5.9 5:37
}
