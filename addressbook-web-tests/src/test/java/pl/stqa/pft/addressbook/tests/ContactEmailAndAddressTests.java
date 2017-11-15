package pl.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailAndAddressTests extends TestBase {
    @Test
    public void testEmail() {
        app.goTo().goToHomePage(); //idziemy na stronę główną
        ContactData contact = app.contact().allContacts().iterator().next(); //ładujemy zbiór kontaktów
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact); // wybieramy losowo jakiś kontakt
       assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));

        /*assertThat(contact.getEmail(), equalTo(contactInfoFromEditForm.getEmail()));
        assertThat(contact.getEmail2(), equalTo(contactInfoFromEditForm.getEmail2()));
        assertThat(contact.getEmail3(), equalTo(contactInfoFromEditForm.getEmail3()));*/
    }

   private String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .stream().filter((s) -> !s.equals("")) //odfiltrowujemy puste łańcuchy
                //.map(ContactEmailAndAddressTests::cleaned) //oczyszczamy znaki specjalne
                .collect(Collectors.joining("\n")); //wybieramy jeden duży łańcuch ze wszystkich telefonów
    }


    @Test
    public void testAddress() {
        app.goTo().goToHomePage(); //idziemy na stronę główną
        ContactData contact = app.contact().allContacts().iterator().next(); //ładujemy zbiór kontaktów
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact); // wybieramy losowo jakiś kontakt
        assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));

    }
}
