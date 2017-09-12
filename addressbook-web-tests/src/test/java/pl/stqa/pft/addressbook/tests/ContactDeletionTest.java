package pl.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTest extends TestBase {

    @Test
    public void testContactModification() {

        app.getContactHelper().selectContact();
        app.getContactHelper().deleteContact();
        app.getContactHelper().alertAccept();

      }
}
