package pl.stqa.pft.mantis.model;

public class MailMessage {

    public String to; //do kogo przyszedł mail
    public String text; //tekst wiadomości

    public MailMessage(String to, String text) {
        this.to = to;
        this.text = text;
    }
}
