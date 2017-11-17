package pl.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

//compare view and edit form
public class ContactDetailedInfoTests extends TestBase {

    @Test
    public void testCompareViewAndEdit() {
        app.goTo().goToHomePage(); //idziemy na stronę główną
        ContactData contact = app.contact().allContacts().iterator().next();
        ContactData contactInfoFromDetailsForm = app.contact().infoFromDetailsForm(contact);// wybieramy losowo jakiś kontak
        ContactData contactInfoFromEditForm = convertContactToEqualsViewFormat(app.contact().infoFromEditForm(contact));
        assertThat(cleanedEditInfo(contactInfoFromDetailsForm.getAllDetails()), equalTo(cleanedEditInfo(mergeData(contactInfoFromEditForm))));
    }

        private String mergeData(ContactData contact) {
        return Arrays.asList(contact.getFirstname(), contact.getLastname(), contact.getAddress(), contact.getTelephonehome(), contact.getMobile(), contact.getWork()
                , contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .stream().filter((s) -> !s.equals("")) //odfiltrowujemy puste łańcuchy
                //.map(ContactDetailedInfoTests::cleaned) //oczyszczamy znaki specjalne
                .collect(Collectors.joining("\n")); //wybieramy jeden duży łańcuch ze wszystkich telefonów
    }

  /// funkcja usuwająca niepotrzebne znaki - nawiasy, myśliniki itp.
    public static String cleanedEditInfo(String info) {
        return info.replaceAll("\n", " ");
        //drugie wyrażenie zastępuje pierwsze
    }

   public static ContactData convertContactToEqualsViewFormat(ContactData contact) {
       String telephonehome = contact.getTelephonehome();
       String mobile = contact.getMobile();
       String work = contact.getWork();
       String address = contact.getAddress();

       if (!address.equals("")) {
           address += "\n";
       }

       if (!telephonehome.equals("")){
           telephonehome = "H: " + telephonehome;
       }

       if (!mobile.equals("")) {
           mobile = "M: " + mobile;
       }
       if (!work.equals("")) {
           work = "W: " + work + "\n";
       }



       return contact.withTelephonehome(telephonehome).withMobilePhone(mobile).withWorkPhone(work).withAddress(address);
   }
}


