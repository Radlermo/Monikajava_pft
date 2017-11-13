package pl.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;

public class ContactPhoneTests extends TestBase {

    @Test
    public void testContactPhones() {
        app.goTo().goToHomePage(); //idziemy na stronę główną
        ContactData contact = app.contact().allContacts().iterator().next(); //ładujemy zbiór kontaktów
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact); // wybieramy losowo jakiś kontakt
        assertThat(contact.getTelephonehome(), equalTo(cleaned(contactInfoFromEditForm.getTelephonehome())));
        assertThat(contact.getMobile(), equalTo(cleaned(contactInfoFromEditForm.getMobile())));
        assertThat(contact.getWork(), equalTo(cleaned(contactInfoFromEditForm.getWork())));
    }
    // funkcja usuwająca niepotrzebne znaki - nawiasy, myśliniki itp.
    public String cleaned(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
        //drugie wyrażenie zastępuje pierwsze
    }


// 5. 10 4:48
}
