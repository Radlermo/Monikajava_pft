package pl.stqa.pft.mantis.appmanager;

import org.subethamail.wiser.Wiser;
import org.subethamail.wiser.WiserMessage;
import pl.stqa.pft.mantis.model.MailMessage;
//import pl.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class MailHelper {

        private ApplicationManager app;
        private final Wiser wiser;

        public MailHelper(ApplicationManager app) {
            this.app = app;
            wiser = new Wiser(); //powstaje objekt typu wiser czyli serwer pocztowy
        }

        public List<MailMessage> waitForMail (int count, long timeout) throws MessagingException, IOException, InterruptedException {
            long start = System.currentTimeMillis(); //zapamiętujemy bieżacy czas
            while (System.currentTimeMillis() < start + timeout) { //sprawdzamy czy czas oczekiwania jeszcze nie upłynął
                if (wiser.getMessages().size() >= count) {
                    return wiser.getMessages().stream().map((m) -> toModelMail(m)).collect(Collectors.toList());//przekształcanie objektów rzezczywistych w modelowe
                }
                try {
                    Thread.sleep(10000);// czekamy 1000 milisekund
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            throw new Error("No mail: (");
        }


        public static MailMessage toModelMail(WiserMessage m) {
            try {
                MimeMessage mm = m.getMimeMessage();
                return new MailMessage(mm.getAllRecipients()[0].toString(), (String) mm.getContent());//lista rzeczywistych wiadomości/ lista odbiorców/ wybierz jednego/ content = treść
            } catch (MessagingException e) {
                e.printStackTrace();
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        public void start() {
            wiser.start();
        }
        public void stop() {
            wiser.stop();
        }
    }

