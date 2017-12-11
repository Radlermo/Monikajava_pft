package pl.stqa.pft.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqa.pft.mantis.model.MailMessage;
import ru.lanwen.verbalregex.VerbalExpression;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class RegistrationTests  extends TestBase {

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testRegistration() throws InterruptedException, MessagingException, IOException {
        long now = System.currentTimeMillis();
        String user = String.format("user%s", now);//rejestracja różnych userów
        String email = String.format("user%s@localhost.localdomain", now); //użycie różnych adresów mailowych
        String password = "password";
        app.registration().start(user, email);
        List<MailMessage> mailMessages = app.mail().waitForMail(2, 50000);
        String confirmationLink = findConfirmationLink(mailMessages, email);
        app.registration().finish(confirmationLink, password);
       assertTrue(app.newSession().login(user, password));
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();//filtrujemy tylko maile, które przyszły na dany adres/wybieramy pierwszy
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build(); //wyrażenia regularne
    return regex.getText(mailMessage.text);  //wyciągamy link z tekstu wiadomości?
    }


    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}